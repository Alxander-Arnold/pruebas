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
@Table(name = "ordenCompra")
public class Orden_Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenCompra;

    @Column(name = "estadoOrden", nullable = true, columnDefinition = "ENUM('Pagado', 'Pendiente', 'Vencido')")
    private String estadoOrden = "Pendiente";

    @OneToOne
    @JoinColumn(name="pago_idPagos", nullable = true)
    private Pago pago;

    @OneToOne
    @JoinColumn(name="detalleEvento_idDetalleEvento")
    private Detalle_evento detalleEvento;

    
}
