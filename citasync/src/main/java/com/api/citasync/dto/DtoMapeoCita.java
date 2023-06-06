package com.api.citasync.dto;

import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;


public record DtoMapeoCita(
        Long id,
        @NotBlank(message = "el nombre es obligatorio")
        String nombre,
        @NotNull(message = "la fecha es obligatoria")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha,
        @NotNull(message = "La hora es obligatoria")
        @DateTimeFormat(pattern = "HH:mm")
        Time hora,
        @NotNull(message = "La duración es obligatoria")
        Integer duracion,
        @NotBlank(message = "el nombre es obligatorio")
        String ubicacion,
        @NotNull
        @Size(min = 10, max = 1000)
        String detalles,
        @NotNull
        Estado estado) {

    public DtoMapeoCita(Cita cita){
        this(cita.getId(), cita.getNombre(), cita.getFecha(), cita.getHora(),
                cita.getDuracion(), cita.getUbicacion(), cita.getDetalles(), cita.getEstado());
    }
}
