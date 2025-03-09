package org.gdzdev.workshop.backend.domain.ports.input;

import org.gdzdev.workshop.backend.application.dto.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.CategoryResponse;

import java.util.List;

public interface CategoryUseCase {

    List<CategoryResponse> fetchAll();

    CategoryResponse fetchByName(String name);

    CategoryResponse fetchById(Long id);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);
}
