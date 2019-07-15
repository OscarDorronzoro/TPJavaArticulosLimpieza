package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entities.Articulo;

public class ArticuloData {

	public void add(Articulo art) {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into articulo(cod_articulo,descripcion,cant_a_pedir,punto_pedido,"
					+ "stock,url_imagen) values(?,?,?,?,?,?)");
			stmt.setString(1, art.getCodArticulo());
			stmt.setString(2, art.getDescripcion());
			stmt.setInt(3, art.getCantAPedir());
			stmt.setInt(4, art.getPuntoPedido());
			stmt.setInt(5, art.getStock());
			stmt.setString(6, art.getUrlImagen());
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
	
	public ArrayList<Articulo> getAll(){
		
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select * from articulo");
			if(rs!=null) {
				while(rs.next()) {
					Articulo art=new Articulo();
					
					art.setCodArticulo(rs.getString("cod_articulo"));
					art.setDescripcion(rs.getString("descripcion"));
					art.setCantAPedir(rs.getInt("cant_a_pedir"));
					art.setPuntoPedido(rs.getInt("punto_pedido"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("url_imagen"));
					
					articulos.add(art);					
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
		
		return articulos;
	}
}
