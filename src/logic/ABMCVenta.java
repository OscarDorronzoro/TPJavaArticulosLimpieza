package logic;


import java.util.ArrayList;
import java.time.LocalDateTime;

import data.VentaData;
import entities.Carrito;
import entities.Linea;
import entities.Venta;
import util.CartException;
import util.CartLineException;
import util.SaleException;

public class ABMCVenta {
	
	private VentaData ventaData = new VentaData();

	@SuppressWarnings("unchecked")
	public void registrarVenta(Venta venta) throws SaleException, CartException, CartLineException {
		ABMCLineaCarrito abmcLineaCarrito = new ABMCLineaCarrito(venta.getCliente());
		Carrito carrito = venta.getCliente().getMiCarrito();
		
		// Cart to Sale
		venta.setLineas((ArrayList<Linea>) (carrito.getLineas().clone()));
		
		// Empty cart
		carrito.setLineas(new ArrayList<Linea>());
		abmcLineaCarrito.deleteAllByCarrito();
		
		venta.setfEmision(LocalDateTime.now());
		ventaData.add(venta);
	}
	
	public ArrayList<Venta> getAll() throws SaleException {
		return ventaData.getAll();
	}
	
	public Venta getOne(int nroVenta) throws SaleException {
		return ventaData.getOne(nroVenta);
	}
	
	public ArrayList<Venta> getAllPendientesByCliente(String username) throws SaleException {
		return ventaData.getAllPendingByCustomer(username);	
	}

	public ArrayList<Venta> getAllCompletedByCustomer(String username) throws SaleException {
		return ventaData.getAllCompletedByCustomer(username);	
	}
	
	public void add(Venta venta) throws SaleException {
		ventaData.add(venta);
	}
	
	public void delete(Venta venta) throws SaleException {
		ventaData.delete(venta);
	}
	
	public void update(Venta venta) throws SaleException {
		ventaData.update(venta);
	}
}
