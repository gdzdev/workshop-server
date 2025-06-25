package org.gdzdev.workshop.backend.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.Purchase;
import org.gdzdev.workshop.backend.domain.port.out.PurchaseRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.PurchaseEntityMapperImpl;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.PurchaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseJpaAdapter implements PurchaseRepositoryPort {
    private final PurchaseJpaRepository _purchasejpaRepository;
    private final PurchaseEntityMapperImpl _mapper;

    @Override
    public Page<Purchase> findAllPaginated(Pageable pageable) {
        return this._purchasejpaRepository.findAll(pageable).map(this._mapper::toModel);
    }
}
