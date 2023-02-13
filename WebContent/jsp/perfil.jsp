<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dam2.dii.p21.DAO.UsuarioDAOMemoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <title>Perfil Usuario</title>
    <link rel="stylesheet" href="./css/style-listado.css" type="text/css"></link>
</head>
<%
    Usuario usuarioP = (Usuario) request.getSession().getAttribute("usuario");
%>
<div class="ventana-listado">
    <h3>Perfil de Usuario</h3>
    <div class="contactos">
	    <div class="contacto">
	        <div class="c_datos">
	            <ul>
	                <li class="id">id<%=usuarioP.getId_us()%></li>
	            </ul>
	        </div>
	        <div class="c_datos2">
	            <ul>
	                <li class="nombre"><b><%=usuarioP.getNombre()%></b></li>
	                <li><%=usuarioP.getApellido()%> <%=usuarioP.getApellido2()%></li>
	            </ul>
	        </div>
	        <div class="c_datos3">
	            <ul>
	                <li>Email: <a href="mailto:<%=usuarioP.getEmail()%>"><%=usuarioP.getEmail()%></a></li>
	                <li>Teléfono: <a href="tel:<%=usuarioP.getTelefono()%>"><%=usuarioP.getTelefono()%></a></li>   
	            </ul>
	        </div>
	        <div class="c_fin">
	            <div class="boton-bus">
	                <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=inicio">
	                <input class="boton" type="button" name="busqueda" value="Volver"/>
	                </a>
	            </div>
	        </div>	        	          
	    </div>
    </div>
</div>