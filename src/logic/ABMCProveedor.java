package logic;

import java.util.ArrayList;

import data.ProveedorData;
import entities.Provider;
import util.ProviderException;

public class ABMCProveedor {
	
	ProveedorData provData = new ProveedorData();

	public void add(Provider prov) throws ProviderException
	{
		provData.add(prov);
	}
	
	public ArrayList<Provider> getAll() throws ProviderException
	{
		return provData.getAll();
	}
	
	public ArrayList<Provider> getAllByArticle( int codigoArticulo) throws ProviderException
	{
		return provData.getAllByArticle(codigoArticulo);
	}
	
	public Provider getOne(String cuit) throws ProviderException
	{
		return provData.getOne(cuit);
	}
	
	public void delete(String cuit) throws ProviderException
	{
		provData.delete(cuit);
	}
	
	public void update(Provider prov) throws ProviderException
	{
		provData.update(prov);
	}
}
