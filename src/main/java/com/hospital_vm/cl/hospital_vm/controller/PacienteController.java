package com.hospital_vm.cl.hospital_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los
 * pacientes.
 * Proporciona endpoints para listar, obtener, crear, actualizar y eliminar
 * pacientes.
 */
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * Obtiene una lista de todos los pacientes.
     * 
     * @return Lista de objetos {@link Paciente}.
     */
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    /**
     * Obtiene un paciente por su ID.
     * 
     * @param id ID del paciente a buscar.
     * @return Objeto {@link Paciente} correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.getPacienteById(id);
        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(paciente);
    }

    /**
     * Guarda un nuevo paciente en el sistema.
     * 
     * @param paciente Objeto {@link Paciente} con la información del paciente a
     *                 guardar.
     * @return Objeto {@link Paciente} guardado.
     */
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteService.savePaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    /**
     * Actualiza completamente la información de un paciente existente.
     * 
     * @param id       ID del paciente a actualizar.
     * @param paciente Objeto {@link Paciente} con la nueva información.
     * @return Objeto {@link Paciente} actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteActualizado = pacienteService.updatePaciente(id, paciente);
        if (pacienteActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pacienteActualizado);
    }

    /**
     * Actualiza parcialmente la información de un paciente existente.
     * 
     * @param id       ID del paciente a actualizar.
     * @param paciente Objeto {@link Paciente} con la información parcial a
     *                 actualizar.
     * @return Objeto {@link Paciente} actualizado parcialmente.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPacienteParcial(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteActualizado = pacienteService.patchPaciente(id, paciente);
        if (pacienteActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pacienteActualizado);
    }

    /**
     * Elimina un paciente del sistema por su ID.
     * 
     * @param id ID del paciente a eliminar.
     * @return Respuesta con código de estado HTTP.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        if (pacienteService.getPacienteById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        pacienteService.deletePaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Busca un paciente por su nombre con JQPL.
     * 
     * @param nombre del paciente a buscar.
     * @return Lista de objetos {@link Paciente} que coincidan con el nombre.
     */
    @GetMapping("/buscar/jpql/{nombre}")
    public ResponseEntity<List<Paciente>> buscarPorNombreJPQL(@PathVariable String nombre) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorNombreJPQL(nombre);

        if (pacientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(pacientes);
    }

    /**
     * Busca un paciente por su nombre con SQL nativo.
     * 
     * @param nombre del paciente a buscar.
     * @return Lista de objetos {@link Paciente} que coincidan con el nombre.
     */
    @GetMapping("/buscar/native/{nombre}")
    public ResponseEntity<List<Paciente>> buscarPorNombreNative(@PathVariable String nombre) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorNombreNative(nombre);

        if (pacientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(pacientes);
    }
}