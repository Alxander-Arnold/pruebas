package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Orden_Compra;
import com.siifo.siifo.repository.OrdenCompraRepository;

@Service
public class OrdenCompraService {
    
    @Autowired
    OrdenCompraRepository repositoryOrdenCompra;

    public List<Orden_Compra> getOcList(){
        return (List<Orden_Compra>) repositoryOrdenCompra.findAll();
    }

    public Optional<Orden_Compra> getOrdenCompra(Long id){
        return (Optional<Orden_Compra>) repositoryOrdenCompra.findById(id);
    }

    public int saveOrUpdate(Orden_Compra oc){
        int res =0;
        Orden_Compra ordenCompra = repositoryOrdenCompra.save(oc);
        if (!ordenCompra.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        repositoryOrdenCompra.deleteById(id);
    }

    //extra methods
    public void actualizarPago(Long idPago, Long idOc){
        repositoryOrdenCompra.actualizarPago(idPago, idOc);
    }


}
