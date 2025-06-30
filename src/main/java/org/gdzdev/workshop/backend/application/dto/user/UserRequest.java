package org.gdzdev.workshop.backend.application.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.enums.UserRole;

@AllArgsConstructor
@Setter @Getter
public class UserRequest {
    private java.util.UUID id;

    private String code;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres.")
    private String name;

    @NotNull(message = "El número no puede ser nulo.")
    @Pattern(regexp = "\\d{8}", message = "El número debe ser de 8 dígitos numéricos.")
    private String number;

    @NotNull(message = "La contraseña no puede ser nulo.")
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 5 y 100 caracteres.")
    private String password;

    @NotNull(message = "El rol no puede ser nulo.")
    private UserRole role;
}
