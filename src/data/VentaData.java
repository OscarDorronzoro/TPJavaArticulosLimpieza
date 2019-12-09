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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.ArticleException;
import util.CartException;
import util.CartLineException;
import util.ClientException;
import util.ProviderException;
import util.SaleException;
import util.SaleLineException;

public class VentaData {
	
	ClienteData clienteData = new ClienteData();
	LineaVentaData lineaVentaData = new LineaVentaData();
	
	public ArrayList<Venta> getAll() throws SaleException, ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleLineException
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
					venta.setfEmision(rs.getDate("f_emision"));
					venta.setfPago(rs.getDate("f_pago"));
					venta.setfCancelacion(rs.getDate("f_cancelacion"));
					venta.setfRetiro(rs.getDate("f_retiro"));
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

	public Venta getOne(int nroVenta) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException
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
				venta.setfCancelacion(rs.getDate("f_cancelacion"));
				venta.setfEmision(rs.getDate("f_emision"));
				venta.setfPago(rs.getDate("f_pago"));
				venta.setfRetiro(rs.getDate("f_retiro"));
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
	
	public ArrayList<Venta> getAllPendientesByCliente(String username) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, SaleException, SaleLineException
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
					
					venta.setfCancelacion(rs.getDate("f_cancelacion"));
					venta.setfEmision(rs.getDate("f_emision"));
					venta.setfPago(rs.getDate("f_pago"));
					venta.setfRetiro(rs.getDate("f_retiro"));
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
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into venta "
					+ "(f_emision,f_cancelacion,f_pago,importe,f_retiro,username) values(?,?,?,?,?,?)");
			stmt.setTimestamp(1, (Timestamp)venta.getfEmision());
			stmt.setTimestamp(2, (Timestamp)venta.getfCancelacion());
			stmt.setTimestamp(3, (Timestamp)venta.getfPago());
			stmt.setDouble(4, venta.getTotal());
			stmt.setTimestamp(5, (Timestamp)venta.getfRetiro());
			stmt.setString(6, venta.getCliente().getUsername());

			for (Linea linea : venta.getLineas()) {
				lineaVentaData.add(linea, venta.getNroVenta());
			}		
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
	public void update(Venta venta) {
		
		throw new NotImplementedException();
	}
	
	
	public void delete(Venta venta) throws SaleException, SaleLineException {
		
		PreparedStatement stmt=null;
		
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
					throw new SaleException("Oops ha ocurrido un error",e);
				}	
		}
		
		
	}
	
	
	}

