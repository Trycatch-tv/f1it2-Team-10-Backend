package com.api.citasync.services;

import com.api.citasync.dto.CitaActualizarDto;
import com.api.citasync.dto.CitaDto;
import com.api.citasync.models.Cita;
import com.api.citasync.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaService {
    private CitaRepository citaRepository;
    // Este constructor inyecta la dependencia CitaRepository en CitaService.
    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    // Recuperar todas las citas de la base de datos.
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }
    //buscar por id
    public Cita buscarCita(Long id) {
        return citaRepository.findById(id).get();
    }
    // Crear una nueva cita en la base de datos.
    public Cita crearCita(CitaDto datosCita) {
        return citaRepository.save(new Cita(datosCita));
    }
    // Actualizar una cita en la base de datos.
    public CitaDto actualizarCita(Long id, CitaActualizarDto datosCita) {
        Cita cita = citaRepository.getReferenceById(id);
        cita.actualizar(datosCita);
        return new CitaDto(cita);
    }
    // Finalizar una cita en la base de datos.
    public void finalizarCita(Long id) {
        Cita cita = citaRepository.findById(id).get();
        cita.setEstado("FINALIZADA");
        citaRepository.save(cita);
    }

}
