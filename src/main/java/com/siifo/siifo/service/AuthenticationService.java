package com.siifo.siifo.service;


import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {

    private boolean isAuth = false;

    public boolean isUserAuthenticaded() {

        return isAuth;
    }

    public void setUserAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

}
