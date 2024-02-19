package com.siifo.siifo.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.siifo.siifo.entity.Usuarios;

public class loginUtils {
    @Autowired
    Usuarios usuario;

    String emailPP=usuario.getCorreoUsuario();

    public boolean getEmail(String email){
        if(emailPP.equals(email)){
            return true;
        } else{
            System.out.println("deberia ser este "+emailPP+". No este: "+email);
            return false;
        }
    }
}
