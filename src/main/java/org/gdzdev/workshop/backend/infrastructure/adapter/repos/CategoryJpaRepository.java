package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByNameIgnoreCase(String name);

    List<CategoryEntity> findByNameContainingIgnoreCase(String name);
}
