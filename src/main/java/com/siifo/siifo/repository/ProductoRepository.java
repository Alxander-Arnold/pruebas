package com.siifo.siifo.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Producto;

@Repository
public interface ProductoRepository extends CrudRepository <Producto, Long>{
 
    @Query(value="Select * from siifo.productos where estado = 'Disponible'", nativeQuery = true)
    Producto productosDisponibles();
    
}