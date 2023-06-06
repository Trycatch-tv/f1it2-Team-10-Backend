package com.api.citasync.models;

import com.api.citasync.dto.DtoActualizarCita;
import com.api.citasync.dto.DtoMapeoCita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Cita(DtoMapeoCita datosCita) {
        this.nombre = datosCita.nombre();
        this.fecha = datosCita.fecha();
        this.hora = datosCita.hora();
        this.duracion = datosCita.duracion();
        this.ubicacion = datosCita.ubicacion();
        this.detalles = datosCita.detalles();
        this.estado = datosCita.estado();
    }

    public void actualizarEstado() {
        this.estado = Estado.FINALIZADA;
    }

    public void actualizarDatos(DtoActualizarCita datosCita) {
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
