package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.SaleResponse;
import org.gdzdev.workshop.backend.domain.model.Sale;
import org.gdzdev.workshop.backend.domain.ports.output.SaleRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.mappers.SaleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SaleRepositoryAdapter implements SaleRepositoryPort {

    private final SaleMapper saleMapper;
    private final SaleJpaRepository saleJpaRepository;

    @Override
    public List<SaleResponse> fetchAllSales() {
        return this.saleJpaRepository.findAll().stream()
                .map(saleMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<Sale> fetchSaleById(Long id) {
        return this.saleJpaRepository.findById(id).map(saleMapper::toModel);
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleMapper.toModel(this.saleJpaRepository.save(saleMapper.toEntity(sale)));
    }
}
