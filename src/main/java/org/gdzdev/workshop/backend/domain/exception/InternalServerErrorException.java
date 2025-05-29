package org.gdzdev.workshop.backend.domain.exception;

public class InternalServerErrorException extends RuntimeException{
    public  InternalServerErrorException( String message ) { super(message); }
}
