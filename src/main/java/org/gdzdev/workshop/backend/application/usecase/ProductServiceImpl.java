package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotExistsException;
import org.gdzdev.workshop.backend.domain.exception.ProductAlreadyExistsException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.port.input.ProductService;
import org.gdzdev.workshop.backend.domain.port.out.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.domain.port.out.ProductRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.ProductEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductEntityMapper productMapper;
    private final CategoryRepositoryPort categoryRepository;
    private final ProductRepositoryPort productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> fetchAll() {
        return this.productMapper.toResponseList(this.productRepository.findAll());
    }

    @Override
    public Page<ProductResponse> fetchAllPaginated(Pageable pageable) {
        return productRepository.findAllPaginated(pageable)
                .map(productMapper::toResponse);
    }

    @Override
    public List<ProductResponse> fetchByNameOrCode(String keyword) {
        if (keyword.isEmpty() || keyword.trim().length() < 3) {
            return List.of();
        }
        return this.productMapper.toResponseList(this.productRepository.findByNameOrCode(keyword, keyword));
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
        this.productRepository.findByCode(productRequest.getCode()).ifPresent(product -> {
            throw new ProductAlreadyExistsException(String.format("Product with code %s already exists", productRequest.getCode()));
        });
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
        }).orElseThrow(() -> new ProductNotExistsException("Product with id " + id + " cannot be updated"));
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
