package org.gdzdev.workshop.backend.application.dto.user;

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
    private String password;
    private String number;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
