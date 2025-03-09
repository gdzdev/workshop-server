package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.domain.ports.output.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.CategoryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final CategoryMapper categoryMapper;
    private final CategoryJpaRepository jpaRepository;

    @Override
    public List<Category> findAll() {
        return this.jpaRepository.findAll().stream()
                .map(categoryMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.jpaRepository.findById(id).map(categoryMapper::toModel);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return this.jpaRepository.findByName(name).map(categoryMapper::toModel);
    }

    @Override
    public Category save(Category category) {
        return categoryMapper.toModel(this.jpaRepository
                .save(categoryMapper.toEntity(category)));
    }

    @Override
    public void deleteById(Long id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return this.jpaRepository.existsById(id);
    }
}
