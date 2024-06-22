package com.siifo.siifo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.siifo.siifo.entity.Detalle_evento;
import com.siifo.siifo.entity.Evento;
import com.siifo.siifo.entity.Lista_elementos_por_evento;
import com.siifo.siifo.entity.Producto;
import com.siifo.siifo.entity.Proveedor;
import com.siifo.siifo.entity.Rol;
import com.siifo.siifo.entity.Usuario;
import com.siifo.siifo.repository.UsuarioRepository;
import com.siifo.siifo.service.AuthenticationService;
import com.siifo.siifo.service.DetalleEventoService;
import com.siifo.siifo.service.EventoService;
import com.siifo.siifo.service.ListaElementosService;
import com.siifo.siifo.service.ProductoService;
import com.siifo.siifo.service.ProveedorService;
import com.siifo.siifo.service.RolService;
import com.siifo.siifo.service.UsuarioService;

@Controller
public class AdminController {

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

	
	//dashboard
    @GetMapping("/admin")
	public String admin(Model model) {
		//iventario
        model.addAttribute("producto", new Producto());
		model.addAttribute("proveedor", new Proveedor());
		//logistica
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

		if(autenticador.isUserAuthenticaded()){
			return "administrador";
			
		} else {
			return "redirect:/";
		}

	}

	//-------------------------------------------- Login ---------------------------------------

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@GetMapping("/validacionUser")
	public String ingreso(@RequestParam String correoUsuario, @RequestParam String contraseñaUsuario, Model model){

		if(correoUsuario != null && contraseñaUsuario != null ){
			
			Usuario usuario = repositoryUsuario.findByCorreo(correoUsuario, contraseñaUsuario);
			
			if(usuario != null) {
				autenticador.setUserAuth(true);
				model.addAttribute("usuario", usuario);
				
				return "redirect:/admin";				
			}
		}
		return "redirect:/login";
	}

	//deslogueo
	@GetMapping("/salir")
	public String salir(){

		autenticador.setUserAuth(false);
		return "redirect:/";
	}

	//--------------------------------------------inventario---------------------------------------
	//registros
    @PostMapping("/register")
	public String registroProducto(@Validated Producto producto,Model model) {
		serviceProducto.saveOrUpdate(producto);
		return "redirect:/admin";
	}

	@PostMapping("/save")
	public String registroProveedor(@Validated Proveedor proveedor,Model model) {
		serviceProoovedor.saveOrUpdate(proveedor);
		return "redirect:/admin";
	}

	//Consultas
	// el proceso debe hacerse desde el rest controller solo para buscar los datos
	// llenar los campos del formulario y mostrarlo mediante eventos en js
		//async
		//prueba
	// @GetMapping("/buscar/{id}")
	// public Integer cosnultaProAsync(@PathVariable Long id){
	// 	CompletableFuture<Producto> productoA = serviceProducto.getProductoByIdAsync(id);
	// 	while (id!=0) {
	// 		Producto producto = productoA.join();
	// 		System.out.println("debes ver esto: "+producto);
	// 		return 1;
	// 	}
	// 	return 0;
	// }
		//proceso dinamico
	// @GetMapping("/buscar")
	// public String consultaAsyncProd(@RequestParam Long idProductos, Model model){
	// 	CompletableFuture<Producto> producto = serviceProducto.getProductoByIdAsync(idProductos);
	// 	if(idProductos!=0 && producto!=null){
	// 		Producto productoA = producto.join();
	// 		model.addAttribute("productoA", productoA);
	// 		System.out.println("hola hola"+productoA);
	// 		return "administrador";
	// 	}else{
	// 		System.out.println("Comprueba la existencia del id o revisa el proceso del service");
	// 		return "redirect:/";
	// 	}
		
	// }
	 // guardar cambios
    @PostMapping("/editProducto")
    public String productoEdit(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", new Producto());
        serviceProducto.saveOrUpdate(producto); // guardamos en la DB si estan todos los datos
        System.out.println("Se actualizo correctamente" + producto.getIdProductos().toString());
        return "redirect:/admin";
    }
	// delete empleado
    @RequestMapping("/deleteProducto")
    public String deletePro(Model model) {
        long idProductos = 1;
		serviceProducto.delete(idProductos);
        System.out.println("se elimino el id: " + idProductos);
        return "redirect:/admin";
    }

	//--------------------------------------------logistica---------------------------------------
	//guardar
	@PostMapping("/registroEvento")
	public String registroDetalleEvento(@Validated Detalle_evento detallevento,Model model){
		serviceDetalleEvento.saveOrUpdate(detallevento);
		System.out.println("Registro: "+detallevento.toString());
		return "redirect:/admin";
	}
	//editar
	@PostMapping("/editDetalleEvento")
	public String editarEvento(@ModelAttribute("detalleEvento") Detalle_evento detalleEvento, Model model){
		model.addAttribute("detalleEvento", new Detalle_evento());
		serviceDetalleEvento.saveOrUpdate(detalleEvento);
		System.out.println("Se actualizo correctamnete el id: "+detalleEvento.getIdDetalleEvento().toString());
		return "redirect:/admin";
	}
	@RequestMapping("/deleteEvento/{idDetalleEvento}")
	public String deleteEvento(@PathVariable Long idDetalleEvento, Model model){
		serviceProoovedor.delete(idDetalleEvento);
		System.out.println("se elimino el id: " + idDetalleEvento);
		return "redirect:/admin";
	}

	//lista elementos por evento
	@PostMapping("/agregarListaEvento")
	public String agregarLista(@Validated Lista_elementos_por_evento lista, Model model){
		serviceListaElementos.saveOrUpdate(lista);
		System.out.println("Se agrego la lista: "+ lista);
		return "redirect:/admin";
	}

	//registro empleado
	@PostMapping("/registroEmpleado")
	public String agregarEmleado(@Validated Usuario usuario, Model model){
		serviceUsuario.saveOrUpdate(usuario);
		System.out.println("Se agrego el usuario: "+usuario+"con rol: "+usuario.getRol());
		return "redirect:/admin";
	}
	//edit empleado
	@PostMapping("/editEmpleado")
	public String editarEmpleado(@ModelAttribute("usuarios") Usuario usuarios, Model model){
		model.addAttribute("usuarios", new Usuario());
		serviceUsuario.saveOrUpdate(usuarios);
		System.out.println("Se actualizo el usuario con CC"+usuarios.getNumeroIdentificacion());
		return "redirect:/admin";
	}
}
