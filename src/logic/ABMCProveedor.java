package logic;

import java.util.ArrayList;

import data.ProveedorData;
import entities.Proveedor;
import util.ProviderException;

public class ABMCProveedor {
	
	ProveedorData provData = new ProveedorData();

	public void add(Proveedor prov) throws ProviderException
	{
		provData.add(prov);
	}
	
	public ArrayList<Proveedor> getAll() throws ProviderException
	{
		return provData.getAll();
	}
	
	public ArrayList<Proveedor> getAllByArticulo( int codigoArticulo) throws ProviderException
	{
		return provData.getAllByArticulo(codigoArticulo);
	}
	
	public Proveedor getOne(String cuit) throws ProviderException
	{
		return provData.getOne(cuit);
	}
	
	public void delete(String cuit) throws ProviderException
	{
		provData.delete(cuit);
	}
	
	public void update(Proveedor prov) throws ProviderException
	{
		provData.update(prov);
	}
}
