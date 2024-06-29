package com.siifo.siifo.entity;

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
@Table(name = "listaElementosPorEvento")
public class Lista_elementos_por_evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idListaDeElementosPorEvento;

    // @OneToMany
    // @JoinColumn(name = "producto_idProductos")
    // private List<Producto> producto;

    @OneToOne
    @JoinColumn(name = "detalleEvento_idDetalleEvento")
    private Detalle_evento detalleEvento;

    @Column(name = "productosLista", nullable = true)
    private String productosLista;

    @OneToOne
    @JoinColumn(name = "usuarios_idUsuarios")
    private Usuario usuario;
}
