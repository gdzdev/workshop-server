package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.enums.CartStatus;
import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.port.out.CartRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.CartMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.CartJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CartJpaAdapter implements CartRepositoryPort {

    private final CartMapper cartMapper;
    private final CartJpaRepository jpaRepository;

    @Override
    public List<Cart> findAllByStatus(CartStatus status) {
        return this.jpaRepository.findAllByStatus(status).stream()
                .map(cartMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findById(Long cartId) {
        return this.jpaRepository.findById(cartId)
                .map(cartMapper::toDomain);
    }

    @Override
    public Optional<Cart> findByActiveCart() {
        return this.jpaRepository.findByStatus(CartStatus.ACTIVE)
                .map(cartMapper::toDomain);
    }

    @Override
    public Cart save(Cart cart) {
        return cartMapper.toDomain(this.jpaRepository
                .save(cartMapper.toEntity(cart)));
    }

    @Override
    public void deleteById(Long cartId) {
        this.jpaRepository.deleteById(cartId);
    }
}
