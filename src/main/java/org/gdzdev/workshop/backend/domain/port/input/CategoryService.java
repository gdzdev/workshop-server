package org.gdzdev.workshop.backend.domain.port.input;

import org.gdzdev.workshop.backend.application.dto.category.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> fetchAll();

    List<CategoryResponse> fetchByName(String name);

    CategoryResponse fetchById(Long id);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);
}
