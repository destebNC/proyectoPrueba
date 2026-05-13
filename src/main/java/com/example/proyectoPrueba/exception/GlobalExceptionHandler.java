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

    // Este método atrapa cualquier error (Exception) que ocurra en el servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleAllErrors(Exception ex, HttpServletRequest request) {

        // Creamos el objeto ProblemDetail (Estándar de Spring 6+)
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Se ha producido un error interno. Por favor, contacte con soporte."
        );

        // Personalizamos los campos según nuestro openapi.yaml
        problem.setTitle("Error interno del servidor");
        problem.setType(URI.create("https://ejemplo.com/errores/error-interno"));
        problem.setInstance(URI.create(request.getRequestURI())); // Muestra en qué URL falló

        // Opcional: añadimos el mensaje real del error para depurar
        problem.setProperty("debug_message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationErrors(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Datos de entrada inválidos");
        problem.setTitle("Error de validación");

        // Recogemos todos los errores de los campos y los juntamos
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();

        problem.setProperty("errores", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }
}