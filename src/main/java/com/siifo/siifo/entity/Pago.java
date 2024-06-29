package com.siifo.siifo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagos;

    @Column(name = "metodoDePago", nullable = true, columnDefinition = "ENUM('Efectivo', 'Medio_Mano')")
    private String metodoDePago;

    @Column(name = "fechaPago", nullable = true)
    private LocalDateTime fechaPago = LocalDateTime.now();

    @Column(name = "valor", nullable = true, columnDefinition = ("FLOAT"))
    private double valor;

}

