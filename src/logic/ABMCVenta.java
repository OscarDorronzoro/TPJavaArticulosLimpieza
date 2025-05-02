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
	public void registerSale(Venta sale) throws SaleException, CartException, CartLineException {
		ABMCLineaCarrito abmcLineaCarrito = new ABMCLineaCarrito(sale.getCliente());
		Carrito cart = sale.getCliente().getMiCarrito();
		
		// Cart to Sale
		sale.setLineas((ArrayList<Linea>) (cart.getLineas().clone()));
		
		// Empty cart on memory
		cart.setLineas(new ArrayList<Linea>());
		
		sale.setfEmision(LocalDateTime.now());
		ventaData.add(sale);
		
		// Empty cart on DB after sale is added
		abmcLineaCarrito.deleteAllByCart();
	}
	
	public ArrayList<Venta> getAll() throws SaleException {
		return ventaData.getAll();
	}
	
	public Venta getOne(int nroVenta) throws SaleException {
		return ventaData.getOne(nroVenta);
	}
	
	public ArrayList<Venta> getAllPendingByCustomer(String username) throws SaleException {
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
