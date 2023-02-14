package dam2.dii.p21.DAO;

import java.util.ArrayList;
import dam2.dii.p21.modelo.Usuario;

public final class UsuarioDAOMemoria implements IUsuarioDAO {
	
	private static final ArrayList<Usuario> LISTA_USUARIOS = new ArrayList<Usuario>();

	public static void llenarListaUsuarios() {
		LISTA_USUARIOS.add(new Usuario("1", "Angel", "Blasco", "Cano", "angel@blasco.es", "111111111", "123", false, 5));
		LISTA_USUARIOS.add(new Usuario("2", "Beatriz", "Cano", "Domingo", "beatriz@cano.es", "222222222", "123", true, 5));
		LISTA_USUARIOS.add(new Usuario("3", "Carlos", "Domingo", "Egido", "carlos@domingo.es", "333333333", "123", false, 5));
		LISTA_USUARIOS.add(new Usuario("4", "Diego", "Egido", "Floren", "diego@egido.es", "444444444", "123", false, 5));
		LISTA_USUARIOS.add(new Usuario("5", "Eric", "Floren", "Guilabert", "eric@floren.es", "555555555", "123", false, 5));
		LISTA_USUARIOS.add(new Usuario("6", "Francisco", "Guilabert", "Huerta", "fran@guilabert.es", "666666666", "123", true, 5));
	}
	private String getNuevoId() {
		int id_us = 0;
		for (int i = 0; i < LISTA_USUARIOS.size(); i++) {
			if (Integer.parseInt(LISTA_USUARIOS.get(i).getId_us()) > id_us) {
				id_us = Integer.parseInt(LISTA_USUARIOS.get(i).getId_us());
			}
		}
		id_us++;
		return String.valueOf(id_us);
	}
	
	@Override
	public ArrayList<Usuario> getListaUsuarios() {
		if (LISTA_USUARIOS.size() == 0) {
			llenarListaUsuarios();
		}
		return LISTA_USUARIOS;
	}

	@Override
	public Usuario getUsuarioById(String id) {
		Usuario usuario = null;
		for (int i = 0; i < LISTA_USUARIOS.size(); i++) {
			if (LISTA_USUARIOS.get(i).getId_us().equals(id)) {
				usuario = LISTA_USUARIOS.get(i);
			}
		}
		return usuario;	
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		Usuario usuario = null;
		for (int i = 0; i < LISTA_USUARIOS.size(); i++) {
			if (LISTA_USUARIOS.get(i).getEmail().equals(email)) {
				usuario = LISTA_USUARIOS.get(i);
			}
		}
		return usuario;	
	}

	@Override
	public boolean crearCliente(Usuario usuario) {
		usuario.setId_us(getNuevoId());
		usuario.setIntentos(5);
		LISTA_USUARIOS.add(usuario);
		return true;
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		boolean exito = false;
		for (int i = 0; i < LISTA_USUARIOS.size(); i++) {
			if (LISTA_USUARIOS.get(i).getId_us().equals(usuario.getId_us())) {
				LISTA_USUARIOS.get(i).setNombre(usuario.getNombre());
				LISTA_USUARIOS.get(i).setApellido(usuario.getApellido());
				LISTA_USUARIOS.get(i).setApellido2(usuario.getApellido2());
				LISTA_USUARIOS.get(i).setEmail(usuario.getEmail());
				LISTA_USUARIOS.get(i).setTelefono(usuario.getTelefono());
				LISTA_USUARIOS.get(i).setPass(usuario.getPass());
				LISTA_USUARIOS.get(i).setIntentos(usuario.getIntentos());
				LISTA_USUARIOS.get(i).setIdioma(usuario.getIdioma());
				exito = true;
			}
		}
		return exito;
	}

	@Override
	public boolean borrarUsuario(String id_us) {
		boolean exito = false;
		for (int i = 0; i < LISTA_USUARIOS.size(); i++) {
			if (LISTA_USUARIOS.get(i).getId_us().equals(id_us)) {
				LISTA_USUARIOS.remove(i);
//				remove(LISTA_USUARIOS.get(i));
				exito = true;
			}
		}
		return exito;
	}
}