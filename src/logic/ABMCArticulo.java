package logic;

import java.util.ArrayList;
import data.ArticuloData;
import entities.Articulo;
import util.ArticleException;
import util.PriceException;
import util.ProviderException;

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
	
	public void add(Articulo art) throws ArticleException, PriceException {
		this.getArticuloData().add(art);
	}
	
	public ArrayList<Articulo> getAll() throws ProviderException, ArticleException, PriceException{
		return this.getArticuloData().getAll();
	}
	
	public  Articulo getOne(int codArticulo) throws ProviderException, ArticleException, PriceException {		
		return this.getArticuloData().getOne(codArticulo);
	}

	public ArrayList<Articulo> getAllByDescripcion(String descripcion) throws ProviderException, ArticleException, PriceException{
		return this.getArticuloData().getAllByDescripcion(descripcion);
	} 
	
	public void delete(int codArticulo) throws ArticleException {
		this.getArticuloData().delete(codArticulo);
	}
	
	public void update(Articulo articulo) throws ArticleException, PriceException {
		this.getArticuloData().update(articulo);
	}
}


