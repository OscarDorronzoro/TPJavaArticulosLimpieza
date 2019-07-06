package Logic;

import data.ClienteData;
import entities.Cliente;

public class ABMCCliente {

	public void add(Cliente c) {
		ClienteData cd = new ClienteData();
		cd.add(c);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ClienteData cd = new ClienteData();
		
//		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
//
//		
//		clientes=cd.getAll();
//		
//		for(Cliente c : clientes) {
//			System.out.println(c.toString());
//		}
	}

}
