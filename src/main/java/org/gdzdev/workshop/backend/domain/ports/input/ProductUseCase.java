package org.gdzdev.workshop.backend.domain.ports.input;

import org.gdzdev.workshop.backend.application.dto.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.ProductResponse;

public interface ProductUseCase {

    Iterable<ProductResponse> fetchAll();

    ProductResponse fetchById(Long id);

    ProductResponse create(ProductRequest productRequest);

    ProductResponse update(Long id, ProductRequest productRequest);

    void deleteById(Long id);
}
