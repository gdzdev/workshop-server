package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CartMapper {

    CartItemEntity toEntity(CartItem model);

    CartItem toModel(CartItemEntity entity);

}

