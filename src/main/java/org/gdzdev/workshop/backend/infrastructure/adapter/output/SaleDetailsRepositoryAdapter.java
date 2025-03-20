package org.gdzdev.workshop.backend.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.SaleDetail;
import org.gdzdev.workshop.backend.domain.ports.output.SaleDetailRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.entities.SaleDetailEntity;
import org.gdzdev.workshop.backend.infrastructure.mappers.SaleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SaleDetailsRepositoryAdapter implements SaleDetailRepositoryPort {

    private final SaleMapper saleMapper;
    private final SaleDetailJpaRepository saleDetailJpaRepository;


    @Override
    public List<SaleDetail> saveAll(List<SaleDetail> saleDetails) {
        List<SaleDetailEntity> savedEntities = saleDetailJpaRepository.saveAll(saleMapper.toEntityList(saleDetails));
        return savedEntities.stream()
                .map(saleMapper::toModel)
                .collect(Collectors.toList());
    }
}
