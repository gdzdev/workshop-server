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

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY) // Use LAZY loading for performance
    @JoinColumn(name = "product_id", nullable = false) // This specifies the foreign key column in cart_items
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY) // Use LAZY loading for performance
    @JoinColumn(name = "cart_id") // Optional: If a CartItem can exist without being in a cart (e.g., only in a purchase)
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY) // New relationship to PurchasesEntity
    @JoinColumn(name = "purchase_id") // This will create a 'purchase_id' column in the 'cart_items' table
    private PurchasesEntity purchase; // This maps to the 'purchase' field in PurchasesEntity

    // You might want to add convenience methods for calculation
    @PrePersist
    @PreUpdate
    public void calculateSubTotal() {
        if (this.unitPrice != null && this.quantity > 0) {
            this.subTotal = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
        }
    }
}
