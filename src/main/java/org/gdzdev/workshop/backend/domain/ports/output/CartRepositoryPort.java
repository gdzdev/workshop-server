package org.gdzdev.workshop.backend.domain.ports.output;

import org.gdzdev.workshop.backend.domain.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartRepositoryPort {

    List<CartItem> findAllCartItems();

    Optional<CartItem> findCartItemById(Long id);

    Optional<CartItem> findCartItemByProductId(Long productId);

    CartItem saveCart(CartItem cartItem);

    void removeFromCart(Long itemId);

    void emptyCart();
}
