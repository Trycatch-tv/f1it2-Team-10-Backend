package com.api.citasync.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;


/**
 * Clase que representa el manejo de excepciones de la aplicación.
 */
@RestControllerAdvice
public class HandlerExceptions {

    /**
     * Maneja las excepciones de validación de los parametros de entrada.
     *
     * @param e exception de validación de los parametros de entrada.
     * @return una respuesta HTTP con los errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> gestionarException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream()
                .map(f -> ErrorMessage.builder().
                        campo(f.getField()).
                        mensaje(f.getDefaultMessage()).build())
                .toList());
    }

    /**
     * Maneja las excepciones de la entidad no encontrada.
     *
     * @param e exception de la entidad no encontrada.
     * @return una respuesta HTTP con el error de la entidad no encontrada.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> gestionarException(EntityNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(ErrorMessage.builder().mensaje(e.getMessage()).build());
    }


    /**
     * Maneja las excepciones de cita existente.
     *
     * @param e exception de cita existente.
     * @return una respuesta HTTP con el error de la cita existente.
     */
    @ExceptionHandler(CitaExistenteException.class)
    public ResponseEntity<ErrorMessage> gestionarException(CitaExistenteException e){
        return ResponseEntity.status(CONFLICT).body(ErrorMessage.builder().mensaje(e.getMessage()).build());
    }


    /**
     * Maneja las excepciones de formato de fecha incorrecto.
     *
     * @param e exception de formato de fecha incorrecto.
     * @return una respuesta HTTP con el error de formato de fecha incorrecto.
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorMessage> gestionarException(DateTimeParseException e){
        return ResponseEntity.status(CONFLICT)
                .body(ErrorMessage.builder()
                        .mensaje("El formato de fecha es incorrecto. Debe ser en formato yyyy-MM-dd HH:mm:ss")
                        .build());
    }
}

