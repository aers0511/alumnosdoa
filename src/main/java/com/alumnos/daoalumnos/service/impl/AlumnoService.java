package com.alumnos.daoalumnos.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumnos.daoalumnos.model.Alumno;
import com.alumnos.daoalumnos.repository.AlumnoRepository;
import com.alumnos.daoalumnos.service.IAlumnoService;

//Esta clase se encarga de toda la logica que se maneja para la Base de Datos.
@Service
public class AlumnoService implements IAlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Autowired // Esto es para que se inyecte la dependecia sin crear una instancia del
               // repositorio.
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;

    }

    @Override
    public List<Alumno> getAlumnos() {
        return alumnoRepository.findAll(); // Obtiene todos los alumnos
    }

    @Override
    public Alumno getAlumnoById(UUID id) {
        return alumnoRepository.findById(id).orElse(null); // Si no encuentra el alumno, retorna null
    }

    @Override
    public List<Alumno> getAlumnoByNombre(String nombre) {

        return alumnoRepository.findByNombre(nombre);
    }

    @Override
    public Alumno saveAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno); // Guarda un alumno en la base de datos
    }

    @Override
    public Alumno updateAlumno(Alumno alumnoDetalles) {

        UUID id = alumnoDetalles.getId();

        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            alumno = alumnoDetalles;
            return alumnoRepository.save(alumno); // Guarda los cambios
        }
        return null; // Si el alumno no existe, retorna null
    }

    @Override
    public Boolean deleteAlumno(UUID id) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            alumnoRepository.delete(alumno); // Elimina el alumno
            return true; // Si se eliminó correctamente, retorna true
        }
        return false; // Si el alumno no existe, retorna false
    }

    @Override
    // Método para actualizar la URL de la imagen
    public Alumno actualizarImagen(UUID id, String imagenUrl) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        alumno.setFotoUrl(imagenUrl);
        return alumnoRepository.save(alumno); // Guarda el alumno con la nueva imagen
    }
}
