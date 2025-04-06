package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.gdzdev.workshop.backend.domain.exception.*;
import org.springframework.web.multipart.MultipartFile;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.model.Category;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.springframework.transaction.annotation.Transactional;
import org.gdzdev.workshop.backend.domain.port.input.ProductService;
import org.gdzdev.workshop.backend.application.dto.PaginatedResponse;
import org.gdzdev.workshop.backend.domain.port.input.CloudinaryService;
import org.gdzdev.workshop.backend.domain.port.out.ProductRepositoryPort;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.domain.port.out.CategoryRepositoryPort;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.ProductEntityMapper;

import java.io.IOException;
import java.util.List;

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
    @Transactional(readOnly = true)
    public List<CartProduct> fetchCartProductsAvailable() {
        return this.productRepository.findAllByAvailable();
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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

        product.setImageUrl(cloudinaryService.getDefaultImageUrl());
        product.setCategory(category);
        return productMapper.toResponse(this.productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest productRequest) {
        return this.productRepository.findById(id).map(productDb -> {
            productDb.setCode(productRequest.getCode());
            productDb.setName(productRequest.getName());
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
    public ProductResponse updateProductImage(Long productId, MultipartFile imageFile) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            if (imageFile == null || imageFile.isEmpty()) {
                throw new ImageProcessingException("Image file cannot be empty");
            }

            String contentType = imageFile.getContentType();
            if (!cloudinaryService.isSupportedImageType(contentType)) {
                throw new ImageProcessingException("Image format not supported. Use JPEG, PNG or WEBP");
            }

            String newImageUrl = cloudinaryService.uploadImage(imageFile);

            try {
                String currentImageUrl = product.getImageUrl();
                if (currentImageUrl != null && !currentImageUrl.equals(cloudinaryService.getDefaultImageUrl())) {
                    cloudinaryService.deleteImage(currentImageUrl);
                }
            } catch (IOException e) {
                System.out.println("Error deleting previous product image {}: {}" + productId + e.getMessage());
            }

            product.setImageUrl(newImageUrl);
            productRepository.save(product);
            return productMapper.toResponse(product);
        } catch (IOException e) {
            throw new ImageProcessingException("Error processing image: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        try {
            String imageUrl = product.getImageUrl();
            if (imageUrl != null && !imageUrl.equals(cloudinaryService.getDefaultImageUrl())) {
                cloudinaryService.deleteImage(imageUrl);
            }
        } catch (IOException e) {
            System.err.println("Error deleting Cloudinary image for product " + id + ": " + e.getMessage());
        }
        productRepository.deleteById(id);
    }
}
