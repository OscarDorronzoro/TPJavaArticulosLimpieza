package logic;

import entities.Cliente;
import util.CartException;
import util.CartLineException;
import util.ClientAlreadyExistException;
import util.ClientException;
import util.ClientNotFoundException;
import util.DoniaMaryException;

import java.util.ArrayList;
import data.ClienteData;
import util.PasswordManager;
import util.SaleException;
import util.SaleLineException;

public class ABMCCliente {

	private ClienteData clienteData;
	
	public ClienteData getClienteData() {
		return clienteData;
	}
	public void setClienteData(ClienteData clienteData) {
		this.clienteData = clienteData;
	}
	
	public ABMCCliente(){
		this.setClienteData(new ClienteData());
	}
	public void add(Cliente c) throws ClientAlreadyExistException {
		try {
			c.setPassword(PasswordManager.encriptar(c.getPassword()));
			this.getClienteData().add(c);
		}
		catch(Exception e) {
			throw new ClientAlreadyExistException("Ese usuario ya existe, elija otro",e);
		}
		
	}
	
	public ArrayList<Cliente> getAll() throws DoniaMaryException{		
		return this.getClienteData().getAll();
	}
	
	public ArrayList<Cliente> getAllByAdmin(boolean isAdmin) throws DoniaMaryException{		
		return this.getClienteData().getAllByAdmin(isAdmin);
	}
	
	public  Cliente getOne(String username) throws DoniaMaryException {		
		return this.getClienteData().getOne(username);
	}
	
	public void completarCliente(Cliente c) throws DoniaMaryException {
		Cliente cli = this.getClienteData().getOneByUserYPassword(c.getUsername(), PasswordManager.encriptar(c.getPassword()));
		
		if(cli==null) {
			throw new ClientNotFoundException("Cliente inexistente");
		}
		else {
			c.setAdmin(cli.isAdmin());
			c.setApellido(cli.getApellido());
			c.setDNI(cli.getDNI());
			c.setMiCarrito(cli.getMiCarrito());
			c.setNombre(cli.getNombre());
			c.setPassword(null);
		}
	}
	
	public void update(Cliente cliente) throws ClientException {
		clienteData.update(cliente);
	}
	
	public void delete(Cliente cliente) throws ClientException, CartException, CartLineException, SaleException, SaleLineException {
		clienteData.delete(cliente);
	}
	
	
	
}



