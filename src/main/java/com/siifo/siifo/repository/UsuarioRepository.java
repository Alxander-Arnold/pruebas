package com.siifo.siifo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Usuarios;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends CrudRepository <Usuarios, Integer>{

    Optional<Usuarios> findByCorreoUsuarioAndPasswordUsuario(String correoUsuario, String passwordUsuario);

}