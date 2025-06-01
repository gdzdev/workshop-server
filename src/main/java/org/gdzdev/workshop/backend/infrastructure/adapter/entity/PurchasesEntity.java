package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@Table(name = "purchases")
public class PurchasesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private BigDecimal discount;

    // This defines the one-to-many relationship with CartItemEntity.
    // 'mappedBy = "purchase"' indicates that the 'purchase' field in CartItemEntity
    // is the owner of this relationship.
    // cascade = CascadeType.ALL ensures that operations (like persist, remove) on PurchasesEntity
    // are cascaded to its associated CartItemEntity objects.
    // orphanRemoval = true means if a CartItem is removed from the 'cartItems' collection,
    // it will be deleted from the database.
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Helper method to add CartItemEntity to the list
    public void addCartItem(CartItemEntity cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setPurchase(this); // Set the back-reference
    }

    // Helper method to remove CartItemEntity from the list
    public void removeCartItem(CartItemEntity cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setPurchase(null); // Remove the back-reference
    }
}
