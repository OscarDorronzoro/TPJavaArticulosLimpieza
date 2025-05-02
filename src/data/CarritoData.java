package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Linea;
import entities.Carrito;

import util.CartException;
import util.CartLineException;
import util.DBException;

import org.apache.logging.log4j.Level;

public class CarritoData {
	
	private LineaCarritoData lineaData = new LineaCarritoData();
	
	public void add(Carrito carrito, String username) throws CartException {
		
		PreparedStatement stmt = null;
			
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into carts (name, username, description) values(?,?,?)"
			);
			
			stmt.setString(1, carrito.getNombre());
			stmt.setString(2, username);
			stmt.setString(3, carrito.getDescripcion()); 
			stmt.executeUpdate();
			
			for (Linea linea : carrito.getLineas()) {
				lineaData.add(linea, carrito.getNombre(),username);
			}
		}
		catch (SQLException e) {
			throw new CartException("Error when adding new cart", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartException("Error when establishing connection to DB, to add new cart", e, Level.ERROR);
		}
		catch (CartLineException e) {
			throw new CartException("Error when adding cart line, to add new cart", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartException("Error when finishing adding new cart", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartException("Error when closing connection to DB, after adding new cart", e, Level.ERROR);
			}
		}
		
	}
	
	public Carrito getOne(String nombre, String username) throws CartException {
		
		Carrito carrito = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from carts where name=? and username=?"
			);
			
			stmt.setString(1, nombre);
			stmt.setString(2, username);			
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
				carrito = new Carrito();
					
				carrito.setNombre(rs.getString("name"));
				carrito.setDescripcion(rs.getString("description"));
				carrito.setLineas(lineaData.getAllByCart(carrito.getNombre(), username));
			}
		}
		catch (SQLException e) {
			throw new CartException("Error when getting one cart", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartException("Error when establishing connection to DB, to get one cart", e, Level.ERROR);
		}
		catch (CartLineException e) {
			throw new CartException("Error when getting all cart line by cart, to get one cart", e, Level.ERROR);
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
					throw new CartException("Error when finishing getting one cart", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new CartException("Error when closing connection to DB, after getting one cart", e, Level.ERROR);
				}
		}
		
		return carrito;
	}
	
	public ArrayList<Carrito> getAllByCustomer(String username) throws CartException {
		
		ArrayList<Carrito> carts = new ArrayList<Carrito>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from carts where username=?"
			);
			
			stmt.setString(1, username);			
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Carrito cart = new Carrito();
						
					cart.setNombre(rs.getString("name"));
					cart.setDescripcion(rs.getString("description"));
					cart.setLineas(lineaData.getAllByCart(cart.getNombre(), username));
					
					carts.add(cart);
				}
			}
		}
		catch (SQLException e) {
			throw new CartException("Error when getting all carts by customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartException("Error when establishing connection to DB, to get all carts by customer", e, Level.ERROR);
		}
		catch (CartLineException e) {
			throw new CartException("Error when getting all cart line by cart, to get all carts by customer", e, Level.ERROR);
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
					throw new CartException("Error when finishing getting all carts by customer", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new CartException("Error when closing connection to DB, after getting all carts by custoemr", e, Level.ERROR);
				}
		}
		
		return carts;
	}
	
	public void delete(Carrito carrito, String username) throws CartException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from carts where name=? and username=?"
			);
			stmt.setString(1,carrito.getNombre());
			stmt.setString(2,username);
			
			for (Linea linea : carrito.getLineas()) {
				lineaData.delete(carrito.getNombre(), username, linea.getArticulo().getCodArticulo());
			}
			
			stmt.execute();
		}
		catch (SQLException e) {
			throw new CartException("Error when deleting cart", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartException("Error when establishing connection to DB, to delete cart", e, Level.ERROR);
		}
		catch (CartLineException e) {
			throw new CartException("Error when deleting cart line, to delete cart", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartException("Error when finishing deleting cart", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartException("Error when closing connection to DB, after deleting cart", e, Level.ERROR);
			}		
		}
		
		
	}

	public void deleteAllByCustomer(Carrito carrito, String username) throws CartException {
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from carts where username=?"
			);
			stmt.setString(1, username);
			
			if (carrito != null) {
				for (Linea linea : carrito.getLineas()) {
					lineaData.delete(carrito.getNombre(), username, linea.getArticulo().getCodArticulo());
				}
			}
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new CartException("Error when deleting all carts by customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartException("Error when establishing connection to DB, to delete all carts by customer", e, Level.ERROR);
		}
		catch (CartLineException e) {
			throw new CartException("Error when deleting cart line, to delete all carts by customer", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartException("Error when finishing deleting all carts by customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartException("Error when closing connection to DB, after deleting all carts by customer", e, Level.ERROR);
			}		
		}
	}

	
}
