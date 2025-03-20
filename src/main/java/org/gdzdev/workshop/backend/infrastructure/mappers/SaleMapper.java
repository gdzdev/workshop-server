package org.gdzdev.workshop.backend.infrastructure.mappers;

import org.gdzdev.workshop.backend.application.dto.SaleResponse;
import org.gdzdev.workshop.backend.domain.model.Sale;
import org.gdzdev.workshop.backend.domain.model.SaleDetail;
import org.gdzdev.workshop.backend.infrastructure.entities.SaleDetailEntity;
import org.gdzdev.workshop.backend.infrastructure.entities.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    Sale toModel(SaleEntity entity);
    SaleEntity toEntity(Sale model);

    SaleResponse toResponse(SaleEntity entity);

    @Mapping(target = "sale", ignore = true)
    List<SaleDetailEntity> toEntityList(List<SaleDetail> saleDetails);

    SaleDetail toModel(SaleDetailEntity entity);

    SaleDetailEntity toEntity(SaleDetail model);

    @Mapping(target = "saleDetails", source = "saleDetails")
    SaleResponse toSaleResponse(Sale sale);
}

