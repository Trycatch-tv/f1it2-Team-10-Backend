package com.api.citasync.dto;

import com.api.citasync.models.Cita;

import java.sql.Time;
import java.time.LocalDate;


public record DtoActualizarCita(LocalDate fecha, Time hora, Integer duracion,
                                String ubicacion, String detalles) {

    public DtoActualizarCita(Cita cita){
        this(cita.getFecha(),cita.getHora(),cita.getDuracion(),
                cita.getUbicacion(),cita.getDetalles());
    }
}
