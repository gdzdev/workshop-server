package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import org.gdzdev.workshop.backend.infrastructure.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleJpaRepository extends JpaRepository<SaleEntity, Long> {
}
