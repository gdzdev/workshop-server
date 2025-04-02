package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.gdzdev.workshop.backend.domain.model.*;
import org.gdzdev.workshop.backend.domain.enums.ItemType;
import org.springframework.transaction.annotation.Transactional;
import org.gdzdev.workshop.backend.domain.port.input.CartService;
import org.gdzdev.workshop.backend.application.dto.cart.CartMessage;
import org.gdzdev.workshop.backend.application.dto.cart.CartRequest;
import org.gdzdev.workshop.backend.domain.port.out.CartRepositoryPort;
import org.gdzdev.workshop.backend.domain.port.out.ProductRepositoryPort;
import org.gdzdev.workshop.backend.domain.port.out.ServicingRepositoryPort;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotAvailbleException;
import org.gdzdev.workshop.backend.domain.exception.ServicingNotAvaibleException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepositoryPort cartRepository;
    private final ProductRepositoryPort productRepository;
    private final ServicingRepositoryPort servicingRepository;

    @Override
    @Transactional(readOnly = true)
    public Cart getCart() {
        List<CartItem> cartItems = cartRepository.findAllCartItems();
        Cart cart = Cart.builder().cartItems(cartItems).build();
        cart.calculateGrandTotal();
        return cart;
    }

    @Override
    @Transactional
    public CartMessage addProductToCart(CartRequest request) {

        CartProduct cartProduct = this.productRepository.findCartProduct(request.getItemId())
                .orElseThrow(() -> new ProductNotAvailbleException("The product you are trying to add does not exist"));

        if (cartProduct.getStock() < request.getQuantity()) {
            throw new ProductNotAvailbleException("There is not enough stock");
        }

        CartItem cartItem = this.cartRepository.findCartItemByProductId(request.getItemId())
                .orElseGet(() -> CartItem.builder()
                        .product(cartProduct)
                        .quantity(request.getQuantity())
                        .type(ItemType.PRODUCT)
                        .subTotal(cartProduct.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
                        .build());

        this.cartRepository.save(cartItem);
        return CartMessage.builder().cartMessage("Product added to cart").build();
    }

    @Override
    @Transactional
    public CartMessage addServicingToCart(CartRequest request) {
        Servicing servicing = this.servicingRepository.findById(request.getItemId())
                .orElseThrow(() -> new ServicingNotAvaibleException("The service you are trying to add does not exist"));

        CartItem cartItem = CartItem.builder()
                .servicing(servicing)
                .quantity(request.getQuantity())
                .type(ItemType.SERVICE)
                .subTotal(servicing.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
                .build();

        this.cartRepository.save(cartItem);
        return CartMessage.builder().cartMessage("Service added to cart").build();
    }

    @Override
    @Transactional
    public CartMessage removeProductFromCart(Long itemId) {
        this.cartRepository.findCartItemById(itemId).ifPresentOrElse(cartItem ->
                        this.cartRepository.removeFromCart(itemId), () -> {
            throw new ProductNotFoundException("Product not found in cart");
        });
        return CartMessage.builder().cartMessage("Product removed from cart").build();
    }

    @Override
    @Transactional
    public CartMessage increaseItemQuantity(Long itemId) {
        CartItem cartItem = this.cartRepository.findCartItemById(itemId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found in cart"));

        if (cartItem.getQuantity() + 1 > cartItem.getProduct().getStock()) {
            throw new ProductNotAvailbleException("Cannot increase quantity, not enough stock");
        }

        cartItem.increaseQuantity();
        this.cartRepository.save(cartItem);

        return CartMessage.builder().cartMessage("Increased item quantity").build();
    }

    @Override
    @Transactional
    public CartMessage decreaseItemQuantity(Long itemId) {
        CartItem cartItem = this.cartRepository.findCartItemById(itemId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found in cart"));

        if (cartItem.getQuantity() > 1) {
            cartItem.decreaseQuantity();
            this.cartRepository.save(cartItem);
            return CartMessage.builder().cartMessage("Decreased item quantity").build();
        }

        this.cartRepository.removeFromCart(cartItem.getId());
        return CartMessage.builder().cartMessage("Item removed from cart").build();
    }

    @Override
    @Transactional
    public CartMessage emptyCart() {
        this.cartRepository.emptyCart();
        return CartMessage.builder().cartMessage("Cart is empty").build();
    }
}
