package com.api.citasync.Services;

import com.api.citasync.Models.Cita;
import com.api.citasync.Repositories.ICitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private ICitaRepository citaRepository;
    // Este constructor inyecta la dependencia ICitaRepository en CitaService.
    @Autowired
    public CitaService(ICitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }
    // Este método crea una nueva cita y la guarda en la base de datos.
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }
    // Este método devuelve una lista de todas las citas de la base de datos.
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }
}
