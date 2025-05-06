package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa a un paciente en el sistema.
 * Contiene información básica como nombre, apellido, RUN, dirección, fecha de
 * nacimiento y correo electrónico.
 */
@Entity
@Table(name = "paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    /**
     * Identificador único del paciente.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del paciente.
     * Este campo es obligatorio.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Apellido del paciente.
     * Este campo es obligatorio.
     */
    @Column(name = "apellido", nullable = false)
    private String apellido;

    /**
     * RUN (Rol Único Nacional) del paciente.
     * Este campo es obligatorio, único y tiene un máximo de 13 caracteres.
     */
    @Column(name = "run", nullable = false, unique = true, length = 13)
    private String run;

    /**
     * Dirección del paciente.
     * Este campo es obligatorio.
     */
    @Column(name = "direccion", nullable = false)
    private String direccion;

    /**
     * Fecha de nacimiento del paciente.
     * Este campo es obligatorio.
     */
    @Column(name = "fechaNacimiento", nullable = false)
    private String fechaNacimiento;

    /**
     * Correo electrónico del paciente.
     * Este campo es obligatorio.
     */
    @Column(name = "correo", nullable = false)
    private String correo;
}