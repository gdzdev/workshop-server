package org.gdzdev.workshop.backend.infrastructure.rest.exceptions;

public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String message) {
        super(message);
    }
}
