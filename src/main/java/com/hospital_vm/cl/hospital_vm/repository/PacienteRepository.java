package com.hospital_vm.cl.hospital_vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospital_vm.cl.hospital_vm.model.Paciente;

import java.util.List;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la entidad
 * {@link Paciente}.
 * Proporciona métodos para realizar consultas personalizadas además de las
 * operaciones CRUD básicas.
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Busca un paciente por su correo electrónico.
     * 
     * @param correo Correo electrónico del paciente.
     * @return Objeto {@link Paciente} correspondiente al correo proporcionado o
     *         {@code null} si no se encuentra.
     */
    public Paciente findByCorreo(String correo);

    /**
     * Busca un paciente por su RUN (Rol Único Nacional).
     * 
     * @param run RUN del paciente.
     * @return Objeto {@link Paciente} correspondiente al RUN proporcionado o
     *         {@code null} si no se encuentra.
     */
    public Paciente findByRun(String run);

    /**
     * Busca pacientes por su nombre utilizando JPQL.
     * 
     * @param nombre Nombre del paciente.
     * @return Lista de objetos {@link Paciente} que coincidan con el nombre.
     */
    @Query("SELECT p FROM Paciente p WHERE p.nombre = :nombre")
    List<Paciente> findByNombreJPQL(String nombre);

    /**
     * Busca pacientes por su nombre utilizando SQL nativo.
     * 
     * @param nombre Nombre del paciente.
     * @return Lista de objetos {@link Paciente} que coincidan con el nombre.
     */
    @Query(value = "SELECT * FROM paciente WHERE nombre = :nombre", nativeQuery = true)
    List<Paciente> findByNombreNative(String nombre);
}