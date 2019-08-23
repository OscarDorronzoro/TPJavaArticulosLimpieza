package logic;

import entities.Cliente;
import util.ClientNotFoundException;
import util.PasswordNotMatchException;
import java.util.ArrayList;
import data.ClienteData;

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
	public void add(Cliente c) {
		this.getClienteData().add(c);
	}
	
	public ArrayList<Cliente> getAll(){		
		return this.getClienteData().getAll();
	}
	public  Cliente getOne(String username) {		
		return this.getClienteData().getOne(username);
	}
	public void completarCliente(Cliente c) throws ClientNotFoundException,PasswordNotMatchException {
		Cliente cli = this.getOne(c.getUsername());
		
		if(cli==null) {
			throw new ClientNotFoundException("Cliente inexistente");
		}
		else{
			if(cli.getPassword().equals(c.getPassword())) {
				c=cli;
			}
			else {
				throw new PasswordNotMatchException("Contraseña incorrecta");
			}
		}
	}
}
