<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@page import="dam2.dii.p21.DAO.UsuarioDAOMemoria"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <title>Editar perfil</title>
</head>
<script>
    function guardarCambios() {
        var respuesta = confirm("¿Seguro que desea guardar las modificaciones a este Usuario?");
        if (respuesta == true) {
            return true;
        } else {
            return false;
        }
    }
</script>
<%
    Usuario usuarioEditable = (Usuario) request.getSession().getAttribute("usuarioEditable");
	String error_email = (String) request.getAttribute("error_email");

%>
<div class="ventana">
    <div class="titulo">
        <h2>Cambiar Datos</h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=editar&id_us=<%=usuarioEditable.getId_us()%>" method="post">
        <fieldset>   
            <div class="campo">
                <label for="email">Email:<span>*</span></label>
                <br>  
                <input class="caja-texto" type="text" id="email" name="email" placeholder="Email" value="<%=usuarioEditable.getEmail()%>" title="Debe introducir un email de usuario" required>       
            </div>
<%
    if (error_email != null) {
%>
            <div class="campo" class="error_nombre"><p class="error"><%=error_email%></p></div>
<%
    }
%>
            <div class="campo">
                <label for="nombre">Nombre:<span>*</span></label>
                <br>  
                <input class="caja-texto" type="text" id="nombre" name="nombre" placeholder="Nombre" value="<%=usuarioEditable.getNombre()%>" title="Debe introducir un nombre" required>
            </div>
            <div class="campo">
                <label for="apellido">Primer apellido:<span>*</span></label>
                <br>  
                <input class="caja-texto" type="text" id="apellido" name="apellido" placeholder="Primer apellido" value="<%=usuarioEditable.getApellido()%>" title="Debe introducir un apellido" required>
            </div>
            <div class="campo">
                <label for="apellido2">Segundo apellido:</label>
                <br>  
                <input class="caja-texto" type="text" id="apellido2" name="apellido2" placeholder="Segundo apellido" value="<%=usuarioEditable.getApellido2()%>" title="Puede introducir un segundo apellido">
            </div>
            <div class="campo">
                <label for="telefono">Teléfono:</label>
                <br>  
                <input class="caja-texto" type="tel" id="telefono" name="telefono" placeholder="Teléfono" value="<%=usuarioEditable.getTelefono()%>" title="Puede introducir un teléfono" pattern="[0-9]{9}">
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="edit" value="Guardar" onclick="return guardarCambios()"/>
                </div>
                <div>
                    <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=inicio">
                        <input class="boton" type="button" name="cancelar" value="Cancelar"/>
                    </a>
                </div>
            </div> 
        </fieldset>
    </form>
</div>