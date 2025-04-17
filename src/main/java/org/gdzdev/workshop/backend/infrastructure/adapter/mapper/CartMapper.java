package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import org.gdzdev.workshop.backend.domain.model.Cart;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public Cart toDomain(CartEntity entity) {
        List<CartItem> items = entity.getCartItems().stream()
                .map(cartItemMapper::toDomain)
                .collect(Collectors.toList());

        return Cart.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .grandTotal(entity.getGrandTotal())
                .cartItems(items)
                .build();
    }

    public CartEntity toEntity(Cart domain) {
        CartEntity entity = new CartEntity();
        entity.setId(domain.getId());
        entity.setStatus(domain.getStatus());
        entity.setGrandTotal(domain.getGrandTotal());

        List<CartItemEntity> itemEntities = domain.getCartItems().stream()
                .map(item -> cartItemMapper.toEntity(item, entity))
                .toList();

        entity.getCartItems().clear();
        entity.getCartItems().addAll(itemEntities);
        return entity;
    }
}
