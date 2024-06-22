package com.siifo.siifo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siifo.siifo.entity.Proveedor;
import com.siifo.siifo.repository.ProveedorRespository;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorRespository proveedorRespository;

    public List<Proveedor> getProductoList(){
        return (List<Proveedor>) proveedorRespository.findAll();
    }

    public Optional<Proveedor> getProducto(Long id){
        return (Optional<Proveedor>) proveedorRespository.findById(id);
    }

    public int saveOrUpdate(Proveedor p){
        int res =0;
        Proveedor proveedor = proveedorRespository.save(p);
        if (!proveedor.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        proveedorRespository.deleteById(id);
    }

}
