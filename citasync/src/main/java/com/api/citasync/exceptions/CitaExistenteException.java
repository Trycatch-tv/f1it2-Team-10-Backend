package com.api.citasync.exceptions;

import java.io.Serial;

public class CitaExistenteException extends Exception{

    @Serial
    private static final long serialVersionUID = 6432137596205315230L;

    public CitaExistenteException(String message){
        super(message);
    }
}
