package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import org.gdzdev.workshop.backend.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
