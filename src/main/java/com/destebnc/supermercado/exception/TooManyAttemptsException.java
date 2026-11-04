package com.destebnc.supermercado.exception;

/** Se traduce a 429 Too Many Requests. */
public class TooManyAttemptsException extends RuntimeException {
    public TooManyAttemptsException() {
        super("Demasiados intentos de inicio de sesión fallidos. Por favor, inténtelo de nuevo más tarde.");
    }
}
