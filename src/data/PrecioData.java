package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Precio;
import util.DBException;
import util.PriceException;

public class PrecioData {

	public void add(Precio precio,int codArticulo) throws PriceException {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into precio(cod_articulo,fecha_desde,precio) values(?,?,?)");
			stmt.setInt(1, codArticulo);
			stmt.setTimestamp(2, Timestamp.valueOf(precio.getFechaDesde()));
			stmt.setDouble(3, precio.getValor());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new PriceException("Error when adding new price", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new PriceException("Error when establishing connection to DB, to add new price", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch (SQLException e) {
				throw new PriceException("Error when finishing adding new price", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new PriceException("Error when closing connection to DB, after adding new price", e, Level.ERROR);
			}
		}
		
	}
	
	public Precio getPrecioActual(int codArticulo) throws PriceException{
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Precio precio=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from precio where precio.cod_articulo=? and fecha_desde=("
					+ "select max(fecha_desde) from precio where precio.cod_articulo=?)");
			stmt.setInt(1, codArticulo);
			stmt.setInt(2, codArticulo);
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
					precio=new Precio();
					
					precio.setFechaDesde(rs.getTimestamp("fecha_desde").toLocalDateTime());
					precio.setValor(rs.getDouble("precio"));					
			}
			
		}
		catch (SQLException e) {
			throw new PriceException("Error when getting current price", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new PriceException("Error when establishing connection to DB, to get current price", e, Level.ERROR);
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
					throw new PriceException("Error when finishing getting current price", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new PriceException("Error when closing connection to DB, after getting current price", e, Level.ERROR);
				}
		}
		
		return precio;
	}
	
	public ArrayList<Precio> getAll(int codArticulo) throws PriceException{
		
		ArrayList<Precio> precios = new ArrayList<Precio>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from precio where precio.cod_articulo=?");
			stmt.setInt(1, codArticulo);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					Precio precio=new Precio();
					
					precio.setFechaDesde(rs.getTimestamp("fecha_desde").toLocalDateTime());
					precio.setValor(rs.getDouble("precio"));
					
					precios.add(precio);					
				}
			}
		}
		catch (SQLException e) {
			throw new PriceException("Error when getting all prices", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new PriceException("Error when establishing connection to DB, to get all prices", e, Level.ERROR);
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
					throw new PriceException("Error when finishing getting all prices", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new PriceException("Error when closing connection to DB, after getting all prices", e, Level.ERROR);
				}
		}
		
		return precios;
	}
	
	public void update(Precio precio, int codArticulo) throws PriceException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update from precio set fecha_desde=?,precio=?"
					+ " where precio.cod_articulo=?");
			stmt.setTimestamp(1, Timestamp.valueOf(precio.getFechaDesde()));
			stmt.setDouble(2, precio.getValor());
			stmt.setInt(6, codArticulo);
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new PriceException("Error when updating price", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new PriceException("Error when establishing connection to DB, to update price", e, Level.ERROR);
		}
		finally {
				try {
					if( stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				}
				catch (SQLException e) {
					throw new PriceException("Error when finishing updating price", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new PriceException("Error when closing connection to DB, after updating price", e, Level.ERROR);
				}
		}
	}
}

