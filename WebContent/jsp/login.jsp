<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <title>Login</title>
</head>    
<div class="ventana">
    <div class="titulo">
        <h2>Acceso Usuarios</h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=acceso" method="post">
        <fieldset>   
<%
	String error_email = (String) request.getAttribute("error_email");
	String error_pass = (String) request.getAttribute("error_pass");
	String email = (String) request.getAttribute("email");
%>

            <div class="campo">
                <label for="name">Introduzca sus datos:</label>
                <br>  
<%
	if (email != null) {
%>
                <input class="caja-texto" type="text" id="email" name="email" value="<%=email%>" title="Debe introducir un email de usuario">
<%
	} else {
%>
                <input class="caja-texto" type="text" id="email" name="email" placeholder="Email de usuario" value="" title="Debe introducir un email de usuario">
<%
    }
%>         
            </div>
<%
	if (error_email != null) {
%>
            <div class="campo" class="error_nombre"><p class="error"><%=error_email%></p></div>
<%
    }
%>
            <div class="campo">            
                <label for="pass">Contraseña:</label>  
                <br>
                <input class="caja-texto" type="password" id="pass" name="pass" value="" title="Debe introducir la contraseña">    
            </div>
<%
	if (error_pass != null) {
%>
             <div class="campo" class="error_pass"><p class="error"><%=error_pass%></p></div>
<%
    }         
%>
            <div class="campo" class="campomini" class="registro">
                <div class="campomini">
                     <a class="registro" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=registro&email=<%=email%>">Registrarse</a>
                </div>
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="aceptar" value="Aceptar">
                </div>
                <div>
                    <a href="<%=request.getContextPath()%>">
                        <input class="boton" type="button" name="cancelar" value="Cancelar"/>
                    </a>
                </div>
            </div> 
        </fieldset>
    </form>
</div>