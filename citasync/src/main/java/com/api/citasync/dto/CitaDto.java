package com.api.citasync.dto;

import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;


@Builder
@Tag(name = "Cita")
public record CitaDto(
        Long id,
        @Schema(description = "El nombre de la cita")
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @Schema(description = "La fecha de la cita")
        @NotNull(message = "La fecha es obligatoria")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha,
        @Schema(description = "La hora de la cita")
        @NotNull(message = "La hora es obligatoria")
        @DateTimeFormat(pattern = "HH:mm")
        Time hora,
        @Schema(description = "La duración de la cita")
        @NotNull(message = "La duración es obligatoria")
        Integer duracion,
        @Schema(description = "La ubicación de la cita")
        @NotBlank(message = "El campo de ubicación es obligatorio")
        String ubicacion,
        @Schema(description = "Los detalles de la cita")
        @NotNull
        @Size(min = 10, max = 1000, message= "El tamaño del campo debe estar entre 10 y 1000 caracteres")
        String detalles,
        
        @Schema(description = "El estado de la cita")
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
