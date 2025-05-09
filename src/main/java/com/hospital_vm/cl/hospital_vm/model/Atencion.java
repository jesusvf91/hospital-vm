package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa una atención médica en el sistema.
 * Contiene información como fecha, hora, costo, paciente asociado y un comentario.
 */
@Entity
@Table(name = "atencion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atencion {

    /**
     * Identificador único de la atención.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha de la atención.
     * Este campo es obligatorio.
     */
    @Column(name = "fecha_atencion", nullable = false)
    private String fechaAtencion;

    /**
     * Hora de la atención.
     * Este campo es obligatorio.
     */
    @Column(name = "hora_atencion", nullable = false)
    private String horaAtencion;

    /**
     * Costo de la atención.
     * Este campo es obligatorio.
     */
    @Column(name = "costo", nullable = false)
    private Double costo;

    /**
     * Comentario adicional sobre la atención.
     * Este campo es opcional.
     */
    @Column(name = "comentario")
    private String comentario;

    /**
     * Paciente asociado a la atención.
     * Este campo es obligatorio y representa una relación muchos-a-uno con la entidad {@link Paciente}.
     */
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

}
