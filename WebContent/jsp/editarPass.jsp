<%@page import="dam2.dii.p21.DAO.UsuarioDAOMemoria"%>
<%@page import="dam2.dii.p21.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String error_pass = (String) request.getAttribute("error_pass");
%>
<title>Cambio de Contraseña</title>
<div class="ventana">
    <div class="titulo">
        <h2>Cambiar Contraseña</h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=editaPass" method="post">
        <fieldset>   
            <div class="campo">            
                <label for="pass">Contraseña actual:<span>*</span></label>  
                <br>
                <input class="caja-texto" type="password" id="pass" name="pass" value="" title="Debe introducir la contraseña actual"> 
            </div>                    
       <%
         if (error_pass != null) {
        %>
            <div class="campo" class="error_pass"><p class="error"><%=error_pass%></p></div>
        <%
         }         
       %>
            <div class="campo">            
                <label for="pass">Nueva Contraseña:<span>*</span></label>  
                <br>
                <input class="caja-texto" type="password" id="pass2" name="pass2" value="" title="Debe introducir la nueva contraseña"> 
            </div>                    
            <div class="campo"> 
                <label for="pass2">Confirme Nueva Contraseña:<span>*</span></label>
                <br>
                <input class="caja-texto" type="password" id="pass3" name="pass3" value="" title="Debe confirmar la nueva contraseña"> 
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="guardar" value="Guardar">
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