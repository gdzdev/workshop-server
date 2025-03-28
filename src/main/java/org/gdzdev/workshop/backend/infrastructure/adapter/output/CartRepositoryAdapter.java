package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.ports.output.CartRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.CartMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CartRepositoryAdapter implements CartRepositoryPort {

    private final CartMapper cartMapper;
    private final CartJpaRepository cartJpaRepository;

    @Override
    public List<CartItem> findAllCartItems() {
        return this.cartJpaRepository.findAll().stream()
                .map(cartMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<CartItem> findCartItemById(Long id) {
        return this.cartJpaRepository.findById(id).map(cartMapper::toModel);
    }

    @Override
    public Optional<CartItem> findCartItemByProductId(Long productId) {
        return this.cartJpaRepository.findByProductId(productId).map(cartMapper::toModel);
    }

    @Override
    public CartItem saveCart(CartItem cartItem) {
        return cartMapper.toModel(this.cartJpaRepository
                .save(cartMapper.toEntity(cartItem)));
    }

    @Override
    public void removeFromCart(Long itemId) {
        this.cartJpaRepository.deleteById(itemId);
    }

    @Override
    public void emptyCart() {
        this.cartJpaRepository.deleteAll();
    }
}
