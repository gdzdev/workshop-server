package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.category.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.category.CategoryResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryAlreadyExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.domain.port.input.CategoryService;
import org.gdzdev.workshop.backend.domain.port.out.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.CategoryEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryEntityMapper categoryMapper;
    private final CategoryRepositoryPort categoryRepository;

    @Override
    public List<CategoryResponse> fetchAll() {
        return this.categoryMapper
                .toResponseList(this.categoryRepository.findAll());
    }

    @Override
    public List<CategoryResponse> fetchByName(String name) {
        if (name.isEmpty() || name.trim().length() < 3) {
            return List.of();
        }
        return this.categoryMapper
                .toResponseList(this.categoryRepository.findAllByName(name));
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
        }).orElseThrow(() -> new CategoryNotExistsException(String.format("Category with id '%s' not found", id))));
    }

    @Override
    public void delete(Long id) {
        if (!this.categoryRepository.existsById(id)) {
            throw new CategoryNotExistsException(String.format("Category with id '%s' not found", id));
        }
        this.categoryRepository.deleteById(id);
    }
}
