package entities;

import java.util.ArrayList;

public class Articulo {
	
	private int codArticulo;
	private String descripcion;
	private int cantAPedir;
	private int puntoPedido;
	private int stock;
	private String urlImagen;
	private Precio precio;
	private ArrayList<Proveedor> proveedores;
	private Categoria categoria;
	
	public Articulo() {
		this.setProveedores(new ArrayList<Proveedor>());
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
	public Precio getPrecio() {
		return precio;
	}
	public void setPrecio(Precio precio) {
		this.precio=precio;
	}
	public ArrayList<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(ArrayList<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
