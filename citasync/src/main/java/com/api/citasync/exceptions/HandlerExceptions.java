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
    public ResponseEntity<List<ErrorMessage>> gestionarException(MethodArgumentNotValidException e){
        List<ErrorMessage> errores = e.getFieldErrors().stream().map(f -> ErrorMessage.builder().
                        campo(f.getField()).
                        mensaje(f.getDefaultMessage()).build())
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> gestionarException(EntityNotFoundException e){
        ErrorMessage error = ErrorMessage.builder().mensaje(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CitaExistenteException.class)
    public ResponseEntity<ErrorMessage> gestionarException(CitaExistenteException e){
        ErrorMessage error = ErrorMessage.builder().mensaje(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorMessage> gestionarException(DateTimeParseException e){
        String mensaje = "El formato de fecha es incorrecto. Debe ser en formato yyyy-MM-dd.";
        ErrorMessage error = ErrorMessage.builder().mensaje(mensaje).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
