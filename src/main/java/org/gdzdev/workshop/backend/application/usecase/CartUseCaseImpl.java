package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.AddItemRequest;
import org.gdzdev.workshop.backend.application.dto.CartResponse;
import org.gdzdev.workshop.backend.domain.exception.ProductNotAvailbleException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotExistsException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.ports.input.CartUseCase;
import org.gdzdev.workshop.backend.domain.ports.output.CartRepositoryPort;
import org.gdzdev.workshop.backend.domain.ports.output.ProductRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartUseCaseImpl implements CartUseCase {

    private final ProductMapper productMapper;
    private final CartRepositoryPort cartRepository;
    private final ProductRepositoryPort productRepository;

    @Override
    public Cart getCart() {
        List<CartItem> cartItems = cartRepository.findAllCartItems();
        Cart cart = Cart.builder().cartItems(cartItems).build();
        cart.calculateGrandTotal();
        return cart;
    }

    @Override
    public CartResponse addItem(AddItemRequest request) {
        Product product = this.productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotExistsException("The product you are trying to add does not exist"));

        if (product.getStock() < request.getQuantity()) {
            throw new ProductNotAvailbleException("There is not enough stock");
        }

        Optional<CartItem> cartItemOptional = this.cartRepository.findCartItemByProductId(request.getProductId());

        if (cartItemOptional.isPresent()) {
            CartItem itemExists = cartItemOptional.get();
            itemExists.setQuantity(itemExists.getQuantity() + request.getQuantity());
            this.cartRepository.saveCart(itemExists);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(productMapper.toProductItem(product));
            newCartItem.setQuantity(request.getQuantity());
            this.cartRepository.saveCart(newCartItem);
        }
        return CartResponse.builder().message("Product added to cart").build();
    }

    @Override
    public CartResponse removeItem(Long itemId) {
        this.cartRepository.findCartItemById(itemId).map(item -> {
            this.cartRepository.removeFromCart(itemId);
            return true;
        }).orElseThrow(() -> new ProductNotFoundException("The product you are trying to remove does not exist"));
        return CartResponse.builder().message("Product removed from cart").build();
    }

    @Override
    public CartResponse clearCart() {
        this.cartRepository.emptyCart();
        return CartResponse.builder().message("Cart is empty").build();
    }

    @Override
    public CartResponse cartTotal() {
        BigDecimal total = this.cartRepository.findAllCartItems().stream().map(CartItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return CartResponse.builder().message(String.format("$ %.2f", total)).build();
    }
}
