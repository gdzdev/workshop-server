package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.category.CategoryRequest;
import org.gdzdev.workshop.backend.application.dto.category.CategoryResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryAlreadyExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotExistsException;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.domain.port.input.CategoryService;
import org.gdzdev.workshop.backend.domain.port.input.CloudinaryService;
import org.gdzdev.workshop.backend.domain.port.out.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.CategoryEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryEntityMapper _categoryMapper;
    private final CategoryRepositoryPort _categoryRepository;
    private final CloudinaryService _cloudinary;

    @Override
    public List<CategoryResponse> fetchAll() {
        return this._categoryMapper
                .toResponseList(this._categoryRepository.findAll());
    }

    @Override
    public List<CategoryResponse> fetchByName(String name) {
        if (name.isEmpty() || name.trim().length() < 3) {
            return List.of();
        }
        return this._categoryMapper
                .toResponseList(this._categoryRepository.findAllByName(name));
    }

    @Override
    public CategoryResponse fetchById(Long id) {
        return this._categoryMapper.toResponse(this._categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id '%s' not found", id))));
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        this._categoryRepository.findByName(categoryRequest.getName()).ifPresent(categoryDb -> {
            throw new CategoryAlreadyExistsException(String.format("Category with name '%s' already exists", categoryRequest.getName()));
        });

        Category category = this._categoryMapper.toModel(categoryRequest);

        try {
            if (!categoryRequest.getFile().isEmpty()) {
                String url = this._cloudinary.uploadImage(categoryRequest.getFile());
                category.setImageUrl(url);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return this._categoryMapper.toResponse(this._categoryRepository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        return this._categoryMapper.toResponse(this._categoryRepository.findById(id).map(categoryDb -> {
            categoryDb.setName(categoryRequest.getName());
            categoryDb.setDescription(categoryRequest.getDescription());
            categoryDb.setImageUrl(categoryRequest.getImageUrl());
            return this._categoryRepository.save(categoryDb);
        }).orElseThrow(() -> new CategoryNotExistsException(String.format("Category with id '%s' not found", id))));
    }

    @Override
    public void delete(Long id) {
        if (!this._categoryRepository.existsById(id)) {
            throw new CategoryNotExistsException(String.format("Category with id '%s' not found", id));
        }
        this._categoryRepository.deleteById(id);
    }
}
