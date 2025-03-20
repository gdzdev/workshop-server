package org.gdzdev.workshop.backend.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    private Long id;
    private String customerName;
    private LocalDateTime saleDate;
    private BigDecimal grandTotal;
    private PaymentStatus paymentStatus;
    private List<SaleDetail> saleDetails;

}
