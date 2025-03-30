package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ServicingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicingJpaRepository extends JpaRepository<ServicingEntity, Long> {
}
