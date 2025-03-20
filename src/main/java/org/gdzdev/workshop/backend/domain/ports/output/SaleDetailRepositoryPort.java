package org.gdzdev.workshop.backend.domain.ports.output;

import org.gdzdev.workshop.backend.domain.model.SaleDetail;

import java.util.List;

public interface SaleDetailRepositoryPort {

    List<SaleDetail> saveAll(List<SaleDetail> saleDetails);
}
