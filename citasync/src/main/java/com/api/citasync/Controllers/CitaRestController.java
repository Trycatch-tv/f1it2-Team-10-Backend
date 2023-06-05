package com.api.citasync.controllers;

import com.api.citasync.dto.CitaActualizarDto;
import com.api.citasync.dto.CitaDto;
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
    private CitaService citaService;
    // Este constructor inyecta la dependencia de CitaService.
    @Autowired
    public CitaRestController(CitaService citaService) {
        this.citaService = citaService;
    }

    /**
     * http://localhost:8080/api/citas/{id}
     * Buscar una cita en la base de datos
     * @param id id de la cita
     * @return la cita con el {id} indicado como una respuesta JSON
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarCita(@PathVariable Long id) {
        Cita cita = citaService.buscarCita(id);
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }

    /**
     * http://localhost:8080/api/citas/listar
     * Recuperar todas las citas de la base de datos
     * @return la lista de citas como una respuesta JSON
     */
    @GetMapping(value = "listar",headers = "Accept=application/json")
    public ResponseEntity<List<Cita>> listarCitas() {
        // Recuperar todas las citas de la base de datos.
        List<Cita> citas = citaService.listarCitas();
        // Devuelve las citas como una respuesta JSON.
        return ResponseEntity.ok(citas);
    }
    /**
     * http://localhost:8080/api/citas/crear
     * Crear una nueva cita en la base de datos
     * @param cita cuerpo de la solicitud JSON
     * @return la cita creada como una respuesta JSON con un código de estado 201
     */
    @PostMapping(value = "crear")
    public ResponseEntity<CitaDto> createCita(@RequestBody @Valid CitaDto cita ) {
        Cita createdCita = citaService.crearCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CitaDto(createdCita));
    }
    /**
     * http://localhost:8080/api/citas/actualizar/id
     * Actualizar una cita en la base de datos
     * @param id identificador de la cita
     * @param cita cuerpo de la solicitud JSON
     * @return la cita actualizada como una respuesta JSON con un código de estado 200
     */
    @PutMapping(value = "actualizar/{id}")
    @Transactional
    public ResponseEntity<CitaDto> actualizarCita(@PathVariable Long id, @RequestBody CitaActualizarDto cita) {
        CitaDto citaActualizada = citaService.actualizarCita(id, cita);
        return ResponseEntity.ok(citaActualizada);
    }

    /**
     * Finalizar (Eliminar) una cita en la base de datos
     * http://localhost:8080/api/citas/finalizar/id
     * @param id identificador de la cita
     * @return
     */
    @PutMapping(value = "finalizar/{id}")
    public ResponseEntity<Cita> actualizarEstado(@PathVariable Long id) {
        citaService.finalizarCita(id);
        return ResponseEntity.ok().build();
    }
}
