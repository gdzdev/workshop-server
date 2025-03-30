package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByProductId(Long productId);
}
