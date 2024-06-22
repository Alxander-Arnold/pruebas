package com.siifo.siifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Detalle_evento;

@Repository
public interface DetalleEventoRepository extends JpaRepository <Detalle_evento, Long> {
    
}
