package com.siifo.siifo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Pago;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Long>{
    
}
