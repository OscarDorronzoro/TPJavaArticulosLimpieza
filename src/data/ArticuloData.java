package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Articulo;
import util.ArticleException;
import util.CategoryException;
import util.DBException;
import util.PriceException;
import util.ProviderException;

public class ArticuloData {

	PrecioData precioData = new PrecioData();
	ProveedorData proveedorData= new ProveedorData();
	CategoriaData categoriaData= new CategoriaData();
	
	public void add(Articulo art) throws ArticleException {
		PreparedStatement stmt = null;
		Statement transaccion = null;
		
		try {
			transaccion = FactoryConnection.getInstancia().getConn().createStatement();
			transaccion.execute("start transaction;");
			
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into articulo(descripcion,cant_a_pedir,punto_pedido,"
					+ "stock,url_imagen,nombre_categoria,is_deleted) values(?,?,?,?,?,?,0)",PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, art.getDescripcion());
			stmt.setInt(2, art.getCantAPedir());
			stmt.setInt(3, art.getPuntoPedido());
			stmt.setInt(4, art.getStock());
			stmt.setString(5, art.getUrlImagen());
			stmt.setString(6, art.getCategoria().getNombre());
			
			stmt.executeUpdate();
			
			ResultSet primaryKey = stmt.getGeneratedKeys();
			if (primaryKey != null && primaryKey.next()) {
				art.setCodArticulo(primaryKey.getInt(1));
			}	
			
			precioData.add(art.getPrecio(),art.getCodArticulo());
			
			transaccion.execute("commit;");
		}
		catch (SQLException doRollback) {
			try {
				transaccion.execute("rollback");
				throw new ArticleException("Error when adding new article", doRollback, Level.ERROR);
			}
			catch (SQLException e) {
				throw new ArticleException("Error when performing rollback from adding new article", e, Level.ERROR);
			}
		}
		catch (DBException e) {
			throw new ArticleException("Error when establishing connection to DB, to add new article", e, Level.ERROR);
		}
		catch (PriceException e) {
			throw new ArticleException("Error when adding new price, to add new article", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
	            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ArticleException("Error when finishing adding new article", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ArticleException("Error when closing connection to DB, after adding new article", e, Level.ERROR);
			}
		}
		
	}
	
	public Articulo getOne(int codArticulo) throws ArticleException {
		
		Articulo art=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from articulo art inner join precio p on art.cod_articulo=p.cod_articulo"
					+ " where art.cod_articulo=? and is_deleted=0");
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
					art.setCategoria(categoriaData.getOne(rs.getString("nombre_categoria")));
			}
		}
		catch (SQLException e) {
			throw new ArticleException("Error when getting one article", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ArticleException("Error when establishing connection to DB, to get one article", e, Level.ERROR);
		}
		catch (ProviderException e) {
			throw new ArticleException("Error when getting all providers by article, to get one article", e, Level.ERROR);
		}
		catch (PriceException e) {
			throw new ArticleException("Error when getting current price, to get one article", e, Level.ERROR);
		}
		catch (CategoryException e) {
			throw new ArticleException("Error when getting one category, to get one article", e, Level.ERROR);
		}
		finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Error when finishing getting one article", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new ArticleException("Error when closing connection to DB, after getting one article", e, Level.ERROR);
				}
		}
		
		return art;
	}
	
	public ArrayList<Articulo> getAll() throws ArticleException {
		
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from articulo where is_deleted = 0");
			if (rs != null) {
				while (rs.next()) {
					Articulo art = new Articulo();
					
					art.setCodArticulo(rs.getInt("cod_articulo"));
					art.setDescripcion(rs.getString("descripcion"));
					art.setCantAPedir(rs.getInt("cant_a_pedir"));
					art.setPuntoPedido(rs.getInt("punto_pedido"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("url_imagen"));
					
					art.setPrecio(precioData.getPrecioActual(art.getCodArticulo()));
					art.setProveedores(proveedorData.getAllByArticulo(art.getCodArticulo()));
					art.setCategoria(categoriaData.getOne(rs.getString("nombre_categoria")));
					
					articulos.add(art);					
				}
			}
		}
		catch (SQLException e) {
			throw new ArticleException("Error when getting all articles", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ArticleException("Error when establishing connection to DB, to get all articles", e, Level.ERROR);
		}
		catch (ProviderException e) {
			throw new ArticleException("Error when getting all providers by article, to get all articles", e, Level.ERROR);
		}
		catch (PriceException e) {
			throw new ArticleException("Error when getting current price, to get all articles", e, Level.ERROR);
		}
		catch (CategoryException e) {
			throw new ArticleException("Error when getting one category, to get all articles", e, Level.ERROR);
		}
		finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Error when finishing getting all articles", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new ArticleException("Error when closing connection to DB, after getting all articles", e, Level.ERROR);
				}
		}
		return articulos;
	}
	
	public ArrayList<Articulo> getAllByDescripcion(String descripcion) throws ArticleException {
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from articulo where descripcion like ? and is_deleted=0");
			stmt.setString(1,"%"+descripcion+"%");
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Articulo art = new Articulo();
					
					art.setCodArticulo(rs.getInt("cod_articulo"));
					art.setDescripcion(rs.getString("descripcion"));
					art.setCantAPedir(rs.getInt("cant_a_pedir"));
					art.setPuntoPedido(rs.getInt("punto_pedido"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("url_imagen"));
					
					art.setPrecio(precioData.getPrecioActual(art.getCodArticulo()));
					art.setProveedores(proveedorData.getAllByArticulo(art.getCodArticulo()));
					art.setCategoria(categoriaData.getOne(rs.getString("nombre_categoria")));
					
					articulos.add(art);					
				}
			}
		}
		catch (SQLException e) {
			throw new ArticleException("Error when getting all articles by description", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ArticleException("Error when establishing connection to DB, to get all articles by description", e, Level.ERROR);
		}
		catch (ProviderException e) {
			throw new ArticleException("Error when getting all providers by article, to get all articles by description", e, Level.ERROR);
		}
		catch (PriceException e) {
			throw new ArticleException("Error when getting current price, to get all articles by description", e, Level.ERROR);
		}
		catch (CategoryException e) {
			throw new ArticleException("Error when getting one category, to get all articles by description", e, Level.ERROR);
		}
		finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Error when finishing getting all articles by description", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new ArticleException("Error when closing connection to DB, after getting all articles by description", e, Level.ERROR);
				}
		}
		
		return articulos;
	}
	
	public void update(Articulo articulo) throws ArticleException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update articulo set descripcion=?,cant_a_pedir=?,punto_pedido=?,"
					+ "stock=?,url_imagen=?, nombre_categoria=? where cod_articulo=?");
			stmt.setString(1, articulo.getDescripcion());
			stmt.setInt(2, articulo.getCantAPedir());
			stmt.setInt(3, articulo.getPuntoPedido());
			stmt.setInt(4, articulo.getStock());
			stmt.setString(5, articulo.getUrlImagen());
			stmt.setString(6, articulo.getCategoria().getNombre());
			stmt.setInt(7, articulo.getCodArticulo());
			
			stmt.executeUpdate();
			
			precioData.add(articulo.getPrecio(), articulo.getCodArticulo());
		}
		catch (SQLException e) {
			throw new ArticleException("Error when updating article", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ArticleException("Error when establishing connection to DB, to update article", e, Level.ERROR);
		}
		catch (PriceException e) {
			throw new ArticleException("Error when adding new price, to update article", e, Level.ERROR);
		}
		finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Error when finishing updating article", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new ArticleException("Error when closing connection to DB, after updating article", e, Level.ERROR);
				}
		}
	}
	
	public void delete(int codArticulo) throws ArticleException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update articulo set is_deleted=1 where cod_articulo=?");
			stmt.setInt(1, codArticulo);
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new ArticleException("Error when deleting article", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ArticleException("Error when establishing connection to DB, to delete article", e, Level.ERROR);
		}
		finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ArticleException("Error when finishing deleting article", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new ArticleException("Error when closing connection to DB, after deleting article", e, Level.ERROR);
				}	
		}
	}
}


