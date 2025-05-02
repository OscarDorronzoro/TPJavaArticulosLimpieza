package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Linea;
import entities.Venta;

import util.CustomerException;
import util.DBException;
import util.SaleException;
import util.SaleLineException;

public class VentaData {
	
	static ClienteData clienteData = new ClienteData();
	static LineaVentaData lineaVentaData = new LineaVentaData();
	
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
	
	public void add(Venta venta) throws SaleException
	{
		PreparedStatement stmt = null;
		Statement transaccion = null;
		
		try {
			transaccion = FactoryConnection.getInstancia().getConn().createStatement();
			transaccion.execute("start transaction");
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into sales "
					+ "(emission_date, sale_amount, username, cancellation_date, payment_date, withdrawal_date) "
					+ "values (?, ?, ?, ?, ?, ?)"
				,PreparedStatement.RETURN_GENERATED_KEYS
			);
			stmt.setTimestamp(1, this.toTimestamp(venta.getfEmision()));
			stmt.setDouble(2, venta.getTotal());
			stmt.setString(3, venta.getCliente().getUsername());
			stmt.setTimestamp(4, this.toTimestamp(venta.getfCancelacion()));
			stmt.setTimestamp(5, this.toTimestamp(venta.getfPago()));
			stmt.setTimestamp(6, this.toTimestamp(venta.getfRetiro()));
			
			stmt.executeUpdate();
			ResultSet primaryKey = stmt.getGeneratedKeys();
			
			if (primaryKey != null && primaryKey.next()) {
				venta.setNroVenta(primaryKey.getInt(1));
			}
			
			for (Linea linea : venta.getLineas()) {
				lineaVentaData.add(linea, venta.getNroVenta());
			}	
			
			transaccion.execute("commit");
		}
		catch (SQLException doRollback) {
			try {
				transaccion.execute("rollback");
				throw new SaleException("Error when adding new sale", doRollback, Level.ERROR);
			}
			catch (SQLException e) {
				throw new SaleException("Error when performing rollback from adding new sale", e, Level.ERROR);
			}
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to add new sale", e, Level.ERROR);
		}
		catch (SaleLineException e) {
			throw new SaleException("Error when adding sale line, to add new sale", e, Level.ERROR);
		}
		finally {
			try {  
				if (stmt != null) {
					stmt.close();
				}
			 	FactoryConnection.getInstancia().releaseConn();
			 	
			}
			catch (SQLException e) {
				throw new SaleException("Error when finishing adding new sale", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after adding new sale", e, Level.ERROR);
			}
		 }		
	}
	
