package logic;

import java.util.ArrayList;

import data.LineaCarritoData;
import entities.Linea;
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
	
	public void add(Linea linea) {
		this.getLineaCarritoData().add(linea,miCarrito.getNombre(),cliente.getUsername());
	}
	
	public void update(Linea linea) {
		this.getLineaCarritoData().update(linea, miCarrito.getNombre(),cliente.getUsername());
	}
	
	public Linea getOne(int codArticulo) throws ProviderException {
		return this.getLineaCarritoData().getOne(miCarrito.getNombre(),cliente.getUsername(), codArticulo);
	}
	
	public ArrayList<Linea> getAllByCarrito() throws ProviderException{
		return this.getLineaCarritoData().getAllByCarrito(miCarrito.getNombre(),cliente.getUsername());
	}
	
	public void delete(int codArticulo) {
		this.lineaCarritoData.delete(miCarrito.getNombre(),cliente.getUsername(), codArticulo);
	}

	public LineaCarritoData getLineaCarritoData() {
		return lineaCarritoData;
	}

	public void setLineaCarritoData(LineaCarritoData lineaCarritoData) {
		this.lineaCarritoData = lineaCarritoData;
	}
}
