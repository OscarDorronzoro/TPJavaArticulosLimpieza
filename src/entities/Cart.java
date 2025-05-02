package entities;

import java.util.ArrayList;

public class Cart {
	
	private String nombre;
	private String descripcion;
	private Customer cliente;
	private ArrayList<Line> lineas;
	
	public Cart() {
		this.setLineas(new ArrayList<Line>());
	}
	
	public Cart(String cartName) {
		this();
		this.setNombre(cartName);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Customer getCliente() {
		return cliente;
	}
	public void setCliente(Customer cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<Line> getLineas() {
		return lineas;
	}
	public void setLineas(ArrayList<Line> lineas) {
		this.lineas = lineas;
	}
	
	public double getTotal() {
		double total = 0;
		for(Line l : this.getLineas()) {
			total += l.getSubTotal();
		}
		return total;
	}
	
	@Override
	public boolean equals(Object obj) {
		Cart cart = (Cart) obj;
		return this.getNombre().equals(cart.getNombre());
	}
}
