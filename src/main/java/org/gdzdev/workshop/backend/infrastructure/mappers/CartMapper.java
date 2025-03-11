package org.gdzdev.workshop.backend.infrastructure.mappers;

import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.entities.CartItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartItem toModel(CartItemEntity entity);
    CartItemEntity toEntity(CartItem model);
}
