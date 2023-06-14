package com.api.citasync.repositories;

import com.api.citasync.models.Cita;
import com.api.citasync.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByFechaAndHoraAndEstadoIn(LocalDate fecha, LocalTime hora, List<Estado> estados);
    List<Cita> findAllByEstadoNotIn(List<Estado> estado);



}
