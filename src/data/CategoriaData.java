package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Categoria;
import util.CategoryException;


public class CategoriaData {

	public void add(Categoria c) throws CategoryException{
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into categoria(nombre,descripcion) values(?,?)"
					);
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getDescripcion());
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CategoryException("Error al agregar la categoria",e,Level.ERROR);
		} 
		finally {
			try {
			if(stmt!=null)stmt.close();
            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CategoryException("Oops, ha ocurrido un error",e,Level.ERROR);
			}
		}
		
	}
	
	public ArrayList<Categoria> getAll() throws CategoryException{
		
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select * from categoria");
			if(rs!=null) {
				while(rs.next()) {
					Categoria c=new Categoria();
					
					c.setNombre(rs.getString("nombre"));
					c.setDescripcion(rs.getString("descripcion"));
					
					categorias.add(c);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CategoryException("Error al recuperar categorias",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new CategoryException("Oops! Ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return categorias;
	}
	
	public Categoria getOne(String nombre) throws CategoryException {
		
		Categoria c=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from categoria where nombre=?");
			stmt.setString(1, nombre);
			rs=stmt.executeQuery();
			
			if(rs!=null&&rs.next()) {
					c=new Categoria();
					
					c.setNombre(rs.getString("nombre"));
					c.setDescripcion(rs.getString("descripcion"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CategoryException("Error al recuperar categoria",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new CategoryException("Oops! Ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return c;
	}
	
	public void update(Categoria categoria) throws CategoryException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update categoria set "
					+ "descripcion=? where nombre=?");
			stmt.setString(1, categoria.getDescripcion());
			stmt.setString(2, categoria.getNombre());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CategoryException("Error al actualizar la categoria", e, Level.ERROR);
		}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CategoryException("Oops, ha ocurrido un error", e, Level.ERROR);
			}	
		}	
	}
	
	public void delete(String nombre) throws CategoryException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement("delete from categoria where nombre=?");
			stmt.setString(1, nombre);
			
			stmt.execute();
			
		}catch(SQLException e) {
			throw new CategoryException("Error al eliminar la categoria",e,Level.ERROR);
		}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new CategoryException("Oops, ha ocurrido un error",e,Level.ERROR);
			}
		}
	}
}
