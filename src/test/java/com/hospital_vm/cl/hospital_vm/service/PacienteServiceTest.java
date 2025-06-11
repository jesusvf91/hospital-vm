package com.hospital_vm.cl.hospital_vm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.repository.PacienteRepository;

@SpringBootTest
@ActiveProfiles("test")
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @MockBean
    private PacienteRepository pacienteRepository;

    @Test
    public void testGetPacientes() {

        //Given

        List<Paciente> pacientes = new ArrayList<>();

        //When
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        //Then
        List<Paciente> result = pacienteService.getAllPacientes();

        assertEquals(pacientes, result);
        assertEquals(0, result.size());

    }
    
}
