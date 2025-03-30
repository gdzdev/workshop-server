package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByCode(String code);

    List<ProductEntity> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
