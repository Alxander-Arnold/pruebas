package com.siifo.siifo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Evento;
import com.siifo.siifo.repository.EventoRepository;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository; 
    
    public List<Evento> getEventosList(){
        return (List<Evento>) eventoRepository.findAll();
    }

}
