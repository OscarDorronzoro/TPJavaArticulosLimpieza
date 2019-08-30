package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Linea;

public class LineaCarritoData extends LineaData {
	
	public void add(Linea linea, String nombreCarrito) {
		
		PreparedStatement stmt=null;
			

		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into linea_carrito "
					+ "(nombre_carrito,cod_articulo,cuit_proveedor) values(?,?,?)");
			
			stmt.setString(1, nombreCarrito);
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
	
	public ArrayList<Linea> getAllByCarrito(String nombreCarrito){
		
		ArrayList<Linea> lineas = new ArrayList<Linea>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from linea_carrito where nombre_carrito=?");
			
			stmt.setString(1,nombreCarrito);
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
	
	public Linea getOne(String nombreCarrito, int codArticulo) {
		
		Linea linea=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from linea_Carrito where nombre_carrito=? and cod_articulo=?");
			
			stmt.setString(1, nombreCarrito);
			stmt.setInt(2, codArticulo);			
			rs=stmt.executeQuery();
			
			if(rs!=null&&rs.next()) {
					linea=new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("cod_articulo")));
					linea.setCantidad(rs.getInt("cantidad"));
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
		
		return linea;
	}
	
	public void delete(String nombreCarrito, int codArticulo) {
		
		PreparedStatement stmt=null;
		
			
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from linea_carrito where nombre_carrito=? and cod_articulo=?");
			stmt.setString(1,nombreCarrito);
			stmt.setInt(2,codArticulo);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}	
		}
		
		
	}
}
