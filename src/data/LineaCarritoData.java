package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Linea;

import util.ArticleException;
import util.CartLineException;
import util.DBException;
import util.ProviderException;

public class LineaCarritoData extends LineaData {
	
	public void add(Linea linea, String nombreCarrito, String username) throws CartLineException {
		PreparedStatement stmt = null;
			
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into cart_lines ("
					+ "cart_name, article_code, provider_cuit, username, amount) "
				+ "values(?,?,?,?,?)"
			);
			
			stmt.setString(1, nombreCarrito);
			stmt.setInt(2, linea.getArticulo().getCodArticulo());;
			stmt.setString(3, linea.getArticulo().getProveedores().get(0).getCuit()); //como elegir un proveedor??
			stmt.setString(4, username);
			stmt.setInt(5, linea.getCantidad());
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new CartLineException("Error when adding new cart line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to add new cart line", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
	            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartLineException("Error when finishing adding new cart line", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartLineException("Error when closing connection to DB, after adding new cart line", e, Level.ERROR);
			}
		}
		
	}
	
	public Linea getOne(String nombreCarrito, String username, int codArticulo) throws CartLineException {
		Linea linea = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from cart_lines "
				+ "where cart_name=? and username=? and article_code=?"
			);
			
			stmt.setString(1, nombreCarrito);
			stmt.setString(2, username);
			stmt.setInt(3, codArticulo);
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
					linea = new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("article_code")));
					linea.setCantidad(rs.getInt("amount"));
					linea.setProveedor(this.getProveedorData().getOne(rs.getString("provider_cuit")));
			}
		}
		catch (SQLException e) {
			throw new CartLineException("Error when getting one cart line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to get one cart line", e, Level.ERROR);
		}
		catch (ArticleException e) {
			throw new CartLineException("Error when getting one article, to get one cart line", e, Level.ERROR);
		}
		catch (ProviderException e) {
			throw new CartLineException("Error when getting one provider, to get one cart line", e, Level.ERROR);
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
					throw new CartLineException("Error when finishing getting one cart line", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new CartLineException("Error when closing connection to DB, after getting one cart line", e, Level.ERROR);
				}
		}
		
		return linea;
	}
	
	public ArrayList<Linea> getAllByCart(String cartName, String username) throws CartLineException {
		ArrayList<Linea> lineas = new ArrayList<Linea>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from cart_lines where cart_name=? and username=?"
			);
			
			stmt.setString(1, cartName);
			stmt.setString(2, username);
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Linea linea = new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("article_code")));
					linea.setCantidad(rs.getInt("amount"));
					linea.setProveedor(this.getProveedorData().getOne(rs.getString("provider_cuit")));
					
					lineas.add(linea);					
				}
			}
		}
		catch (SQLException e) {
			throw new CartLineException("Error when getting all cart lines by cart", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to get all cart lines by cart", e, Level.ERROR);
		}
		catch (ArticleException e) {
			throw new CartLineException("Error when getting one article, to get all cart lines by cart", e, Level.ERROR);
		}
		catch (ProviderException e) {
			throw new CartLineException("Error when getting one provider, to get all cart lines by cart", e, Level.ERROR);
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
				throw new CartLineException("Error when finishing getting all cart lines by cart", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartLineException("Error when closing connection to DB, after getting all cart lines by cart", e, Level.ERROR);
			}
		}
		
		return lineas;
	}
	
	public void update(Linea linea, String nombreCarrito, String username) throws CartLineException {
		PreparedStatement stmt = null;

		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
				"update cart_lines set amount=? "
				+ "where cart_name=? and username=? and article_code=?"
			);

			stmt.setInt(1, linea.getCantidad());;
			stmt.setString(2, nombreCarrito);
			stmt.setString(3, username);
			stmt.setInt(4, linea.getArticulo().getCodArticulo());

			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new CartLineException("Error when updating cart line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to update cart line", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
	            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartLineException("Error when finishing updating cart line", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartLineException("Error when closing connection to DB, after updating cart line", e, Level.ERROR);
			}
		}
		
	}
	
	public void delete(String nombreCarrito, String username, int codArticulo) throws CartLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from cart_lines "
				+ "where cart_name=? and username=? and article_code=?"
			);
			stmt.setString(1, nombreCarrito);
			stmt.setString(2, username);
			stmt.setInt(3, codArticulo);
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new CartLineException("Error when deleting cart line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to delete cart line", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartLineException("Error when finishing deleting cart line", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartLineException("Error when closing connection to DB, after deleting cart line", e, Level.ERROR);
			}	
		}
		
		
	}
	
	public void deleteAllByCart(String cartName, String username) throws CartLineException
	{
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"delete from cart_lines "
					+ "where cart_name=? and username=?");
			stmt.setString(1, cartName);
			stmt.setString(2, username);
			
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new CartLineException("Error when deleting cart line by cart", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to delete cart line by cart", e, Level.ERROR);
		}
		finally
		{
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch (SQLException e) {
				throw new CartLineException("Error when finishing deleting cart line by cart", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CartLineException("Error when closing connection to DB, after deleting cart line by cart", e, Level.ERROR);
			}
		}
		
	}
}
