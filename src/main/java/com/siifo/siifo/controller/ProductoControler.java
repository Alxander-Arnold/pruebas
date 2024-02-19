package com.siifo.siifo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siifo.siifo.entity.Producto;
import com.siifo.siifo.service.ProductoService;

@RestController
@RequestMapping(path = "/api/admin")
public class ProductoControler {
    @GetMapping("/demo")
    public String demo(){
        return "Holwiws";
    }

    @Autowired
    private ProductoService productoService;

	@GetMapping
    public List<Producto> getAll(){
        return productoService.getProductoList();
    }

    @GetMapping("/{productoId}")
    public Optional<Producto> getById(@PathVariable("productoId") Long productoId){
        return productoService.getProducto(productoId);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Producto producto){
        productoService.saveOrUpdate(producto);
    }
    
    @DeleteMapping("/{productoId}")
    public void saveUpdate(@PathVariable("productoId") Long productoId){
        productoService.delete(productoId);
    }



}
