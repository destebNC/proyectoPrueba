package com.example.proyectoPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Manejo de Excepciones de Lógica de Negocio (Usuario no encontrado, Contraseña mala, etc.)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handleBusinessErrors(RuntimeException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, // Código 400
                ex.getMessage() // Aquí sale el mensaje exacto: "Contraseña incorrecta" o "Inventario no encontrado"
        );
        problem.setTitle("Error de lógica de negocio");
        problem.setType(URI.create("https://ejemplo.com/errores/bad-request"));
        problem.setInstance(URI.create(request.getRequestURI()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    // 2. Manejo de Errores de Validación (Como un @Email mal puesto, o un @NotNull vacío)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Datos de entrada inválidos");
        problem.setTitle("Error de validación");
        problem.setInstance(URI.create(request.getRequestURI()));

        // Recogemos todos los errores de los campos y los juntamos
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();

        problem.setProperty("errores", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    // 3. Manejo de TODOS los demás errores imprevistos (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleAllErrors(Exception ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Se ha producido un error interno. Por favor, contacte con soporte."
        );

        problem.setTitle("Error interno del servidor");
        problem.setType(URI.create("https://ejemplo.com/errores/error-interno"));
        problem.setInstance(URI.create(request.getRequestURI()));

        // Añadimos el mensaje real del error para depurar
        problem.setProperty("debug_message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problem);
    }
}