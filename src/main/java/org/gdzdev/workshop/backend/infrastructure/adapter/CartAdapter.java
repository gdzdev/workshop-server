package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.enums.CartStatus; // Use domain.enums.CartStatus
import org.gdzdev.workshop.backend.domain.model.Cart;     // Use domain.model.Cart
import org.gdzdev.workshop.backend.domain.port.out.CartRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.CartEntityMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.CartJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CartAdapter implements CartRepositoryPort {

    private final CartEntityMapper cartMapper; // This mapper should now map Cart <-> CartEntity
    private final CartJpaRepository cartJpaRepository;

    @Override
    public List<Cart> findAllByStatus(CartStatus status) {
        // Assuming CartJpaRepository has findByStatus returning List<CartEntity>
        return cartJpaRepository.findByStatus(status)
                .stream()
                .map(cartMapper::toModel) // Map from CartEntity to Cart
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findById(Long cartId) {
        return cartJpaRepository.findById(cartId).map(cartMapper::toModel); // Map from CartEntity to Cart
    }

    @Override
    public Optional<Cart> findByActiveCart() {
        // You'll need a method in CartJpaRepository like findByStatus(CartStatus.ACTIVE)
        // or a custom query to find an active cart based on your definition of "active".
        // For example, if 'ACTIVE' is a status in your enum:
        return cartJpaRepository.findByStatus(CartStatus.ACTIVE) // Assuming CartStatus.ACTIVE exists
                .stream()
                .findFirst() // Get the first active cart if multiple exist
                .map(cartMapper::toModel);
        // OR, if there's a specific field like `isActive` in your entity:
        // return cartJpaRepository.findByIsActiveTrue().map(cartMapper::toModel);
    }

    @Override
    public Cart save(Cart cart) {
        return cartMapper.toModel(this.cartJpaRepository.save(cartMapper.toEntity(cart))); // Map Cart <-> CartEntity
    }

    @Override
    public void deleteById(Long cartId) {
        this.cartJpaRepository.deleteById(cartId);
    }

    // Note: The methods like findAllCartItems(), findCartItemByProductId(),
    // removeFromCart(), emptyCart() that were in your original CartAdapter
    // are NOT part of CartRepositoryPort, so they cannot have @Override here.
    // If you need them, you should add them to the CartRepositoryPort interface first,
    // or rethink if they truly belong to the Cart's repository port.
}