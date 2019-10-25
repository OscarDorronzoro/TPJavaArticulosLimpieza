package entities;

public class Cliente {

	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private boolean admin;
	private String DNI;
	private Carrito miCarrito;
	
	public Cliente() {
		this.setMiCarrito(new Carrito());
		this.miCarrito.setNombre("CompraActual");
		this.miCarrito.setDescripcion("Aqui se encuentran los articulos que ha añadido en su ultima sesion");
		this.setAdmin(false);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	@Override
	public String toString() {
		return "Nombre: "+this.getNombre()+"  Apellido: "+this.getApellido();
	}
	public Carrito getMiCarrito() {
		return miCarrito;
	}
	public void setMiCarrito(Carrito miCarrito) {
		this.miCarrito = miCarrito;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
