package com.alumnos.daoalumnos.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alumnos.daoalumnos.model.Alumno;
import com.alumnos.daoalumnos.service.impl.AlumnoService;
import com.alumnos.daoalumnos.utils.helpers.PathHelper;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos() {

        List<Alumno> alumnos = alumnoService.getAlumnos();

        if (alumnos.isEmpty()) {
            // Lanza una excepción estándar si no se encuentran alumnos
            throw new NoSuchElementException("No se encontraron alumnos.");
        }

        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable UUID id) {
        Alumno alumno = alumnoService.getAlumnoById(id);

        if (alumno != null) {
            return ResponseEntity.ok(alumno);
        }
        // Lanza una excepción estándar si no se encuentró
        throw new NoSuchElementException("No se encontró el alumno.");
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Alumno>> getAlumnoByNombre(@PathVariable String nombre) {
        List<Alumno> alumno = alumnoService.getAlumnoByNombre(nombre);

        if (alumno.isEmpty()) {
            // Lanza una excepción estándar si no se encuentró
            throw new NoSuchElementException("No se encontró el alumno.");
        }
        return ResponseEntity.ok(alumno);
    }

    @PostMapping
    public ResponseEntity<Alumno> agregarAlumno(@RequestBody Alumno alumno) {
        return ResponseEntity.created(PathHelper.locationHelperID(alumnoService.saveAlumno(alumno), "/{id}")).build();
    }

    @PutMapping
    public ResponseEntity<Alumno> editarAlumno(@RequestBody Alumno alumno) {

        Alumno alumnoSaved = alumnoService.updateAlumno(alumno);

        if (alumnoSaved != null) {
            return ResponseEntity.ok(alumnoSaved);
        }
        // Lanza una excepción estándar si no se encuentró
        throw new NoSuchElementException("No se encontró el alumno " + alumno.toString());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Alumno> eliminarAlumno(@PathVariable(required = true) UUID id) {
        Alumno alumnoDeleted = alumnoService.getAlumnoById(id);

        if (alumnoDeleted != null) {
            alumnoService.deleteAlumno(id);
            return ResponseEntity.ok(alumnoDeleted);
        }
        // Lanza una excepción estándar si no se encuentró
        throw new NoSuchElementException("No se encontró el alumno.");

    }

    // Endpoint para actualizar la imagen de un alumno
    @PutMapping("/{id}/foto")
    public ResponseEntity<Alumno> actualizarFoto(@PathVariable UUID id, @RequestBody String imagenUrl) {
        Alumno alumnoActualizado = alumnoService.actualizarImagen(id, imagenUrl);
        return ResponseEntity.ok(alumnoActualizado);
    }

}
