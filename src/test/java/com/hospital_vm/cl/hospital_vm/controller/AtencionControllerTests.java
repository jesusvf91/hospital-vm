package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.service.AtencionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AtencionController.class)
public class AtencionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AtencionService atencionService;

    @Test
    void listarAtenciones_debeRetornarListaVacia() throws Exception {
        Mockito.when(atencionService.getAllAtenciones()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/atenciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void obtenerAtencionesPorPaciente_noEncontrado() throws Exception {
        Mockito.when(atencionService.getAtencionesByPacienteId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/atenciones/pacientes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearAtencion_debeRetornarCreado() throws Exception {
        Atencion atencion = new Atencion();
        atencion.setId(1L);
        atencion.setFechaAtencion("2024-06-05");
        atencion.setHoraAtencion("10:00");
        atencion.setCosto(10000.0);

        Mockito.when(atencionService.createAtencion(any(Atencion.class))).thenReturn(atencion);

        mockMvc.perform(post("/atenciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fechaAtencion\":\"2024-06-05\",\"horaAtencion\":\"10:00\",\"costo\":10000}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void crearAtencionPorPaciente_pacienteNoEncontrado() throws Exception {
        Mockito.when(atencionService.createAtencionByPacienteID(any(Atencion.class), eq(1L))).thenReturn(null);

        mockMvc.perform(post("/atenciones/pacientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fechaAtencion\":\"2024-06-05\",\"horaAtencion\":\"10:00\",\"costo\":10000}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearAtencionPorPaciente_exito() throws Exception {
        Atencion atencion = new Atencion();
        atencion.setId(2L);
        atencion.setFechaAtencion("2024-06-05");
        atencion.setHoraAtencion("11:00");
        atencion.setCosto(15000.0);

        Mockito.when(atencionService.createAtencionByPacienteID(any(Atencion.class), eq(2L))).thenReturn(atencion);

        mockMvc.perform(post("/atenciones/pacientes/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fechaAtencion\":\"2024-06-05\",\"horaAtencion\":\"11:00\",\"costo\":15000}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L));
    }
}