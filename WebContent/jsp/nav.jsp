<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String opcionNav;
    if (usuario == null) {
    	opcionNav = (String) request.getAttribute("opcion");
    } else {
    	opcionNav = (String) request.getSession().getAttribute("opcion");
    }
	if (opcionNav == null) {
		opcionNav = "";
	}
%>
<%
    if (opcionNav.equals("") || !opcionNav.equals("perfil")) {
%>
<nav>
    <div class="pre-nav"></div>
</nav>
<%
    } else if (opcionNav.equals("perfil")) {
%>
<nav>
    <div class="contenedor" id="menu-container">
        <ul id="ico-contenedor">
            <li>
                <ul class="contenido" id="menu">
                    <li><a class="opcion-menu" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditar">CAMBIAR DATOS</a></li>
                    <li><a class="opcion-menu" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditarPass">CAMBIAR CONTRASEÑA</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<%
    }
%>