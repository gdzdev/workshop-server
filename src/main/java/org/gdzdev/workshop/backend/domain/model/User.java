package org.gdzdev.workshop.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gdzdev.workshop.backend.domain.enums.UserRole;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter @Setter
public class User {
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


