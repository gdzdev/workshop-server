package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartEntityMapper {
    CartEntity toEntity(Cart model);

    Cart toModel(CartEntity entity);
}

