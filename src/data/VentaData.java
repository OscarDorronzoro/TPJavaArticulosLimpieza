package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Linea;
import entities.Venta;
import util.ArticleException;
import util.CartException;
import util.CartLineException;
import util.CategoryException;
import util.ClientException;
import util.DBException;
import util.PriceException;
import util.ProviderException;
import util.SaleException;
import util.SaleLineException;

public class VentaData {
	
	static ClienteData clienteData = new ClienteData();
	static LineaVentaData lineaVentaData = new LineaVentaData();
	
	public void add(Venta venta) throws SaleException, SaleLineException
	{
		PreparedStatement stmt=null;
		Statement transaccion=null;
		
		try {
			transaccion=FactoryConnection.getInstancia().getConn().createStatement();
			transaccion.execute("start transaction");
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into venta "
					+ "(f_emision,importe,username) values(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setTimestamp(1, new java.sql.Timestamp(venta.getfEmision().getTime()));
			//stmt.setDate(2, new java.sql.Timestamp(venta.getfCancelacion().getTime()));
			//stmt.setDate(3, new java.sql.Timestamp(venta.getfPago().getTime()));
			stmt.setDouble(2, venta.getTotal());
			//stmt.setDate(5,new java.sql.Timestamp(venta.getfRetiro().getTime()));
			stmt.setString(3, venta.getCliente().getUsername());	
			
			stmt.executeUpdate();
			ResultSet primaryKey = stmt.getGeneratedKeys();
			
			if(primaryKey!=null && primaryKey.next()) {
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
	
	public Venta getOne(int nroVenta) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException, PriceException, CategoryException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Venta venta = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from venta where nro_venta=?");
			stmt.setInt(1, nroVenta);
			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next())
			{
				venta = new Venta();
				venta.setfCancelacion(rs.getTimestamp("f_cancelacion"));
				venta.setfEmision(rs.getTimestamp("f_emision"));
				venta.setfPago(rs.getTimestamp("f_pago"));
				venta.setfRetiro(rs.getTimestamp("f_retiro"));
				venta.setNroVenta(rs.getInt("nro_venta"));
				venta.setImporte(rs.getDouble("importe"));
				
				venta.setCliente(clienteData.getOne(rs.getString("username")));
				venta.setLineas(lineaVentaData.getAllByVenta(venta.getNroVenta()));
				
			}
		}
		catch (SQLException e) {
			throw new SaleException("Error when getting one sale", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to get one sale", e, Level.ERROR);
		}
		finally
		{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
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
	
	public ArrayList<Venta> getAll() throws SaleException, ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleLineException, PriceException, CategoryException
	{
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select * from venta");
			if(rs!=null) {
				while(rs.next()) {
					Venta venta = new Venta();
					
					venta.setNroVenta(rs.getInt("nro_venta"));
					venta.setfEmision(rs.getTimestamp("f_emision"));
					venta.setfPago(rs.getTimestamp("f_pago"));
					venta.setfCancelacion(rs.getTimestamp("f_cancelacion"));
					venta.setfRetiro(rs.getTimestamp("f_retiro"));
					venta.setImporte(rs.getDouble("importe"));
					
					venta.setCliente(clienteData.getOne(rs.getString("username")));
					venta.setLineas(lineaVentaData.getAllByVenta(venta.getNroVenta()));	
					
					
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
		
	public ArrayList<Venta> getAllPendientesByCliente(String username) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException, PriceException, CategoryException
	{
		ArrayList<Venta> ventas=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from venta where username=? and f_pago is null");
			stmt.setString(1, username);
			rs= stmt.executeQuery();
			
			if(rs!=null)
			{
				ventas = new ArrayList<Venta>();
				
				while(rs.next())
				{
					Venta venta= new Venta();
					
					venta.setfCancelacion(rs.getTimestamp("f_cancelacion"));
					venta.setfEmision(rs.getTimestamp("f_emision"));
					venta.setfPago(rs.getTimestamp("f_pago"));
					venta.setfRetiro(rs.getTimestamp("f_retiro"));
					venta.setNroVenta(rs.getInt("nro_venta"));
					venta.setImporte(rs.getDouble("importe"));
					
					venta.setCliente(clienteData.getOne(username));
					venta.setLineas(lineaVentaData.getAllByVenta(venta.getNroVenta()));
					
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
		finally
		{
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
			
	public void update(Venta venta) throws SaleException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update venta set f_retiro=?, f_pago=?, f_cancelacion=? where nro_venta=?");
			stmt.setTimestamp(1, venta.getfRetiro()==null?null:new Timestamp(venta.getfRetiro().getTime()));
			stmt.setTimestamp(2, venta.getfPago()==null?null:new Timestamp(venta.getfPago().getTime()));
			stmt.setTimestamp(3, venta.getfCancelacion()==null?null:new Timestamp(venta.getfCancelacion().getTime()));
			stmt.setInt(4, venta.getNroVenta());
			
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
	
	public void delete(Venta venta) throws SaleException, SaleLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from venta where nroVenta=?");
			stmt.setInt(1,venta.getNroVenta());
			
			for (Linea linea : venta.getLineas()) {
				lineaVentaData.delete(venta.getNroVenta(), linea);
			}
			
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new SaleException("Error when deleting sale", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleException("Error when establishing connection to DB, to delete sale", e, Level.ERROR);
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
	
	public void deleteAllByCliente(String username) throws SaleException, SaleLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete v, lv from venta v "
					+ "inner join linea_venta lv on v.nro_venta=lv.nro_venta where v.username=?");
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

