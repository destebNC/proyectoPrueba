package com.example.proyectoPrueba.exception;

/** Se traduce a 404 Not Found. */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
