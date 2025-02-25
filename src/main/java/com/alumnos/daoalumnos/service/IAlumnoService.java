package com.alumnos.daoalumnos.service;

import java.util.List;
import java.util.UUID;

import com.alumnos.daoalumnos.model.Alumno;

public interface IAlumnoService {

    List<Alumno> getAlumnos();

    Alumno getAlumnoById(UUID id);

    List<Alumno> getAlumnoByNombre(String nombre);

    Alumno saveAlumno(Alumno alumno);

    Alumno updateAlumno(Alumno alumno);

    Boolean deleteAlumno(UUID id);

    Alumno actualizarImagen(UUID id, String imagenUrl);

}
