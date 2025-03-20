package org.gdzdev.workshop.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.gdzdev.workshop.backend.domain.model.PaymentStatus;

@Data
public class SaleRequest {

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;
}
