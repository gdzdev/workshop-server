package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.ProductResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.ports.input.ProductUseCase;
import org.gdzdev.workshop.backend.domain.ports.output.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.domain.ports.output.ProductRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductMapper productMapper;
    private final ProductRepositoryPort productRepository;
    private final CategoryRepositoryPort categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> fetchAll() {
        return this.productMapper.toResponseList(this.productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse fetchById(Long id) {
        return this.productRepository.findById(id).map(productMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Product product = productMapper.toModel(productRequest);

        product.setCategory(category);
        return productMapper.toResponse(this.productRepository.save(product));
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
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
            productDb.setCategory(category);
            return productMapper.toResponse(this.productRepository.save(productDb));
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
