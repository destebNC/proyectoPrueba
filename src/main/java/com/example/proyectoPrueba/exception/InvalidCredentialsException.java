package com.example.proyectoPrueba.exception;

/** Se traduce a 401 Unauthorized. */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Usuario o contraseña incorrectos");
    }
}
