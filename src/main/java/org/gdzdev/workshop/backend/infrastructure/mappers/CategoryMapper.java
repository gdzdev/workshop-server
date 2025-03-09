package org.gdzdev.workshop.backend.infrastructure.mappers;

import org.gdzdev.workshop.backend.application.dto.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.CategoryResponse;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toModel(CategoryEntity categoryEntity);

    Category toModel(CategoryRequest categoryRequest);

    CategoryEntity toEntity(Category category);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);
}
