package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Linea;
import entities.Carrito;
import util.ArticleException;
import util.CartException;
import util.CartLineException;
import util.CategoryException;
import util.DBException;
import util.PriceException;
import util.ProviderException;

import org.apache.logging.log4j.Level;

public class CarritoData {
	
	private LineaCarritoData lineaData=new LineaCarritoData();
	
	public void add(Carrito carrito, String username) throws CartException, CartLineException {
		
		PreparedStatement stmt=null;
			
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into carrito "
					+ "(nombre,username,descripcion) values(?,?,?)");
			
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
	
	public Carrito getOne(String nombre, String username) throws ProviderException, CartLineException, CartException, ArticleException, PriceException, CategoryException {
		
		Carrito carrito = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from carrito where nombre=? and username=?");
			
			stmt.setString(1, nombre);
			stmt.setString(2, username);			
			rs=stmt.executeQuery();
			
			if(rs!=null&&rs.next()) {
				carrito=new Carrito();
					
				carrito.setNombre(rs.getString("nombre"));
				carrito.setDescripcion(rs.getString("descripcion"));
				carrito.setLineas(lineaData.getAllByCarrito(carrito.getNombre(),username));

				
			}
		}
		catch (SQLException e) {
			throw new ProviderException("Error when getting one cart", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to get one cart", e, Level.ERROR);
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
					throw new ProviderException("Error when finishing getting one cart", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new ProviderException("Error when closing connection to DB, after getting one cart", e, Level.ERROR);
				}
		}
		
		return carrito;
	}
	
	public void delete(Carrito carrito, String username) throws CartException, CartLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from carrito where nombre=? and username=?");
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

	public void deleteAllByCliente(Carrito carrito, String username) throws DBException, CartException, CartLineException {
		
		PreparedStatement stmt=null;
		
			
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from carrito where username=?");
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
