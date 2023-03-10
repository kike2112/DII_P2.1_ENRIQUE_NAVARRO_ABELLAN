package dam2.dii.p21.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import dam2.dii.p21.DAO.IUsuarioDAO;
import dam2.dii.p21.DAO.UsuarioDAOMemoria;
import dam2.dii.p21.modelo.Usuario;
import dam2.dii.p21.utiles.Utiles;

public final class Agenda {
	private static final IUsuarioDAO dao = new UsuarioDAOMemoria();
	
	public static ArrayList<Usuario> cargarUsuarios() {
		ArrayList<Usuario> listaUsuarios = dao.getListaUsuarios();
		return listaUsuarios;
	}
	public static ArrayList<Usuario> getListaUsuariosClientes() {
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		ArrayList<Usuario> listaUsuariosClientes = new ArrayList<Usuario>();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (!listaUsuariosTemp.get(i).isRolAdmin()) {
				listaUsuariosClientes.add(listaUsuariosTemp.get(i));
			}
		}
		return listaUsuariosClientes;
	}
	public static ArrayList<Usuario> cargarClientesBuscados(String busqueda) {
		ArrayList<String> arrayBusqueda = Utiles.separaPalabras(busqueda);
		ArrayList<Usuario> listaClientesBuscados = getListaClientesBuscados(arrayBusqueda);
		return listaClientesBuscados;
	}
	public static ArrayList<Usuario> getListaClientesBuscados(ArrayList<String> arrayBusqueda) {
		ArrayList<Usuario> listaClientesBuscados = new ArrayList<Usuario>();	
		ArrayList<Usuario> listaUsuariosClientes = getListaUsuariosClientes();
		for (int i = 0; i < listaUsuariosClientes.size(); i++) {
			boolean agregado = false;
			for (int k = 0; k < arrayBusqueda.size(); k++) {		
				boolean usada = false;
				for (int j = 0; j <= listaUsuariosClientes.get(i).toString().length() - arrayBusqueda.get(k).length(); j++) {
					//Si coincideno pero no esta agregado el Us ni usada la palabra
					if (!agregado && !usada && listaUsuariosClientes.get(i).toString().substring(j, j + arrayBusqueda.get(k).length()).equalsIgnoreCase(arrayBusqueda.get(k).substring(0, arrayBusqueda.get(k).length()))) {
						listaClientesBuscados.add(listaUsuariosClientes.get(i));
						agregado = true;
						usada = true;
					}
				}	
			}
		}
		return listaClientesBuscados;
	}
	public static ArrayList<Usuario> eliminarUsuario(String id_us) {
		dao.borrarUsuario(id_us);
		ArrayList<Usuario> listaUsuarios = getListaUsuariosClientes();
		return listaUsuarios;
	}
	public static Usuario cargarUsuario(String id_us) {
		Usuario usuario = dao.getUsuarioById(id_us);
		return usuario;
	}
	public static Usuario RegistrarUsuario(Usuario usuarioTemp) {		
		dao.crearCliente(usuarioTemp);
		Usuario usuario = dao.getUsuarioByEmail(usuarioTemp.getEmail());
		return usuario;
	}
	public static Usuario LoguearUsuario(Usuario usuarioTemp) {
		restaurarIntentos(usuarioTemp.getEmail());
		Usuario usuario = dao.getUsuarioByEmail(usuarioTemp.getEmail());
		return usuario;
	}
	public static boolean restaurarIntentos(String email) {
		boolean restaurado = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email)) {
				listaUsuariosTemp.get(i).setIntentos(5);
				dao.actualizarUsuario(listaUsuariosTemp.get(i));
				restaurado = true;
			}
		}
		return restaurado;
	}
	public static boolean restarIntento(String email) {
		boolean restado = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email)) {
				listaUsuariosTemp.get(i).setIntentos(listaUsuariosTemp.get(i).getIntentos() - 1);
				dao.actualizarUsuario(listaUsuariosTemp.get(i));
				restado = true;
			}
		}
		return restado;
	}
	public static boolean actualizarUsuario(Usuario usuarioTemp) {
		boolean exito = false;
		if (dao.actualizarUsuario(usuarioTemp)) {
			exito = true;
		}
		return exito;
	}
	
	
	public static boolean actualizarPassUsuario(Usuario usuario, String pass3) {
		boolean exito = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getId_us().equals(usuario.getId_us())) {
				listaUsuariosTemp.get(i).setPass(pass3);
				dao.actualizarUsuario(listaUsuariosTemp.get(i));
				exito = true;
			}
		}
		return exito;
	}


	
	
	////////////Comprobadores varios///////////////
	public static boolean existeEmailEnAgenda(String email) {
		boolean existe_email = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email)) {
				existe_email = true;
			}
		}
		return existe_email;
	}
	public static boolean existeEmailEnOtroUsuario(Usuario usuario) {
		boolean existe_email = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(usuario.getEmail()) && !listaUsuariosTemp.get(i).getId_us().equalsIgnoreCase(usuario.getId_us())) {
				existe_email = true;				
			}
		}
		return existe_email;
	}
	public static boolean claveCoincide(String email, String pass) {
		boolean coincide = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email) && listaUsuariosTemp.get(i).getPass().equals(pass)) {
				coincide = true;
			}
		}
		return coincide;
	}
	public static boolean estaBloqueado(String email) {
		boolean esta_bloqueado = false;
		ArrayList<Usuario> listaUsuariosTemp = dao.getListaUsuarios();
		for (int i = 0; i < listaUsuariosTemp.size(); i++) {
			if (listaUsuariosTemp.get(i).getEmail().equals(email) && listaUsuariosTemp.get(i).getIntentos() <= 0) {
				esta_bloqueado = true;
			}
		}
		return esta_bloqueado;
	}
	/////////////////Comprobador de login ///////////////
	public static HashMap<String, String> formalizarLogueo(String email, String pass) {
		HashMap<String,String> mensaje = new HashMap<String,String>();			
			if (existeEmailEnAgenda(email) && estaBloqueado(email)) {
				mensaje.put("error_email", "El usuario con email \"" + email + "\" est?? bloqueado.");		
			} else if (pass.length() < 1) {
				mensaje.put("error_pass", "Introduzca contrase??a.");
			} else if (existeEmailEnAgenda(email)) {
				if (claveCoincide(email, pass)) {
					//exito
				} else {
					mensaje.put("error_pass", "Contrase??a incorrecta");
					if (!dao.getUsuarioByEmail(email).isRolAdmin() && pass.length() > 0) {
						Agenda.restarIntento(email);
					}	
					if (dao.getUsuarioByEmail(email).getIntentos() == 0) {
						mensaje.put("error_email", "El usuario con email \"" + email + "\" est?? bloqueado.");
					} else if (dao.getUsuarioByEmail(email).getIntentos() > 0 && pass.length() > 0 && !dao.getUsuarioByEmail(email).isRolAdmin()) {
						mensaje.put("error_pass", "Incorrecto. Le quedan \""+ dao.getUsuarioByEmail(email).getIntentos() + "\" intentos.");
					}
				}
			} else if (email.length() > 0) {
				mensaje.put("error_email", "No existe el usuario con email \"" + email + "\".");
			}
			if (email.length() < 1) {
				mensaje.put("error_email", "Introduce un email de usuario.");
			}
		return mensaje;
	}
	//////////////////Comprobador edicion de perfil////////////////////
	public static HashMap<String, String> formalizarEdicionPerfil(Usuario usuarioTemp) {
		HashMap<String,String> mensaje = new HashMap<String,String>();
		if (existeEmailEnOtroUsuario(usuarioTemp)) {
			mensaje.put("error_email", "El email \"" + usuarioTemp.getEmail() + "\" ya est?? registrado");
		}		
		return mensaje;
	}
	public static HashMap<String, String> formalizarEdicionPass(Usuario usuario, String pass, String pass2, String pass3) {
		HashMap<String,String> mensaje = new HashMap<String,String>();
		if (pass.length() < 1 || pass2.length() < 1 || pass3.length() < 1) {
			mensaje.put("error_pass", "Introduzca todos los campos requeridos.");
		} else if (claveCoincide(usuario.getEmail(), pass)) {
			if (pass2.equals(pass3)) {
				// exito // falta logica
			} else {
				mensaje.put("error_pass", "No coinciden las contrase??as.");
			}
		} else {
			mensaje.put("error_pass", "Contrase??a incorrecta");
		}	
		return mensaje;
	}
	//////////////////Comprobador de registro///////////////////////////////
	public static HashMap<String, String> formalizarRegistro(String email, String pass, String pass2) {
		HashMap<String,String> mensaje = new HashMap<String,String>();
			if (!existeEmailEnAgenda(email)) {	
				if (pass.equals(pass2)) {
					// exito // falta logica
				} else {
					mensaje.put("error_pass", "No coinciden las contrase??as.");
				}
			} else {
				mensaje.put("error_email", "El email \"" + email + "\" ya est?? registrado");
			}		
		return mensaje;
	}
}