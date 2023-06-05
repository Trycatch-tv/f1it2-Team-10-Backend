package com.api.citasync.repositories;


import com.api.citasync.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
}
