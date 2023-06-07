package com.api.citasync.dto;

import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

@Builder
public record CitaDto(
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
        Estado estado) {

    public static CitaDto fromEntity(Cita cita){
        return CitaDto.builder()
                .id(cita.getId())
                .nombre(cita.getNombre())
                .fecha(cita.getFecha())
                .hora(cita.getHora())
                .duracion(cita.getDuracion())
                .ubicacion(cita.getUbicacion())
                .detalles(cita.getDetalles())
                .estado(cita.getEstado())
                .build();
    }

    public static Cita toEntity(CitaDto cita){
        return Cita.builder()
                .id(cita.id)
                .nombre(cita.nombre)
                .fecha(cita.fecha)
                .hora(cita.hora)
                .duracion(cita.duracion)
                .ubicacion(cita.ubicacion)
                .detalles(cita.detalles)
                .build();

    }
}
