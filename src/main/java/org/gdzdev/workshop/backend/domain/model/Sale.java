package org.gdzdev.workshop.backend.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Sale {

    private Long id;
    private String customerName;
    private LocalDateTime saleDate;
    private BigDecimal grandTotal;
    private PaymentStatus paymentStatus;

    @Builder.Default
    private List<SaleDetail> saleDetails = new ArrayList<>();

    public void addDetails(List<SaleDetail> saleDetails) {
        this.saleDetails.addAll(saleDetails);
    }
}
