package entities;

public class Line {

	private int cantidad;
	private Article articulo;
	private Provider proveedor;
	
	public double getSubTotal() {
		return articulo.getPrecio().getValor()*cantidad;
	}
	
	@Override
	public boolean equals(Object obj) {
		Line line = (Line) obj;
		return this.getArticulo().getCodArticulo() == line.getArticulo().getCodArticulo();
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Article getArticulo() {
		return articulo;
	}
	public void setArticulo(Article articulo) {
		this.articulo = articulo;
	}
	public Provider getProveedor() {
		return proveedor;
	}
	public void setProveedor(Provider proveedor) {
		this.proveedor = proveedor;
	}
}
