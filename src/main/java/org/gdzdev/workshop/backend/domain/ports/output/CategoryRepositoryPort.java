package org.gdzdev.workshop.backend.domain.ports.output;

import org.gdzdev.workshop.backend.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    Category save(Category category);

    void deleteById(Long id);

    boolean existsById(Long id);
}
