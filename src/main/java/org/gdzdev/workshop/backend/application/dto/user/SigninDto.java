package org.gdzdev.workshop.backend.application.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class SigninDto {
    private java.util.UUID id;

    private String code;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres.")
    private String name;

    @NotNull(message = "El email no puede ser nulo.")
    @Email(message = "El email no es valido.")
    private String email;

    @NotNull(message = "La contraseña no puede ser nulo.")
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 5 y 100 caracteres.")
    private String password;
}
