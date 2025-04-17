package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductEntityMapper productMapper;

    public CartItem toDomain(CartItemEntity entity) {
        return CartItem.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .subtotal(entity.getSubTotal())
                .unitPrice(entity.getUnitPrice())
                .product(productMapper.toModel(entity.getProduct()))
                .build();
    }

    public CartItemEntity toEntity(CartItem domain, CartEntity cartEntity) {
        CartItemEntity entity = new CartItemEntity();
        entity.setId(domain.getId());
        entity.setQuantity(domain.getQuantity());
        entity.setSubTotal(domain.getSubTotal());
        entity.setUnitPrice(domain.getUnitPrice());
        entity.setProduct(productMapper.toEntity(domain.getProduct()));
        entity.setCart(cartEntity);
        return entity;
    }
}
