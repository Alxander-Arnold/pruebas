package com.siifo.siifo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "encuestaSatisfaccion")
public class Encuesta_satisfaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncuestaSatisfaccion;

    @Column(name = "fechaEncuesta", nullable = true)
    private Date fechaEncuesta;

    @Column(name = "calificacion", nullable = true)
    private int calificacion;

    @Column(name = "comentario", nullable = true)
    private String comentario;

    @OneToOne
    @JoinColumn(name = "detalle_idDetalleEvento")
    private Detalle_evento detalle_evento;
}
