//evento que al dar click en las tres lineas mostrara y ocultara el side-menu

let arrow = document.querySelectorAll(".arrow");
for (var i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e) => {
    let arrowParent = e.target.parentElement.parentElement; //selecting main parent of arrow
    arrowParent.classList.toggle("showMenu");
  });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});



//formulario pantalla
const form = document.getElementById("form");

const mostrarDashboard = document.getElementById("mostrarDashboard");

const hideDashboard = document.getElementById("dashboard") //oculatar dashboard

//-------------------------- sidebar-modulos -------------\\
//logistica
const registrarEvento = document.getElementById("registrarEvento")
const consultarEvento = document.getElementById("consultarEvento")
const listaEvento = document.getElementById("listaEvento")

const registarEmpleado = document.getElementById("registarEmpleado")
const consultarEmpleado = document.getElementById("consultarEmpleado")

const generarReporteLogistica = document.getElementById("generarReporteLogistica")

//inventario
const registrarProducto = document.getElementById("registrarProducto")
const consultarProducto = document.getElementById("consultarProducto")

const registrarProveedor = document.getElementById("registrarProveedor")
const consultarProveedor = document.getElementById("consultarProveedor")

const generarReporteInventario = document.getElementById("generarReporteInventario")

//ventas
const registrarOV = document.getElementById("registrarOV")
const consultarOV = document.getElementById("consultarOV")

const registrarOC = document.getElementById("registrarOC")
const consultarOC = document.getElementById("consultarOC")

//-------------------------- logistica -------------\
const agregarEvento = document.getElementById("agregarEvento")
const formularioConsultarVenta = document.getElementById("formularioConsultarVenta")
const agregarFormEmpleado = document.getElementById("agregarFormEmpleado")
const formularioConsultarEmpleado = document.getElementById("formularioConsultarEmpleado");
const formConsultaEvento = document.getElementById("formConsultaEvento");
const buscarEvento = document.getElementById("buscarEvento");
const agregarListaEvento = document.getElementById("agregarListaEvento");
const buscarEmpleado = document.getElementById("buscarEmpleado");
const consultarFormEmpleado = document.getElementById("consultarFormEmpleado");


//Registrar evento
registrarEvento.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  agregarEvento.style.display = "block";

  //agregar empleado boton
  const contenedorCampos = document.getElementById("contenedorCampos");
  const agregarEmpleado = document.getElementById("agregarEmpleado");
  let contadorEmpleados = 1;
  let datalistClon;
  let nuevoCampo;
  agregarEmpleado.addEventListener("click", () => {
    contadorEmpleados++;

    nuevoCampo = document.createElement("input");
    nuevoCampo.list = "empleado";
    nuevoCampo.classList = "form-control my-4 py-2 descartarEmpleados";
    nuevoCampo.id = "agregarEmpleados" + contadorEmpleados;
    nuevoCampo.name = "empleado No." + contadorEmpleados;
    nuevoCampo.placeholder = "agrega al empleado " + contadorEmpleados;

    const datalist = document.getElementById("empleado");
    datalistClon = datalist.cloneNode(true);
    datalistClon.setAttribute("class", "removeDatalist")
    nuevoCampo.setAttribute("list", "empleado");

    contenedorCampos.appendChild(nuevoCampo);
    contenedorCampos.appendChild(datalistClon);
  });

  
  //boton eliminar empleado
  const descartarEmpleado = document.getElementById("descartarEmpleado") //este es el boton
  descartarEmpleado.addEventListener("click", ()=> {
    if(contadorEmpleados > 1){
    const ultimoCampo = document.getElementById("agregarEmpleados" + contadorEmpleados);
    contenedorCampos.removeChild(ultimoCampo)
    contadorEmpleados--;
    }
    
  });
})

//consulta evento
consultarEvento.addEventListener("click", function() {
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  formularioConsultarVenta.style.display = "block";
})
//formulario post consulta
function guardarValor(){
  const valor= document.getElementById("idEvento").value;
  return valor;
};

