package com.siifo.siifo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Proveedor;

@Repository
public interface ProveedorRespository extends CrudRepository<Proveedor, Long>{

    
} 
