package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.enums.CartStatus;
import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.port.out.CartRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.CartMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.CartJpaRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Primary
@RequiredArgsConstructor
public class CartJpaAdapter implements CartRepositoryPort {
    private final CartMapper _cartMapper;
    private final CartJpaRepository _jpaRepository;

    @Override
    public List<Cart> findAllByStatus(CartStatus status) {
        return this._jpaRepository.findAllByStatus(status).stream()
                .map(_cartMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findById(Long cartId) {
        return this._jpaRepository.findById(cartId)
                .map(_cartMapper::toDomain);
    }

    @Override
    public Optional<Cart> findByActiveCart() {
        return this._jpaRepository.findByStatus(CartStatus.ACTIVE)
                .map(_cartMapper::toDomain);
    }

    @Override
    public Cart save(Cart cart) {
        return _cartMapper.toDomain(this._jpaRepository
                .save(_cartMapper.toEntity(cart)));
    }

    @Override
    public void deleteById(Long cartId) {
        this._jpaRepository.deleteById(cartId);
    }
}
