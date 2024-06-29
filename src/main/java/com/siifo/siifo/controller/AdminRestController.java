package com.siifo.siifo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siifo.siifo.entity.Detalle_evento;
import com.siifo.siifo.entity.Orden_Compra;
import com.siifo.siifo.entity.Producto;
import com.siifo.siifo.entity.Usuario;
import com.siifo.siifo.repository.OrdenCompraRepository;
import com.siifo.siifo.repository.ProductoRepository;
import com.siifo.siifo.repository.UsuarioRepository;
import com.siifo.siifo.service.DetalleEventoService;
import com.siifo.siifo.service.OrdenCompraService;
import com.siifo.siifo.service.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/siifo")
public class AdminRestController {
    
    @Autowired
    ProductoService serviceProducto;

    @Autowired
    DetalleEventoService serviceDetalleEvento;

    @Autowired
    UsuarioRepository repositoryUsuario;

    @Autowired
    ProductoRepository repositoryProducto;

    @Autowired 
    OrdenCompraService serviceOrdenCompra;

    @Autowired
    OrdenCompraRepository repositoryOrdenCompra;

    //consutla de producto  //  Inventario
    @GetMapping("/inventario/producto/{idProductos}")
    public Optional getProdcutoById(@PathVariable Long idProductos){
        Optional<Producto> producto= serviceProducto.getProducto(idProductos);
        return producto;
    }

    //consulta general de productos // Inventario
    @GetMapping("/inventario/productos")
    public Producto getProductosList(){
        Producto productos = repositoryProducto.productosDisponibles();
        return productos;
    }

    //eliminar un producto
    @RequestMapping("/inventario/producto/{idProducto}/delete")
    public void getDeleteProducto(@PathVariable Long idProducto){
        serviceProducto.delete(idProducto);
    }

    //consulta detalleEvento  //  Logistica
    @GetMapping("/logistica/evento/{idEvento}")
    public Optional getDetalleEventobyId(@PathVariable Long idEvento){
        Optional<Detalle_evento> detalleEvento = serviceDetalleEvento.getDetalleById(idEvento);
        return detalleEvento;
    }
    //eliminar detallevento // Logistica
    @RequestMapping("/logistica/deleteEvento/{num}")
    public void deleteMehtodDetalle(@PathVariable Long num){
        serviceDetalleEvento.delete(num);
    }

    //consulta empleado  //  Logistica
    @GetMapping("/logistica/empleados/{num}")
    public Optional getMethodName(@PathVariable int num) {
        Optional<Usuario> usuarioByNum = repositoryUsuario.findByNumeroI(num);
        return usuarioByNum;
    }

    //consultar Orden Compra // compras
    @GetMapping("/compras/ordencompra/{id}")
    public Optional getMethodOrdenCompra(@PathVariable Long id){
        Optional<Orden_Compra> ordenC = serviceOrdenCompra.getOrdenCompra(id);
        return ordenC;
    }

    //eliminar orden compra // compras
    @RequestMapping("/compras/ordencompra/{id}/delete")
    public void getMethodDeleteOrdenCompra(@PathVariable Long id){
        serviceOrdenCompra.delete(id);
    }

    @GetMapping("/compras/ordencompra/ordenventa/{id}")
    public Orden_Compra postMethodName(@PathVariable Long id) {
        Orden_Compra oc = repositoryOrdenCompra.postOrdenVenta(id);
        return oc;
    }

}
