package com.api.citasync.controllers;

import com.api.citasync.dto.CitaRespuestaDto;
import com.api.citasync.dto.CitaSolicitudDto;
import com.api.citasync.exceptions.CitaExistenteException;
import com.api.citasync.services.CitaService;
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
    public ResponseEntity<CitaRespuestaDto> buscarCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.buscarCita(id));
    }

    /**
     * Recuperar todas las citas de la base de datos
     * @return la lista de citas como una respuesta JSON
     */
    @GetMapping()
    @Operation(summary = "Recuperar todas las citas de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de citas encontrada"),
    })
    public ResponseEntity<List<CitaRespuestaDto>> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    /**
     * Crear una nueva cita en la base de datos
     * @param cita cuerpo de la solicitud JSON
     * @return la cita creada como una respuesta JSON con un código de estado 201
     */
    @PostMapping()
    @Operation(summary = "Crear una nueva cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cita creada"),
            @ApiResponse(responseCode = "400", description = "Cita no creada"),
            @ApiResponse(responseCode = "409", description = "Cita ya existe")

    })
    public ResponseEntity<CitaRespuestaDto> crearCita(@RequestBody @Valid CitaSolicitudDto cita ) throws CitaExistenteException {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crearCita(cita));
    }

    /**
     * Actualizar una cita en la base de datos
     * @param cita cuerpo de la solicitud JSON
     * @return la cita actualizada como una respuesta JSON con un código de estado 200
     */
    @PutMapping()
    @Transactional
    @Operation(summary = "Actualizar una cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cita actualizada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<CitaRespuestaDto> actualizarCita(@RequestBody @Valid CitaSolicitudDto cita){
        return ResponseEntity.ok(citaService.actualizarCita(cita));
    }

    /**
     * Cancelar (Eliminar) una cita en la base de datos
     * @param id identificador de la cita
     * @return código de estado 200
     */
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Cancelar (Eliminar) una cita en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cita cancelada"),
        @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id){
        citaService.cancelarCita(id);
        return ResponseEntity.ok().build();
    }

}
