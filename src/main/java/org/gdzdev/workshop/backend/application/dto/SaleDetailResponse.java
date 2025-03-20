package org.gdzdev.workshop.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleDetailResponse {

    private CartProduct product;
    private int quantity;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonProperty("sub_total")
    private BigDecimal subTotal;
}
