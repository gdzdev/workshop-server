package org.gdzdev.workshop.backend.application.usecase;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.exception.ProductNotAvailbleException;
import org.gdzdev.workshop.backend.domain.exception.ProductNotFoundException;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.ports.input.StockUseCase;
import org.gdzdev.workshop.backend.domain.ports.output.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockUseCaseImpl implements StockUseCase {

    private final ProductRepositoryPort productRepository;

    @Override
    @Transactional
    public void discountStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found to discount stock"));

        if (product.getStock() < quantity) {
            throw new ProductNotAvailbleException(String.format("Insufficient stock for the product '%s'", product.getName()));
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void increaseStock(Long productId, int quantity) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found to increase stock"));
        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }
}
