package org.gdzdev.workshop.backend.domain.port.input;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> fetchAll();

    List<CartProduct> fetchCartProductsAvailable();

    Page<ProductResponse> fetchAllPaginated(Pageable pageable);

    List<ProductResponse> fetchByNameOrCode(String keyword);

    ProductResponse fetchById(Long id);

    ProductResponse create(ProductRequest productRequest);

    ProductResponse update(Long id, ProductRequest productRequest);

    void deleteById(Long id);
}
