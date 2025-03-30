package org.gdzdev.workshop.backend.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.model.ItemType;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(nullable = false)
    private int quantity;

    private BigDecimal subTotal;

    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private ServicingEntity servicing;

}
