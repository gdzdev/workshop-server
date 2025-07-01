package org.gdzdev.workshop.backend.application.usecase.carts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdzdev.workshop.backend.domain.enums.CartStatus;
import org.gdzdev.workshop.backend.domain.exception.*;
import org.gdzdev.workshop.backend.domain.port.out.CartRepositoryPort;
import org.springframework.stereotype.Service;
import org.gdzdev.workshop.backend.domain.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.gdzdev.workshop.backend.domain.port.input.CartService;
import org.gdzdev.workshop.backend.application.dto.cart.CartMessage;
import org.gdzdev.workshop.backend.domain.port.out.ProductRepositoryPort;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepositoryPort cartPort;
    private final ProductRepositoryPort productRepository;

    @Override
    @Transactional
    public Cart getActiveCart() {
        Cart cart = this.cartPort.findByActiveCart()
                .orElseGet(this::createCart);
        cart.calculateGrandTotal();
        return cart;
    }

    @Override
    @Transactional
    public Cart createCart() {
        cartPort.findAllByStatus(CartStatus.ACTIVE).forEach(cart -> {
            cart.setStatus(CartStatus.ABANDONED);
            cartPort.save(cart);
        });

        Cart newCart = Cart.builder()
                .status(CartStatus.ACTIVE)
                .grandTotal(BigDecimal.ZERO)
                .build();

        return cartPort.save(newCart);
    }
















    @Override
    @Transactional(readOnly = true)
    public Integer getItemsCount() {
        log.info("\n\n\nInteger getItemsCount()\n\n\n");
        Cart cart = cartPort.findByActiveCart()
                .orElseThrow(() -> new CartNotFoundException("No active cart found."));
        return cart.getTotalItemsCount();
    }

    @Override
    @Transactional
    public CartMessage addProductToCart(Long productId) {
        log.info("CartMessage addProductToCart(Long productId)\n\n");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotAvailbleException("The product you are trying to add does not exist."));

        Cart cart = getActiveCart();

        Optional<CartItem> optionalItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (optionalItem.isPresent()) {
            CartItem existingItem = optionalItem.get();
            if (existingItem.getQuantity() + 1 > product.getStock()) {
                throw new ProductNotAvailbleException("Not enough stock to increase the quantity.");
            }
            existingItem.increaseQuantity();
        } else {
            if (product.getStock() < 1) {
                throw new ProductNotAvailbleException("Product is out of stock.");
            }

            CartItem newItem = CartItem.builder()
                    .product(product)
                    .quantity(1)
                    .unitPrice(product.getPrice())
                    .build();

            cart.addItem(newItem);
        }

        cart.calculateGrandTotal();
        cartPort.save(cart);
        return CartMessage.builder().cartMessage("Product added to cart successfully.").build();
    }

    @Override
    @Transactional
    public CartMessage removeProductFromCart(Long itemId) {
        log.info("CartMessage removeProductFromCart(Long itemId)\n\n\n");
        Cart cart = getActiveCart();

        boolean removed = cart.removeItem(itemId);
        if (!removed) {
            throw new CartItemNotFoundException("Cart item not found.");
        }

        cart.calculateGrandTotal();
        cartPort.save(cart);
        return CartMessage.builder().cartMessage("Product removed from cart successfully.").build();
    }

    @Override
    @Transactional
    public CartMessage increaseItemQuantity(Long itemId) {
        log.info("CartMessage increaseItemQuantity(Long itemId)\n\n\n");
        Cart cart = getActiveCart();

        CartItem item = cart.getCartItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found."));

        if (item.getQuantity() + 1 > item.getProduct().getStock()) {
            throw new ProductNotAvailbleException("Not enough stock to increase the quantity.");
        }

        item.increaseQuantity();
        cart.calculateGrandTotal();
        cartPort.save(cart);

        return CartMessage.builder().cartMessage("Item quantity increased successfully.").build();
    }

    @Override
    @Transactional
    public CartMessage decreaseItemQuantity(Long itemId) {
        log.info("CartMessage decreaseItemQuantity(Long itemId)\n\n\n");
        Cart cart = getActiveCart();

        CartItem item = cart.getCartItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found."));

        item.decreaseQuantity();

        if (item.getQuantity() <= 0) {
            cart.removeItem(item.getId());
        }

        cart.calculateGrandTotal();
        cartPort.save(cart);
        return CartMessage.builder().cartMessage("Item quantity decreased successfully.").build();
    }

    @Override
    @Transactional
    public CartMessage emptyCart() {
        log.info("CartMessage emptyCart()\n\n\n");
        Cart cart = getActiveCart();

        if (cart.getCartItems().isEmpty()) {
            throw new EmptyCartOperationException("Cart is already empty.");
        }

        cart.clear();
        cart.calculateGrandTotal();
        cartPort.save(cart);
        return CartMessage.builder().cartMessage("Cart emptied successfully.").build();
    }
}
