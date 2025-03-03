package org.gdzdev.workshop.backend.domain.ports.output;

import org.gdzdev.workshop.backend.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);
}