buscarEvento.addEventListener("click", function(){
  const id = guardarValor();
  //falta leer el id del formulario actual
  let url=`http://localhost:8081/siifo/logistica/evento/${id}`;
  //Esta funcion nos permitira obtener los datos que necesitamos
  function requestInventario(url){
    return new Promise((resolve, reject)=>{
      //aqui manejamos cualquier error en la url
      fetch(url)
      .then(response =>{ //promise chain
        if(!response.ok){
          throw new Error('error en la url')
        }
        return response.json();
      })
      //devolvemos datos
      .then(data => {
        resolve(data);
      })
      //devolvemos error
      .catch(error=>{
        reject(error);
      });
    });
  }
  //solicitamos los datos
  requestInventario(url)
    //resolve
    .then(data=>{
      for(const datas in data){
        console.log(datas, data[datas]);
      }
      // console.log('Respuesta', data);
      formConsultaEvento.style.display= "block";
      const myform = document.getElementById("formEditDetalleEvento");
      myform.elements.idDetalleEvento.value= data.idDetalleEvento;
      myform.elements.nombreCliente.value= data.nombreCliente;
      myform.elements.cedulaCliente.value= data.cedulaCliente;
      myform.elements.paqueteEvento.value= data.paqueteEvento;
      myform.elements.direccionEvento.value=data.direccionEvento;
      myform.elements.fechaEvento.value=data.fechaEvento;
      myform.elements.aforoEvento.value= data.aforoEvento;
      myform.elements.valorEvento.value= data.valorEvento;
      myform.elements.observacion.value= data.observacion;
      //llaves foraneas
      myform.elements.usuario.value= data.usuario.nombreUsuario;
      myform.elements.evento.value= data.evento.nombreEvento;
      
      //mostrar datos
    
    })
    //reject
    .catch(error=>{
      console.log('Error', error);
    });
})

//agregar lista de evento

listaEvento.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProveedor.style.display = "none";
  agregarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none";
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  consultarFormEmpleado.style.display='none';

  agregarListaEvento.style.display = "block";
})

//Registrar empleado
registarEmpleado.addEventListener("click", function(){
  hideDashboard.style.display = "none"
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';
  
  agregarFormEmpleado.style.display = "block"
})

//Consultar empleado
consultarEmpleado.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  formularioConsultarEmpleado.style.display = "block";
})
//post Consulta de empleado
function guardarI(){
  const valor = document.getElementById("identificacion").value;
  return valor;
}
buscarEmpleado.addEventListener("click", function(){
  const num = guardarI();
  let url=`http://localhost:8081/siifo/logistica/empleados/${num}`;
  //Esta funcion nos permitira obtener los datos que necesitamos
  function requestUsuarios(url){
    return new Promise((resolve, reject)=>{
      //aqui manejamos cualquier error en la url
      fetch(url)
      .then(response =>{ //promise chain
        if(!response.ok){
          throw new Error('error en la url')
        }
        return response.json();
      })
      //devolvemos datos
      .then(data => {
        resolve(data);
      })
      //devolvemos error
      .catch(error=>{
        reject(error);
      });
    });
  }
  //solicitamos los datos
  requestUsuarios(url)
    //resolve
    .then(data=>{
      consultarFormEmpleado.style.display= "block"; //formulario post consulta
      //mostrar datos
      const myForm = document.getElementById("formEditEmpleado"); //insertamos dentro de las propiedades del form
      myForm.elements.idUsuario.value = data.idUsuario;
      myForm.elements.tipoIdentificacion.value = data.tipoIdentificacion;
      myForm.elements.numeroIdentificacion.value = data.numeroIdentificacion;
      myForm.elements.nombreUsuario.value = data.nombreUsuario;
      myForm.elements.apellidoUsuario.value = data.apellidoUsuario;
      myForm.elements.numeroUsuario.value = data.numeroUsuario;
      myForm.elements.correoUsuario.value = data.correoUsuario;
      myForm.elements.contraseñaUsuario.value = data.contraseñaUsuario;
      myForm.elements.rol.value = data.rol.nombreRol;
      // document.getElementById("idUsuario").value = data.idUsuario;
      // document.getElementById("tipoIdentificacion").value = data.tipoIdentificacion;
      // document.getElementById("numeroIdentificacion").value = data.numeroIdentificacion;
      // document.getElementById("nombreUsuario").value = data.nombreUsuario;
      // document.getElementById("apellidoUsuario").value = data.apellidoUsuario;
      // document.getElementById("numeroUsuario").value = data.numeroUsuario;
      // document.getElementById("correoUsuario").value = data.correoUsuario;
      // document.getElementById("contraseñaUsuario").value = data.contraseñaUsuario;
      // document.getElementById("rol").value = data.rol.nombreRol;
    })
    //reject
    .catch(error=>{
      console.log('Error', error);
    });
})



