package org.gdzdev.workshop.backend.infrastructure.adapter.mapper;

import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseRequest;
import org.gdzdev.workshop.backend.application.dto.purchase.PurchaseResponse;
import org.gdzdev.workshop.backend.domain.model.CartItem;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.PurchasesEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseEntityMapperImpl {
    private CartItemMapper cartItemMapper;

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

    public  List<PurchaseResponse> entityListToResponseList(List<PurchasesEntity> entities) {
        return entities.stream()
                .map(this::entityToResponse)
                .toList();
    }

    public  PurchasesEntity requestToEntity(PurchaseRequest request) {
        if (request == null) return null;

        PurchasesEntity entity = new PurchasesEntity();
        List<CartItemEntity> items = request.getCartItems()
                .stream()
                .map(this.cartItemMapper::modelToEntity)
                .toList();

        entity.setProvider(request.getProvider());
        entity.setTotalPrice(request.getTotalPrice());
        entity.setCartItems(items);
        entity.setCount(request.getCartItems().toArray().length);

        return entity;
    }
}



//PurchasesEntity modelToEntity(Purchase model);
//PurchasesEntity responseToEntity(PurchaseResponse response);
//Purchase responseToModel(PurchaseResponse response);
//Purchase entityToModel(PurchasesEntity entity);
//PurchaseResponse entityToResponse(PurchasesEntity entity);
//PurchasesEntity requestToEntity(PurchaseRequest request);