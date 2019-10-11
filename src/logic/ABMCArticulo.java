package logic;

import java.util.ArrayList;
import data.ArticuloData;
import entities.Articulo;

public class ABMCArticulo {

	private ArticuloData articuloData;
	
	public ArticuloData getArticuloData() {
		return articuloData;
	}
	public void setArticuloData(ArticuloData articuloData) {
		this.articuloData = articuloData;
	}
	
	public ABMCArticulo(){
		this.setArticuloData(new ArticuloData());
	}
	
	public void add(Articulo art) {
		this.getArticuloData().add(art);
	}
	
	public ArrayList<Articulo> getAll(){
		return this.getArticuloData().getAll();
	}
	
	public  Articulo getOne(int codArticulo) {		
		return this.getArticuloData().getOne(codArticulo);
	}

	public ArrayList<Articulo> getAllByDescripcion(String descripcion){
		return this.getArticuloData().getAllByDescripcion(descripcion);
	} 
	
	public void delete(int codArticulo) {
		this.getArticuloData().delete(codArticulo);
	}
	
	public void update(Articulo articulo) {
		this.getArticuloData().update(articulo);
	}
}


