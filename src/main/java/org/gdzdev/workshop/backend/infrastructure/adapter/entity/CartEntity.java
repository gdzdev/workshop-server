package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.enums.CartStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal grandTotal = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CartStatus status;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = CartStatus.ACTIVE;
        calculateGrandTotal();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        calculateGrandTotal();
    }

    public void calculateGrandTotal() {
        this.grandTotal = this.cartItems.stream()
                .map(CartItemEntity::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}