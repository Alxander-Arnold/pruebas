package com.siifo.siifo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @Column(name = "nombreEvento", nullable = true, columnDefinition = "VARCHAR(45)")
    private String nombreEvento;

    
    @Column(name = "descripcionEvento", nullable = true, columnDefinition = "VARCHAR(200)")
    private String descripcionEvento;
}
