package com.destebnc.supermercado.exception;

/** Se traduce a 401 Unauthorized. */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Usuario o contraseña incorrectos");
    }
}
