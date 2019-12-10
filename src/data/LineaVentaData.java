package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Linea;
import entities.Proveedor;
import util.ArticleException;
import util.ProviderException;
import util.SaleLineException;

public class LineaVentaData extends LineaData {

	public void add(Linea linea, int nroVenta) throws SaleLineException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into linea_venta "
					+ "(nro_venta,cantidad,cod_articulo,cuit_proveedor) values(?,?,?,?)");
			
			stmt.setInt(1, nroVenta);
			stmt.setInt(2,linea.getCantidad());
			stmt.setInt(3, linea.getArticulo().getCodArticulo());
			
			ArrayList<Proveedor> proveedores = linea.getArticulo().getProveedores();
			stmt.setString(4, proveedores.get((int)(Math.random()*proveedores.size())).getCuit()); //proveedor elejido aleatoriamente
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleLineException("Error al agregar linea de venta",e,Level.ERROR);
		}
		finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleLineException("Oops, ha ocurrido un error",e,Level.ERROR);
			}
		}
		
	}
	
	public ArrayList<Linea> getAllByVenta(int nroVenta) throws ProviderException, ArticleException, SaleLineException{
		
		ArrayList<Linea> lineas = new ArrayList<Linea>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from linea_venta where nro_venta=?");
			stmt.setInt(1,nroVenta);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Linea linea=new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("cod_articulo")));
					linea.setCantidad(rs.getInt("cantidad"));
					linea.setProveedor(this.getProveedorData().getOne(rs.getString("cuit_proveedor")));
					
					lineas.add(linea);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleLineException("Error al recuperar lineas de venta",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new SaleLineException("Oops, ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return lineas;
	}
	
	public void delete(int nroVenta,Linea linea) throws SaleLineException {
		
		PreparedStatement stmt=null;
		
			
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from linea_venta where nro_venta=? and cod_articulo=?"
					+ " and cuit_proveedor=?");
			stmt.setInt(1,nroVenta);
			stmt.setInt(2,linea.getArticulo().getCodArticulo());
			stmt.setString(3,linea.getProveedor().getCuit());
			
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SaleLineException("Error al eliminar linea de venta",e,Level.ERROR);
		}
		finally {
			try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new  SaleLineException("Oops, ha ocurrido un error",e,Level.ERROR);
				}	
		}
		
		
	}
}
