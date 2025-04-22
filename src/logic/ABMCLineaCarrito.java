package logic;

import java.util.ArrayList;

import data.LineaCarritoData;
import entities.Linea;
import util.ArticleException;
import util.CartLineException;
import util.CategoryException;
import util.PriceException;
import util.ProviderException;
import entities.Carrito;
import entities.Cliente;

public class ABMCLineaCarrito {
	
	private LineaCarritoData lineaCarritoData;
	private Carrito miCarrito;
	private Cliente cliente;
	
	public ABMCLineaCarrito(Cliente cliente) {
		this.setLineaCarritoData(new LineaCarritoData());
		this.setMiCarrito(cliente.getMiCarrito());
		this.setCliente(cliente);
		
	}
	
	public ABMCLineaCarrito(Cliente cliente, Carrito carrito) {
		this.setLineaCarritoData(new LineaCarritoData());
		this.setMiCarrito(carrito);
		this.setCliente(cliente);
		
	}
	
	// Getters and setter
	public void setLineaCarritoData(LineaCarritoData lineaCarritoData) {
		this.lineaCarritoData = lineaCarritoData;
	}
	public LineaCarritoData getLineaCarritoData() {
		return this.lineaCarritoData;
	}
	
	public void setMiCarrito(Carrito miCarrito) {
		this.miCarrito = miCarrito;
	}
	public Carrito getMiCarrito() {
		return this.miCarrito;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	
	// Persistence
	public void add(Linea linea) throws CartLineException {
		this.getLineaCarritoData().add(linea, this.getMiCarrito().getNombre(), this.getCliente().getUsername());
	}
	
	public void update(Linea linea) throws CartLineException {
		this.getLineaCarritoData().update(linea, this.getMiCarrito().getNombre(), this.getCliente().getUsername());
	}
	
	public Linea getOne(int codArticulo) throws ProviderException, CartLineException, ArticleException, PriceException, CategoryException {
		return this.getLineaCarritoData().getOne(this.getMiCarrito().getNombre(), this.getCliente().getUsername(), codArticulo);
	}
	
	public ArrayList<Linea> getAllByCarrito() throws ProviderException, CartLineException, ArticleException, PriceException, CategoryException{
		return this.getLineaCarritoData().getAllByCarrito(this.getMiCarrito().getNombre(), this.getCliente().getUsername());
	}
	
	public void delete(int codArticulo) throws CartLineException {
		this.getLineaCarritoData().delete(this.getMiCarrito().getNombre(), this.getCliente().getUsername(), codArticulo);
	}
	
	public void deleteAllByCarrito() throws CartLineException
	{
		this.getLineaCarritoData().deleteAllByCarrito(this.getMiCarrito().getNombre(), this.getCliente().getUsername());
	}
}
