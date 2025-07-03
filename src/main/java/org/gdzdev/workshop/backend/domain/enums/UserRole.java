package org.gdzdev.workshop.backend.domain.enums;

public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}