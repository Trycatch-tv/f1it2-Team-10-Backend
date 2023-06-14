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

@RestControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> gestionarException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream()
                .map(f -> ErrorMessage.builder().
                        campo(f.getField()).
                        mensaje(f.getDefaultMessage()).build())
                .toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> gestionarException(EntityNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(ErrorMessage.builder().mensaje(e.getMessage()).build());
    }

    @ExceptionHandler(CitaExistenteException.class)
    public ResponseEntity<ErrorMessage> gestionarException(CitaExistenteException e){
        return ResponseEntity.status(CONFLICT).body(ErrorMessage.builder().mensaje(e.getMessage()).build());
    }


    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorMessage> gestionarException(DateTimeParseException e){
        return ResponseEntity.status(CONFLICT)
                .body(ErrorMessage.builder()
                        .mensaje("El formato de fecha es incorrecto. Debe ser en formato yyyy-MM-dd HH:mm:ss")
                        .build());
    }

}
