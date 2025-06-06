package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.enums.CartStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal grandTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CartStatus status;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) // Changed "product" to "cart"
    private List<CartItemEntity> cartItems = new ArrayList<>();
}
