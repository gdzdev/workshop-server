package org.gdzdev.workshop.backend.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// this method should be implemented for business client purchase to a provider

@Setter
@Getter
@Builder
public class Purchases {
    private int count;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private List<String> products;

    @Builder.Default
    public List<CartItem> cartItems = new ArrayList<>();
    @Builder.Default
    public List<CartProduct> cartProducts = new ArrayList<>();

    public BigDecimal calculateTotalPrice() {
        BigDecimal itemsTotal = cartItems.stream()
                .map(CartItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal productsTotal = cartProducts.stream()
                .map(CartProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalPrice = itemsTotal.add(productsTotal);
        return this.totalPrice;
    }

    public double calculateTotalDiscount() {
        // TODO: Investigate how make a discount to items and products cart
        throw new RuntimeException("Method not implemented");
    }
}
