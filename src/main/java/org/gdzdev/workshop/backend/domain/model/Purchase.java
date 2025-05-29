package org.gdzdev.workshop.backend.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CartItemEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// this method should be implemented for business client purchase to a provider

@Setter
@Getter
@Builder
public class Purchase {
    private String provider;
    private int count;
    private BigDecimal discount;
    private BigDecimal totalPrice;

    @Builder.Default
    private List<CartItemEntity> cartItems = new ArrayList<>();
//    @Builder.Default
//    private List<ProductEntity> cartProducts = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BigDecimal calculateTotalPrice() {
        BigDecimal itemsTotal = cartItems.stream()
                .map(CartItemEntity::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal productsTotal = cartProducts.stream()
//                .map(ProductEntity::getPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        this.totalPrice = itemsTotal.add(productsTotal);
        this.totalPrice = itemsTotal;
        return this.totalPrice;
    }

    public double calculateTotalDiscount() {
        // TODO: Investigate how make a discount to items and products cart
        throw new RuntimeException("Method not implemented");
    }
}
