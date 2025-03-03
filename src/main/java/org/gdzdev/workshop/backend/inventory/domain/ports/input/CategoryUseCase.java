package org.gdzdev.workshop.backend.inventory.domain.ports.input;

import org.gdzdev.workshop.backend.inventory.domain.model.Category;

public interface CategoryUseCase {

    Iterable<Category> fetchAll();

    Category fetchByName(String name);

    Category fetchById(Long id);

    Category create(Category category);

    Category update(Long id, Category category);

    void delete(Long id);
}
