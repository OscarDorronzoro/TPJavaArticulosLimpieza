package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Articulo;
import util.ArticleException;
import util.ProviderException;

public class ArticuloData {

	PrecioData precioData = new PrecioData();
	ProveedorData proveedorData=new ProveedorData();
	
	public void add(Articulo art) throws ArticleException {
		PreparedStatement stmt=null;
		Statement transaccion = null;
		
		try {
			transaccion = FactoryConnection.getInstancia().getConn().createStatement();
			transaccion.execute("start transaction;");
			
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

			if(primaryKey!=null && primaryKey.next()) {
				art.setCodArticulo(primaryKey.getInt(1));
				precioData.add(art.getPrecio(),art.getCodArticulo());
			}	
			
			transaccion.execute("commit;");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				transaccion.execute("rollback;");
			} catch (SQLException e1) {
				
			}
			throw new ArticleException("Error al agregar artículo", e, Level.ERROR) ;
		}
		finally {
			try {
				if(stmt!=null) stmt.close();
	            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
        	throw new ArticleException("Oops, ha ocurrido un error", e, Level.ERROR);
			}
		}
		
	}
	
	public ArrayList<Articulo> getAll() throws ProviderException, ArticleException{
		
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
					
					art.setProveedores(proveedorData.getAllByArticulo(art.getCodArticulo()));
					
					articulos.add(art);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ArticleException("Error al recuperar articulos", e, Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Oops, ha ocurrido un error", e, Level.ERROR);
				}
		}
		
		return articulos;
	}
	
	public Articulo getOne(int codArticulo) throws ProviderException, ArticleException {
		
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
					
					art.setProveedores(proveedorData.getAllByArticulo(art.getCodArticulo()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ArticleException("Error al recuperar artículo", e, Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Oops, ha ocurrido un error", e, Level.ERROR);
				}
		}
		
		return art;
	}
	
	public ArrayList<Articulo> getAllByDescripcion(String descripcion) throws ProviderException, ArticleException{
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from articulo where descripcion like ?");
			stmt.setString(1,"%"+descripcion+"%");
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
					
					art.setProveedores(proveedorData.getAllByArticulo(art.getCodArticulo()));
					
					articulos.add(art);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ArticleException("Error al recuperar articulos", e, Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Oops, ha ocurrido un error", e, Level.ERROR);
				}
		}
		
		return articulos;
	}
	
	public void delete(int codArticulo) throws ArticleException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from articulo where art.cod_articulo=?");
			stmt.setInt(1, codArticulo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ArticleException("Error al eliminar artículo", e, Level.ERROR);
		}
		finally {
				try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Oops, ha ocurrido un error", e, Level.ERROR);
				}
		}
	}
	
	public void update(Articulo articulo) throws ArticleException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update articulo set descripcion=?,cant_a_pedir=?,punto_pedido=?,"
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
			throw new ArticleException("Error al actualizar artículo", e, Level.ERROR);
		}
		finally {
				try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Oops, ha ocurrido un error", e, Level.ERROR);
				}
		}
	}
}
