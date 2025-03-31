package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.domain.port.out.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.CategoryEntityMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.CategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryRepositoryPort {

    private final CategoryEntityMapper categoryMapper;
    private final CategoryJpaRepository jpaRepository;

    @Override
    public List<Category> findAll() {
        return this.jpaRepository.findAll().stream()
                .map(categoryMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(categoryMapper::toModel);
    }

    @Override
    public List<Category> findAllByName(String name) {
        return this.jpaRepository.findByNameContainingIgnoreCase(name).stream()
                .map(categoryMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return this.jpaRepository.findByNameIgnoreCase(name)
                .map(categoryMapper::toModel);
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
