package com.siifo.siifo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siifo.siifo.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    @Query(value = "Select * from siifo.usuarios where correo_usuario = :correo AND contraseña_usuario = :contraseña", nativeQuery = true)
    Usuario findByCorreo(String correo, String contraseña);

    @Query(value = "Select * from siifo.usuarios where numero_identificacion = :num", nativeQuery = true)
    Optional<Usuario> findByNumeroI(int num);

    @Query(value="Select * from siifo.usuarios where rol_id_rol = 3", nativeQuery=true)
    List<Usuario> findByClerk();
}