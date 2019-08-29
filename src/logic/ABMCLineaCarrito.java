package logic;

import java.util.ArrayList;

import data.LineaCarritoData;
import entities.Linea;
import entities.Carrito;

public class ABMCLineaCarrito {
	
	private LineaCarritoData lineaCarritoData;
	private Carrito miCarrito;
	
	public ABMCLineaCarrito(Carrito carrito) {
		this.setLineaCarritoData(new LineaCarritoData());
		miCarrito=carrito;
	}
	
	public void add(Linea linea) {
		this.getLineaCarritoData().add(linea,miCarrito.getNombre());
	}
	
	public Linea getOne(int codArticulo) {
		return this.getLineaCarritoData().getOne(miCarrito.getNombre(), codArticulo);
	}
	
	public ArrayList<Linea> getAllByCarrito(){
		return this.getLineaCarritoData().getAllByCarrito(miCarrito.getNombre());
	}
	
	public void delete(int codArticulo) {
		this.lineaCarritoData.delete(miCarrito.getNombre(), codArticulo);
	}

	public LineaCarritoData getLineaCarritoData() {
		return lineaCarritoData;
	}

	public void setLineaCarritoData(LineaCarritoData lineaCarritoData) {
		this.lineaCarritoData = lineaCarritoData;
	}
}
