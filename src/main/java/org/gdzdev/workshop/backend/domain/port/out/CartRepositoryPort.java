package org.gdzdev.workshop.backend.domain.port.out;

import org.gdzdev.workshop.backend.domain.enums.CartStatus;
import org.gdzdev.workshop.backend.domain.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepositoryPort {

    List<Cart> findAllByStatus(CartStatus status);

    Optional<Cart> findById(Long cartId);

    Optional<Cart> findByActiveCart();

    Cart save(Cart cart);

    void deleteById(Long cartId);
}
