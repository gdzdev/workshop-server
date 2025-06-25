package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseRequest;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseResponse;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.domain.model.Purchase;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.PurchasesEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseEntityMapperImpl {
    private final CartItemMapper cartItemMapper;

    public PurchaseResponse entityToResponse(PurchasesEntity purchase) {
        if (purchase == null) return null;

        Long id = purchase.getId();
        String provider = purchase.getProvider();
        int count = purchase.getCount();
        BigDecimal totalPrice = purchase.getTotalPrice();
        BigDecimal discount = purchase.getDiscount();
        List<CartItemEntity> cartItems = purchase.getCartItems();
        LocalDateTime createdAt = purchase.getCreatedAt();
        LocalDateTime updatedAt = purchase.getUpdatedAt();

        List<CartItem> items = cartItems
                .stream()
                .map(cartItemMapper::toDomain)
                .toList();

        return new PurchaseResponse(
                id,
                provider,
                count,
                totalPrice,
                discount,
                items,
                createdAt,
                updatedAt
        );
    }

    public Purchase toModel(PurchasesEntity entity) {
        if (entity == null) return null;

        List<CartItem> cartItems = entity.getCartItems().stream().map(this.cartItemMapper::toDomain).toList();

        return Purchase
                .builder()
                .id(entity.getId())
                .provider(entity.getProvider())
                .count(entity.getCount())
                .discount(entity.getDiscount())
                .totalPrice(entity.getTotalPrice())
                .cartItems(cartItems)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<PurchaseResponse> entityListToResponseList(List<PurchasesEntity> entities) {
        if (entities.isEmpty()) return new ArrayList<>();

        return entities.stream()
                .map(this::entityToResponse)
                .toList();
    }

    public PurchasesEntity requestToEntity(PurchaseRequest request) {
        if (request == null) return null;

        PurchasesEntity entity = new PurchasesEntity();

        List<CartItemEntity> items = request.getCartItems()
                .stream()
                .map(cartItemMapper::toEntity)
                .toList();

        entity.setProvider(request.getProvider());
        entity.setTotalPrice(request.getTotalPrice());
        entity.setDiscount(request.getDiscount());
        entity.setCartItems(items);
        entity.setCount(request.getCartItems().size());

        return entity;
    }

    public PurchaseResponse toResponse(Purchase model) {
        if (model == null) return null;

        return new PurchaseResponse(
                model.getId(),
                model.getProvider(),
                model.getCount(),
                model.getTotalPrice(),
                model.getDiscount(),
                model.getCartItems(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}

//PurchasesEntity modelToEntity(Purchase model);
//PurchasesEntity responseToEntity(PurchaseResponse response);
//Purchase responseToModel(PurchaseResponse response);
//PurchaseResponse entityToResponse(PurchasesEntity entity);
//PurchasesEntity requestToEntity(PurchaseRequest request);