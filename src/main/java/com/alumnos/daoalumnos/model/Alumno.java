package com.alumnos.daoalumnos.model;

import java.util.UUID;

import com.alumnos.daoalumnos.common.Identificable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "alumnos")
@Getter // Genera los getters automáticamente
@Setter // Genera los setters automáticamente
@NoArgsConstructor // Constructor sin parámetros
@AllArgsConstructor // Constructor con todos los parámetros
@ToString // Genera el método toString automáticamente
public class Alumno implements Identificable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int celular;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String fotoUrl;

    // Constructor sin el parámetro foto
    public Alumno(String nombre, String apellido, String correo, int celular, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.direccion = direccion;
        this.fotoUrl = null; // No se asigna inicialmente
    }

    @Override
    public UUID getId() {
        return id;
    }

    // Aquí no es necesario agregar los métodos setId, setCelular, etc. ya que
    // Lombok los genera automáticamente


}