	public Venta getOne(int saleNumber) throws SaleException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Venta venta = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from sales where sale_number=?"
			);
			stmt.setInt(1, saleNumber);
			
			rs = stmt.executeQuery();
			if (rs != null && rs.next())
			{
				venta = new Venta();
				venta.setfCancelacion(this.toLocalDateTime(rs.getTimestamp("cancellation_date")));
				venta.setfEmision(this.toLocalDateTime(rs.getTimestamp("emission_date")));
				venta.setfPago(this.toLocalDateTime(rs.getTimestamp("payment_date")));
				venta.setfRetiro(this.toLocalDateTime(rs.getTimestamp("withdrawal_date")));
				venta.setNroVenta(rs.getInt("sale_number"));
				venta.setImporte(rs.getDouble("sale_amount"));
				
				venta.setCliente(clienteData.getOne(rs.getString("username")));
				venta.setLineas(lineaVentaData.getAllBySale(venta.getNroVenta()));
				
			}
		}
		catch (SQLException e) {
			throw new SaleException("Error when getting one sale", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to get one sale", e, Level.ERROR);
		}
		catch (CustomerException e) {
			throw new SaleException("Error when getting one customer, to get one sale", e, Level.ERROR);
		}
		catch (SaleLineException e) {
			throw new SaleException("Error when getting all sale lines by sale, to get one sale", e, Level.ERROR);
		}
		finally
		{
			try {
				if (rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
				
			}
			catch (SQLException e) {
				throw new SaleException("Error when finishing getting one sale", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after getting one sale", e, Level.ERROR);
			}
		}
	
		return venta;
	}
	
	public ArrayList<Venta> getAll() throws SaleException {
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from sales");
			
			if (rs != null) {
				while (rs.next()) {
					Venta venta = new Venta();
					
					venta.setNroVenta(rs.getInt("sale_number"));
					venta.setfEmision(this.toLocalDateTime(rs.getTimestamp("emission_date")));
					venta.setfPago(this.toLocalDateTime(rs.getTimestamp("payment_date")));
					venta.setfCancelacion(this.toLocalDateTime(rs.getTimestamp("cancellation_date")));
					venta.setfRetiro(this.toLocalDateTime(rs.getTimestamp("withdrawal_date")));
					venta.setImporte(rs.getDouble("sale_amount"));
					
					venta.setCliente(clienteData.getOne(rs.getString("username")));
					venta.setLineas(lineaVentaData.getAllBySale(venta.getNroVenta()));
					
					ventas.add(venta);
				}
			}
		}
		catch (SQLException e) {
			throw new SaleException("Error when getting all sales", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to get all sales", e, Level.ERROR);
		}
		catch (CustomerException e) {
			throw new SaleException("Error when getting one customer, to get all sales", e, Level.ERROR);
		}
		catch (SaleLineException e) {
			throw new SaleException("Error when getting all sale lines by sale, to get all sales", e, Level.ERROR);
		}
		finally {
				try {
					if( rs != null) {
						rs.close();
					}
					if( stmt != null ) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new SaleException("Error when finishing getting all sales", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new SaleException("Error when closing connection to DB, after getting all sales", e, Level.ERROR);
				}
		}
		
		return ventas;
	}
		
	public ArrayList<Venta> getAllPendingByCustomer(String username) throws SaleException	{
		ArrayList<Venta> ventas = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			String query = "select * from sales where username=? and payment_date is null";
			if (username == null) {
				query = "select * from sales where payment_date is null";
			}
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				query
			);
			
			if (username != null) {
				stmt.setString(1, username);
			}
			
			rs = stmt.executeQuery();
			if (rs != null) {
				ventas = new ArrayList<Venta>();
				
				while (rs.next()) {
					Venta venta= new Venta();
					
					venta.setfCancelacion(this.toLocalDateTime(rs.getTimestamp("cancellation_date")));
					venta.setfEmision(this.toLocalDateTime(rs.getTimestamp("emission_date")));
					venta.setfPago(this.toLocalDateTime(rs.getTimestamp("payment_date")));
					venta.setfRetiro(this.toLocalDateTime(rs.getTimestamp("withdrawal_date")));
					venta.setNroVenta(rs.getInt("sale_number"));
					venta.setImporte(rs.getDouble("sale_amount"));
					
					venta.setCliente(clienteData.getOne(username));
					venta.setLineas(lineaVentaData.getAllBySale(venta.getNroVenta()));
					
					ventas.add(venta);	
				}
			}
		}
		catch (SQLException e) {
			throw new SaleException("Error when getting all pending sales by customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to get all pending sales by customer", e, Level.ERROR);
		}
		catch (CustomerException e) {
			throw new SaleException("Error when getting one customer, to get all pending sales by customer", e, Level.ERROR);
		}
		catch (SaleLineException e) {
			throw new SaleException("Error when getting all sale lines by sale, to get all pending sales by customer", e, Level.ERROR);
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
				throw new SaleException("Error when finishing getting all pending sales by customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after getting all pending sales by customer", e, Level.ERROR);
			}
		}
		
		return ventas;
	}
	
	public ArrayList<Venta> getAllCompletedByCustomer(String username) throws SaleException	{
		ArrayList<Venta> ventas = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			String query = "select * from sales "
					+ "where username=? and payment_date is not null and cancellation_date is null";
			
			if (username == null) {
				query = "select * from sales "
						+ "where payment_date is not null and cancellation_date is null";
			}
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				query
			);
			
			if (username != null) {
				stmt.setString(1, username);
			}
			
			rs = stmt.executeQuery();
			if (rs != null) {
				ventas = new ArrayList<Venta>();
				
				while (rs.next()) {
					Venta venta= new Venta();
					
					venta.setfCancelacion(this.toLocalDateTime(rs.getTimestamp("cancellation_date")));
					venta.setfEmision(this.toLocalDateTime(rs.getTimestamp("emission_date")));
					venta.setfPago(this.toLocalDateTime(rs.getTimestamp("payment_date")));
					venta.setfRetiro(this.toLocalDateTime(rs.getTimestamp("withdrawal_date")));
					venta.setNroVenta(rs.getInt("sale_number"));
					venta.setImporte(rs.getDouble("sale_amount"));
					
					venta.setCliente(clienteData.getOne(username));
					venta.setLineas(lineaVentaData.getAllBySale(venta.getNroVenta()));
					
					ventas.add(venta);	
				}
			}
		}
		catch (SQLException e) {
			throw new SaleException("Error when getting all completed sales by customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to get all completed sales by customer", e, Level.ERROR);
		}
		catch (CustomerException e) {
			throw new SaleException("Error when getting one customer, to get all completed sales by customer", e, Level.ERROR);
		}
		catch (SaleLineException e) {
			throw new SaleException("Error when getting all sale lines by sale, to get all completed sales by customer", e, Level.ERROR);
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
				throw new SaleException("Error when finishing getting all completed sales by customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after getting all completed sales by customer", e, Level.ERROR);
			}
		}
		return ventas;
	}
	
	public void update(Venta sale) throws SaleException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update sales "
					+ "set withdrawal_date=?, payment_date=?, cancellation_date=? "
				+ "where sale_number=?"
			);
			stmt.setTimestamp(1, this.toTimestamp(sale.getfRetiro()));
			stmt.setTimestamp(2, this.toTimestamp(sale.getfPago()));
			stmt.setTimestamp(3, this.toTimestamp(sale.getfCancelacion()));
			stmt.setInt(4, sale.getNroVenta());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new SaleException("Error when updating sale", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to update sale", e, Level.ERROR);
		}
		finally{
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleException("Error when finishing updating sale", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after updating sale", e, Level.ERROR);
			}
			
		}
	}
	
	public void delete(Venta sale) throws SaleException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from sales where sale_number=?");
			stmt.setInt(1, sale.getNroVenta());
			
			for (Linea sellLine : sale.getLineas()) {
				lineaVentaData.delete(sale.getNroVenta(), sellLine);
			}
			
			stmt.execute();
		}
		catch (SQLException e) {
			throw new SaleException("Error when deleting sale", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to delete sale", e, Level.ERROR);
		}
		catch (SaleLineException e) {
			throw new SaleException("Error when deleting sale lines, to delete sale", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleException("Error when finishing deleting sale", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after deleting sale", e, Level.ERROR);
			}	
		}		
	}
	
	public void deleteAllByCustomer(String username) throws SaleException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete s, sl from sales s "
					+ "inner join sale_lines sl on s.sale_number=sl.sale_number "
				+ "where v.username=?"
			);
			stmt.setString(1, username);
			
			stmt.execute();
		}
		catch (SQLException e) {
			throw new SaleException("Error when deleting all sales by customer", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to delete all sales by customer", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleException("Error when finishing deleting all sales by customer", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleException("Error when closing connection to DB, after deleting all sales by customer", e, Level.ERROR);
			}	
		}		
	}
	
}

