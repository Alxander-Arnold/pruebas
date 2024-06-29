package com.siifo.siifo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductos;
    @Column(name = "nombre_productos", nullable = true, columnDefinition = "VARCHAR(250)")
    private String nombreProductos;
    @Column(name = "precio_compra", nullable = true)
    private double precioCompra;
    @Column(name = "cantidad", nullable = true)
    private int cantidad;
    @Column(name = "fecha_entrega", nullable = true)
    private Date fechaEntrega;
    @Column(name = "estado", nullable = true, columnDefinition = "VARCHAR(250)")
    private String estado = "Disponible";
}
