package org.gdzdev.workshop.backend.domain.port.out;

import org.gdzdev.workshop.backend.domain.model.Product;
import org.gdzdev.workshop.backend.domain.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseRepositoryPort {
    Page<Purchase> findAllPaginated(Pageable pageable);
}


