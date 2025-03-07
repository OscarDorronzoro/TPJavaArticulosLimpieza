package entities;

import java.time.LocalDateTime;

public class Precio {

	private double valor;
	private LocalDateTime fechaDesde;
	
	public Precio() {
		this.setFechaDesde(LocalDateTime.now());
	}
	
	public Precio(double valor) {
		this();
		this.setValor(valor);
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDateTime getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(LocalDateTime fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
}
