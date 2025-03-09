package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.CategoryResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryAlreadyExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.domain.ports.input.CategoryUseCase;
import org.gdzdev.workshop.backend.domain.ports.output.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {

    private final CategoryMapper categoryMapper;
    private final CategoryRepositoryPort categoryRepository;

    @Override
    public List<CategoryResponse> fetchAll() {
        return this.categoryMapper.toResponseList(this.categoryRepository.findAll());
    }

    @Override
    public CategoryResponse fetchByName(String name) {
        return this.categoryMapper.toResponse(this.categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with name '%s' not found", name))));
    }

    @Override
    public CategoryResponse fetchById(Long id) {
        return this.categoryMapper.toResponse(this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id '%s' not found", id))));
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        this.categoryRepository.findByName(categoryRequest.getName()).ifPresent(categoryDb -> {
            throw new CategoryAlreadyExistsException(String.format("Category with name '%s' already exists", categoryRequest.getName()));
        });
        return this.categoryMapper.toResponse(this.categoryRepository.save(this.categoryMapper.toModel(categoryRequest)));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        return this.categoryMapper.toResponse(this.categoryRepository.findById(id).map(categoryDb -> {
            categoryDb.setName(categoryRequest.getName());
            categoryDb.setDescription(categoryRequest.getDescription());
            categoryDb.setImageUrl(categoryRequest.getImageUrl());
            return this.categoryRepository.save(categoryDb);
        }).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id '%s' not found", id))));
    }

    @Override
    public void delete(Long id) {
        if (!this.categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(String.format("Category with id '%s' not found", id));
        }
        this.categoryRepository.deleteById(id);
    }
}
