package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.application.dto.category.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.category.CategoryResponse;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    Category toModel(CategoryEntity categoryEntity);

    Category toModel(CategoryRequest categoryRequest);

    CategoryEntity toEntity(Category category);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);
}
