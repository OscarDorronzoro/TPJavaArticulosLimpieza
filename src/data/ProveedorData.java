package data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Provider;
import util.DBException;
import util.ProviderException;

public class ProveedorData {

	public void add(Provider prov) throws ProviderException {
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"insert into providers (cuit, address, phone_number, email, business_name) "
				+ "values(?,?,?,?,?)"
			);
			
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
	
	public Provider getOne(String cuit) throws ProviderException {
		
		Provider prov = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from providers p where p.cuit=?"); 
			stmt.setString(1, cuit);
			
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
					prov = new Provider();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("address"));
					prov.setMail(rs.getString("email"));
					prov.setRazonSocial(rs.getString("business_name"));
					prov.setTelefono(rs.getString("phone_number"));
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
	
	public ArrayList<Provider> getAll() throws ProviderException{
		
		ArrayList<Provider> proveedores = new ArrayList<Provider>();
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from providers");
			
			if (rs != null) {
				while (rs.next()) {
					Provider prov=new Provider();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("address"));
					prov.setMail(rs.getString("email"));
					prov.setRazonSocial(rs.getString("business_name"));
					prov.setTelefono(rs.getString("phone_number"));
										
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
	
	public ArrayList<Provider> getAllByArticle(int articleCode) throws ProviderException{
		
		ArrayList<Provider> proveedores = new ArrayList<Provider>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"select * from providers p "
					+ "inner join articles_providers ap on p.cuit=ap.cuit "
				+ "where ap.article_code=?"
			);
			stmt.setInt(1, articleCode);
			
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Provider prov=new Provider();
					
					prov.setCuit(rs.getString("cuit"));
					prov.setDireccion(rs.getString("address"));
					prov.setMail(rs.getString("email"));
					prov.setRazonSocial(rs.getString("business_name"));
					prov.setTelefono(rs.getString("phone_number"));
										
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

	public void update(Provider proveedor) throws ProviderException {
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"update providers p set "
					+ "address=?, phone_number=?, email=?, business_name=? "
				+ "where p.cuit=?"
			);
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
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
				"delete from providers p where p.cuit=?"
			);
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
