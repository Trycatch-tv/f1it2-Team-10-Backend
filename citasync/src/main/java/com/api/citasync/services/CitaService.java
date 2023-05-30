package com.api.citasync.services;

import com.api.citasync.dto.DtoActualizarCita;
import com.api.citasync.dto.DtoMapeoCita;
import com.api.citasync.models.Cita;
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
    public Cita crearCita(DtoMapeoCita datosCita) {
        Cita cita = new Cita(datosCita);
        return citaRepository.save(cita);
    }
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public DtoMapeoCita actualizarCita(Long id, DtoActualizarCita datosCita){
        Cita cita = citaRepository.getReferenceById(id);
        cita.actualizarDatos(datosCita);
        return new DtoMapeoCita(cita);
    }

    public void eliminarCita(Long id){
        citaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        Cita cita = citaRepository.getReferenceById(id);
        cita.actualizarEstado();
    }
}
