package data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Proveedor;
import util.DBException;
import util.ProviderException;

public class ProveedorData {

	public void add(Proveedor prov) throws ProviderException {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into proveedor(cuit,direccion,telefono,mail,razon_social) values(?,?,?,?,?)");
			
			stmt.setString(1, prov.getCuit());
			stmt.setString(2, prov.getDireccion());
			stmt.setString(3, prov.getTelefono());
			stmt.setString(4, prov.getMail());
			stmt.setString(5, prov.getRazonSocial());
			
			stmt.executeUpdate();
				
		}
		catch (SQLException e) {
			throw new ProviderException("Error when adding new provider", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to add new provider", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ProviderException("Error when finishing adding new provider", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ProviderException("Error when closing connection to DB, after adding new provider", e, Level.ERROR);
			}
		}
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
					prov.setMail(rs.getString("mail"));
					prov.setRazonSocial(rs.getString("razon_social"));
					prov.setTelefono(rs.getString("telefono"));
			}
		}
		catch (SQLException e) {
			throw new ProviderException("Error when getting one provider", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to get one provider", e, Level.ERROR);
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
				throw new ProviderException("Error when finishing getting one provider", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ProviderException("Error when closing connection to DB, after getting one provider", e, Level.ERROR);
			}
		}
		
		return prov;
	}
	
	public ArrayList<Proveedor> getAll() throws ProviderException{
		
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from proveedor");
			if (rs != null) {
				while (rs.next()) {
					Proveedor prov=new Proveedor();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("direccion"));
					prov.setMail(rs.getString("mail"));
					prov.setRazonSocial(rs.getString("razon_social"));
					prov.setTelefono(rs.getString("telefono"));
										
					proveedores.add(prov);					
				}
			}
		}
		catch (SQLException e) {
			throw new ProviderException("Error when getting all providers", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to get all providers", e, Level.ERROR);
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
				throw new ProviderException("Error when finishing getting all providers", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ProviderException("Error when closing connection to DB, after getting all providers", e, Level.ERROR);
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
					prov.setMail(rs.getString("mail"));
					prov.setRazonSocial(rs.getString("razon_social"));
					prov.setTelefono(rs.getString("telefono"));
										
					proveedores.add(prov);					
				}
			}
		}
		catch (SQLException e) {
			throw new ProviderException("Error when getting all providers by article", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to get all providers by article", e, Level.ERROR);
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
				throw new ProviderException("Error when finishing getting all providers by article", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ProviderException("Error when closing connection to DB, after getting all providers by article", e, Level.ERROR);
			}
		}
		
		return proveedores;
	}

	public void update(Proveedor proveedor) throws ProviderException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update proveedor p set direccion=?, "
					+ "telefono=?, mail=?, razon_social=? where p.cuit=?");
			stmt.setString(1, proveedor.getDireccion());
			stmt.setString(2, proveedor.getTelefono());
			stmt.setString(3, proveedor.getMail());
			stmt.setString(4, proveedor.getRazonSocial());
			stmt.setString(5, proveedor.getCuit());
			
									
			stmt.executeUpdate();
			
			
		}
		catch (SQLException e) {
			throw new ProviderException("Error when updating provider", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to update provider", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ProviderException("Error when finishing updating provider", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ProviderException("Error when closing connection to DB, after updating provider", e, Level.ERROR);
			}
		}
	}
	
	public void delete(String cuit) throws ProviderException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from proveedor p where p.cuit=?");
			stmt.setString(1, cuit);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new ProviderException("Error when deleting provider", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new ProviderException("Error when establishing connection to DB, to delete provider", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ProviderException("Error when finishing deleting provider", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new ProviderException("Error when closing connection to DB, after deleting provider", e, Level.ERROR);
			}	
		}
	}
}
