package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Linea;
import util.ProviderException;

public class LineaVentaData extends LineaData {

	public void add(Linea linea, int nroVenta) {
		
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into linea_venta "
					+ "(nro_venta,cod_articulo,cuit_proveedor) values(?,?,?)");
			
			stmt.setInt(1, nroVenta);
			stmt.setInt(2, linea.getArticulo().getCodArticulo());;
			stmt.setString(3, linea.getArticulo().getProveedores().get(0).getCuit()); //como elegir un proveedor??
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
			if(stmt!=null)stmt.close();
            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
        	e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Linea> getAllByVenta(int nroVenta) throws ProviderException{
		
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
					linea.setCantidad(rs.getInt("cant_a_pedir"));
					
					lineas.add(linea);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return lineas;
	}
}
