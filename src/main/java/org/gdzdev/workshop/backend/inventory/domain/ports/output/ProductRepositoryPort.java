package org.gdzdev.workshop.backend.inventory.domain.ports.output;

import org.gdzdev.workshop.backend.inventory.domain.model.Product;

import java.util.Optional;

public interface ProductRepositoryPort {

    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);
}
