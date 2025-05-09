package com.hospital_vm.cl.hospital_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.repository.AtencionRepository;

@Service
public class AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private PacienteService pacienteService;

    public List<Atencion> getAllAtenciones() {
        return atencionRepository.findAll();
    }
    
    public Atencion getAtencionById(Long id) {
        return atencionRepository.findById(id).orElse(null);
    }

    public Atencion createAtencion(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    public Atencion createAtencionByPacienteID(Atencion atencion, Long pacienteId) {
        // Verifica si el paciente existe
        if (pacienteService.getPacienteById(pacienteId) == null) {
            return null; // o lanzar una excepción
        }

        // Asigna el paciente a la atención
        atencion.setPaciente(pacienteService.getPacienteById(pacienteId));

        return atencionRepository.save(atencion);
    }

    public Atencion updateAtencion(Long id, Atencion atencion) {
        if (atencionRepository.existsById(id)) {
            atencion.setId(id);
            return atencionRepository.save(atencion);
        }
        return null;
    }

    public void deleteAtencion(Long id) {
        if (atencionRepository.existsById(id)) {
            atencionRepository.deleteById(id);
        }
    }

    public List<Atencion> getAtencionesByPacienteId(Long pacienteId) {
        return atencionRepository.findByPacienteId(pacienteId);
    }    
}
