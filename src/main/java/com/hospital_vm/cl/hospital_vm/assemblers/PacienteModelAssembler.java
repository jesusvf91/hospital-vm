package com.hospital_vm.cl.hospital_vm.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.hospital_vm.cl.hospital_vm.controller.PacienteControllerV2;
import com.hospital_vm.cl.hospital_vm.model.Paciente;

@Component
public class PacienteModelAssembler implements RepresentationModelAssembler<Paciente, EntityModel<Paciente>> {

    @Override
    public EntityModel<Paciente> toModel(Paciente paciente) {
        Link selfLink = linkTo(methodOn(PacienteControllerV2.class).obtenerPacientePorId(paciente.getId()))
                .withSelfRel();
        Link allPacientesLink = linkTo(methodOn(PacienteControllerV2.class).listarPacientes()).withRel("pacientes");

        // Link de creación marcado como POST
        Link crearLink = linkTo(methodOn(PacienteControllerV2.class).actualizarPaciente(paciente.getId(), paciente))
                .withRel("crear")
                .withType("POST");

        return EntityModel.of(paciente, selfLink, allPacientesLink, crearLink);
    }
}