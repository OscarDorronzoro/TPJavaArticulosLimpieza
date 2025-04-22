package entities;

import java.util.ArrayList;

public class Cliente {

	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private boolean admin;
	private String dni;
	private String email;
	private ArrayList<Carrito> myCarts;
	
	public Cliente() {
		this.myCarts = new ArrayList<Carrito>();
		
		Carrito cart = new Carrito("currentPurchase");
		cart.setDescripcion("Aqui se encuentran los articulos que ha añadido en su ultima sesion");
		this.setMiCarrito(cart);
		
		/*
		cart = new Carrito("favorites");
		cart.setDescripcion("Articulos guardados como favoritos");
		this.setMiCarrito(cart);
		
		cart = new Carrito("wishList");
		cart.setDescripcion("Articulos que se desean comprar en el futuro");
		this.setMiCarrito(cart);
		
		cart = new Carrito("budget");
		cart.setDescripcion("Conjunto de articulos guardados para evaluar costo");
		this.setMiCarrito(cart);
		*/
		
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
		return this.dni;
	}
	public void setDNI(String dni) {
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + this.getNombre() + "  Apellido: " + this.getApellido();
	}
	
	public Carrito getMiCarrito() {
		return this.myCarts.getFirst();
	}
	public Carrito getMiCarrito(String cartName) {
		Carrito cart = new Carrito(cartName);
		int index = this.myCarts.indexOf(cart);
		if (index == -1) {
			return null;
		}
		return this.myCarts.get(index);
	}
	public void setMiCarrito(Carrito cart) {
		int index = this.myCarts.indexOf(cart);
		if (index == -1) {
			this.myCarts.add(cart);
		}
		else {
			this.myCarts.remove(index);
			this.myCarts.add(index, cart);
		}
	}
	
	public ArrayList<Carrito> getMyCarts() {
		return this.myCarts;
	}
	
	public void setMiCarrito(ArrayList<Carrito> carts) {
		this.myCarts = carts;
	}

	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
