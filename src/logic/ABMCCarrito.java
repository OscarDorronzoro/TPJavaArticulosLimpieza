package logic;

import data.CarritoData;
import entities.Carrito;
import util.CartException;
import util.CartLineException;
import util.DoniaMaryException;

public class ABMCCarrito {

	private CarritoData carritoData;
	
	public ABMCCarrito() {
		this.carritoData=new CarritoData();
	}
	
	public void add(Carrito carrito,String username) throws CartException, CartLineException {
		this.carritoData.add(carrito,username);
	}
	
	public Carrito getOne(String nombre,String username) throws DoniaMaryException {
		return this.carritoData.getOne(nombre,username);
	}
	
	public void delete(Carrito carrito,String username) throws CartException, CartLineException {
		this.carritoData.delete(carrito, username);
	}

}
