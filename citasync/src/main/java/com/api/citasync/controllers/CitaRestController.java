package com.api.citasync.controllers;

import com.api.citasync.dto.DtoActualizarCita;
import com.api.citasync.dto.CitaDto;
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


    /**
     * http://localhost:8080/api/citas
     * Recuperar todas las citas de la base de datos
     * @return la lista de citas como una respuesta JSON
     */
    @GetMapping()
    public ResponseEntity<List<CitaDto>> listarCitas() {
        return ResponseEntity.ok(citaService.ListarCitas());
    }

    /**
     * http://localhost:8080/api/citas
     * Crear una nueva cita en la base de datos
     * @param cita cuerpo de la solicitud JSON
     * @return la cita creada como una respuesta JSON con un código de estado 201
     */
    @PostMapping()
    public ResponseEntity<CitaDto> crearCita(@RequestBody @Valid CitaDto cita ) {
        CitaDto createdCita = citaService.crearCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCita);
    }

    /**
     * http://localhost:8080/api/citas/id
     * Actualizar una cita en la base de datos
     * @param id identificador de la cita
     * @param cita cuerpo de la solicitud JSON
     * @return la cita actualizada como una respuesta JSON con un código de estado 200
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CitaDto> actualizarCita(@PathVariable Long id, @RequestBody DtoActualizarCita cita){
        CitaDto citaActualizada = citaService.actualizarCita(id, cita);
        return ResponseEntity.ok(citaActualizada);
    }

    /**
     * Cancelar (Eliminar) una cita en la base de datos
     * http://localhost:8080/api/citas/finalizar/id
     * @param id identificador de la cita
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id){
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }

}
