package org.gdzdev.workshop.backend.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.model.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "sales")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    private BigDecimal grandTotal;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetailEntity> saleDetails;
}
