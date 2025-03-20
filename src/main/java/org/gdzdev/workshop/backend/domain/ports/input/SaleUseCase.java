package org.gdzdev.workshop.backend.domain.ports.input;

import org.gdzdev.workshop.backend.application.dto.SaleRequest;
import org.gdzdev.workshop.backend.application.dto.SaleResponse;
import org.gdzdev.workshop.backend.domain.model.Sale;

import java.util.List;

public interface SaleUseCase {

    List<SaleResponse> fetchAllSales();

    Sale fetchSaleById(Long id);

    SaleResponse createSale(SaleRequest saleRequest);
}
