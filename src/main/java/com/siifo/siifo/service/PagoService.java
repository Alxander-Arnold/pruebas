package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Pago;
import com.siifo.siifo.repository.PagoRepository;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;

    public List<Pago> getPagosList(){
        return (List<Pago>) pagoRepository.findAll();
    }

    public Optional<Pago> getPagos(Long id){
        return (Optional<Pago>) pagoRepository.findById(id);
    }

    public int saveOrUpdate(Pago p){
        int res =0;
        Pago pago = pagoRepository.save(p);
        if (!pago.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        pagoRepository.deleteById(id);
    }
}
