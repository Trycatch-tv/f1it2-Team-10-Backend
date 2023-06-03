package com.api.citasync.repositories;

import com.api.citasync.dto.CitaDto;
import com.api.citasync.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;

@Repository
// Esta interfaz proporciona métodos para acceder a citas en la base de datos.
// Extiende JpaRepository<Cita, Long> para heredar métodos para operaciones CRUD básicas en citas.
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
