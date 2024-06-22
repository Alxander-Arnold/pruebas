package com.siifo.siifo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Rol;
import com.siifo.siifo.repository.RolRepository;

@Service
public class RolService {
    @Autowired 
	public RolRepository rolRepository;

    public List<Rol> getRolList(){
        return (List<Rol>) rolRepository.findAll();
    }
}
