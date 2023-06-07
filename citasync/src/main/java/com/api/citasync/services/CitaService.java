package com.api.citasync.services;

import com.api.citasync.dto.DtoActualizarCita;
import com.api.citasync.dto.CitaDto;
import com.api.citasync.exceptions.CitaExistenteExceptcion;
import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import com.api.citasync.repositories.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public CitaDto crearCita(CitaDto datosCita) {
        if (citaRepository.existsByFechaAndHoraAndEstadoIn(datosCita.fecha(),
                datosCita.hora(), List.of(Estado.AGENDADA, Estado.REAGENDADA))){
            throw new CitaExistenteExceptcion("Ya existe una cita en esta Fecha y hora");
        }
        Cita cita = CitaDto.toEntity(datosCita);
        cita.setEstado(Estado.AGENDADA);
        return CitaDto.fromEntity(citaRepository.save(cita));
    }

    public List<CitaDto> ListarCitas(){
        return citaRepository.findAllByEstadoNotIn(List.of(Estado.FINALIZADA, Estado.CANCELADA))
                .stream()
                .map(CitaDto::fromEntity)
                .toList();
    }

    public CitaDto actualizarCita(Long id, DtoActualizarCita datosCita){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        Cita cita = citaRepository.getReferenceById(id);
        cita.actualizarDatos(datosCita);
        return CitaDto.fromEntity(cita);
    }

    public void cancelarCita(Long id){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        Cita cita = citaRepository.getReferenceById(id);
        cita.setEstado(Estado.CANCELADA);
    }
}
