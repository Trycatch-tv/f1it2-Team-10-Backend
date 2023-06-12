package com.api.citasync.services;

import com.api.citasync.dto.CitaDtoActualizar;
import com.api.citasync.dto.CitaDto;
import com.api.citasync.exceptions.CitaExistenteException;
import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import com.api.citasync.repositories.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private final String CITA_NO_ENCONTRADA = "No fue encontrada la cita con el ID: ";
    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    /**
     * Crear una nueva cita en la base de datos
     * @param datosCita cuerpo de la solicitud JSON
     * @return CitaDto con la cita creada como una respuesta JSON
     */
    public CitaDto crearCita(CitaDto datosCita) {
        try {
            if (citaRepository.existsByFechaAndHoraAndEstadoIn(datosCita.fecha(),
                    datosCita.hora(), List.of(Estado.AGENDADA, Estado.REAGENDADA))){
                throw new CitaExistenteException("Ya existe una cita en esta Fecha y hora");
            }
        } catch (CitaExistenteException e) {
            System.out.println(e.getMessage());
        }
        Cita cita = CitaDto.toEntity(datosCita);
        cita.setEstado(Estado.AGENDADA);
        return CitaDto.fromEntity(citaRepository.save(cita));
    }

    /**
     * Lista todas las citas
     * @return List<CitaDto> con todas las citas
     */
    public List<CitaDto> listarCitas(){
        return citaRepository.findAllByEstadoNotIn(List.of(Estado.FINALIZADA, Estado.CANCELADA))
                .stream()
                .map(CitaDto::fromEntity)
                .toList();
    }

    /**
     * Buscar una cita en la base de datos
     * @param id id de la cita
     * @return CitaDto con la cita encontrada con los datos
     */
    public CitaDto buscarCita(Long id){
        return CitaDto.fromEntity(citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CITA_NO_ENCONTRADA + id)));
    }

    /**
     * Actualizar una cita en la base de datos
     * @param id id de la cita
     * @param datosCita cuerpo de la solicitud JSON
     * @return CitaDto con la cita actualizada con los datos
     */
    public CitaDto actualizarCita(Long id, CitaDtoActualizar datosCita){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CITA_NO_ENCONTRADA + id));
        Cita cita = citaRepository.getReferenceById(id);
        if (datosCita.fecha() != null ){
            cita.setFecha(datosCita.fecha());
            cita.setEstado(Estado.REAGENDADA);
        }
        if (datosCita.hora() != null ){
            cita.setHora(datosCita.hora());
            cita.setEstado(Estado.REAGENDADA);
        }
        if (datosCita.duracion() != null ){
            cita.setDuracion(datosCita.duracion());
        }
        if (datosCita.ubicacion() != null ){
            cita.setUbicacion(datosCita.ubicacion());
        }
        if (datosCita.detalles() != null ){
            cita.setDetalles(datosCita.detalles());
        }
        return CitaDto.fromEntity(cita);
    }

    /**
     * Cancelar una cita en la base de datos
     * @param id id de la cita
     */
    public void cancelarCita(Long id){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CITA_NO_ENCONTRADA + id));
        Cita cita = citaRepository.getReferenceById(id);
        cita.setEstado(Estado.CANCELADA);
    }

}
