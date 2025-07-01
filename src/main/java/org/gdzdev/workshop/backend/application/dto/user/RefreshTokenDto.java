package org.gdzdev.workshop.backend.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RefreshTokenDto {
    @NotBlank(message = "El token no puede estar vacío.")
    private String token;
}
