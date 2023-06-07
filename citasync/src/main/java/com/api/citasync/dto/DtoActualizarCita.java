package com.api.citasync.dto;

import java.sql.Time;
import java.time.LocalDate;


public record DtoActualizarCita(LocalDate fecha, Time hora, Integer duracion,
                                String ubicacion, String detalles) {

}
