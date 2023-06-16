package com.api.citasync.exceptions;

import java.io.Serial;

/**
 * Exception que se lanza cuando se intenta crear una cita que ya existe.
 */
public class CitaExistenteException extends Exception{

    @Serial
    private static final long serialVersionUID = 6432137596205315230L;

    /**
     * Constructor de la exception.
     * @param message Mensaje de la exception.
     */
    public CitaExistenteException(String message){
        super(message);
    }
}
