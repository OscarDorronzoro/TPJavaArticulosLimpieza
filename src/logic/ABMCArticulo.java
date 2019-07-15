package logic;

import java.util.ArrayList;
import data.ArticuloData;
import entities.Articulo;

public class ABMCArticulo {

	public void add(Articulo art) {
		ArticuloData ad = new ArticuloData();
		ad.add(art);
	}
	
	public ArrayList<Articulo> getAll(){
		ArticuloData ad = new ArticuloData();
		return ad.getAll();
	}
}
