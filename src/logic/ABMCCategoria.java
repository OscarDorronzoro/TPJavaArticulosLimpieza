package logic;

import java.util.ArrayList;

import data.CategoriaData;
import entities.Categoria;
import util.CategoryException;

public class ABMCCategoria {

	private CategoriaData categoriaData= new CategoriaData();
	
	public void add(Categoria categoria) throws CategoryException{
		this.categoriaData.add(categoria);
	}
	
	public ArrayList<Categoria> getAll() throws CategoryException{
		return this.categoriaData.getAll();
	}
	 
	public  Categoria getOne(String nombre) throws CategoryException {		
		return this.categoriaData.getOne(nombre);
	}

	public void delete(String nombre) throws CategoryException {
		this.categoriaData.delete(nombre);
	}
	
	public void update(Categoria categoria) throws CategoryException{
		this.categoriaData.update(categoria);
	}
}
