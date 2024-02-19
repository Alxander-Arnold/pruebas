package com.siifo.siifo.controller;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siifo.siifo.service.ProductoService;

// import com.siifo.siifo.model.producto;

import com.siifo.siifo.entity.Producto;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class rutas {

	@Autowired
    private ProductoService productoService;

	//landing site
	// @GetMapping("/")
	// public String index() {
	// 	return "index";
	// }

	// @RequestMapping("/login")
	// public String login() {
	// 	return "login";
	// }

	@RequestMapping("/contacto")
	public String contacto() {
		return "contacto";
	}
	
	//catagories site
	@RequestMapping("/sonido")
	public String sonido() {
		return "sonido";
	}

	@RequestMapping("/banquetes")
	public String banquetes() {
		return "banquetes";
	}

	@RequestMapping("/recreacion")
	public String recreacion() {
		return "recreacion";
	}

	@RequestMapping("/colegios")
	public String colegios() {
		return "colegios";
	}

	//sub-categories-recreacion
	@RequestMapping("/recreacion/ferias")
	public String ferias() {
		return "ferias";
	} 

	@RequestMapping("/recreacion/decoraciones")
	public String decoraciones() {
		return "decoraciones";
	} 

	@RequestMapping("/recreacion/baby_shower")
	public String babyShower() {
		return "baby_showers";
	}

	@RequestMapping("/recreacion/recreacion_infantil")
	public String recreacion_infantil() {
		return "recreacion_infantil";
	}

	@RequestMapping("/recreacion/minitks")
	public String minitks() {
		return "minitks";
	}

	@RequestMapping("/recreacion/refrigerios")
	public String refrigerios() {
		return "refrigerios";
	}

	@RequestMapping("/recreacion/talleres_ludicos")
	public String talleresLudicos() {
		return "talleres_ludicos";
	}

	@RequestMapping("/recreacion/saltarines")
	public String saltarines() {
		return "saltarines";
	}

	//sub-categories-colegios
	@RequestMapping("/colegios/chaquetas_prom")
	public String chaquetasProm() {
		return "chaquetas_prom";
	}

	@RequestMapping("/colegios/jean_day")
	public String jeanDay() {
		return "jean_day";
	}

	@RequestMapping("/colegios/dia_del_nino")
	public String diaDelNino() {
		return "dia_del_nino";
	}

	@RequestMapping("/colegios/fiestas_prom")
	public String fiestas_prom() {
		return "fiestas_prom";
	}

	

	//rol site
	@RequestMapping("/admin")
	public String admin(ModelMap model) {
		model.addAttribute("producto", new Producto());   
		return "administrador";										 
	}	
	//modulo inventario:
	@GetMapping("/consultarProducto")
	public String getPlant1() {
		return "consultarProducto";
	}
	
	@GetMapping("/editarPro")
	public String getPlant2() {
		return "editarPro";
	}

	//Registrar
	@PostMapping("/register")
	public String registroProducto(@ModelAttribute("producto") Producto producto) {
		productoService.saveOrUpdate(producto);
		System.out.println("Supon que: " + producto);
		return "index";
	}
	//Consultar
	@GetMapping("/buscar/{idProducto}")
    public String getById(@RequestParam("idProducto") Long idProducto, Model model){
        Optional<Producto>productos=productoService.getProducto(idProducto);
        System.out.println("Se cargó correctamente = " + productos);
        model.addAttribute("productos", productos.orElse(null));
        return "consultaProducto";
    }
	//editar

	@RequestMapping("/editar/buscar/{idProducto}")
	public String actulizarPro(@PathVariable Long idProducto ,Model model){
		Optional<Producto>productos=productoService.getProducto(idProducto);
        System.out.println("Se cargo correctamente el id = " + productos);
        model.addAttribute("productos", productos.orElse(null));
		return "editarPro";
	}

	@PostMapping("/buscar/register")
    public String actulizarPro(@ModelAttribute("productos")Producto producto){
        productoService.saveOrUpdate(producto);
        System.out.println("Se actualizo correctamente el id: " + producto.getIdProductos().toString());
        return "index";
    }
    
	//eliminar
	@RequestMapping("/editar/delete/{idProducto}")
	public String requestMethodName(@PathVariable Long idProducto) {
		productoService.delete(idProducto);
		System.out.println("Se elimino el id: "+ idProducto);
		return "administrador";
	}
	



}
