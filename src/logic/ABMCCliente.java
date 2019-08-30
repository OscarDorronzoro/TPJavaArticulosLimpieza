package logic;

import entities.Cliente;
import util.ClientAlreadyExistException;
import util.ClientNotFoundException;
import util.PasswordNotMatchException;

import java.util.ArrayList;
import data.ClienteData;
import util.PasswordManager;

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
	
	public ArrayList<Cliente> getAll(){		
		return this.getClienteData().getAll();
	}
	public  Cliente getOne(String username) {		
		return this.getClienteData().getOne(username);
	}
	public void completarCliente(Cliente c) throws ClientNotFoundException {
		Cliente cli = this.getClienteData().getOneByUserYPassword(c.getUsername(), PasswordManager.encriptar(c.getPassword()));
		
		if(cli==null) {
			throw new ClientNotFoundException("Cliente inexistente");
		}
		else{
			c=cli;
		}
	}
}
