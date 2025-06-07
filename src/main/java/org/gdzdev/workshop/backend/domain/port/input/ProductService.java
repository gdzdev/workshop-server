package org.gdzdev.workshop.backend.domain.port.input;

import org.gdzdev.workshop.backend.application.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.application.dto.product.ProductRequest;
import org.gdzdev.workshop.backend.application.dto.product.ProductResponse;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductResponse> fetchAll();

    List<CartProduct> fetchCartProductsAvailable();

    PaginatedResponse<ProductResponse> fetchAllPaginated(Pageable pageable);

    List<ProductResponse> fetchByNameOrCode(String keyword);

    ProductResponse fetchById(Long id);

    ProductResponse create(ProductRequest productRequest);

    ProductResponse update(Long id, ProductRequest productRequest);

    ProductResponse updateProductImage(Long id, MultipartFile image);

    void deleteById(Long id);
}
