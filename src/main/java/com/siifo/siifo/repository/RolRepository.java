package com.siifo.siifo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siifo.siifo.entity.Rol;

import com.siifo.siifo.utils.RolNombre;


public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByNombreRol(RolNombre nombreRol);
}
