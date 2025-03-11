package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import org.gdzdev.workshop.backend.infrastructure.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByProductId(Long productId);
}
