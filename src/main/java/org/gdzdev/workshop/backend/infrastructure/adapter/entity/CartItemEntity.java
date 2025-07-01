package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table(name = "cart_items")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @PrePersist
    @PreUpdate
    public void calculateSubTotal() {
        if (this.unitPrice != null && this.quantity > 0) {
            this.subTotal = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
        } else {
            this.subTotal = BigDecimal.ZERO;
        }
    }
}