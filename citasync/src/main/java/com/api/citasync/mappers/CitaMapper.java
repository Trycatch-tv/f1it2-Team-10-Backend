package com.api.citasync.mappers;

import com.api.citasync.dto.CitaRespuestaDto;
import com.api.citasync.dto.CitaSolicitudDto;
import com.api.citasync.models.Cita;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CitaMapper {

    public static CitaRespuestaDto toCitaRespuestaDto(Cita cita){
        return CitaRespuestaDto.builder()
                .id(cita.getId())
                .nombre(cita.getNombre())
                .dateTime(cita.getFecha().atTime(cita.getHora()))
                .duracion(cita.getDuracion())
                .ubicacion(cita.getUbicacion())
                .detalles(cita.getDetalles())
                .estado(cita.getEstado())
                .build();
    }

    public static Cita toEntity(CitaSolicitudDto cita){
        return Cita.builder()
                .nombre(cita.nombre())
                .fecha(cita.dateTime().toLocalDate())
                .hora(cita.dateTime().toLocalTime())
                .duracion(cita.duracion())
                .ubicacion(cita.ubicacion())
                .detalles(cita.detalles())
                .build();

    }
}
