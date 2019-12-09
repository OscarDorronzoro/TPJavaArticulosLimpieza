package entities;

public class Linea{

	private int cantidad;
	private Articulo articulo;
	private Proveedor proveedor;
	
	public double getSubTotal() {
		return articulo.getPrecio().getValor()*cantidad;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getArticulo().getCodArticulo()==((Linea)o).getArticulo().getCodArticulo();
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
