package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Article;
import util.ArticleException;
import util.CategoryException;
import util.DBException;
import util.PriceException;
import util.ProviderException;

public class ArticuloData {

	PrecioData precioData = new PrecioData();
	ProveedorData proveedorData= new ProveedorData();
	CategoriaData categoriaData= new CategoriaData();
	
	public void add(Article art) throws ArticleException {
		PreparedStatement stmt = null;
		Statement transaccion = null;
		
		try {
			transaccion = FactoryConnection.getInstancia().getConn().createStatement();
			transaccion.execute("start transaction;");
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into articles("
					+ "description, amount_to_order, order_limit, stock, image_url, category_name, is_deleted) "
					+ "values(?,?,?,?,?,?,0)"
				,PreparedStatement.RETURN_GENERATED_KEYS
			);
			
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
	
	public Article getOne(int codArticulo) throws ArticleException {
		
		Article art = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from articles art inner join prices p on art.code=p.article_code"
					+ " where art.code=? and is_deleted=0");
			stmt.setInt(1, codArticulo);
			
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
					art = new Article();
					
					art.setCodArticulo(rs.getInt("code"));
					art.setDescripcion(rs.getString("description"));
					art.setCantAPedir(rs.getInt("amount_to_order"));
					art.setPuntoPedido(rs.getInt("order_limit"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("image_url"));
					
					art.setPrecio(precioData.getCurrentPrice(art.getCodArticulo()));
					art.setProveedores(proveedorData.getAllByArticle(art.getCodArticulo()));
					art.setCategoria(categoriaData.getOne(rs.getString("category_name")));
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
	
	public ArrayList<Article> getAll() throws ArticleException {
		
		ArrayList<Article> articulos = new ArrayList<Article>();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from articles where is_deleted = 0");
			if (rs != null) {
				while (rs.next()) {
					Article art = new Article();
					
					art.setCodArticulo(rs.getInt("code"));
					art.setDescripcion(rs.getString("description"));
					art.setCantAPedir(rs.getInt("amount_to_order"));
					art.setPuntoPedido(rs.getInt("order_limit"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("image_url"));
					
					art.setPrecio(precioData.getCurrentPrice(art.getCodArticulo()));
					art.setProveedores(proveedorData.getAllByArticle(art.getCodArticulo()));
					art.setCategoria(categoriaData.getOne(rs.getString("category_name")));
					
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
	
	public ArrayList<Article> getAllByDescription(String descripcion) throws ArticleException {
		ArrayList<Article> articulos = new ArrayList<Article>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from articles where description like ? and is_deleted=0"
			);
			stmt.setString(1,"%"+descripcion+"%");
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Article art = new Article();
					
					art.setCodArticulo(rs.getInt("code"));
					art.setDescripcion(rs.getString("description"));
					art.setCantAPedir(rs.getInt("amout_to_order"));
					art.setPuntoPedido(rs.getInt("order_limit"));
					art.setStock(rs.getInt("stock"));
					art.setUrlImagen(rs.getString("image_url"));
					
					art.setPrecio(precioData.getCurrentPrice(art.getCodArticulo()));
					art.setProveedores(proveedorData.getAllByArticle(art.getCodArticulo()));
					art.setCategoria(categoriaData.getOne(rs.getString("category_name")));
					
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
	
	public void update(Article articulo) throws ArticleException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update articles set "
				+ "description=?, amout_to_order=?, order_limit=?,"
				+ "stock=?,image_url=?, category_name=? "
				+ "where code=?"
			);
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
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"update articles set is_deleted=1 where code=?");
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


