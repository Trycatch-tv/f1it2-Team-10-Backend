package com.api.citasync.dto;

import com.api.citasync.models.Cita;

import java.sql.Time;
import java.util.Date;
public record CitaActualizarDto(
    String nombre,
    Date fecha,
    Time hora,
    Integer duracion,
    String ubicacion,
    String detalles,
    String estado
) {
    public CitaActualizarDto(Cita cita) {
        this(cita.getNombre(), cita.getFecha(), cita.getHora(), cita.getDuracion(), cita.getUbicacion(), cita.getDetalles(), cita.getEstado());

    }
}
