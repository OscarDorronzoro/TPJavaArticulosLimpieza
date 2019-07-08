package entities;

import java.sql.Date;

public class Venta {

	private int nroVenta;
	private Date fEmision;
	private Date fPago;
	private Date fRetiro;
	private Date fCancelacion;
	private Cliente cliente;
	
	public int getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}
	public Date getfEmision() {
		return fEmision;
	}
	public void setfEmision(Date fEmision) {
		this.fEmision = fEmision;
	}
	public Date getfPago() {
		return fPago;
	}
	public void setfPago(Date fPago) {
		this.fPago = fPago;
	}
	public Date getfRetiro() {
		return fRetiro;
	}
	public void setfRetiro(Date fRetiro) {
		this.fRetiro = fRetiro;
	}
	public Date getfCancelacion() {
		return fCancelacion;
	}
	public void setfCancelacion(Date fCancelacion) {
		this.fCancelacion = fCancelacion;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
