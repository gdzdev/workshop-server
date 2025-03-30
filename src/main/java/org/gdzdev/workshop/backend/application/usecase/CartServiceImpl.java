package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.cart.CartMessage;
import org.gdzdev.workshop.backend.domain.exception.ServicingNotAvaibleException;
import org.gdzdev.workshop.backend.domain.model.*;
import org.gdzdev.workshop.backend.application.dto.cart.CartRequest;
import org.gdzdev.workshop.backend.domain.exception.ProductNotAvailbleException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.port.input.CartService;
import org.gdzdev.workshop.backend.domain.port.out.CartRepositoryPort;
import org.gdzdev.workshop.backend.domain.port.out.ProductRepositoryPort;
import org.gdzdev.workshop.backend.domain.port.out.ServicingRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepositoryPort cartRepository;
    private final ProductRepositoryPort productRepository;
    private final ServicingRepositoryPort servicingRepository;

    @Override
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
    public CartMessage removeProductFromCart(Long itemId) {
        this.cartRepository.findCartItemById(itemId).map(CartItem::getProduct)
                .ifPresent(cartItem -> this.cartRepository.removeFromCart(itemId));
        return CartMessage.builder().cartMessage("Product removed from cart").build();
    }

    @Override
    public CartMessage increaseItemQuantity(Long itemId) {
        CartItem cartItem = this.cartRepository.findCartItemById(itemId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found in cart"));

        if (cartItem.getQuantity() >= cartItem.getProduct().getStock()) {
            throw new ProductNotAvailbleException("Cannot increase quantity, not enough stock");
        }

        cartItem.increaseQuantity();
        cartItem.setSubTotal(cartItem.getSubTotal());
        this.cartRepository.save(cartItem);

        return CartMessage.builder().cartMessage("Increased item quantity").build();
    }

    @Override
    public CartMessage decreaseItemQuantity(Long productId) {
        CartItem cartItem = this.cartRepository.findCartItemById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found in cart"));

        if (cartItem.getQuantity() > 1) {
            cartItem.decreaseQuantity();
            cartItem.setSubTotal(cartItem.getSubTotal());
            this.cartRepository.save(cartItem);
        } else {
            this.cartRepository.removeFromCart(cartItem.getId());
        }

        return CartMessage.builder().cartMessage("Decreased item quantity").build();
    }

    @Override
    public CartMessage emptyCart() {
        this.cartRepository.emptyCart();
        return CartMessage.builder().cartMessage("Cart is empty").build();
    }
}
