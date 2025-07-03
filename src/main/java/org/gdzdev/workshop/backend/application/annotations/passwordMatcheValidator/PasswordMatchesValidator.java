package org.gdzdev.workshop.backend.application.annotations.passwordMatcheValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.gdzdev.workshop.backend.application.dto.user.SignupDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // Opcional: Se puede usar para inicializar el validador con parámetros de la anotación.
        // En este caso, no es necesario.
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (!(obj instanceof SignupDto)) {
            return false;
        }

        SignupDto user = (SignupDto) obj;

        boolean isValid = user.getPassword() != null &&
                user.getConfirmPassword() != null &&
                user.getPassword().equals(user.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}