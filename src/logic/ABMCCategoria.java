package logic;

import java.util.ArrayList;

import data.CategoriaData;
import entities.Categoria;
import util.CategoryException;

public class ABMCCategoria {

	private CategoriaData categoriaData = new CategoriaData();
	
	public void add(Categoria category) throws CategoryException{
		this.categoriaData.add(category);
	}
	
	public ArrayList<Categoria> getAll() throws CategoryException{
		return this.categoriaData.getAll();
	}
	 
	public  Categoria getOne(String name) throws CategoryException {		
		return this.categoriaData.getOne(name);
	}

	public void delete(String name) throws CategoryException {
		this.categoriaData.delete(name);
	}
	
	public void update(Categoria category) throws CategoryException{
		this.categoriaData.update(category);
	}
}
