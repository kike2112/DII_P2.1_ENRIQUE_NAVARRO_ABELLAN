<%@page import="java.util.ArrayList"%>
<%@page import="dam2.dii.p21.DAO.UsuarioDAOMemoria"%>
<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String opcionListado = (String) request.getAttribute("opcionListado");
	if (opcionListado == null) {
		opcionListado = "";
	}
	
    ArrayList<Usuario> listaUsuarios;
    String busqueda = "";
    String msgError = "";
	if (opcionListado.equals("opcionBusqueda")) {
%>
	 <title>Buscador</title>
<% 
	    listaUsuarios = (ArrayList<Usuario>) request.getAttribute("listaClientesBuscados");
	    busqueda = (String) request.getAttribute("busqueda");
	    if (!busqueda.equals("") && listaUsuarios.size() == 0) {
	    	msgError = "No se han encontrado coincidencias";
	    } else if (busqueda.equals("")) {
	    	msgError = "No se ha seleccionado ningún criterio de búsqueda";
	    }
	} else {
		listaUsuarios = (ArrayList<Usuario>) request.getSession().getAttribute("listaUsuariosClientes");
%>
	<title>Lista Clientes</title>
<% 
	}
%>
<head>
    <link rel="stylesheet" href="./css/style-listado.css" type="text/css"></link>
</head>
<script>
    function confirmaBorrar() {
        var respuesta = confirm("¿Seguro que desea eliminar este contacto?");
        if (respuesta == true) {
            return true;
        } else {
            return false;
        }
    }
</script>
<div class="ventana-listado">
<%	if (opcionListado.equals("opcionBusqueda")) { %>
		<h3>Resultados de Busqueda:</h3>
<%	} else { %>
		<h3>Lista de Clientes</h3>
<%  } %>
    <div class="contactos">
    <div class="contacto">
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=buscador" method="post">
        <div class="c_datos">
        </div>
        
	        <div class="c_datos2">
	            <ul>
	                <li class="nombre-bus"><label for="busqueda" class="nombre">Búsqueda:
<%   if (!busqueda.equals("")) { %>
                    <p>"<%=busqueda%>"</p>
<%   }
     if (!msgError.equals("")) { %>
                    <p class=error>"<%=msgError%>"</p>
<%   } %>                   
                    </label></li>	  
	            </ul>
	        </div>
	        <div class="c_datos3">
	            <input class="caja-busqueda" type="text" id="busqueda" name="busqueda" placeholder="Introduzca datos para búsqueda..." value="">   
	        </div>  
	        <div class="c_fin">
	            <div class="boton-bus">
	                <input class="boton" type="submit" name="busqueda" value="Buscar"/>
	            </div>
	            <div class="boton-bus">
                    <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=inicio">
                    <input class="boton" type="button" name="busqueda" value="Volver"/>
                    </a>
                </div>
	        </div>
        
    
    </form>
    </div>
    <div class="linea-busqueda">
        <div class="pre-nav"></div>
    </div>
<%
   for (int i = 0; i < listaUsuarios.size();i++) {
%>
    <div class="contacto">
        <div class="c_datos">
            <ul>
                <li class="id">id<%=listaUsuarios.get(i).getId_us()%></li>
            </ul>
        </div>
        <div class="c_datos2">
            <ul>
                <li class="nombre"><b><%=listaUsuarios.get(i).getNombre()%></b></li>
                <li><%=listaUsuarios.get(i).getApellido()%> <%=listaUsuarios.get(i).getApellido2()%></li>
            </ul>
        </div>
        <div class="c_datos3">
            <ul>
                <li>Email: <a href="mailto:<%=listaUsuarios.get(i).getEmail()%>"><%=listaUsuarios.get(i).getEmail()%></a></li>
                <li>Teléfono: <a href="tel:<%=listaUsuarios.get(i).getTelefono()%>"><%=listaUsuarios.get(i).getTelefono()%></a></li>   
            </ul>
        </div>  
        <div class="c_fin">
            <div class="botones-lis">
                <!-- <form action="< %=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditar" method="post">
					<input type="hidden" id="id_us" name="id_us" value="< %=listaUsuarios.get(i).getId_us()%>"/>
					<input class="boton" type="button" type="submit" value="Editar"/>
				</form>
				<form action="< %=request.getContextPath()%>/AccesoUsuario?opcion=eliminar" method="post">
					<input type="hidden" id="id_us" name="id_us" value="< %=listaUsuarios.get(i).getId_us()%>"/>
					<input class="boton" type="button" type="submit" value="Eliminar" onclick="return confirmaBorrar()"/>
				</form> -->
                <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditar&id_us=<%=listaUsuarios.get(i).getId_us()%>">
                <input class="boton" type="button" name="edit" value="Editar"/>
                </a>
                <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=eliminar&id_us=<%=listaUsuarios.get(i).getId_us()%>">
                <input class="boton" type="button" name="delete" value="Eliminar" onclick="return confirmaBorrar()"/>
                </a>
            </div>
        </div>
    </div>
<%
   }
%>
    </div>
</div>