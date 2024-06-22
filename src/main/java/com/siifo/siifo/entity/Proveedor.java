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
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(name = "ciudad", nullable = true, columnDefinition = "VARCHAR(45)")
    private String ciudad;

    @Column(name = "nit", nullable = true, columnDefinition = "INT(11)")
    private int nit;

    @Column(name = "nombreProveedor", nullable = true, columnDefinition = "VARCHAR(100)")
    private String nombreProveedor;
}
