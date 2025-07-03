package org.gdzdev.workshop.backend.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.user.RefreshTokenDto;
import org.gdzdev.workshop.backend.application.dto.user.SigninDto;
import org.gdzdev.workshop.backend.application.dto.user.SignupDto;
import org.gdzdev.workshop.backend.application.usecase.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl _authService;
    private final AuthenticationManager _authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signUp(@RequestBody @Valid SignupDto data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .builder()
                        .response(this._authService.signUp(data))
                        .status("success")
                        .build()
                );
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<?>> signIn(@RequestBody @Valid SigninDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getName(), data.getPassword());
        var authUser = this._authenticationManager.authenticate(usernamePassword);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse
                        .builder()
                        .response(this._authService.signing(data, authUser))
                        .status("success")
                        .build()
                );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<?>> refreshToken(@RequestBody @Valid RefreshTokenDto token) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse
                        .builder()
                        .response(this._authService.validateToken(token))
                        .status("success")
                        .build()
                );
    }
}