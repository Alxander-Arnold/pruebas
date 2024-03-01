package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Producto;
import com.siifo.siifo.entity.Usuarios;
import com.siifo.siifo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    //metodos comunes

    // public List<Usuarios> getUsersList(){
    //     return usuarioRepository.findAll();
    // }

    // public Optional<Usuarios> getUser(Long id){
    //     return usuarioRepository.findById(id);
    // }

    // public void saveOrUpdate(Usuarios usuario){
    //     usuarioRepository.save(usuario);
    // }

    // public void delete(Long id){
    //     usuarioRepository.deleteById(id);
    // }

}
