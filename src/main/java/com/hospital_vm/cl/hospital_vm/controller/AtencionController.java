package com.hospital_vm.cl.hospital_vm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador REST para gestionar las operaciones relacionadas con las
 * atenciones.
 * Proporciona endpoints para listar y obtener atenciones por paciente.
 */
@RestController
@RequestMapping("/atenciones")
@Tag(name = "Atenciones", description = "Operaciones relacionadas con las atenciones de pacientes")
public class AtencionController {

    /**
     * Logger de la clase para registrar eventos y errores.
     */
    private static final Logger logger = LoggerFactory.getLogger(AtencionController.class);

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
    @Operation(summary = "Listar todas las atenciones", description = "Obtiene una lista con todas las atenciones registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de atenciones obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Atencion.class)))
    })
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
    @Operation(summary = "Obtener atenciones por paciente", description = "Obtiene una lista de atenciones para un paciente específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atenciones encontradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Atencion.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron atenciones para el paciente")
    })
    @GetMapping("/pacientes/{idPaciente}")
    public ResponseEntity<List<Atencion>> obtenerAtencionesPorPaciente(@PathVariable Long idPaciente) {
        logger.info("[obtenerAtencionesPorPaciente] Inicio");
        logger.debug("[obtenerAtencionesPorPaciente] Obteeniendo atenciones para el paciente con ID: {}", idPaciente);

        List<Atencion> atenciones = atencionService.getAtencionesByPacienteId(idPaciente);

        if (atenciones == null || atenciones.isEmpty()) {
            logger.warn("No se encontraron atenciones para el paciente con ID: {}", idPaciente);
            return ResponseEntity.notFound().build();
        }

        logger.info("[obtenerAtencionesPorPaciente] Se encontraron {} atenciones para el paciente con ID: {}", atenciones.size(), idPaciente);
        logger.info("[obtenerAtencionesPorPaciente] Fin", atenciones.size(), idPaciente);
        return ResponseEntity.ok(atenciones);
    }

    /**
     * Crea una nueva atención en el sistema.
     * 
     * @param atencion Objeto {@link Atencion} con los datos de la atención.
     * @return Objeto {@link Atencion} creado.
     */
    @Operation(summary = "Crear nueva atención", description = "Crea una nueva atención en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atención creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Atencion.class)))
    })
    @PostMapping
    public ResponseEntity<Atencion> crearAtencion(@RequestBody Atencion atencion) {
        Atencion nuevaAtencion = atencionService.createAtencion(atencion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtencion);
    }

    /**
     * Crea una nueva atención asociada a un paciente.
     * 
     * @param pacienteId ID del paciente al que se asociará la atención.
     * @param atencion   Objeto {@link Atencion} con los datos de la atención (sin
     *                   el paciente).
     * @return Objeto {@link Atencion} creado.
     */
    @Operation(summary = "Crear atención asociada a un paciente", description = "Crea una nueva atención vinculada al paciente especificado por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atención creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Atencion.class))),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @PostMapping("/pacientes/{pacienteId}")
    public ResponseEntity<Atencion> crearAtencion(@PathVariable Long pacienteId, @RequestBody Atencion atencion) {
        Atencion nuevaAtencion = atencionService.createAtencionByPacienteID(atencion, pacienteId);
        if (nuevaAtencion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtencion);
    }
}
