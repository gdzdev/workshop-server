package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.ports.output.ProductRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductMapper productMapper;
    private final ProductJpaRepository jpaRepository;

    @Override
    public List<Product> findAll() {
        return this.jpaRepository.findAll().stream()
                .map(productMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameOrCode(String name, String code) {
        return this.jpaRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(name, code)
                .stream().map(productMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(productMapper::toModel);
    }

    @Override
    public Product save(Product product) {
        return productMapper.toModel(this.jpaRepository
                .save(productMapper.toEntity(product)));
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
