package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Price;
import util.DBException;
import util.PriceException;

public class PrecioData {

	private LocalDateTime toLocalDateTime(Timestamp sqlDatetime) {
		if (sqlDatetime == null) {
			return null;
		}
		return sqlDatetime.toLocalDateTime();
	}
	
	private Timestamp toTimestamp(LocalDateTime javaDatetime) {
		if (javaDatetime == null) {
			return null;
		}
		return Timestamp.valueOf(javaDatetime);
	}
	
	public void add(Price precio,int codArticulo) throws PriceException {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into prices(article_code, date_from, price) "
				+ "values(?,?,?)"
			);
			stmt.setInt(1, codArticulo);
			stmt.setTimestamp(2, this.toTimestamp(precio.getFechaDesde()));
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
	
	public Price getCurrentPrice(int articleCode) throws PriceException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Price precio = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from prices pr where pr.article_code=? "
					+ "and date_from=("
						+ "select max(date_from) from prices pr2 "
						+ "where pr2.article_code=? and date_from <= ?"
					+ ")"
			);
			stmt.setInt(1, articleCode);
			stmt.setInt(2, articleCode);
			
			// Avoid getting futures prices
			stmt.setTimestamp(3, this.toTimestamp(LocalDateTime.now()));
			
			rs = stmt.executeQuery();
			
			if (rs != null && rs.next()) {
					precio = new Price();
					
					precio.setFechaDesde(this.toLocalDateTime(rs.getTimestamp("date_from")));
					precio.setValor(rs.getDouble("price"));
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
	
	public ArrayList<Price> getAll(int codArticulo) throws PriceException{
		
		ArrayList<Price> precios = new ArrayList<Price>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from prices pr where pr.article_code=?"
			);
			stmt.setInt(1, codArticulo);
			
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Price precio = new Price();
					
					precio.setFechaDesde(this.toLocalDateTime(rs.getTimestamp("date_from")));
					precio.setValor(rs.getDouble("price"));
					
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
	
	public void update(Price price, int articleCode) throws PriceException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update from prices set date_from=?, price=?"
				+ " where article_code=?"
			);
			stmt.setTimestamp(1, this.toTimestamp(price.getFechaDesde()));
			stmt.setDouble(2, price.getValor());
			stmt.setInt(6, articleCode);
			
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

