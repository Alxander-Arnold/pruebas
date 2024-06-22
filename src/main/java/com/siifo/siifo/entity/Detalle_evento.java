package com.siifo.siifo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalleEvento")
public class Detalle_evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEvento;

    @ManyToOne
    @JoinColumn(name = "usuarios_idUsuarios")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "evento_idEvento")
    private Evento evento;

    @Column(name = "fechaEvento", nullable = true)
    private Date fechaEvento;

    @Column(name = "aforoEvento", nullable = true)
    private int aforoEvento;

    @Column(name = "valorEvento", nullable = true, columnDefinition = "DECIMAL(10)")
    private Double valorEvento;

    @Column(name = "observacion", nullable = true, columnDefinition = "VARCHAR(200)")
    private String observacion;

    @Column(name = "nombreCliente", nullable = true, columnDefinition = "VARCHAR(45)")
    private String nombreCliente;

    @Column(name = "cedulaCliente", nullable = true)
    private int cedulaCliente;

    @Column(name = "paqueteEvento", nullable = true, columnDefinition = "ENUM('Orange', 'Blue', 'Platinum')")
    private String paqueteEvento;

    @Column(name = "direccionEvento", nullable = true, columnDefinition = "VARCHAR(200)")
    private String direccionEvento;

}

