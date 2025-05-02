package logic;


import java.util.ArrayList;
import java.time.LocalDateTime;

import data.VentaData;
import entities.Cart;
import entities.Line;
import entities.Sale;
import util.CartLineException;
import util.SaleException;

public class ABMCVenta {
	
	private VentaData ventaData = new VentaData();

	@SuppressWarnings("unchecked")
	public void registerSale(Sale sale) throws SaleException, CartLineException {
		ABMCLineaCarrito abmcLineaCarrito = new ABMCLineaCarrito(sale.getCliente());
		Cart cart = sale.getCliente().getMiCarrito();
		
		// Cart to Sale
		sale.setLineas((ArrayList<Line>) (cart.getLineas().clone()));
		
		// Empty cart on memory
		cart.setLineas(new ArrayList<Line>());
		
		sale.setfEmision(LocalDateTime.now());
		ventaData.add(sale);
		
		// Empty cart on DB after sale is added
		abmcLineaCarrito.deleteAllByCart();
	}
	
	public ArrayList<Sale> getAll() throws SaleException {
		return ventaData.getAll();
	}
	
	public Sale getOne(int nroVenta) throws SaleException {
		return ventaData.getOne(nroVenta);
	}
	
	public ArrayList<Sale> getAllPendingByCustomer(String username) throws SaleException {
		return ventaData.getAllPendingByCustomer(username);	
	}

	public ArrayList<Sale> getAllCompletedByCustomer(String username) throws SaleException {
		return ventaData.getAllCompletedByCustomer(username);	
	}
	
	public void add(Sale venta) throws SaleException {
		ventaData.add(venta);
	}
	
	public void delete(Sale venta) throws SaleException {
		ventaData.delete(venta);
	}
	
	public void update(Sale venta) throws SaleException {
		ventaData.update(venta);
	}
}
