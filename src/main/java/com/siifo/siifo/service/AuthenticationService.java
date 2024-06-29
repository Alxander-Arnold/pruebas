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

    
    private boolean isAuthCoor = false;

    public boolean isUserAuthenticadedCoor() {

        return isAuthCoor;
    }

    public void setUserAuthCoor(boolean isAuthCoor) {
        this.isAuthCoor = isAuthCoor;
    }

}