package com.api.citasync.models;

import com.api.citasync.dto.CitaDtoActualizar;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_cita")
    private LocalDate fecha;

    @Column(name = "hora_cita")
    private Time hora;

    @Column(name = "duracion")
    private int duracion;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "detalles", columnDefinition = "TEXT")
    private String detalles;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public void actualizarDatos(CitaDtoActualizar datosCita) {
        if (datosCita.fecha() != null ){
            this.fecha = datosCita.fecha();
            this.estado = Estado.REAGENDADA;
        }
        if (datosCita.hora() != null ){
            this.hora = datosCita.hora();
            this.estado = Estado.REAGENDADA;
        }
        if (datosCita.duracion() != null ){
            this.duracion = datosCita.duracion();
        }
        if (datosCita.ubicacion() != null ){
            this.ubicacion = datosCita.ubicacion();
        }
        if (datosCita.detalles() != null ){
            this.detalles = datosCita.detalles();
        }
    }
}
