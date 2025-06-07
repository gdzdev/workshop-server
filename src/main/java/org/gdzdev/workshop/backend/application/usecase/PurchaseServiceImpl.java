package org.gdzdev.workshop.backend.application.usecase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.gdzdev.workshop.backend.application.dto.PaginatedResponse;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseRequest;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseResponse;
import org.gdzdev.workshop.backend.domain.exception.InternalServerErrorException;
import org.gdzdev.workshop.backend.domain.exception.PurchaseNotFoundException;
import org.gdzdev.workshop.backend.domain.port.input.PurchaseService;

import org.gdzdev.workshop.backend.domain.port.out.PurchaseRepositoryPort;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.PurchasesEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.mapper.PurchaseEntityMapperImpl;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.PurchaseJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseEntityMapperImpl _mapper;
    private final PurchaseJpaRepository _repository;
    private final PurchaseRepositoryPort _purchaseRepositoryPort;

    @Override
    public PurchaseResponse createPurchase(PurchaseRequest purchase) {
        PurchasesEntity entityToSave = this._mapper.requestToEntity(purchase);

        if (entityToSave == null) throw new InternalServerErrorException("Lo siento, algo salio mal");

        PurchasesEntity savedEntity = this._repository.save(entityToSave);

        return this._mapper.entityToResponse(savedEntity);
    }

    @Override
    public PurchaseResponse UpdatePurchase(PurchaseRequest purchase) {
        return null;
    }

    @Override
    public boolean deletePurchase(Long id) {
        return false;
    }

    @Override
    @Transactional
    public List<PurchaseResponse> getAllPurchases() {
        List<PurchaseResponse> purchases = this._repository
                .findAll()
                .stream()
                .map(this._mapper::entityToResponse)
                .toList();

        if (purchases.isEmpty()) return new ArrayList<>();

        return purchases;
    }

    @Override
    @Transactional
    public PurchaseResponse getPurchaseById(Long id) {
        // TODO: should create a dto for validate this parameter
        if (id == null) throw new RuntimeException("El campo id es requerido.");
        if (id <= 0) throw new RuntimeException("El campo id debe de ser mayor de 0.");

        return this._repository
                .findById(id)
                .map(this._mapper::entityToResponse)
                .orElseThrow(() -> new PurchaseNotFoundException("La compra no fue encontrada"));
    }

    @Override
    @Transactional
    public List<PurchaseResponse> searchPurchase(String provider) {
        List<PurchasesEntity> purchases = this._repository.findAllByProvider(provider);

        if (purchases.isEmpty()) return new ArrayList<>();

        return this._mapper.entityListToResponseList(purchases);
    }

    @Override
    @Transactional
    public PaginatedResponse<PurchaseResponse> fetchAllPaginated(Pageable pageable) {
        Page<PurchaseResponse> page = this._purchaseRepositoryPort.findAllPaginated(pageable).map(this._mapper::toResponse);
        return PaginatedResponse
                .<PurchaseResponse>builder()
                .content(page.getContent())
                .currentPage(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }
}
