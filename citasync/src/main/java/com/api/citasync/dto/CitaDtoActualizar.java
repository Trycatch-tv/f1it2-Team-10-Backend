package com.api.citasync.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.sql.Time;
import java.time.LocalDate;

@Tag(name = "Actualizar Cita")
public record CitaDtoActualizar(
        @Schema (description = "La fecha de la cita")
        LocalDate fecha,
        @Schema(description = "La hora de la cita")
        Time hora,
        @Schema(description = "La duración de la cita")
        Integer duracion,
        @Schema (description = "La ubicación de la cita")
        String ubicacion,
        @Schema(description = "Los detalles de la cita")
        String detalles) {

}
