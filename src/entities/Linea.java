package entities;

public class Linea{

	private int cantidad;
	private Articulo articulo;
	private Proveedor proveedor;
	
	public double getSubTotal() {
		return articulo.getPrecio().getValor()*cantidad;
	}
	
	@Override
	public boolean equals(Object obj) {
		Linea line = (Linea) obj;
		return this.getArticulo().getCodArticulo() == line.getArticulo().getCodArticulo();
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
