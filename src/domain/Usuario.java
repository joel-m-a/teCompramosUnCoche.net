package domain;

public class Usuario {
	public String usuario;
	public String password;
	public String rol; // "ADMIN" o "CLIENTE"

	public Usuario(String usuario, String password, String rol) {
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}
}