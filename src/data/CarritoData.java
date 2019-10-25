package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Linea;
import entities.Carrito;
import util.CartException;
import util.DoniaMaryException;

public class CarritoData {
	
	private LineaCarritoData lineaData=new LineaCarritoData();
	
	public void add(Carrito carrito, String username) throws CartException {
		
		PreparedStatement stmt=null;
			

		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into carrito "
					+ "(nombre,username,descripcion) values(?,?,?)");
			
			stmt.setString(1, carrito.getNombre());
			stmt.setString(2, username);
			stmt.setString(3, carrito.getDescripcion()); 
			stmt.executeUpdate();
			
			for (Linea linea : carrito.getLineas()) {
				lineaData.add(linea, carrito.getNombre(),username);
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CartException("Error al agregar un carrito", e);
		}
		finally {
			try {
			if(stmt!=null)stmt.close();
            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new CartException("Oops, ha ocurrido un error", e);
			}
		}
		
	}
	
	public Carrito getOne(String nombre, String username) throws DoniaMaryException {
		
		Carrito carrito=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from carrito where nombre=? and username=?");
			
			stmt.setString(1, nombre);
			stmt.setString(2, username);			
			rs=stmt.executeQuery();
			
			if(rs!=null&&rs.next()) {
				carrito=new Carrito();
					
				carrito.setNombre(rs.getString("nombre"));
				carrito.setDescripcion(rs.getString("descripcion"));
				carrito.setLineas(lineaData.getAllByCarrito(carrito.getNombre(),username));

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CartException("Error al buscar el carrito",e);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new CartException("Oops ha ocurrido un error",e);
				}
		}
		
		return carrito;
	}
	
	public void delete(Carrito carrito, String username) throws CartException {
		
		PreparedStatement stmt=null;
		
			
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from carrito where nombre=? and username=?");
			stmt.setString(1,carrito.getNombre());
			stmt.setString(2,username);
			
			for (Linea linea : carrito.getLineas()) {
				lineaData.delete(carrito.getNombre(), username, linea.getArticulo().getCodArticulo());
			}
			
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CartException("Error al eliminar el carrito",e);
		}
		finally {
			try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new CartException("Oops ha ocurrido un error",e);
				}	
		}
		
		
	}

}
