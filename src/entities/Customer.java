package entities;

import java.util.ArrayList;

public class Customer {

	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private boolean admin;
	private String dni;
	private String email;
	private ArrayList<Cart> myCarts;
	
	public Customer() {
		this.myCarts = new ArrayList<Cart>();
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
	
	public Cart getMiCarrito() {
		Cart cart = this.getMiCarrito("currentPurchase");
		if (cart == null) {
			cart = this.myCarts.getFirst();
		}
		return cart;
	}
	public Cart getMiCarrito(String cartName) {
		Cart cart = new Cart(cartName);
		int index = this.myCarts.indexOf(cart);
		if (index == -1) {
			return null;
		}
		return this.myCarts.get(index);
	}
	public void setMiCarrito(Cart cart) {
		int index = this.myCarts.indexOf(cart);
		if (index == -1) {
			this.myCarts.add(cart);
		}
		else {
			this.myCarts.remove(index);
			this.myCarts.add(index, cart);
		}
	}
	
	public ArrayList<Cart> getMyCarts() {
		return this.myCarts;
	}
	
	public void setMiCarrito(ArrayList<Cart> carts) {
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
