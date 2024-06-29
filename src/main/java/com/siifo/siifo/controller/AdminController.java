package com.siifo.siifo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siifo.siifo.entity.Detalle_evento;
import com.siifo.siifo.entity.Evento;
import com.siifo.siifo.entity.Lista_elementos_por_evento;
import com.siifo.siifo.entity.Orden_Compra;
import com.siifo.siifo.entity.Pago;
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
import com.siifo.siifo.service.PagoService;
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

	//compras
	@Autowired
	public OrdenCompraService serviceOrdenCompra;

	@Autowired
	public PagoService servicePago; 

	
	//dashboard
    @GetMapping("/admin")
	public String admin(ModelMap model) {
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
		model.addAttribute("pago", new Pago());
		List<Pago> pagos = servicePago.getPagosList();
		model.addAttribute("pagos", pagos);

        // Obtener datos de eventos por mes
		List<Object[]> eventosPorMes = serviceDetalleEvento.obtenerConteoEventosPorMes();

        int[] eventosMensuales = new int[12];
        for (Object[] result : eventosPorMes) {
            int mes = ((Number) result[0]).intValue();
            int conteo = ((Number) result[1]).intValue();
            eventosMensuales[mes - 1] = conteo;
        }
        model.addAttribute("eventosMensuales", eventosMensuales);
		
		if(autenticador.isUserAuthenticaded()){
			autenticador.setUserAuthCoor(false);
			return "administrador";
		} else {
			return "redirect:/";
		}

	}

	//-------------------------------------------- Login ---------------------------------------

	//deslogueo
	@GetMapping("/salir")
	public String salir(){

		autenticador.setUserAuth(false);
		return "redirect:/";
	}

	//--------------------------------------------inventario---------------------------------------
	//registro producto 
    @PostMapping("/register")
	public String registroProducto(@Validated Producto producto, BindingResult result, RedirectAttributes redirectAttributes) {
		// alerta de error de registro
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar el producto. Verifique los campos e intente nuevamente.");
			return "redirect:/admin";
		}
	

		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceProducto.saveOrUpdate(producto);
			// alerta de registro exitoso
			redirectAttributes.addFlashAttribute("successMessage", "Producto registrado exitosamente.");
			return "redirect:/admin";
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "Producto registrado exitosamente.");
			return "redirect:/coor";
		}
		
	}

	// guardar cambios
    @PostMapping("/editProducto")
    public String productoEdit(@ModelAttribute("producto") Producto producto, Model model, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al modificar los cambios. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		model.addAttribute("producto", new Producto());

		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceProducto.saveOrUpdate(producto); // guardamos en la DB si estan todos los datos
			redirectAttributes.addFlashAttribute("successMessage", "producto actualizado");
			return "redirect:/admin";
		}
		else {
			serviceProducto.saveOrUpdate(producto); // guardamos en la DB si estan todos los datos
			redirectAttributes.addFlashAttribute("successMessage", "producto actualizado");
			return "redirect:/coor";
		}
    }
	// delete producto
    @RequestMapping("/deleteProducto")
    public String deletePro(Model model) {
        long idProductos = 1;
		serviceProducto.delete(idProductos);
        System.out.println("se elimino el id: " + idProductos);
        
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			return "redirect:/admin";
		}
		else {
			return "redirect:/coor";
		}
    }
	//provedor
	@PostMapping("/save")
	public String registroProveedor(@Validated Provedor proveedor, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar el proveedor. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceProoovedor.saveOrUpdate(proveedor);
			redirectAttributes.addFlashAttribute("successMessage", "proveedor registrado exitosamente.");
			return "redirect:/admin";
		}
		else {
			serviceProoovedor.saveOrUpdate(proveedor);
			redirectAttributes.addFlashAttribute("successMessage", "proveedor registrado exitosamente.");
			return "redirect:/coor";
		}
	}

	//--------------------------------------------logistica---------------------------------------
	//guardar evento
	@PostMapping("/registroEvento")
	public String registroDetalleEvento(@Validated Detalle_evento detallevento, BindingResult result, RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar el evento. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";  // Asegúrate de que esta URL sea la correcta para mostrar el formulario nuevamente
        }
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceDetalleEvento.saveOrUpdate(detallevento);
			redirectAttributes.addFlashAttribute("successMessage", "Evento registrado exitosamente.");
			return "redirect:/admin";
		}
		else {
			serviceDetalleEvento.saveOrUpdate(detallevento);
			redirectAttributes.addFlashAttribute("successMessage", "Evento registrado exitosamente.");
			return "redirect:/coor";
		}

	}
	//editar evento
	@PostMapping("/editDetalleEvento")
	public String editarEvento(@ModelAttribute("detalleEvento") Detalle_evento detalleEvento, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al modificar los cambios. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		model.addAttribute("detalleEvento", new Detalle_evento());
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceDetalleEvento.saveOrUpdate(detalleEvento);
			redirectAttributes.addFlashAttribute("successMessage", "evento actualizado");
			return "redirect:/admin";
		}
		else {
			serviceDetalleEvento.saveOrUpdate(detalleEvento);
			redirectAttributes.addFlashAttribute("successMessage", "evento actualizado");
			return "redirect:/coor";
		}
	}

	//eliminar evento
	// @RequestMapping("/logistica/deleteEvento/{num}")
    // public String deleteEvento(@PathVariable Long num, Model model, BindingResult result, RedirectAttributes redirectAttributes){
	// 	if (result.hasErrors()) {
    //         redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el evento. Verifique los campos e intente nuevamente.");
    //         return "redirect:/admin";
    //     }

	// 	//TODO: PENDIENTE!
    //     serviceDetalleEvento.delete(num);
		
	// 	// Manejo de roles
	// 	if (autenticador.isUserAuthenticaded() == true) {
	// 		return "redirect:/admin";
	// 	}
	// 	else {
	// 		return "redirect:/coor";
	// 	}
    // }

	//lista elementos por evento
	@PostMapping("/agregarListaEvento")
	public String agregarLista(@Validated Lista_elementos_por_evento lista, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar la lista. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceListaElementos.saveOrUpdate(lista);
			redirectAttributes.addFlashAttribute("successMessage", "Lista agregada");
			return "redirect:/admin";
		}
		else {
			serviceListaElementos.saveOrUpdate(lista);
			redirectAttributes.addFlashAttribute("successMessage", "Lista agregada");
			return "redirect:/coor";
		}
	}

	//registro empleado
	@PostMapping("/registroEmpleado")
	public String agregarEmleado(@Validated Usuario usuario, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al registrar el empleado. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceUsuario.saveOrUpdate(usuario);
			redirectAttributes.addFlashAttribute("successMessage", "Empleado Registrado");
			return "redirect:/admin";
		}
		else {
			serviceUsuario.saveOrUpdate(usuario);
			redirectAttributes.addFlashAttribute("successMessage", "Empleado Registrado");
			return "redirect:/coor";
		}
	}
	
	//edit empleado
	@PostMapping("/editEmpleado")
	public String editarEmpleado(@ModelAttribute("usuarios") Usuario usuarios, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al modificar los cambios. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		model.addAttribute("usuarios", new Usuario());
		
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceUsuario.saveOrUpdate(usuarios);
			redirectAttributes.addFlashAttribute("successMessage", "Empleado Modificado");
			return "redirect:/admin";
		}
		else {
			serviceUsuario.saveOrUpdate(usuarios);
			redirectAttributes.addFlashAttribute("successMessage", "Empleado Modificado");
			return "redirect:/coor";
		}
	}

	//--------------------------------------------Compras :D---------------------------------------

	@PostMapping("/registroOrdenCompra")
	public String postMethodOc(@Validated Orden_Compra oc, Model model, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al modificar los cambios. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			serviceOrdenCompra.saveOrUpdate(oc);
			redirectAttributes.addFlashAttribute("successMessage", "Orden De Venta Registrada");
			return "redirect:/admin";
		}
		else {
			serviceOrdenCompra.saveOrUpdate(oc);
			redirectAttributes.addFlashAttribute("successMessage", "Orden De Venta Registrada");
			return "redirect:/coor";
		}
	}

	//registro de pago
	@PostMapping("/registroPago")
	public String postMethod(@Validated Pago p, Model model, BindingResult result, RedirectAttributes redirectAttributes){
		if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al modificar los cambios. Verifique los campos e intente nuevamente.");
            return "redirect:/admin";
        }
		
		
		// Manejo de roles
		if (autenticador.isUserAuthenticaded() == true) {
			servicePago.saveOrUpdate(p);
			serviceOrdenCompra.actualizarPago(p.getIdPagos(), p.getIdPagos());
			redirectAttributes.addFlashAttribute("successMessage", "Pago Registrado");
			return "redirect:/admin";
		}
		else {
			servicePago.saveOrUpdate(p);
			redirectAttributes.addFlashAttribute("successMessage", "Pago Registrado");
			return "redirect:/coor";
		}
	}
	

}
