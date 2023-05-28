package com.api.citasync.Controllers;

import com.api.citasync.Models.Cita;
import com.api.citasync.Services.CitaService;
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
    //Este método crea una nueva cita y la guarda en la base de datos. La nueva cita se pasa como un cuerpo de solicitud JSON.

    /**
     *http://localhost:8080/api/citas/crear
     * @param cita
     * @return
     */
    @PostMapping(value = "crear",headers = "Accept=application/json")
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        // Crea una nueva cita desde el cuerpo de la solicitud JSON.
        Cita createdCita = citaService.crearCita(cita);
        // Devuelve la cita creada como una respuesta JSON con un código de estado 201 Creado.
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCita);
    }

}
