package com.api.citasync.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Contiene los atributos de la cita que se solicita.
 */
@Builder
@Tag(name = "Cita")
public record CitaSolicitudDto(
        Long id,
        @Schema(description = "El nombre de la cita")
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @Schema(description = "La fecha de la cita")
        @NotNull(message = "La fecha es obligatoria")
        LocalDateTime dateTime,
        @Schema(description = "La duración de la cita")
        @NotNull(message = "La duración es obligatoria")
        Integer duracion,
        @Schema(description = "La ubicación de la cita")
        @NotBlank(message = "La ubicación es obligatoria")
        String ubicacion,
        @Schema(description = "Los detalles de la cita")
        @NotNull()
        @Size(min = 10, max = 1000, message= "El tamaño del campo debe estar entre 10 y 1000 caracteres")
        String detalles) implements Serializable {

        @Serial
        private static final long serialVersionUID = -6356993035548545L;

}
