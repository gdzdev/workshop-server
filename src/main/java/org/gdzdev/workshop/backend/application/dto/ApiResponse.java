package org.gdzdev.workshop.backend.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private String status;
    private T response;
}
