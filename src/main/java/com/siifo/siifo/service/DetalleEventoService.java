package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Detalle_evento;
import com.siifo.siifo.repository.DetalleEventoRepository;

@Service
public class DetalleEventoService {
    
    @Autowired
    DetalleEventoRepository detalleEventoRepository;
    //trae la busqueda que hace el repository para saber cuantos eventos se hace al mes
    public List<Object[]> obtenerConteoEventosPorMes() {
        return detalleEventoRepository.countEventosByMonth();
    }

    public List<Detalle_evento> getDetalleEventoList(){
        return (List<Detalle_evento>) detalleEventoRepository.findAll();
    }

    public Optional<Detalle_evento> getDetalleById(Long id){
        return (Optional<Detalle_evento>) detalleEventoRepository.findById(id);
    }

    public int saveOrUpdate(Detalle_evento p){
        int res =0;
        Detalle_evento detalleEvento = detalleEventoRepository.save(p);
        if (!detalleEvento.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        detalleEventoRepository.deleteById(id);
    }
}
