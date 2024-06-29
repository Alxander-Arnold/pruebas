package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Lista_elementos_por_evento;
import com.siifo.siifo.repository.ListaElementosRepository;

@Service
public class ListaElementosService {
    
    @Autowired
    ListaElementosRepository listaElementosRepository;

    public List<Lista_elementos_por_evento> getListaElementos(){
        return (List<Lista_elementos_por_evento>) listaElementosRepository.findAll();
    }

    public Optional<Lista_elementos_por_evento> getListaEbyId(Long id){
        return (Optional<Lista_elementos_por_evento>) listaElementosRepository.findById(id);
    }

    public int saveOrUpdate(Lista_elementos_por_evento le){
        int res =0;
        Lista_elementos_por_evento listaElementos = listaElementosRepository.save(le);
        if (!listaElementos.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        listaElementosRepository.deleteById(id);;
    }

}
