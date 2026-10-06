package com.destebnc.supermercado.exception;

/** Se traduce a 409 Conflict (por ejemplo, usuario ya registrado). */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
