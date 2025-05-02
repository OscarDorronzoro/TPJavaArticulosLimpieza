package entities;

import java.util.ArrayList;

public class Article {
	
	private int codArticulo;
	private String descripcion;
	private int cantAPedir;
	private int puntoPedido;
	private int stock;
	private String urlImagen;
	private Price precio;
	private ArrayList<Provider> proveedores;
	private Category categoria;
	
	public Article() {
		this.setProveedores(new ArrayList<Provider>());
		this.setCategoria(new Category());
	}
	
	public int getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantAPedir() {
		return cantAPedir;
	}
	public void setCantAPedir(int cantAPedir) {
		this.cantAPedir = cantAPedir;
	}
	public int getPuntoPedido() {
		return puntoPedido;
	}
	public void setPuntoPedido(int puntoPedido) {
		this.puntoPedido = puntoPedido;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public Price getPrecio() {
		return precio;
	}
	public void setPrecio(Price precio) {
		this.precio=precio;
	}
	public ArrayList<Provider> getProveedores() {
		return proveedores;
	}

	public void setProveedores(ArrayList<Provider> proveedores) {
		this.proveedores = proveedores;
	}

	public Category getCategoria() {
		return categoria;
	}

	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}
}
