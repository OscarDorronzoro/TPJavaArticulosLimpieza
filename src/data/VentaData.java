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
import util.ClientException;
import util.PriceException;
import util.ProviderException;
import util.SaleException;
import util.SaleLineException;

public class VentaData {
	
	static ClienteData clienteData = new ClienteData();
	static LineaVentaData lineaVentaData = new LineaVentaData();
	
	public ArrayList<Venta> getAll() throws SaleException, ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleLineException, PriceException
	{
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select * from venta");
			if(rs!=null) {
				while(rs.next()) {
					Venta venta=new Venta();
					
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleException("Error al recuperar ventas", e, Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new SaleException("Oops, ha ocurrido un error", e, Level.ERROR);
				}
		}
		
		return ventas;
	}
	

	public Venta getOne(int nroVenta) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException, PriceException
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleException("Error al recuperar venta",e,Level.ERROR);
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
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new SaleException("Oops, ha ocurrido un error",e,Level.ERROR);
			}
		}
	
		return venta;
	}
	
	public ArrayList<Venta> getAllPendientesByCliente(String username) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException, PriceException
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
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw  new SaleException("Ha ocurrido un error al recuperar las ventas", e, Level.ERROR);
		}
		finally
		{
			try {
				if(rs!=null){rs.close();}
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new SaleException("Oops, ha ocurrido un error", e, Level.ERROR);
			}
			
		}
		
		return ventas;
	}
	
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				transaccion.execute("rollback");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
			}
			throw new SaleException("Error al agregar venta", e, Level.ERROR);
		}
		finally {
			
			try {  
				if(stmt!=null) {stmt.close();}
			 	FactoryConnection.getInstancia().releaseConn();
			 	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			 	throw new SaleException("Oops, ha ocurrido un error", e, Level.ERROR);
			}
		 }		
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleException("Error al actualizar la venta",e);
		}
		finally{
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleException("Oops, ha ocurrido un error",e);
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleException("Error al eliminar la venta",e);
		}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleException("Oops, ha ocurrido un error",e);
			}	
		}		
	}
	
	public void deleteAllByCliente(String username) throws SaleException, SaleLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete venta,linea_venta from venta v "
					+ "inner join linea_venta lv on v.nro_venta=lv.nro_venta where v.username=?");
			stmt.setString(1, username);
			
			//for (Linea linea : venta.getLineas()) {
			//	lineaVentaData.delete(venta.getNroVenta(), linea);
			//}
			
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleException("Error al eliminar las ventas",e);
		}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleException("Oops, ha ocurrido un error",e);
			}	
		}		
	}
	
}

