<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String emailHeader = (String) request.getAttribute("email");
    Usuario usuarioHeader = (Usuario) request.getSession().getAttribute("usuario");
%>
<header>
    <div class="contenedor" id="cabecera-container">
        <div class="contenido" id="cabecera">
            <div></div>
<%
    if (usuarioHeader == null) {
%>
            <div>      
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=login">LOGIN</a>      
<%      if (emailHeader == null) {%>                         
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=registro&email=null">REGISTRO</a>
<%      } else {%>                
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=registro&email=<%=emailHeader%>">REGISTRO</a>
<%      }%>            
            </div>
<%
    } else {
%>            
            <div>
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=perfil"><%=usuarioHeader.getNombre().toUpperCase()%></a>          
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=cerrarSesion">CERRAR SESIÓN</a>         
            </div>
<%
    }
%>
        </div>
    </div>
</header>