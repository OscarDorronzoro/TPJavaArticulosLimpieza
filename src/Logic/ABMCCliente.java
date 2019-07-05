package Logic;

import java.util.ArrayList;

import data.ClienteData;
import entities.Cliente;

public class ABMCCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClienteData cd = new ClienteData();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		
		clientes=cd.getAll();//fhgdf
		
		for(Cliente c : clientes) {
			System.out.println(c.toString());
		}
	}

}
