package logic;

import entities.Cliente;
import java.util.ArrayList;
import data.ClienteData;

public class ABMCCliente {

	public void add(Cliente c) {
		ClienteData cd = new ClienteData();
		cd.add(c);
	}
	
	public ArrayList<Cliente> getAll(){
		ClienteData cd = new ClienteData();
		return cd.getAll();
	}
}
