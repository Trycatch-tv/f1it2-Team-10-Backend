package com.api.citasync.Repositories;

import com.api.citasync.Models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Esta interfaz proporciona métodos para acceder a citas en la base de datos.
// Extiende JpaRepository<Cita, Long> para heredar métodos para operaciones CRUD básicas en citas.
public interface ICitaRepository extends JpaRepository<Cita, Long> {
}
