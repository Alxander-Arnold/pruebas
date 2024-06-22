package com.siifo.siifo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Producto;

@Repository
public interface ProductoRepository extends CrudRepository <Producto, Long>{
 
    
}