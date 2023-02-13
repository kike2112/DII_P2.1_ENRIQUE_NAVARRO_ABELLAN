package dam2.dii.p21.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dam2.dii.p21.modelo.Usuario;
import dam2.dii.p21.servicio.Agenda;



/**
 * Servlet implementation class AccesoUsuario
 */
@WebServlet("/AccesoUsuario")
public class AccesoUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesoUsuario() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		if (opcion == null) {
			opcion = "";
		}
		String id_us = request.getParameter("id_us");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String apellido2 = request.getParameter("apellido2");
	    String email = request.getParameter("email");
		if (email == null) {
			email = "";
		}
	    String telefono = request.getParameter("telefono");
	    String pass = request.getParameter("pass");
		if (pass == null) {
			pass = "";
		}
	    
	    Usuario usuarioTemp = new Usuario(nombre, apellido, apellido2, email, telefono, pass);       
	
		if (opcion.equals("registro")) { //Deriva a la vista de registro
			request.setAttribute("email", email);
			request.setAttribute("usuarioTemp", usuarioTemp);
			request.setAttribute("opcion", "registro");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (opcion.equals("login")) { //Deriva a la vista de login	
			request.setAttribute("opcion", "login");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (opcion.equals("perfil")) {
			request.getSession().setAttribute("opcion", "perfil");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (opcion.equals("pintaEditar")) { //Deriva a la vista de perfil editable
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin() && id_us != null) {
				Usuario usuarioEditable = Agenda.cargarUsuario(id_us);
				if (usuarioEditable != null) {
					request.getSession().setAttribute("usuarioEditable", usuarioEditable);
				} else {
					request.getSession().setAttribute("opcion", "noTieneAcceso");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} else {
				request.getSession().setAttribute("usuarioEditable", usuario);
			}
			request.getSession().setAttribute("opcion", "editarPerfil");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (opcion.equals("pintaEditarPass")) {
			request.getSession().setAttribute("opcion", "editarPass");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (opcion.equals("eliminar")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario != null) {
				if (usuario.isRolAdmin() && !usuario.getId_us().equals(id_us)) {
					ArrayList<Usuario> listaUsuariosClientes = Agenda.eliminarUsuario(id_us);
					request.getSession().setAttribute("listaUsuariosClientes", listaUsuariosClientes);
					request.getSession().setAttribute("opcion", "acceso_admin");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				} else {
					request.getSession().setAttribute("opcion", "noTieneAcceso");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} else {
				request.getSession().setAttribute("opcion", "noTieneAcceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		} else if (opcion.equals("cerrarSesion")) {
			request.getSession().setAttribute("opcion", null);
			request.getSession().setAttribute("usuario", null);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (opcion.equals("inicio")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin()) {
				request.getSession().setAttribute("opcion", "acceso_admin");
			} else {
				request.getSession().setAttribute("opcion", null);
			}	
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else if (opcion.equals("")) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		if (opcion == null) {
			opcion = "";
		}
		String id_us = request.getParameter("id_us");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String apellido2 = request.getParameter("apellido2");
	    String email = request.getParameter("email");
		if (email == null) {
			email = "";
		}
	    String telefono = request.getParameter("telefono");
	    String pass = request.getParameter("pass");
		if (pass == null) {
			pass = "";
		}
	    String pass2 = request.getParameter("pass2");
	    String pass3 = request.getParameter("pass3");
	    
	    Usuario usuarioTemp = new Usuario(nombre, apellido, apellido2, email, telefono, pass);       
	
		if (opcion.equals("agregar")) { //Formaliza el registro y lo añade a la Base de Datos, luego la manda a la vista de bienvenida.
			ArrayList<Usuario> listaUsuarios = Agenda.cargarUsuarios();//Carga los usuarios que podrán ver los administradores
			HashMap<String,String> mensaje = Agenda.formalizarRegistro(email, pass, pass2);
			if (mensaje.containsKey("error_email") || mensaje.containsKey("error_pass")) {
				request.setAttribute("error_email", mensaje.get("error_email"));
				request.setAttribute("error_pass", mensaje.get("error_pass"));
				request.setAttribute("email", email);
				request.setAttribute("usuarioTemp", usuarioTemp);
				request.setAttribute("opcion", "registro");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else { // exito. pasar usuario y sesion		 
				Usuario usuario = Agenda.RegistrarUsuario(usuarioTemp);			
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("opcion", "acceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		} else if (opcion.equals("acceso")) { //Formaliza el logueo. Hace comprobaciones hasta que se loguea correctamente o lo manda al registro
			ArrayList<Usuario> listaUsuarios = Agenda.cargarUsuarios();//Carga los usuarios que podrán ver los administradores
			HashMap<String,String> mensaje = Agenda.formalizarLogueo(email, pass);		
			if (mensaje.containsKey("error_email") || mensaje.containsKey("error_pass")) {
				request.setAttribute("error_email", mensaje.get("error_email"));
				request.setAttribute("error_pass", mensaje.get("error_pass"));
				request.setAttribute("email", email);
				request.setAttribute("opcion", "login");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else { // exito. pasar usuario y sesion
				Usuario usuario = Agenda.LoguearUsuario(usuarioTemp);
				request.getSession().setAttribute("usuario", usuario);
				if (usuario.isRolAdmin()) {
					ArrayList<Usuario> listaUsuariosClientes = Agenda.getListaUsuariosClientes();
					request.getSession().setAttribute("listaUsuariosClientes", listaUsuariosClientes);
					request.getSession().setAttribute("opcion", "acceso_admin");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				} else {
					request.getSession().setAttribute("opcion", "acceso");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			}
		} else if (opcion.equals("buscador")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin()) {
				String busqueda = request.getParameter("busqueda");
				ArrayList<Usuario> listaClientesBuscados = Agenda.cargarClientesBuscados(busqueda);
				request.setAttribute("listaClientesBuscados", listaClientesBuscados);		
				request.setAttribute("opcionListado", "opcionBusqueda");
				request.setAttribute("busqueda", busqueda);
				request.getSession().setAttribute("opcion", "acceso_admin");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("opcion", "noTieneAcceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}	
		} else if (opcion.equals("editar")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (usuario.isRolAdmin() || (!usuario.isRolAdmin() && usuario.getId_us().equalsIgnoreCase(id_us) )) {
				usuarioTemp.setId_us(id_us);
				HashMap<String,String> mensaje = Agenda.formalizarEdicionPerfil(usuarioTemp);
				if (mensaje.containsKey("error_email")) {
					request.setAttribute("error_email", mensaje.get("error_email"));
					request.setAttribute("usuarioTemp", usuarioTemp);
					request.setAttribute("opcion", "editarPerfil");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else { // exito. cambiar datos		 		
					Agenda.actualizarUsuario(usuarioTemp);
					usuario = (Usuario) request.getSession().getAttribute("usuario");
		            if (usuario.isRolAdmin()) {
		            	ArrayList<Usuario> listaUsuariosClientes = Agenda.getListaUsuariosClientes();
						request.getSession().setAttribute("listaUsuariosClientes", listaUsuariosClientes);
		            	request.getSession().setAttribute("opcion", "acceso_admin");
					} else {
						request.getSession().setAttribute("opcion", "acceso");
					}		
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} else {
				request.getSession().setAttribute("opcion", "noTieneAcceso");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}	
		} else if (opcion.equals("editaPass")) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			HashMap<String,String> mensaje = Agenda.formalizarEdicionPass(usuario, pass, pass2, pass3);
			if (mensaje.containsKey("error_pass")) {
				request.setAttribute("error_pass", mensaje.get("error_pass"));
				request.getSession().setAttribute("opcion", "editarPass");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else { // exito. cambiar contraseñas
				Agenda.actualizarPassUsuario(usuario, pass3);
	            if (usuario.isRolAdmin()) {
	            	request.getSession().setAttribute("opcion", "acceso_admin");
				} else {
					request.getSession().setAttribute("opcion", "acceso");
				}		
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}	
		} else if (opcion.equals("")) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}