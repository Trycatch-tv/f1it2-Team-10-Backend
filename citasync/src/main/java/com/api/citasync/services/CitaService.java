package com.api.citasync.services;

import com.api.citasync.dto.DtoActualizarCita;
import com.api.citasync.dto.DtoMapeoCita;
import com.api.citasync.exceptions.CitaExistenteExcepcion;
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

    public DtoMapeoCita crearCita(DtoMapeoCita datosCita) {
        if (citaRepository.existsByFechaAndHoraAndEstadoIn(datosCita.fecha(),
                datosCita.hora(), List.of(Estado.AGENDADA, Estado.REAGENDADA))){
            throw new CitaExistenteExcepcion("Ya existe una cita en esta Fecha y hora");
        }

        return new DtoMapeoCita(citaRepository.save(new Cita(datosCita)));
    }

    public List<DtoMapeoCita> listarCitas() {
        return citaRepository.findAll().stream().map(DtoMapeoCita::new).toList();
    }

    public List<DtoMapeoCita> ListarCitas2(){
        return citaRepository.findAllByEstadoNotIn(List.of(Estado.FINALIZADA));
    }

    public DtoMapeoCita actualizarCita(Long id, DtoActualizarCita datosCita){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        Cita cita = citaRepository.getReferenceById(id);
        cita.actualizarDatos(datosCita);
        return new DtoMapeoCita(cita);
    }

    public void finalizarCita(Long id){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        Cita cita = citaRepository.getReferenceById(id);
        cita.actualizarEstado();
    }
}
