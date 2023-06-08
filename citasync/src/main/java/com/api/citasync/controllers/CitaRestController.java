package com.api.citasync.controllers;

import com.api.citasync.dto.CitaDtoActualizar;
import com.api.citasync.dto.CitaDto;
import com.api.citasync.services.CitaService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@Tag(name = "Citas Rest Controller API")
public class CitaRestController {
    private final CitaService citaService;
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
    @Operation(summary = "Buscar una cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cita encontrada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<CitaDto> buscarCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.buscarCita(id));
    }
    /**
     * http://localhost:8080/api/citas
     * Recuperar todas las citas de la base de datos
     * @return la lista de citas como una respuesta JSON
     */
    @GetMapping("/listar")
    @Operation(summary = "Recuperar todas las citas de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de citas encontrada"),
    })
    public ResponseEntity<List<CitaDto>> listarCitas() {
        return ResponseEntity.ok(citaService.ListarCitas());
    }

    /**
     * http://localhost:8080/api/citas
     * Crear una nueva cita en la base de datos
     * @param cita cuerpo de la solicitud JSON
     * @return la cita creada como una respuesta JSON con un código de estado 201
     */
    @PostMapping("/crear")
    @Operation(summary = "Crear una nueva cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cita creada"),
            @ApiResponse(responseCode = "400", description = "Cita no creada"),
            @ApiResponse(responseCode = "409", description = "Cita ya existe")

    })
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
    @PutMapping("actualizar/{id}")
    @Transactional
    @Operation(summary = "Actualizar una cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cita actualizada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<CitaDto> actualizarCita(@PathVariable Long id, @RequestBody CitaDtoActualizar cita){
        CitaDto citaActualizada = citaService.actualizarCita(id, cita);
        return ResponseEntity.ok(citaActualizada);
    }

    /**
     * Cancelar (Eliminar) una cita en la base de datos
     * http://localhost:8080/api/citas/finalizar/id
     * @param id identificador de la cita
     * @return
     */

    @DeleteMapping("cancelar/{id}")
    @Transactional
    @Operation(summary = "Cancelar (Eliminar) una cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cita cancelada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id){
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }

}
