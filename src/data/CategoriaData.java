package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Categoria;

import util.CategoryException;
import util.DBException;


public class CategoriaData {

	public void add(Categoria c) throws CategoryException {
		PreparedStatement stmt = null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into categories(nombre, descripcion) values(?,?)"
			);
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getDescripcion());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new CategoryException("Error when adding new category", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CategoryException("Error when establishing connection to DB, to add new category", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch (SQLException e) {
				throw new CategoryException("Error when finishing adding new category", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CategoryException("Error when closing connection to DB, after adding new category", e, Level.ERROR);
			}
		}
		
	}
	
	public Categoria getOne(String nombre) throws CategoryException {
		
		Categoria c = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from categories where name=?"
			);
			stmt.setString(1, nombre);
			rs=stmt.executeQuery();
			
			if (rs != null && rs.next()) {
					c = new Categoria();
					
					c.setNombre(rs.getString("name"));
					c.setDescripcion(rs.getString("description"));
			}
		}
		catch (SQLException e) {
			throw new CategoryException("Error when getting one category", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CategoryException("Error when establishing connection to DB, to get one category", e, Level.ERROR);
		}
		finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if(stmt != null) {
						stmt.close();
					}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new CategoryException("Error when finishing getting one category", e, Level.ERROR);
				}
				catch (DBException e) {
					throw new CategoryException("Error when closing connection to DB, after getting one category", e, Level.ERROR);
				}
		}
		
		return c;
	}
	
	public ArrayList<Categoria> getAll() throws CategoryException{
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from categories");
			
			if (rs != null) {
				while (rs.next()) {
					Categoria c = new Categoria();
					
					c.setNombre(rs.getString("name"));
					c.setDescripcion(rs.getString("description"));
					
					categorias.add(c);					
				}
			}
		}
		catch (SQLException e) {
			throw new CategoryException("Error when getting all categories", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CategoryException("Error when establishing connection to DB, to get all categories", e, Level.ERROR);
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
				throw new CategoryException("Error when finishing getting all categories", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CategoryException("Error when closing connection to DB, after getting all categories", e, Level.ERROR);
			}
		}
		
		return categorias;
	}
		
	public void update(Categoria categoria) throws CategoryException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update categories set description=? where name=?"
			);
			stmt.setString(1, categoria.getDescripcion());
			stmt.setString(2, categoria.getNombre());
			
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new CategoryException("Error when updating category", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CategoryException("Error when establishing connection to DB, to update category", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CategoryException("Error when finishing updating category", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CategoryException("Error when closing connection to DB, after updating category", e, Level.ERROR);
			}	
		}	
	}
	
	public void delete(String nombre) throws CategoryException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from categories where name=?"
			);
			stmt.setString(1, nombre);
			
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new CategoryException("Error when deleting category", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new CategoryException("Error when establishing connection to DB, to delete category", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			}
			catch (SQLException e) {
				throw new CategoryException("Error when finishing deleting category", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new CategoryException("Error when closing connection to DB, after deleting category", e, Level.ERROR);
			}
		}
	}
}
