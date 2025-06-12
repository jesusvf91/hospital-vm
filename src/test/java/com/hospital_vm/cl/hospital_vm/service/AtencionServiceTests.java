package com.hospital_vm.cl.hospital_vm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.hospital_vm.cl.hospital_vm.model.Atencion;
import com.hospital_vm.cl.hospital_vm.repository.AtencionRepository;

@SpringBootTest
@ActiveProfiles("test")
public class AtencionServiceTests {

    @Autowired
    private AtencionService atencionService;

    @MockBean
    private AtencionRepository atencionRepository;

    @Test
    public void testGetAtenciones() {
        //Given
        List<Atencion> atenciones = List.of(
            new Atencion(1L, "Consulta General", "2023-10-01", null, null, null),
            new Atencion(2L, "Chequeo Anual", "2023-10-02", null, null, null)
        );

        // When
        when(atencionRepository.findAll()).thenReturn(null);

        // Then
        List<Atencion> result = atencionService.getAllAtenciones();

        assertEquals(null, result);
    }
}
