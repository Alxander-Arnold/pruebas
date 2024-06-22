package com.siifo.siifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Lista_elementos_por_evento;

@Repository
public interface ListaElementosRepository extends JpaRepository<Lista_elementos_por_evento, Long>{

    
}
