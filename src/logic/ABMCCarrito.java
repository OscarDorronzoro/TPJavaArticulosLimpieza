package logic;

import data.CarritoData;
import entities.Carrito;
import util.CartException;

public class ABMCCarrito {

	private CarritoData carritoData;
	
	public ABMCCarrito() {
		this.carritoData = new CarritoData();
	}
	
	public void add(Carrito cart, String username) throws CartException {
		this.carritoData.add(cart, username);
	}
	
	public Carrito getOne(String name, String username) throws CartException {
		return this.carritoData.getOne(name, username);
	}
	
	public void delete(Carrito cart, String username) throws CartException {
		this.carritoData.delete(cart, username);
	}

}
