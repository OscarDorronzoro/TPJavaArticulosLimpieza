package data;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Cliente;
import util.PasswordManager;

import util.CartException;
import util.CustomerException;
import util.DBException;
import util.PasswordDoesNotMatchException;
import util.SaleException;

public class ClienteData {
	
	static CarritoData carritoData  = new CarritoData();
	static VentaData ventaData = new VentaData();
	
	public void add(Cliente c) throws CustomerException {
		PreparedStatement stmt = null;
		Statement transaccion = null;
		try {
			transaccion = FactoryConnection.getInstancia().getConn().createStatement();
			transaccion.execute("begin");
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into customers(name, last_name, dni, username, password, is_admin, email) "
				+ "values(?,?,?,?,?,?,?)"
			);
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getApellido());
			stmt.setString(3, c.getDNI());
			stmt.setString(4, c.getUsername());
			stmt.setString(5, c.getPassword());
			stmt.setBoolean(6, c.isAdmin());
			stmt.setString(7, c.getEmail());
			
			stmt.executeUpdate();
			
			carritoData.add(c.getMiCarrito(), c.getUsername());
			transaccion.execute("commit");
		}
		catch (SQLException | CartException doRollback) {
			try {
				transaccion.execute("rollback");
				throw new CustomerException("Error when adding new customer", doRollback, Level.ERROR);
			}
			catch (SQLException e) {
				throw new CustomerException("Error when performing rollback from adding new customer", e, Level.ERROR);
			}
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to add new customer", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CustomerException("Error when finishing adding new customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after adding new customer", e, Level.ERROR);
			}
		}
	}
	
	public Cliente getOne(String username) throws CustomerException {
		
		Cliente c = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from customers where username=?"
			);
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
				c = new Cliente();
				
				c.setNombre(rs.getString("name"));
				c.setApellido(rs.getString("last_name"));
				c.setDNI(rs.getString("dni"));
				c.setPassword(rs.getString("password"));
				c.setUsername(rs.getString("username"));
				c.setAdmin(rs.getBoolean("is_admin"));
				c.setEmail(rs.getString("email"));
				
				c.setMiCarrito(carritoData.getOne("compraActual", c.getUsername()));
			}
		}
		catch (SQLException e) {
			throw new CustomerException("Error when getting one customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to get one customer", e, Level.ERROR);
		}
		catch (CartException e) {
			throw new CustomerException("Error when getting one cart, to get one customer", e, Level.ERROR);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CustomerException("Error when finishing getting one customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after getting one customer", e, Level.ERROR);
			}
		}
		
		return c;
	}

	public ArrayList<Cliente> getAll() throws CustomerException {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from customers");
			
			if (rs != null) {
				while (rs.next()) {
					Cliente c = new Cliente();
					
					c.setNombre(rs.getString("name"));
					c.setApellido(rs.getString("last_name"));
					c.setDNI(rs.getString("dni"));
					c.setPassword(rs.getString("password"));
					c.setUsername(rs.getString("username"));
					c.setAdmin(rs.getBoolean("is_admin"));
					c.setEmail(rs.getString("email"));
					
					c.setMiCarrito(carritoData.getOne("compraActual", c.getUsername()));
					
					clientes.add(c);					
				}
			}
		}
		catch (SQLException e) {
			throw new CustomerException("Error when getting all customers", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to get all customers", e, Level.ERROR);
		}
		catch (CartException e) {
			throw new CustomerException("Error when getting one cart, to get all customers", e, Level.ERROR);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CustomerException("Error when finishing getting all customers", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after getting all customers", e, Level.ERROR);
			}
		}
		return clientes;
	}
	
	public Cliente getOneByUserYPassword(String username,String plainTextPassword ) throws CustomerException {
		Cliente c = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from customers where username=?"
			);
			
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
				String storedPassword = rs.getString("password");
				boolean isValidPassword;
				isValidPassword = PasswordManager.validatePassword(plainTextPassword, storedPassword);
				
				if (!isValidPassword) {
					throw new PasswordDoesNotMatchException("User password does't match", null, Level.INFO);
				}
				
				c = new Cliente();
				
				c.setNombre(rs.getString("name"));
				c.setApellido(rs.getString("last_name"));
				c.setDNI(rs.getString("dni"));
				c.setUsername(rs.getString("username"));
				c.setAdmin(rs.getBoolean("is_admin"));
				c.setEmail(rs.getString("email"));
				
				c.setMiCarrito(carritoData.getAllByCustomer(c.getUsername()));
			}
		}
		catch (SQLException e) {
			throw new CustomerException("Error when getting one customer by username and password", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to get one customer by username and password", e, Level.ERROR);
		}
		catch (NoSuchAlgorithmException e) {
			throw new CustomerException("Error when comparing user's password, no such algorithm", e, Level.ERROR);
		}
		catch (InvalidKeySpecException e) {
			throw new CustomerException("Error when comparing user's password, invalid key spec", e, Level.ERROR);
		}
		catch (CartException e) {
			throw new CustomerException("Error when getting one cart, to get one customer by username and password", e, Level.ERROR);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CustomerException("Error when finishing getting one customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after getting one customer", e, Level.ERROR);
			}
		}
		return c;
	}
		
	public ArrayList<Cliente> getAllByAdmin(boolean isAdmin) throws CustomerException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from customers where is_admin=?"
			);
			stmt.setBoolean(1, isAdmin);
			
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cliente c = new Cliente();
					
					c.setNombre(rs.getString("name"));
					c.setApellido(rs.getString("last_name"));
					c.setDNI(rs.getString("dni"));
					c.setPassword(rs.getString("password"));
					c.setUsername(rs.getString("username"));
					c.setAdmin(rs.getBoolean("is_admin"));
					c.setEmail(rs.getString("email"));
					
					c.setMiCarrito(carritoData.getOne("currentPurchase", c.getUsername()));
					
					clientes.add(c);					
				}
			}
		}
		catch (SQLException e) {
			throw new CustomerException("Error when getting all admin users", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to get all admin users", e, Level.ERROR);
		}
		catch (CartException e) {
			throw new CustomerException("Error when getting one cart, to get all admin users", e, Level.ERROR);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CustomerException("Error when finishing getting all admin users", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after getting all admin users", e, Level.ERROR);
			}
		}
		
		return clientes;
	}
	
	public void update(Cliente cliente) throws CustomerException
	{
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update customers set name=?, last_name=?, dni=?, "
				+ "is_admin=?, email=? "
				+ "where username=?"
			);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getDNI());
			stmt.setBoolean(4, cliente.isAdmin());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getUsername());
			
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new CustomerException("Error when updating customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to update customer", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CustomerException("Error when finishing updating customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after updating customer", e, Level.ERROR);
			}	
		}	
	}
	
	public void delete(Cliente cliente) throws CustomerException {
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from customers where username=?"
			);
			stmt.setString(1, cliente.getUsername());
			
			carritoData.deleteAllByCustomer(cliente.getMiCarrito(), cliente.getUsername());
			ventaData.deleteAllByCustomer(cliente.getUsername());
			
			stmt.execute();
		}
		catch (SQLException e) {
			throw new CustomerException("Error when deleting customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CustomerException("Error when establishing connection to DB, to delete customer", e, Level.ERROR);
		}
		catch (CartException e) {
			throw new CustomerException("Error when deleting all carts by customer, to delete customer", e, Level.ERROR);
		}
		catch (SaleException e) {
			throw new CustomerException("Error when deleting all sales by customer, to delete customer", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch (SQLException e) {
				throw new CustomerException("Error when finishing deleting sale", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CustomerException("Error when closing connection to DB, after deleting sale", e, Level.ERROR);
			}
		}
	}
	
}
