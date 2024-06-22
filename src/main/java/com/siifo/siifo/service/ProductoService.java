package com.siifo.siifo.service;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.siifo.siifo.repository.ProductoRepository;
import com.siifo.siifo.entity.Producto;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getProductoList(){
        return (List<Producto>) productoRepository.findAll();
    }

    //cocnsulta asincrona para fomruarios dinamicos 
    @Async
    public CompletableFuture<Producto> getProductoByIdAsync(Long id){
        return CompletableFuture.completedFuture(productoRepository.findById(id).orElse(null));
    }

    public Optional<Producto> getProducto(Long id){
        return (Optional<Producto>) productoRepository.findById(id);
    }

    public int saveOrUpdate(Producto p){
        int res =0;
        Producto producto = productoRepository.save(p);
        if (!producto.equals(null)) {
            res = 1;
        }
        return res;
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }
}
