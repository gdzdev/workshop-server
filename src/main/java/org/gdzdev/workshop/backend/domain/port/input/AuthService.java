package org.gdzdev.workshop.backend.domain.port.input;

import org.gdzdev.workshop.backend.application.dto.user.SigninDto;
import org.gdzdev.workshop.backend.infrastructure.rest.exceptions.InvalidJwtException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails loadUserByUsername(String username);
    UserDetails signUp(SigninDto data) throws InvalidJwtException;
}
