package logic;

import java.util.ArrayList;

import data.LineaCarritoData;
import entities.Line;
import util.CartLineException;
import entities.Cart;
import entities.Customer;

public class ABMCLineaCarrito {
	
	private LineaCarritoData lineaCarritoData;
	private Cart cart;
	private Customer customer;
	
	public ABMCLineaCarrito(Customer customer) {
		this.setLineaCarritoData(new LineaCarritoData());
		this.setCart(customer.getMiCarrito());
		this.setCustomer(customer);
		
	}
	
	public ABMCLineaCarrito(Customer cliente, Cart carrito) {
		this.setLineaCarritoData(new LineaCarritoData());
		this.setCart(carrito);
		this.setCustomer(cliente);
		
	}
	
	// Getters and setter
	public void setLineaCarritoData(LineaCarritoData lineaCarritoData) {
		this.lineaCarritoData = lineaCarritoData;
	}
	public LineaCarritoData getLineaCarritoData() {
		return this.lineaCarritoData;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Cart getCart() {
		return this.cart;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return this.customer;
	}
	
	// Persistence
	public void add(Line linea) throws CartLineException {
		this.getLineaCarritoData().add(linea, this.getCart().getNombre(), this.getCustomer().getUsername());
	}
	
	public void update(Line linea) throws CartLineException {
		this.getLineaCarritoData().update(linea, this.getCart().getNombre(), this.getCustomer().getUsername());
	}
	
	public Line getOne(int codArticulo) throws CartLineException {
		return this.getLineaCarritoData().getOne(this.getCart().getNombre(), this.getCustomer().getUsername(), codArticulo);
	}
	
	public ArrayList<Line> getAllByCart() throws CartLineException {
		return this.getLineaCarritoData().getAllByCart(this.getCart().getNombre(), this.getCustomer().getUsername());
	}
	
	public void delete(int codArticulo) throws CartLineException {
		this.getLineaCarritoData().delete(this.getCart().getNombre(), this.getCustomer().getUsername(), codArticulo);
	}
	
	public void deleteAllByCart() throws CartLineException {
		this.getLineaCarritoData().deleteAllByCart(this.getCart().getNombre(), this.getCustomer().getUsername());
	}
}
