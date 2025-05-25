package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseRequest;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseResponse;
import org.gdzdev.workshop.backend.domain.model.Purchase;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.PurchasesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CartEntityMapper.class, ProductEntityMapper.class})
public interface PurchaseEntityMapper {
    PurchasesEntity modelToEntity(Purchase model);
    PurchasesEntity responseToEntity(PurchaseResponse response);
    Purchase responseToModel(PurchaseResponse response);
    Purchase entityToModel(PurchasesEntity entity);
    PurchaseResponse entityToResponse(PurchasesEntity entity);
    PurchasesEntity requestToEntity(PurchaseRequest request);
    List<PurchaseResponse> entityListToResponseList(List<PurchasesEntity> entities);
}
