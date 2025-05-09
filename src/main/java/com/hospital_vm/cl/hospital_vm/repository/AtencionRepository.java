package com.hospital_vm.cl.hospital_vm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital_vm.cl.hospital_vm.model.Atencion;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    public List<Atencion> findByPacienteId(Long pacienteId);
}
