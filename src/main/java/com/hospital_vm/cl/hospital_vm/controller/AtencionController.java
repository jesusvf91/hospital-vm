package com.hospital_vm.cl.hospital_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controlador REST para gestionar las operaciones relacionadas con las
 * atenciones.
 * Proporciona endpoints para listar y obtener atenciones por paciente.
 */
@RestController
@RequestMapping("/atenciones")
public class AtencionController {

    /**
     * Servicio para gestionar las operaciones relacionadas con las atenciones.
     */
    @Autowired
    private AtencionService atencionService;

    /**
     * Obtiene una lista de todas las atenciones.
     * 
     * @return Lista de objetos {@link Atencion}.
     */
    @GetMapping
    public ResponseEntity<List<Atencion>> listarAtenciones() {
        List<Atencion> atenciones = atencionService.getAllAtenciones();
        return ResponseEntity.ok(atenciones);
    }

    /**
     * Obtiene una lista de atenciones asociadas a un paciente específico.
     * 
     * @param idPaciente ID del paciente cuyas atenciones se desean obtener.
     * @return Lista de objetos {@link Atencion} asociados al paciente.
     */
    @GetMapping("/pacientes/{idPaciente}")
    public ResponseEntity<List<Atencion>> obtenerAtencionesPorPaciente(@PathVariable Long idPaciente) {
        List<Atencion> atenciones = atencionService.getAtencionesByPacienteId(idPaciente);
        
        if (atenciones == null || atenciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(atenciones);
    }

    /**
     * Crea una nueva atención en el sistema.
     * 
     * @param atencion Objeto {@link Atencion} con los datos de la atención.
     * @return Objeto {@link Atencion} creado.
     */
    @PostMapping
    public ResponseEntity<Atencion> crearAtencion(@RequestBody Atencion atencion) {
        Atencion nuevaAtencion = atencionService.createAtencion(atencion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtencion);
    }

    /**
     * Crea una nueva atención asociada a un paciente.
     * 
     * @param pacienteId ID del paciente al que se asociará la atención.
     * @param atencion   Objeto {@link Atencion} con los datos de la atención (sin el paciente).
     * @return Objeto {@link Atencion} creado.
     */
    @PostMapping("/pacientes/{pacienteId}")
    public ResponseEntity<Atencion> crearAtencion(@PathVariable Long pacienteId, @RequestBody Atencion atencion) {
        Atencion nuevaAtencion = atencionService.createAtencionByPacienteID(atencion, pacienteId);
        if (nuevaAtencion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtencion);
    }
}
