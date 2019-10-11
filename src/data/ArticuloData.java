package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entities.Articulo;
import entities.Precio;

public class ArticuloData {

	PrecioData precioData = new PrecioData();
	
	public void add(Articulo art) {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into articulo(descripcion,cant_a_pedir,punto_pedido,"
					+ "stock,url_imagen) values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, art.getDescripcion());
			stmt.setInt(2, art.getCantAPedir());
			stmt.setInt(3, art.getPuntoPedido());
			stmt.setInt(4, art.getStock());
			stmt.setString(5, art.getUrlImagen());
			stmt.executeUpdate();
			
			ResultSet primaryKey = stmt.getGeneratedKeys();
		
			precioData.add(art.getPrecio(),art.getCodArticulo());

			
			if(primaryKey!=null && primaryKey.next()) {
				art.setCodArticulo(primaryKey.getInt(1));
			}
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
					
					art.setCodArticulo(rs.getInt("cod_articulo"));
					art.setDescripcion(rs.getString("descripcion"));
					art.setCantAPedir(rs.getInt("cant_a_pedir"));
					art.setPuntoPedido(rs.getInt("punto_pedido"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("url_imagen"));
					
					art.setPrecio(precioData.getPrecioActual(art.getCodArticulo()));
					
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
	
	public Articulo getOne(int codArticulo) {
		
		Articulo art=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from articulo art inner join precio p on art.cod_articulo=p.cod_articulo"
					+ " where art.cod_articulo=?");
			stmt.setInt(1, codArticulo);
			rs=stmt.executeQuery();
			if(rs!=null&&rs.next()) {
					art=new Articulo();
					
					art.setCodArticulo(rs.getInt("cod_articulo"));
					art.setDescripcion(rs.getString("descripcion"));
					art.setCantAPedir(rs.getInt("cant_a_pedir"));
					art.setPuntoPedido(rs.getInt("punto_pedido"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("url_imagen"));
					art.setPrecio(precioData.getPrecioActual(art.getCodArticulo()));
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
		
		return art;
	}
	
	public ArrayList<Articulo> getAllByDescripcion(String descripcion){
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		ResultSet rs=null;
		//Statement stmt=null;
		PreparedStatement stmt=null;
		
		try {
			//stmt = FactoryConnection.getInstancia().getConn().createStatement();
			//rs=stmt.executeQuery("select * from articulo where"
			//		+ " descripcion like '%"+descripcion+"%' ");
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from articulo where descripcion like '%?%'");
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					Articulo art=new Articulo();
					
					art.setCodArticulo(rs.getInt("cod_articulo"));
					art.setDescripcion(rs.getString("descripcion"));
					art.setCantAPedir(rs.getInt("cant_a_pedir"));
					art.setPuntoPedido(rs.getInt("punto_pedido"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("url_imagen"));
					art.setPrecio(precioData.getPrecioActual(art.getCodArticulo()));
					
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
	
	public void delete(int codArticulo) {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from articulo where art.cod_articulo=?");
			stmt.setInt(1, codArticulo);
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
	
	public void update(Articulo articulo) {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update from articulo set descripcion=?,cant_a_pedir=?,punto_pedido=?,"
					+ "stock=?,url_imagen=? where art.cod_articulo=?");
			stmt.setString(1, articulo.getDescripcion());
			stmt.setInt(2, articulo.getCantAPedir());
			stmt.setInt(3, articulo.getPuntoPedido());
			stmt.setInt(4, articulo.getStock());
			stmt.setString(5, articulo.getUrlImagen());
			stmt.setInt(6, articulo.getCodArticulo());
			
			stmt.executeUpdate();
			
			precioData.add(articulo.getPrecio(), articulo.getCodArticulo());
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
