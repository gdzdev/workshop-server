package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.gdzdev.workshop.backend.domain.enums.CartStatus;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByStatus(CartStatus status);

    List<CartEntity> findAllByStatus(CartStatus status);
}
