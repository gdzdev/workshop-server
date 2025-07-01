package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_items")
public class SaleItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_price_at_sale", nullable = false)
    private BigDecimal unitPriceAtSale;

    @Column(name = "cost_price_at_sale", nullable = false)
    private BigDecimal costPriceAtSale;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(name = "cost_total", nullable = false)
    private BigDecimal costTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEntity sale;

    @PrePersist
    @PreUpdate
    public void calculateTotals() {
        if (this.unitPriceAtSale != null && this.quantity > 0) {
            this.subTotal = this.unitPriceAtSale.multiply(BigDecimal.valueOf(this.quantity));
        } else {
            this.subTotal = BigDecimal.ZERO;
        }

        if (this.costPriceAtSale != null && this.quantity > 0) {
            this.costTotal = this.costPriceAtSale.multiply(BigDecimal.valueOf(this.quantity));
        } else {
            this.costTotal = BigDecimal.ZERO;
        }
    }
}