package com.api.citasync.services;

import com.api.citasync.dto.CitaRespuestaDto;
import com.api.citasync.dto.CitaSolicitudDto;
import com.api.citasync.exceptions.CitaExistenteException;
import com.api.citasync.mappers.CitaMapper;
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
    public CitaRespuestaDto crearCita(CitaSolicitudDto datosCita) throws CitaExistenteException {
            if (citaRepository.existsByFechaAndHoraAndEstadoIn(datosCita.dateTime().toLocalDate(),
                    datosCita.dateTime().toLocalTime(), List.of(Estado.AGENDADA, Estado.REAGENDADA))){
                throw new CitaExistenteException("Ya existe una cita en esta Fecha y hora");
            }

        Cita cita = CitaMapper.toEntity(datosCita);
        cita.setEstado(Estado.AGENDADA);
        return CitaMapper.toCitaRespuestaDto(citaRepository.save(cita));
    }

    /**
     * Lista todas las citas
     * @return List<CitaDto> con todas las citas
     */
    public List<CitaRespuestaDto> listarCitas(){
        return citaRepository.findAllByEstadoNotIn(List.of(Estado.FINALIZADA, Estado.CANCELADA))
                .stream()
                .map(CitaMapper::toCitaRespuestaDto)
                .toList();
    }

    /**
     * Buscar una cita en la base de datos
     * @param id id de la cita
     * @return CitaDto con la cita encontrada con los datos
     */
    public CitaRespuestaDto buscarCita(Long id){
        return CitaMapper.toCitaRespuestaDto(citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CITA_NO_ENCONTRADA + id)));
    }

    /**
     * Actualizar una cita en la base de datos
     * @param datosCita cuerpo de la solicitud JSON
     * @return CitaDto con la cita actualizada con los datos
     */
    public CitaRespuestaDto actualizarCita(CitaSolicitudDto datosCita){
        final Cita cita = citaRepository.getReferenceById(citaRepository.findById(datosCita.id())
                .orElseThrow(() -> new EntityNotFoundException(CITA_NO_ENCONTRADA + datosCita.id())).getId());
        return CitaMapper.toCitaRespuestaDto(actualizarDatos(cita, datosCita));
    }

    private Cita actualizarDatos(Cita cita, CitaSolicitudDto datosCita){
        cita.setFecha(datosCita.dateTime().toLocalDate());
        cita.setHora(datosCita.dateTime().toLocalTime());
        cita.setEstado(Estado.REAGENDADA);
        cita.setDuracion(datosCita.duracion());
        cita.setDetalles(datosCita.detalles());
        cita.setUbicacion(datosCita.ubicacion());

        return cita;
    }

    /**
     * Cancelar una cita en la base de datos
     * @param id id de la cita
     */
    public void cancelarCita(Long id){
        final Cita cita = citaRepository.getReferenceById(citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CITA_NO_ENCONTRADA + id)).getId());
        cita.setEstado(Estado.CANCELADA);
    }

}
