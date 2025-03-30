package org.gdzdev.workshop.backend.domain.port.out;

import org.gdzdev.workshop.backend.domain.model.CartProduct;
import org.gdzdev.workshop.backend.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<Product> findAll();

    Optional<CartProduct> findCartProduct(Long id);

    Page<Product> findAllPaginated(Pageable pageable);

    List<Product> findByNameOrCode(String name, String code);

    Optional<Product> findById(Long id);

    Optional<Product> findByCode(String code);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);
}
