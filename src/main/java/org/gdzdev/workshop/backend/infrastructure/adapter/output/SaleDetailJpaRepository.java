package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import org.gdzdev.workshop.backend.infrastructure.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDetailJpaRepository extends JpaRepository<SaleDetailEntity, Long> {
}
