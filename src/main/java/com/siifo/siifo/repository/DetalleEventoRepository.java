package com.siifo.siifo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Detalle_evento;

@Repository
public interface DetalleEventoRepository extends JpaRepository <Detalle_evento, Long> {
    //hacemos la consulta de la fecha que hacel evento y le agregamos la funcion MONTH para ue identifique el mes
    @Query("SELECT FUNCTION('MONTH', d.fechaEvento) as month, COUNT(d) as count FROM Detalle_evento d GROUP BY FUNCTION('MONTH', d.fechaEvento)")
    List<Object[]> countEventosByMonth();
}
