package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID id;

    @Column(name = "sale_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime saleDate;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "final_cost_amount")
    private BigDecimal finalCostAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", unique = true)
    private CartEntity sourceCart;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemEntity> saleItems = new ArrayList<>();

    // Opcional: Campos adicionales para la venta
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "customer_id")
    // private CustomerEntity customer; // Si la venta está asociada a un cliente registrado

    // @Column(name = "payment_method")
    // private String paymentMethod; // Ej: "CASH", "CARD", "TRANSFER"

    // @Column(name = "transaction_id")
    // private String transactionId; // ID de la transacción del sistema de pago


    @PrePersist
    @PreUpdate
    public void calculateSaleTotals() {
        BigDecimal calculatedTotal = BigDecimal.ZERO;
        BigDecimal calculatedCost = BigDecimal.ZERO;

        for (SaleItemEntity item : saleItems) {
            calculatedTotal = calculatedTotal.add(item.getSubTotal());
            calculatedCost = calculatedCost.add(item.getCostTotal());
        }

        this.totalAmount = calculatedTotal.subtract(this.discountAmount);
        this.finalCostAmount = calculatedCost;
    }
}