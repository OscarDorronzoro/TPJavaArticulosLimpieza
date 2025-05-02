package logic;

import java.util.ArrayList;

import data.LineaCarritoData;
import entities.Linea;
import util.CartLineException;
import entities.Carrito;
import entities.Cliente;

public class ABMCLineaCarrito {
	
	private LineaCarritoData lineaCarritoData;
	private Carrito cart;
	private Cliente customer;
	
	public ABMCLineaCarrito(Cliente customer) {
		this.setLineaCarritoData(new LineaCarritoData());
		this.setCart(customer.getMiCarrito());
		this.setCustomer(customer);
		
	}
	
	public ABMCLineaCarrito(Cliente cliente, Carrito carrito) {
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
	
	public void setCart(Carrito cart) {
		this.cart = cart;
	}
	public Carrito getCart() {
		return this.cart;
	}
	
	public void setCustomer(Cliente customer) {
		this.customer = customer;
	}
	public Cliente getCustomer() {
		return this.customer;
	}
	
	// Persistence
	public void add(Linea linea) throws CartLineException {
		this.getLineaCarritoData().add(linea, this.getCart().getNombre(), this.getCustomer().getUsername());
	}
	
	public void update(Linea linea) throws CartLineException {
		this.getLineaCarritoData().update(linea, this.getCart().getNombre(), this.getCustomer().getUsername());
	}
	
	public Linea getOne(int codArticulo) throws CartLineException {
		return this.getLineaCarritoData().getOne(this.getCart().getNombre(), this.getCustomer().getUsername(), codArticulo);
	}
	
	public ArrayList<Linea> getAllByCart() throws CartLineException {
		return this.getLineaCarritoData().getAllByCart(this.getCart().getNombre(), this.getCustomer().getUsername());
	}
	
	public void delete(int codArticulo) throws CartLineException {
		this.getLineaCarritoData().delete(this.getCart().getNombre(), this.getCustomer().getUsername(), codArticulo);
	}
	
	public void deleteAllByCart() throws CartLineException {
		this.getLineaCarritoData().deleteAllByCart(this.getCart().getNombre(), this.getCustomer().getUsername());
	}
}
