package logic;

import java.util.ArrayList;
import data.ArticuloData;
import entities.Article;
import util.ArticleException;

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
	
	public void add(Article article) throws ArticleException {
		this.getArticuloData().add(article);
	}
	
	public ArrayList<Article> getAll() throws ArticleException {
		return this.getArticuloData().getAll();
	}
	
	public  Article getOne(int articleCode) throws ArticleException {		
		return this.getArticuloData().getOne(articleCode);
	}

	public ArrayList<Article> getAllByDescription(String description) throws ArticleException {
		return this.getArticuloData().getAllByDescription(description);
	} 
	
	public void delete(int articleCode) throws ArticleException {
		this.getArticuloData().delete(articleCode);
	}
	
	public void update(Article article) throws ArticleException {
		this.getArticuloData().update(article);
	}
}


