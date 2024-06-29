package com.siifo.siifo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Provedor;

@Repository
public interface ProveedorRespository extends CrudRepository<Provedor, Long>{

    
} 
