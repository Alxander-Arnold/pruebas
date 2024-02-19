package com.siifo.siifo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * este controller manjera las peticiones para poder logearse al sistema
 * como nota: podemos dejar aqui tammbien el regsitro de usuarios
 * administrativos dado la conveniencia de entitys
 */

import com.siifo.siifo.utils.loginUtils;

@RestController
@RequestMapping(path="api/login")
public class LoginController {
    // debemos modificar el service de usuario(o crear servicios especiales)
    // con el fin de solo solicitar los datos basicos de las credenciales de acceso
    // debemos intentar mantener la secion iniciada hasta que se cierre
    // las rutas deberian ser accesibles solo si estas logeado.
    @Autowired
    private loginUtils login;

    @GetMapping
    public boolean accessMoment(@RequestParam String email){
        return login.getEmail(email);
    }

    
}
 