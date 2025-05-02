package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {

	private int nroVenta;
	private LocalDateTime fEmision;
	private LocalDateTime fPago;
	private LocalDateTime fRetiro;
	private LocalDateTime fCancelacion;
	private double importe;
	private Customer cliente;
	private ArrayList<Line> lineas;
	
	public double getTotal() {
		double total = 0;
		for(Line l : lineas) {
			total += l.getSubTotal();
		}
		return total;
	}
	
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public int getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}
	public LocalDateTime getfEmision() {
		return fEmision;
	}
	public void setfEmision(LocalDateTime fEmision) {
		this.fEmision = fEmision;
	}
	public LocalDateTime getfPago() {
		return fPago;
	}
	public void setfPago(LocalDateTime fPago) {
		this.fPago = fPago;
	}
	public LocalDateTime getfRetiro() {
		return fRetiro;
	}
	public void setfRetiro(LocalDateTime fRetiro) {
		this.fRetiro = fRetiro;
	}
	public LocalDateTime getfCancelacion() {
		return fCancelacion;
	}
	public void setfCancelacion(LocalDateTime fCancelacion) {
		this.fCancelacion = fCancelacion;
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
}
