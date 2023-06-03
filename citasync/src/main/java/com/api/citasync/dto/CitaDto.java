package com.api.citasync.dto;
import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

public record CitaDto (

        Long id,
// The name of the Cita.
        @NotNull String nombre,

// The date of the Cita.
        @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,

// The time of the Cita.
        @NotNull @DateTimeFormat(pattern = "HH:mm") Time hora,

// The duration of the Cita in minutes.
        @Min(1) Integer duracion,

// The location of the Cita.
        @NotBlank String ubicacion,

// The details of the Cita.
        @Size(min = 10, max = 1000) String detalles,

// The state of the Cita.
        @NotNull Estado estado

) {

    //contructor con parametros para crear un objeto Cita
    public CitaDto(Cita cita) {
        this(cita.getId(), cita.getNombre(), cita.getFecha(), cita.getHora(), cita.getDuracion(), cita.getUbicacion(), cita.getDetalles(), Estado.valueOf(cita.getEstado()));
    }
}
