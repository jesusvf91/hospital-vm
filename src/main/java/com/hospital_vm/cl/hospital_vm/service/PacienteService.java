package com.hospital_vm.cl.hospital_vm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.repository.PacienteRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los pacientes.
 * Proporciona métodos para listar, obtener, crear, actualizar y eliminar
 * pacientes.
 */
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    /**
     * Obtiene una lista de todos los pacientes registrados.
     * 
     * @return Lista de objetos {@link Paciente}.
     */
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    /**
     * Obtiene un paciente por su ID.
     * 
     * @param id ID del paciente a buscar.
     * @return Objeto {@link Paciente} correspondiente al ID proporcionado o
     *         {@code null} si no se encuentra.
     */
    public Paciente getPacienteById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo paciente en el sistema.
     * Si ya existe un paciente con el mismo RUN, devuelve el paciente existente.
     * 
     * @param paciente Objeto {@link Paciente} con la información del paciente a
     *                 guardar.
     * @return Objeto {@link Paciente} guardado o existente.
     */
    public Paciente savePaciente(Paciente paciente) {
        Paciente pacientePorRut = pacienteRepository.findByRun(paciente.getRun());

        if (paciente.getRun() != null && pacientePorRut != null) {
            return pacientePorRut;
        }

        return pacienteRepository.save(paciente);
    }

    /**
     * Elimina un paciente del sistema por su ID.
     * 
     * @param id ID del paciente a eliminar.
     */
    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    /**
     * Actualiza completamente la información de un paciente existente.
     * 
     * @param id       ID del paciente a actualizar.
     * @param paciente Objeto {@link Paciente} con la nueva información.
     * @return Objeto {@link Paciente} actualizado o {@code null} si el paciente no
     *         existe.
     */
    public Paciente updatePaciente(Long id, Paciente paciente) {
        if (pacienteRepository.existsById(id)) {
            paciente.setId(id);
            return pacienteRepository.save(paciente);
        }

        return null;
    }

    /**
     * Actualiza parcialmente la información de un paciente existente.
     * Solo se actualizan los campos proporcionados en el objeto {@link Paciente}.
     * 
     * @param id       ID del paciente a actualizar.
     * @param paciente Objeto {@link Paciente} con la información parcial a
     *                 actualizar.
     * @return Objeto {@link Paciente} actualizado o {@code null} si el paciente no
     *         existe.
     */
    public Paciente patchPaciente(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty()) {
            return null;
        }

        Paciente existingPaciente = optionalPaciente.get();

        if (paciente.getNombre() != null)
            existingPaciente.setNombre(paciente.getNombre());
        if (paciente.getApellido() != null)
            existingPaciente.setApellido(paciente.getApellido());
        if (paciente.getRun() != null)
            existingPaciente.setRun(paciente.getRun());
        if (paciente.getDireccion() != null)
            existingPaciente.setDireccion(paciente.getDireccion());
        if (paciente.getFechaNacimiento() != null)
            existingPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        if (paciente.getCorreo() != null)
            existingPaciente.setCorreo(paciente.getCorreo());

        return pacienteRepository.save(existingPaciente);
    }

    /**
     * Busca un paciente por su nombre con JQPL.
     * 
     * @param nombre del paciente a buscar.
     * @return Lista de objetos {@link Paciente} que coincidan con el nombre.
     */
    public List<Paciente> buscarPacientesPorNombreJPQL(String nombre) {
        return pacienteRepository.findByNombreJPQL(nombre);
    }

    /**
     * Busca un paciente por su nombre con SQL nativo.
     * 
     * @param nombre del paciente a buscar.
     * @return Lista de objetos {@link Paciente} que coincidan con el nombre.
     */
    public List<Paciente> buscarPacientesPorNombreNative(String nombre) {
        return pacienteRepository.findByNombreNative(nombre);
    }
}