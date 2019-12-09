package logic;


import java.util.ArrayList;
import java.util.Date;

import data.VentaData;
import entities.Venta;
import util.ArticleException;
import util.CartException;
import util.CartLineException;
import util.ClientException;
import util.ProviderException;
import util.SaleException;
import util.SaleLineException;

public class ABMCVenta {
	
	private VentaData ventaData = new VentaData();

	public void registrarVenta(Venta venta) throws SaleException{
		venta.setLineas(venta.getCliente().getMiCarrito().getLineas());
		venta.setfEmision(new Date());
		
	}
	
	public ArrayList<Venta> getAll() throws SaleException, ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleLineException
	{
		return ventaData.getAll();
	}
	
	public Venta getOne(int nroVenta) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException
	{
		return ventaData.getOne(nroVenta);
	}
	
	public ArrayList<Venta> getAllPendientesByCliente(String username) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException
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
}
