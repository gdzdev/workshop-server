package org.gdzdev.workshop.backend.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.application.annotations.passwordMatcheValidator.PasswordMatches;
import org.gdzdev.workshop.backend.domain.enums.UserRole;

@AllArgsConstructor
@Setter
@Getter
@PasswordMatches
public class SignupDto {
    private java.util.UUID id;

    private String code;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres.")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(min = 5, max = 100, message = "El apellido debe tener entre 5 y 100 caracteres.")
    private String lastName;

    @NotNull(message = "El email no puede ser nulo.")
    @Email(message = "El email no es valido.")
    private String email;

    @NotNull(message = "La contraseña no puede ser nulo.")
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 5 y 100 caracteres.")
    private String password;

    @NotNull(message = "El campo confirmar contraseña no puede ser nulo.")
    @Size(min = 5, max = 100, message = "El campo confirmar contraseña debe tener entre 5 y 100 caracteres.")
    private String confirmPassword;
}
