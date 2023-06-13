package com.api.citasync.models;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
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

}
