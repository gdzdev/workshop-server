package org.gdzdev.workshop.backend.inventory.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.inventory.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.inventory.domain.model.Category;
import org.gdzdev.workshop.backend.inventory.domain.ports.input.CategoryUseCase;
import org.gdzdev.workshop.backend.inventory.domain.ports.output.CategoryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {

    private final CategoryRepositoryPort categoryRepository;

    @Override
    public Iterable<Category> fetchAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category fetchByName(String name) {
        return this.categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with name '%s' not found", name)));
    }

    @Override
    public Category fetchById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id '%s' not found", id)));
    }

    @Override
    public Category create(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        return this.categoryRepository.findById(id).map(categoryDb -> {
            categoryDb.setName(category.getName());
            categoryDb.setDescription(category.getDescription());
            return this.categoryRepository.save(categoryDb);
        }).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id '%s' not found", id)));
    }

    @Override
    public void delete(Long id) {
        if (!this.categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(String.format("Category with id '%s' not found", id));
        }
        this.categoryRepository.deleteById(id);
    }
}
