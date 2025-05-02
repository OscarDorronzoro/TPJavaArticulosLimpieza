package logic;

import entities.Cart;
import entities.Customer;
import util.CartException;
import util.CartLineException;
import util.CustomerAlreadyExistException;
import util.CustomerException;
import util.CustomerNotFoundException;
import util.DoniaMaryException;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

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
	
	public void add(Customer customer) throws CustomerAlreadyExistException {
		try {
			customer.setPassword(PasswordManager.encrypt(customer.getPassword()));
			
			Cart cart = new Cart("currentPurchase");
			cart.setDescripcion("Here there are articles added on last session");
			customer.setMiCarrito(cart);
			
			cart = new Cart("favorites");
			cart.setDescripcion("Favorites articles");
			customer.setMiCarrito(cart);
			
			cart = new Cart("wishList");
			cart.setDescripcion("Articles that you want to buy");
			customer.setMiCarrito(cart);
			
			cart = new Cart("budget");
			cart.setDescripcion("Saved articles to calc budget");
			customer.setMiCarrito(cart);
			
			this.getClienteData().add(customer);
		}
		catch(Exception e) {
			throw new CustomerAlreadyExistException("Customer's username already exists, choose another", e);
		}
		
	}
	
	public ArrayList<Customer> getAll() throws DoniaMaryException{		
		return this.getClienteData().getAll();
	}
	
	public ArrayList<Customer> getAllByAdmin(boolean isAdmin) throws DoniaMaryException{		
		return this.getClienteData().getAllByAdmin(isAdmin);
	}
	
	public  Customer getOne(String username) throws DoniaMaryException {		
		return this.getClienteData().getOne(username);
	}
	
	public void completeCustomer(Customer customer) throws DoniaMaryException {
		Customer fullCustomer = this.getClienteData().getOneByUserYPassword(customer.getUsername(), customer.getPassword());
		
		if (fullCustomer == null) {
			throw new CustomerNotFoundException("Cliente inexistente", null, Level.INFO);
		}
	
		customer.setAdmin(fullCustomer.isAdmin());
		customer.setApellido(fullCustomer.getApellido());
		customer.setDNI(fullCustomer.getDNI());
		customer.setMiCarrito(fullCustomer.getMyCarts());
		customer.setNombre(fullCustomer.getNombre());
		customer.setPassword(null);
	}
	
	public void update(Customer cliente) throws CustomerException {
		clienteData.update(cliente);
	}
	
	public void delete(Customer cliente) throws CustomerException, CartException, CartLineException, SaleException, SaleLineException {
		clienteData.delete(cliente);
	}
	
	
	
}



