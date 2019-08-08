package entities;

import java.util.ArrayList;

public class Carrito {
	
	private String nombre;
	private String descripcion;
	private Cliente cliente;
	private ArrayList<Linea> lineas;
	
	public Carrito() {
		this.setLineas(new ArrayList<Linea>());
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Linea> getLineas() {
		return lineas;
	}
	public void setLineas(ArrayList<Linea> lineas) {
		this.lineas = lineas;
	}

}
