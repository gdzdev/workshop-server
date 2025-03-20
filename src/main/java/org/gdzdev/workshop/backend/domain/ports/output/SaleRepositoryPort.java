package org.gdzdev.workshop.backend.domain.ports.output;

import org.gdzdev.workshop.backend.application.dto.SaleResponse;
import org.gdzdev.workshop.backend.domain.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepositoryPort {

    List<SaleResponse> fetchAllSales();

    Optional<Sale> fetchSaleById(Long id);

    Sale createSale(Sale sale);

}
