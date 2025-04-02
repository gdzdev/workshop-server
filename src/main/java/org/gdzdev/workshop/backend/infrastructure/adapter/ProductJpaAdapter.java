package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.port.out.ProductRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.ProductEntityMapper;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.ProductJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductRepositoryPort {

    private final ProductEntityMapper productEntityMapper;
    private final ProductJpaRepository jpaRepository;

    @Override
    public List<Product> findAll() {
        return this.jpaRepository.findAll().stream()
                .map(productEntityMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<CartProduct> findAllByAvailable() {
        return this.jpaRepository.findByAvailableTrue().stream()
                .map(productEntityMapper::toCartProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<CartProduct> findCartProduct(Long id) {
        return this.jpaRepository.findById(id)
                .map(productEntityMapper::toCartProduct);
    }

    @Override
    public Page<Product> findAllPaginated(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(productEntityMapper::toModel);
    }

    @Override
    public List<Product> findByNameOrCode(String name, String code) {
        return this.jpaRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(name, code)
                .stream().map(productEntityMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(productEntityMapper::toModel);
    }

    @Override
    public Optional<Product> findByCode(String code) {
        return this.jpaRepository.findByCode(code)
                .map(productEntityMapper::toModel);
    }

    @Override
    public Product save(Product product) {
        return productEntityMapper.toModel(this.jpaRepository
                .save(productEntityMapper.toEntity(product)));
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
