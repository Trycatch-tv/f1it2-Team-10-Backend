package com.api.citasync.controllers;

import com.api.citasync.dto.DtoActualizarCita;
import com.api.citasync.dto.DtoMapeoCita;
import com.api.citasync.models.Cita;
import com.api.citasync.services.CitaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaRestController {
    private final CitaService citaService;
    @Autowired
    public CitaRestController(CitaService citaService) {
        this.citaService = citaService;
    }


    //Este método recupera todas las citas de la base de datos y las devuelve como una respuesta JSON.
    /**
     *http://localhost:8080/api/citas/listar
     * @return
     */
    @GetMapping(value = "listar",headers = "Accept=application/json")
    public ResponseEntity<List<Cita>> listarCitas() {
        // Recuperar todas las citas de la base de datos.
        List<Cita> citas = citaService.listarCitas();
        // Devuelve las citas como una respuesta JSON.
        return ResponseEntity.ok(citas);
    }

    /**
     *http://localhost:8080/api/citas/crear
     * @param cita
     * @return
     */
    @PostMapping(value = "crear",headers = "Accept=application/json")
    public ResponseEntity<DtoMapeoCita> createCita(@RequestBody @Valid DtoMapeoCita cita) {
        Cita createdCita = citaService.crearCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoMapeoCita(createdCita));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoMapeoCita> actualizarCita(@PathVariable Long id, @RequestBody DtoActualizarCita cita){
        DtoMapeoCita citaActualizada = citaService.actualizarCita(id, cita);
        return ResponseEntity.ok(citaActualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

}
