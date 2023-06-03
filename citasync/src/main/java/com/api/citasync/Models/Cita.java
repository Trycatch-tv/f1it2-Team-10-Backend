package com.api.citasync.models;

import com.api.citasync.dto.CitaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter//Métodos Getters
@Setter//Métodos Setters
@NoArgsConstructor//constructor sin parametros
@AllArgsConstructor//constructor con parametros
@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_cita")
    private Date fecha;

    @Column(name = "hora_cita")
    private Time hora;

    @Column(name = "duracion")
    private int duracion;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "detalles", columnDefinition = "TEXT")
    private String detalles;

    @Column(name = "estado")
    private String estado;

    public Cita(CitaDto datosCita) {
        this.nombre = datosCita.nombre();
        this.fecha = datosCita.fecha();
        this.hora = datosCita.hora();
        this.duracion = datosCita.duracion();
        this.ubicacion = datosCita.ubicacion();
        this.detalles = datosCita.detalles();
        this.estado = String.valueOf(datosCita.estado());
    }
}