//-------------------------- Inventario -------------\
const agregarProducto = document.getElementById("agregarProducto");
const formularioConsultarProducto = document.getElementById("formularioConsultarProducto")
const agregarProveedor = document.getElementById("agregarProveedor");
const formularioConsultarProveedor = document.getElementById("formularioConsultarProveedor");
const consultaAsync = document.getElementById("productoConsultaAsync");
const botonBuscarProducto = document.getElementById("buscarProducto");

// registrar producto
registrarProducto.addEventListener("click", function(){
  
  hideDashboard.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none";
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";  
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';
  
  agregarProducto.style.display = "block";
})

// consultar Producto
consultarProducto.addEventListener("click", function(){

  hideDashboard.style.display = "none";
  agregarProveedor.style.display = "none";
  agregarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none";
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  formularioConsultarProducto.style.display = "block";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';
})
//obtener valor del input
function guardarInput(){
  const valor = document.getElementById("idProductos").value;
  return valor;
}
//
buscarProducto.addEventListener("click", function(){
  const id = guardarInput();
  console.log("hola prueba", id);
  let url=`http://localhost:8081/siifo/inventario/producto/${id}`;
  //Esta funcion nos permitira obtener los datos que necesitamos
  function requestInventario(url){
    return new Promise((resolve, reject)=>{
      //aqui manejamos cualquier error en la url
      fetch(url)
      .then(response =>{ //promise chain
        if(!response.ok){
          throw new Error('error en la url')
        }
        return response.json();
      })
      //devolvemos datos
      .then(data => {
        resolve(data);
      })
      //devolvemos error
      .catch(error=>{
        reject(error);
      });
    });
  }
  //solicitamos los datos
  requestInventario(url)
    //resolve
    .then(data=>{
      console.log('Respuesta', data);
      consultaAsync.style.display= "block";
      //mostrar datos
      document.getElementById("idProductosFormAsync").value = id;
      document.getElementById("nombreProductos").value = data.nombreProductos;
      document.getElementById("cantidad").value = data.cantidad;
      document.getElementById("precioCompra").value = data.precioCompra;
      document.getElementById("fechaEntrega").value = data.fechaEntrega;
      document.getElementById("estado").value = data.estado;
    })
    //reject
    .catch(error=>{
      console.log('Error', error);
    });
})



// Registrar Proveedor

registrarProveedor.addEventListener("click", function(){
  
  agregarProducto.style.display = "none";
  hideDashboard.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none";
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';
  
  agregarProveedor.style.display = "block";
})

consultarProveedor.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  formularioConsultarProveedor.style.display = "block";  
})



//-------------------------- Ventas -------------\
const agregarOrdenVenta = document.getElementById("agregarOrdenVenta")
const formularioConsultarOV = document.getElementById("formularioConsultarOV")
const agregarRegistroCompra = document.getElementById("agregarRegistroCompra")
const formularioConsultarOC = document.getElementById("formularioConsultarOC")

//Rregistrar orden de venta
registrarOV.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  agregarOrdenVenta.style.display = "block";
})

//Consultar orden venta
consultarOV.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none"; 
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  formularioConsultarOV.style.display = "block";
})

//Orden compra
registrarOC.addEventListener("click", function(){
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  agregarRegistroCompra.style.display = "block";
})

//Consultar Orden venta
consultarOC.addEventListener("click", function() {
  hideDashboard.style.display = "none";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  agregarListaEvento.style.display = "none";
  consultarFormEmpleado.style.display='none';

  formularioConsultarOC.style.display = "block";
})


//Dashboard
mostrarDashboard.addEventListener("click", function mostrarFormulario() {
  hideDashboard.style.display = "block";
  agregarProducto.style.display = "none";
  agregarProveedor.style.display = "none";
  formularioConsultarProducto.style.display = "none";
  formularioConsultarProveedor.style.display = "none"; 
  agregarOrdenVenta.style.display = "none";
  formularioConsultarOV.style.display = "none";
  agregarRegistroCompra.style.display = "none";
  formularioConsultarOC.style.display = "none";
  agregarEvento.style.display = "none";
  formularioConsultarVenta.style.display = "none";
  agregarFormEmpleado.style.display = "none";
  formularioConsultarEmpleado.style.display = "none";
  consultaAsync.style.display= "none";
  formConsultaEvento.style.display='none';
  consultarFormEmpleado.style.display='none';
})



