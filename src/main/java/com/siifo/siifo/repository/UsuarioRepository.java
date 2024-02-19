package com.siifo.siifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuarios, Long>{

}