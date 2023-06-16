package com.api.citasync.dto;

import com.api.citasync.models.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Contiene los atributos de la cita consultada.
 */
@Builder
@Tag(name = "Actualizar Cita")
public record CitaRespuestaDto(

        @Schema(description = "El id de la cita")
        Long id,
        @Schema(description = "El nombre de la cita")
        String nombre,
        @Schema (description = "La fecha y hora de la cita")
        LocalDateTime dateTime,
        @Schema(description = "La duración de la cita")
        Integer duracion,
        @Schema (description = "La ubicación de la cita")
        String ubicacion,
        @Schema(description = "Los detalles de la cita")
        String detalles,
        @Schema(description = "El estado de la cita")
        Estado estado) implements Serializable {

        @Serial
        private static final long serialVersionUID = -4987463355548545L;
}
