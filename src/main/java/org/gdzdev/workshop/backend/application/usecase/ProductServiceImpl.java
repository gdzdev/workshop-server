package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.gdzdev.workshop.backend.application.dto.PaginatedResponse;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;
import org.gdzdev.workshop.backend.domain.exception.CategoryNotFoundException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotExistsException;
import org.gdzdev.workshop.backend.domain.exception.ProductAlreadyExistsException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.gdzdev.workshop.backend.domain.port.input.CloudinaryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductEntityMapper productMapper;
    private final CategoryRepositoryPort categoryRepository;
    private final ProductRepositoryPort productRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> fetchAll() {
        return this.productMapper.toResponseList(this.productRepository.findAll());
    }

    @Override
    public List<CartProduct> fetchCartProductsAvailable() {
        return this.productRepository.findAllByAvailable();
    }

    @Override
    public PaginatedResponse<ProductResponse> fetchAllPaginated(Pageable pageable) {
        Page<ProductResponse> page = this.productRepository.findAllPaginated(pageable)
                .map(productMapper::toResponse);

        return PaginatedResponse.<ProductResponse>builder()
                .content(page.getContent())
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize()).build();
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

        if (productRequest.getFile().isEmpty() && productRequest.getImageUrl().isEmpty()) throw new RuntimeException("");

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Product product = productMapper.toModel(productRequest);

        try{
            String url = this.cloudinaryService.uploadImage(productRequest.getFile());

            product.setCategory(category);

            if (!url.isBlank()) product.setImageUrl(url);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return productMapper.toResponse(this.productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest) {
        return this.productRepository.findById(id).map(productDb -> {
            productDb.setCode(productRequest.getCode());
            productDb.setName(productRequest.getName());
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
    public ProductResponse updateProductImage(Long id, MultipartFile image) {
        return null;
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
