package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Provedor;
import com.siifo.siifo.repository.ProveedorRespository;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorRespository proveedorRespository;

    public List<Provedor> getProductoList(){
        return (List<Provedor>) proveedorRespository.findAll();
    }

    public Optional<Provedor> getProducto(Long id){
        return (Optional<Provedor>) proveedorRespository.findById(id);
    }

    public int saveOrUpdate(Provedor p){
        int res =0;
        Provedor provedor = proveedorRespository.save(p);
        if (!provedor.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        proveedorRespository.deleteById(id);
    }

}
