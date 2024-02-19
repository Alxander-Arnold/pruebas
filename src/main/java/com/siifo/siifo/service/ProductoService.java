package com.siifo.siifo.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.siifo.siifo.repository.ProductoRepository;
import com.siifo.siifo.entity.Producto;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getProductoList(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(Long id){
        return productoRepository.findById(id);
    }

    public void saveOrUpdate(Producto producto){
        productoRepository.save(producto);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }
}
