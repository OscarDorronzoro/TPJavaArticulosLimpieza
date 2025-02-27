package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Linea;
import util.ArticleException;
import util.CartLineException;
import util.CategoryException;
import util.DBException;
import util.PriceException;
import util.ProviderException;

public class LineaCarritoData extends LineaData {
	
	public void add(Linea linea, String nombreCarrito, String username) throws CartLineException {
		
		PreparedStatement stmt = null;
			

		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into linea_carrito "
					+ "(nombre_carrito,cod_articulo,cuit_proveedor,username,cantidad) values(?,?,?,?,?)");
			
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
	
	public Linea getOne(String nombreCarrito, String username, int codArticulo) throws ProviderException, CartLineException, ArticleException, PriceException, CategoryException {
		
		Linea linea = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from linea_carrito where nombre_carrito=? and username=? and cod_articulo=?");
			
			stmt.setString(1, nombreCarrito);
			stmt.setString(2, username);
			stmt.setInt(3, codArticulo);			
			rs=stmt.executeQuery();
			
			if (rs != null && rs.next()) {
					linea=new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("cod_articulo")));
					linea.setCantidad(rs.getInt("cantidad"));
					linea.setProveedor(this.getProveedorData().getOne(rs.getString("cuit_proveedor")));
			}
		}
		catch (SQLException e) {
			throw new CartLineException("Error when getting one cart line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CartLineException("Error when establishing connection to DB, to get one cart line", e, Level.ERROR);
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
	
	public ArrayList<Linea> getAllByCarrito(String nombreCarrito,String username) throws ProviderException, CartLineException, ArticleException, PriceException, CategoryException{
		
		ArrayList<Linea> lineas = new ArrayList<Linea>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from linea_carrito where nombre_carrito=? and username=?");
			
			stmt.setString(1,nombreCarrito);
			stmt.setString(2,username);
			rs=stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Linea linea = new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("cod_articulo")));
					linea.setCantidad(rs.getInt("cantidad"));
					linea.setProveedor(this.getProveedorData().getOne(rs.getString("cuit_proveedor")));
					
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
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("update linea_carrito "
					+ "set cantidad=? where nombre_carrito=? and username=?");

			stmt.setInt(1, linea.getCantidad());;
			stmt.setString(2, nombreCarrito);
			stmt.setString(3, username);

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
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from linea_carrito where nombre_carrito=? and username=? and cod_articulo=?");
			stmt.setString(1,nombreCarrito);
			stmt.setString(2,username);
			stmt.setInt(3,codArticulo);
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
	
	public void deleteAllByCarrito(String nombreCarrito, String username) throws CartLineException
	{
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("delete from linea_carrito where nombre_carrito=? and username=?");
			stmt.setString(1, nombreCarrito);
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
