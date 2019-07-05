package entities;

public class Articulo {
	
	private String codArticulo;
	private String descripcion;
	private int cantAPedir;
	private int puntoPedido;
	private int stock;
	
	public String getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(String codArticulo) {
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
}
