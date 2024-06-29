package com.siifo.siifo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siifo.siifo.entity.Orden_Compra;

@Repository
public interface OrdenCompraRepository extends CrudRepository<Orden_Compra, Long> {
    
    @Transactional
    @Modifying
    @Query(value="update siifo.orden_compra set estado_orden = 'Pagado', pago_id_pagos = :idpago where id_orden_compra = :idOc", nativeQuery=true)
    void actualizarPago(Long idpago, Long idOc);

    @Query(value="Select * from siifo.orden_compra where estado_orden = 'Pagado' and id_orden_compra = :id",nativeQuery=true)
    Orden_Compra postOrdenVenta(Long id);
      
}
