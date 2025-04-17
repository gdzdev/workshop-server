package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {

    CartItemEntity toEntity(CartItem model);

    CartItem toModel(CartItemEntity entity);

}

