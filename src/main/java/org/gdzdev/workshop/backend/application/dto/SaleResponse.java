package org.gdzdev.workshop.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.gdzdev.workshop.backend.domain.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleResponse {

    private Long id;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("sale_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleDate;

    @JsonProperty("grand_total")
    private BigDecimal grandTotal;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    @JsonProperty("sale_details")
    private List<SaleDetailResponse> saleDetails;
}
