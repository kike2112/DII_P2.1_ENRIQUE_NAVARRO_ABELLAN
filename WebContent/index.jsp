<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="./css/style.css" type="text/css"></link>
</head>
<%
    Usuario usuarioI = (Usuario) request.getSession().getAttribute("usuario");
    String opcion;
    if (usuarioI == null) {
    	opcion = (String) request.getAttribute("opcion");
    } else {
    	opcion = (String) request.getSession().getAttribute("opcion");
    }
    if (usuarioI == null && opcion == null) {
        opcion = (String) request.getSession().getAttribute("opcion");
    }
    if (opcion == null) {
        opcion = "";       
%>
    <title>Página principal</title>
<%              
    }
%> 
<body>
    <%@ include file="/jsp/header.jsp"%>
    <%@ include file="/jsp/nav.jsp"%>
    <main>
    
    
<%
    if (opcion.equals("registro")) {
%>
    <%@ include file="/jsp/registro.jsp"%>
<%
	} else if (opcion.equals("login")) {
%>        
	<%@ include file="/jsp/login.jsp"%>
<%
    } else if (opcion.equals("acceso_admin")) {
%>
    <%@ include file="/jsp/listado.jsp"%>
<%
    } else if (opcion.equals("acceso")) {
///////////// Acceso cliente
	} else if (opcion.equals("perfil")) {
%>
    <%@ include file="/jsp/perfil.jsp"%>
<%
	} else if (opcion.equals("editarPerfil")) {
%>
    <%@ include file="/jsp/editarPerfil.jsp"%>
<%
	} else if (opcion.equals("editarPass")) {
%>
    <%@ include file="/jsp/editarPass.jsp"%>
<%
    } else if (opcion.equals("noTieneAcceso")) {
%>
    <%@ include file="/jsp/noAcceso.jsp"%>
<%
    }
%>
    
    
    <br/><br/><br/><br/><br/><br/><br/>
    </main>
    <%@ include file="/jsp/footer.jsp"%> 
</body>
</html>