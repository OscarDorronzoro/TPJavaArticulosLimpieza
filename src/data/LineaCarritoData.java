package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Linea;
import util.ProviderException;

public class LineaCarritoData extends LineaData {
	
	public void add(Linea linea, String nombreCarrito, String username) {
		
		PreparedStatement stmt=null;
			

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
	
	public void update(Linea linea, String nombreCarrito, String username) {
		
		PreparedStatement stmt=null;
			

		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("update linea_carrito "
					+ "set cantidad=? where nombre_carrito=? and username=?");

			stmt.setInt(1, linea.getCantidad());;
			stmt.setString(2, nombreCarrito);
			stmt.setString(3, username);

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
	
	public ArrayList<Linea> getAllByCarrito(String nombreCarrito,String username) throws ProviderException{
		
		ArrayList<Linea> lineas = new ArrayList<Linea>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from linea_carrito where nombre_carrito=? and username=?");
			
			stmt.setString(1,nombreCarrito);
			stmt.setString(2,username);
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					Linea linea=new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("cod_articulo")));
					linea.setCantidad(rs.getInt("cantidad"));
					
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
	
	public Linea getOne(String nombreCarrito, String username, int codArticulo) throws ProviderException {
		
		Linea linea=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from linea_Carrito where nombre_carrito=? and username=? and cod_articulo=?");
			
			stmt.setString(1, nombreCarrito);
			stmt.setString(2, username);
			stmt.setInt(3, codArticulo);			
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
	
	public void delete(String nombreCarrito, String username, int codArticulo) {
		
		PreparedStatement stmt=null;
		
			
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from linea_carrito where nombre_carrito=? and username=? and cod_articulo=?");
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
