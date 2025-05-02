package logic;

import java.util.ArrayList;

import data.CategoriaData;
import entities.Category;
import util.CategoryException;

public class ABMCCategoria {

	private CategoriaData categoriaData = new CategoriaData();
	
	public void add(Category category) throws CategoryException{
		this.categoriaData.add(category);
	}
	
	public ArrayList<Category> getAll() throws CategoryException{
		return this.categoriaData.getAll();
	}
	 
	public  Category getOne(String name) throws CategoryException {		
		return this.categoriaData.getOne(name);
	}

	public void delete(String name) throws CategoryException {
		this.categoriaData.delete(name);
	}
	
	public void update(Category category) throws CategoryException{
		this.categoriaData.update(category);
	}
}
