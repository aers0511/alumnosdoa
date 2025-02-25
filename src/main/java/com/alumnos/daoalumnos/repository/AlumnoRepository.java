package com.alumnos.daoalumnos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumnos.daoalumnos.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, UUID> {
    // Aquí puedes agregar consultas personalizadas si las necesitas porque por
    // default no se obtienen todas.

    // Método para buscar por nombre de alumno:
    List<Alumno> findByNombre(String nombre);
}
