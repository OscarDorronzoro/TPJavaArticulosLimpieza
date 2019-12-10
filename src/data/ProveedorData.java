package data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Proveedor;
import util.ProviderException;

public class ProveedorData {

	public void add(Proveedor prov) throws ProviderException {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into proveedor(cuit,direccion) values(?,?)");
			
			stmt.setString(1, prov.getCuit());
			stmt.setString(2, prov.getDireccion());
			
			stmt.executeUpdate();
				
		}
		catch (SQLException e)  {
			// TODO Auto-generated catch block
			throw new ProviderException("Error al agregar proveedor");
		}
		finally {
			try {
			if(stmt!=null)stmt.close();
            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ProviderException("Oops ha ocurrido un error");
			}
		}
		
	}
	
	public ArrayList<Proveedor> getAll() throws ProviderException{
		
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select * from proveedor");
			if(rs!=null) {
				while(rs.next()) {
					Proveedor prov=new Proveedor();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("direccion"));
										
					proveedores.add(prov);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ProviderException("Error al buscar proveedores");
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ProviderException("Oops ha ocurrido un error");
				}
		}
		
		return proveedores;
	}
	
	public ArrayList<Proveedor> getAllByArticulo(int codigoArticulo) throws ProviderException{
		
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from proveedor p inner join articulo_proveedor ap on p.cuit=ap.cuit "
					+ "where ap.cod_articulo=?");
			stmt.setInt(1, codigoArticulo);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Proveedor prov=new Proveedor();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("direccion"));
										
					proveedores.add(prov);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ProviderException("Error al buscar proveedores");
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ProviderException("Oops ha ocurrido un error");
				}
		}
		
		return proveedores;
	}
	
	public Proveedor getOne(String cuit) throws ProviderException {
		
		Proveedor prov=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from proveedor p where p.cuit=?"); 
			stmt.setString(1, cuit);
			rs=stmt.executeQuery();
			if(rs!=null&&rs.next()) {
					prov=new Proveedor();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("direccion"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ProviderException("Error al buscar proveedor");
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ProviderException("Oops ha ocurrido un error");
				}
		}
		
		return prov;
	}

	public void delete(String cuit) throws ProviderException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from proveedor p where p.cuit=?");
			stmt.setString(1, cuit);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ProviderException("Error al eliminar proveedor");
		}
		finally {
				try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ProviderException("Oops ha ocurrido un error");
				}
		}
	}
	
	public void update(Proveedor proveedor) throws ProviderException{
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update proveedor p set direccion=? where p.cuit=?");
			stmt.setString(1, proveedor.getDireccion());
									
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ProviderException("Error al actualizar proveedor");
		}
		finally {
				try {
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ProviderException("Oops ha ocurrido un error");
				}
		}
	}
}
