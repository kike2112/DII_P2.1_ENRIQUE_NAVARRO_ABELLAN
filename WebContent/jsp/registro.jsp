<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Registro</title>
<div class="ventana">
    <div class="titulo">
        <h2>Registro de Usuario</h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=agregar" method="post">
        <fieldset>
<%
	String error_email = (String) request.getAttribute("error_email");
	String error_pass = (String) request.getAttribute("error_pass");
	String email = (String) request.getAttribute("email");
	Usuario usuarioTemp = (Usuario) request.getAttribute("usuarioTemp");
%>

            <div class="campo">
                <label for="email">Email:<span>*</span></label>
                <br>  
<%
    if (email != null) {
    	if (!email.equals("") && !email.equals("null")) {
%>
                <input class="caja-texto" type="text" id="email" name="email" value="<%=email%>" title="Debe introducir un email de usuario" required> 
<%
    	}
    } 
    if (email == null || email.equals("null")) {
%>
                <input class="caja-texto" type="text" id="email" name="email" placeholder="Email de usuario" value="" title="Debe introducir un email de usuario" required>
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
                <label for="pass">Contraseña:<span>*</span></label>  
                <br>
                <input class="caja-texto" type="password" id="pass" name="pass" value="" title="Debe introducir la contraseña" required> 
            </div>                    
       <%
         if (error_pass != null) {
        %>
            <div class="campo" class="error_pass"><p class="error"><%=error_pass%></p></div>
        <%
         }         
       %>
            <div class="campo"> 
                <label for="pass2">Confirme contraseña:<span>*</span></label>
                <br>
                <input class="caja-texto" type="password" id="pass2" name="pass2" value="" title="Debe confirmar la contraseña" required> 
            </div>
            <div class="campo">
                <label for="nombre">Nombre:<span>*</span></label>
                <br>
<%
    if (usuarioTemp.getNombre() != null) {
        if (!usuarioTemp.getNombre().equals("") && !usuarioTemp.getNombre().equals("null")) {
%>                  
                <input class="caja-texto" type="text" id="nombre" name="nombre" value="<%=usuarioTemp.getNombre()%>" title="Debe introducir un email de usuario" required>
<%
        }
    } 
    if (usuarioTemp.getNombre() == null || usuarioTemp.getNombre().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="nombre" name="nombre" placeholder="Nombre" value="" title="Debe introducir un email de usuario" required>
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="apellido">Primer apellido:<span>*</span></label>
                <br>  
<%
    if (usuarioTemp.getApellido() != null) {
        if (!usuarioTemp.getApellido().equals("") && !usuarioTemp.getApellido().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="apellido" name="apellido" value="<%=usuarioTemp.getApellido()%>" title="Debe introducir un email de usuario" required>
<%
        }
    } 
    if (usuarioTemp.getApellido() == null || usuarioTemp.getApellido().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="apellido" name="apellido" placeholder="Primer apellido" value="" title="Debe introducir un email de usuario" required>
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="apellido2">Segundo apellido:</label>
                <br>  
<%
    if (usuarioTemp.getApellido2() != null) {
        if (!usuarioTemp.getApellido2().equals("") && !usuarioTemp.getApellido2().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="apellido2" name="apellido2" value="<%=usuarioTemp.getApellido2()%>" title="Puede introducir un segundo apellido">
<%
        }
    } 
    if (usuarioTemp.getApellido2() == null || usuarioTemp.getApellido2().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="apellido2" name="apellido2" placeholder="Segundo apellido" value="" title="Puede introducir un segundo apellido">
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="telefono">Teléfono:</label>
                <br>  
<%
    if (usuarioTemp.getTelefono() != null) {
        if (!usuarioTemp.getTelefono().equals("") && !usuarioTemp.getTelefono().equals("null")) {
%>               
                <input class="caja-texto" type="tel" id="telefono" name="telefono" value="<%=usuarioTemp.getTelefono()%>" title="Puede introducir un teléfono" pattern="[0-9]{9}">
<%
        }
    } 
    if (usuarioTemp.getTelefono() == null || usuarioTemp.getTelefono().equals("null")) {
%>                
                <input class="caja-texto" type="tel" id="telefono" name="telefono" placeholder="Teléfono" title="Puede introducir un teléfono" pattern="[0-9]{9}">
<%
    }
%>            
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="registrar" value="Registrar">
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