package org.gdzdev.workshop.backend.inventory.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.inventory.application.dto.ProductRequest;
import org.gdzdev.workshop.backend.inventory.application.dto.ProductResponse;
import org.gdzdev.workshop.backend.inventory.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.inventory.domain.ports.input.ProductUseCase;
import org.gdzdev.workshop.backend.inventory.domain.ports.output.ProductRepositoryPort;
import org.gdzdev.workshop.backend.inventory.infrastructure.mappers.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductMapper productMapper;
    private final ProductRepositoryPort productRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<ProductResponse> fetchAll() {
        return productMapper.toDtoList(this.productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse fetchById(Long id) {
        return this.productRepository.findById(id).map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest productRequest) {
        String code = productRequest.getCode();
        return productMapper.toDto(this.productRepository
                .save(productMapper.toModel(productRequest)));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest) {
        return this.productRepository.findById(id).map(productDb -> {
            productDb.setCode(productRequest.getCode());
            productDb.setName(productRequest.getName());
            productDb.setAvailable(productRequest.getAvailable());
            productDb.setImageUrl(productRequest.getImageUrl());
            productDb.setStock(productRequest.getStock());
            productDb.setCost(productRequest.getCost());
            productDb.setPrice(productRequest.getPrice());
            return productMapper.toDto(this.productRepository.save(productDb));
        }).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " cannot be updated"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!this.productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        this.productRepository.deleteById(id);
    }
}
