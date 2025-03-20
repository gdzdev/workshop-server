package org.gdzdev.workshop.backend.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_datails")
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SaleEntity sale;

    @ManyToOne
    private ProductEntity product;

    private int quantity;
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal subTotal;
}
