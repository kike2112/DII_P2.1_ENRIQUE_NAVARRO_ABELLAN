package dam2.dii.p21.modelo;

public class Usuario {

	private String id_us;
	private String nombre;
	private String apellido;
	private String apellido2;
	private String email;
	private String telefono;
	private String pass;
	private int intentos;
	private boolean rolAdmin;
	private String idioma;
	
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String pass) {
		super();
		this.nombre = nombre;
		this.pass = pass;
	}
	public Usuario(String nombre, String apellido, String apellido2, String email, String telefono,
			String pass) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
	}
	public Usuario(String nombre, String apellido, String apellido2, String email, String telefono,
			String pass, boolean rolAdmin, int intentos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.rolAdmin = rolAdmin;
		this.intentos = intentos;
	}
	public Usuario(String id_us, String nombre, String apellido, String apellido2, String email, String telefono,
			String pass, boolean rolAdmin, int intentos) {
		super();
		this.id_us = id_us;
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.rolAdmin = rolAdmin;
		this.intentos = intentos;
	}
	
	public String getId_us() {
		return id_us;
	}
	public void setId_us(String id_us) {
		this.id_us = id_us;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isRolAdmin() {
		return rolAdmin;
	}
	public void setRolAdmin(boolean rolAdmin) {
		this.rolAdmin = rolAdmin;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	@Override
	public String toString() {
		return this.email + this.nombre + this.apellido + this.apellido2 + this.telefono;
	}
}
