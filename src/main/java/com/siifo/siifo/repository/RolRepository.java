package com.siifo.siifo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long>{
    
}
