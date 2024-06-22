package com.siifo.siifo.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrdenCompra")
public class Orden_Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenCompra;

    @Column(name = "estadoOrden", nullable = true, columnDefinition = "ENUM('Pagado', 'Pendiente', 'Vencido')")
    private String estadoOrden;

    @OneToMany
    @JoinColumn(name="pago_idPagos")
    private List<Pago> pago;

    @OneToOne
    @JoinColumn(name="detalleEvento_idDetalleEvento")
    private Detalle_evento detalleEvento;
}
