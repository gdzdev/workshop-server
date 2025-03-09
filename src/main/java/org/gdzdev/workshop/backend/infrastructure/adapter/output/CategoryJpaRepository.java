package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import org.springframework.data.jpa.repository.JpaRepository;
import org.gdzdev.workshop.backend.infrastructure.entities.CategoryEntity;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
