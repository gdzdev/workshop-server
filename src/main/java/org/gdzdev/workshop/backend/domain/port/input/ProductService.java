package org.gdzdev.workshop.backend.domain.port.input;

import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductResponse> fetchAll();

    Page<ProductResponse> fetchAllPaginated(Pageable pageable);

    List<ProductResponse> fetchByNameOrCode(String keyword);

    ProductResponse fetchById(Long id);

    ProductResponse create(ProductRequest productRequest);

    ProductResponse update(Long id, ProductRequest productRequest);

    void deleteById(Long id);
}
