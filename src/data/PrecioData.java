package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Precio;

public class PrecioData {

	public void add(Precio precio,int codArticulo) {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into precio(cod_articulo,fecha_desde,precio) values(?,?,?)");
			stmt.setInt(1, codArticulo);
			stmt.setDate(2, (java.sql.Date)precio.getFechaDesde());
			stmt.setDouble(3, precio.getValor());
			
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
	
	public Precio getPrecioActual(int codArticulo){
		
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
					
					precio.setFechaDesde(rs.getDate("fecha_desde"));
					precio.setValor(rs.getDouble("precio"));					
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
		
		return precio;
	}
	
	public ArrayList<Precio> getAll(int codArticulo){
		
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
					
					precio.setFechaDesde(rs.getDate("fecha_desde"));
					precio.setValor(rs.getDouble("precio"));
					
					precios.add(precio);					
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
		
		return precios;
	}
	
	public void update(Precio precio, int codArticulo) {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update from precio set fecha_desde=?,precio=?"
					+ " where precio.cod_articulo=?");
			stmt.setDate(1, (java.sql.Date)precio.getFechaDesde());
			stmt.setDouble(2, precio.getValor());
			stmt.setInt(6, codArticulo);
			
			stmt.executeUpdate();
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

