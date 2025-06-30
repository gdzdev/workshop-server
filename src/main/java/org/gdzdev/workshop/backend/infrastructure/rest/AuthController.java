package org.gdzdev.workshop.backend.infrastructure.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gdzdev.workshop.backend.application.dto.ApiResponse;
import org.gdzdev.workshop.backend.application.dto.user.JwtDto;
import org.gdzdev.workshop.backend.application.dto.user.UserRequest;
import org.gdzdev.workshop.backend.application.usecase.AuthServiceImpl;
import org.gdzdev.workshop.backend.application.usecase.shared.JwtServiceImpl;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = { "https://simplified-inventory-management.vercel.app", "*" })
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager _authenticationManager;
    private final AuthServiceImpl _authService;
    private final JwtServiceImpl _jwtService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signUp(@RequestBody @Valid UserRequest data) {
        try {
            UserDetails user = this._authService.signUp(data);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ApiResponse
                            .builder()
                            .response(user)
                            .status("success")
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse
                            .builder()
                            .status("failed")
                            .response(e.getMessage())
                            .build()
                    );
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<?>> signIn(@RequestBody @Valid UserRequest data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getName(), data.getPassword());

            var authUser = this._authenticationManager.authenticate(usernamePassword);
            var accessToken = this._jwtService.generateAccessToken((UserEntity) authUser.getPrincipal());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse
                    .builder()
                    .response(new JwtDto(accessToken))
                    .status("success")
                    .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse
                    .builder()
                    .response(e.getMessage())
                    .status("failed")
                    .build()
            );
        }
    }
}