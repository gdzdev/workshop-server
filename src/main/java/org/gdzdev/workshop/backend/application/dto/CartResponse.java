package org.gdzdev.workshop.backend.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

    private String message;
}
