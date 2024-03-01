package com.siifo.siifo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siifo.siifo.entity.Usuarios;
import com.siifo.siifo.repository.UsuarioRepository;

/**
 * este controller manjera las peticiones para poder logearse al sistema
 * como nota: podemos dejar aqui tammbien el regsitro de usuarios
 * administrativos dado la conveniencia de entitys
 */

@RestController
@RequestMapping(path="api/login")
public class LoginController {
    // debemos modificar el service de usuario(o crear servicios especiales)

    @Autowired
    private final UsuarioRepository usuarioRepository;
    //documentar este constructor
    public LoginController(final UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository; 
    }


    @PostMapping
    public boolean accessMoment(@RequestParam(name = "email", required = false) String email, @RequestParam(name="password", required = false) String password){
        do{
            Optional<Usuarios> usuarios = this.usuarioRepository.findByCorreoUsuarioAndPasswordUsuario(email, password);
            System.out.println("creo que ya:" + usuarios);
            return true;
        } while(email != null && password != null);
    }

    
}
 