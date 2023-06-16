package com.api.citasync.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Entidad Cita.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "citas")
public class Cita implements Serializable {

    @Serial
    private static final long serialVersionUID = -3655210176873555969L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_cita")
    private LocalDate fecha;

    @Column(name = "hora_cita")
    private LocalTime hora;

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
