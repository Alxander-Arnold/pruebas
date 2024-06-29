package com.siifo.siifo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Evento;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long>{
    
}
