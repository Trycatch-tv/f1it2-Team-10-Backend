package com.api.citasync.services;

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
    // Este método crea una nueva cita y la guarda en la base de datos.
    public Cita crearCita(CitaDto datosCita) {
        return citaRepository.save(new Cita(datosCita));
    }
    // Este método devuelve una lista de todas las citas de la base de datos.
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }


}
