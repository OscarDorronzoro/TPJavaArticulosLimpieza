package logic;


import java.util.ArrayList;
import java.util.Date;

import data.VentaData;
import entities.Linea;
import entities.Venta;
import util.ArticleException;
import util.CartException;
import util.CartLineException;
import util.CategoryException;
import util.ClientException;
import util.PriceException;
import util.ProviderException;
import util.SaleException;
import util.SaleLineException;

public class ABMCVenta {
	
	private VentaData ventaData = new VentaData();

	@SuppressWarnings("unchecked")
	public void registrarVenta(Venta venta) throws SaleException, SaleLineException, CartLineException{
	
		ABMCLineaCarrito abmcLineaCarrito = new ABMCLineaCarrito(venta.getCliente());
		
		venta.setLineas((ArrayList<Linea>)(venta.getCliente().getMiCarrito().getLineas().clone()));
		venta.getCliente().getMiCarrito().setLineas(new ArrayList<Linea>());
		abmcLineaCarrito.deleteAllByCarrito();
		venta.setfEmision(new Date());
		ventaData.add(venta);
		
	}
	
	public ArrayList<Venta> getAll() throws SaleException, ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleLineException, PriceException, CategoryException
	{
		return ventaData.getAll();
	}
	
	public Venta getOne(int nroVenta) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException, PriceException, CategoryException
	{
		return ventaData.getOne(nroVenta);
	}
	
	public ArrayList<Venta> getAllPendientesByCliente(String username) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException, PriceException, CategoryException
	{
		return ventaData.getAllPendientesByCliente(username);	
	}
	
	public void add(Venta venta) throws SaleException, SaleLineException
	{
		ventaData.add(venta);
	}
	
	public void delete(Venta venta) throws SaleException, SaleLineException
	{
		ventaData.delete(venta);
	}
	public void update(Venta venta) throws SaleException {
		ventaData.update(venta);
	}
}
