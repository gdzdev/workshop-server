package org.gdzdev.workshop.backend.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.gdzdev.workshop.backend.domain.enums.UserRole;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserResponse {
    private java.util.UUID id;
    private String code;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}