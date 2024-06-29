package com.siifo.siifo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siifo.siifo.entity.Detalle_evento;
import com.siifo.siifo.entity.Evento;
import com.siifo.siifo.entity.Lista_elementos_por_evento;
import com.siifo.siifo.entity.Orden_Compra;
import com.siifo.siifo.entity.Producto;
import com.siifo.siifo.entity.Provedor;
import com.siifo.siifo.entity.Rol;
import com.siifo.siifo.entity.Usuario;
import com.siifo.siifo.repository.UsuarioRepository;
import com.siifo.siifo.service.AuthenticationService;
import com.siifo.siifo.service.DetalleEventoService;
import com.siifo.siifo.service.EventoService;
import com.siifo.siifo.service.ListaElementosService;
import com.siifo.siifo.service.OrdenCompraService;
import com.siifo.siifo.service.ProductoService;
import com.siifo.siifo.service.ProveedorService;
import com.siifo.siifo.service.RolService;
import com.siifo.siifo.service.UsuarioService;

@Controller
public class CoordinadorController {
    //login
	@Autowired
	public AuthenticationService autenticador;

	//logistica

	@Autowired
	public EventoService serviceEvento;

	@Autowired
	public UsuarioService serviceUsuario;

	@Autowired
	public UsuarioRepository repositoryUsuario;

	@Autowired
	public DetalleEventoService serviceDetalleEvento;


	@Autowired
	public ListaElementosService serviceListaElementos;

	@Autowired
	public RolService serviceRol;

	//inventario
    @Autowired
    public ProductoService serviceProducto;

	@Autowired
    public ProveedorService serviceProoovedor;

	//compras
	@Autowired
	public OrdenCompraService serviceOrdenCompra;

	

    @GetMapping("/coor")
	public String admin(Model model) {
		//invetario
		model.addAttribute("producto", new Producto());
		model.addAttribute("proveedor", new Provedor());
		//Logistica
		model.addAttribute("rol", new Rol());
		model.addAttribute("detalleEvento", new Detalle_evento());
		List<Evento> evento = serviceEvento.getEventosList();
		model.addAttribute("evento", evento);
		List<Usuario> empleados = repositoryUsuario.findByClerk();
		model.addAttribute("empleados", empleados);
		model.addAttribute("usuarios", new Usuario());
		model.addAttribute("listaElementosEvento", new Lista_elementos_por_evento());
		//lista unica para la lista de elemtnos por E
		List<Detalle_evento> detalleventos = serviceDetalleEvento.getDetalleEventoList();
		model.addAttribute("detalleventos", detalleventos);
		//Empleados
		List<Rol> tipoRol = serviceRol.getRolList();
		model.addAttribute("Rol", tipoRol);
		//compras
		model.addAttribute("ordenCompra", new Orden_Compra());

		// Obtener datos de eventos por mes
		List<Object[]> eventosPorMes = serviceDetalleEvento.obtenerConteoEventosPorMes();

		int[] eventosMensuales = new int[12];
		for (Object[] result : eventosPorMes) {
			int mes = ((Number) result[0]).intValue();
			int conteo = ((Number) result[1]).intValue();
			eventosMensuales[mes - 1] = conteo;
		}
		model.addAttribute("eventosMensuales", eventosMensuales);

		if(autenticador.isUserAuthenticadedCoor()){
			autenticador.setUserAuth(false);
			return "coordinador";
			
		} else {
			return "redirect:/";
		}
	}

	//-------------------------------------------- Login ---------------------------------------

	@GetMapping("/salirCoor")
	public String salir(){

		autenticador.setUserAuthCoor(false);
		return "redirect:/";
	}

	//--------------------------------------------inventario---------------------------------------

	//registro producto 
    @PostMapping("/registerCoor")
	public String registroProducto(@Validated Producto producto,Model model) {
		serviceProducto.saveOrUpdate(producto);
		return "redirect:/coor";
	}

	// guardar cambios
    @PostMapping("/editProductoCoor")
    public String productoEdit(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", new Producto());
        serviceProducto.saveOrUpdate(producto); // guardamos en la DB si estan todos los datos
        System.out.println("Se actualizo correctamente" + producto.getIdProductos().toString());
        return "redirect:/admin";
    }

	@RequestMapping("/deleteProductoCoor")
    public String deletePro(Model model) {
        long idProductos = 1;
		serviceProducto.delete(idProductos);
        System.out.println("se elimino el id: " + idProductos);
        return "redirect:/admin";
    }
	

}
