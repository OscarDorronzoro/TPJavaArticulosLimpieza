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
		miCarrito=cliente.getMiCarrito();
		this.cliente=cliente;
		
	}
	
	public void add(Linea linea) throws CartLineException {
		this.getLineaCarritoData().add(linea,miCarrito.getNombre(),cliente.getUsername());
	}
	
	public void update(Linea linea) throws CartLineException {
		this.getLineaCarritoData().update(linea, miCarrito.getNombre(),cliente.getUsername());
	}
	
	public Linea getOne(int codArticulo) throws ProviderException, CartLineException, ArticleException, PriceException, CategoryException {
		return this.getLineaCarritoData().getOne(miCarrito.getNombre(),cliente.getUsername(), codArticulo);
	}
	
	public ArrayList<Linea> getAllByCarrito() throws ProviderException, CartLineException, ArticleException, PriceException, CategoryException{
		return this.getLineaCarritoData().getAllByCarrito(miCarrito.getNombre(),cliente.getUsername());
	}
	
	public void delete(int codArticulo) throws CartLineException {
		this.lineaCarritoData.delete(miCarrito.getNombre(),cliente.getUsername(), codArticulo);
	}

	public LineaCarritoData getLineaCarritoData() {
		return lineaCarritoData;
	}

	public void setLineaCarritoData(LineaCarritoData lineaCarritoData) {
		this.lineaCarritoData = lineaCarritoData;
	}
	
	public void deleteAllByCarrito() throws CartLineException
	{
		this.lineaCarritoData.deleteAllByCarrito(miCarrito.getNombre(), cliente.getUsername());
	}
}
