package com.siifo.siifo.entity;

import java.sql.Date;

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
@Table(name = "productosPorProvedor")
public class Productos_por_proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductosPorProveedor;

    @ManyToOne
    @JoinColumn(name = "productos_idProdcutos")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "proveedor_idProveedor")
    private Proveedor proveedor;

    @Column(name = "fechaEntrega", nullable = true)
    private Date fechaEntrega;

    @Column(name = "cantidad", nullable = true)
    private int cantidad;

    @Column(name = "valor", nullable = true)
    private double valor;

    @Column(name = "categoria", nullable = true, columnDefinition = "ENUM('Sonido', 'Luces', 'Decoraciones', 'Accesorios', 'Colegios', 'Otros')")
    private String categoria;
}
