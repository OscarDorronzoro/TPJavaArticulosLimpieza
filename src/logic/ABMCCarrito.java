package logic;

import data.CarritoData;
import entities.Cart;
import util.CartException;

public class ABMCCarrito {

	private CarritoData carritoData;
	
	public ABMCCarrito() {
		this.carritoData = new CarritoData();
	}
	
	public void add(Cart cart, String username) throws CartException {
		this.carritoData.add(cart, username);
	}
	
	public Cart getOne(String name, String username) throws CartException {
		return this.carritoData.getOne(name, username);
	}
	
	public void delete(Cart cart, String username) throws CartException {
		this.carritoData.delete(cart, username);
	}

}
