package com.api.citasync.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> MethodArgumentNotValidException(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(f-> ErrorMessage.builder().
                        campo(f.getField()).
                        mensaje(f.getDefaultMessage()).build())
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> EntityNotFoundException(EntityNotFoundException e){
        ErrorMessage error = ErrorMessage.builder().mensaje(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CitaExistenteExceptcion.class)
    public ResponseEntity<?> CitaExistenteExcepcion(CitaExistenteExceptcion e){
        ErrorMessage error = ErrorMessage.builder().mensaje(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> DateTimeParseException(DateTimeParseException e){
        String mensaje = "El formato de fecha es incorrecto. Debe ser en formato yyyy-MM-dd.";
        ErrorMessage error = ErrorMessage.builder().mensaje(mensaje).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
