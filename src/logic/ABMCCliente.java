package logic;

import entities.Cliente;
import data.ClienteData;

public class ABMCCliente {

	public void add(Cliente c) {
		ClienteData cd = new ClienteData();
		cd.add(c);
	}
}
