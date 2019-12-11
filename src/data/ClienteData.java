package data;

import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Cliente;
import util.ArticleException;
import util.CartException;
import util.CartLineException;
import util.ClientException;
import util.PriceException;
import util.ProviderException;
import util.SaleException;
import util.SaleLineException;

public class ClienteData {
	
	static CarritoData carritoData  = new CarritoData();
	static VentaData ventaData = new VentaData();
	
	public void add(Cliente c) throws CartLineException, ClientException {
		PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(
					"insert into cliente(nombre,apellido,dni,username,password,admin) values(?,?,?,?,?,?)"
					);
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getApellido());
			stmt.setString(3, c.getDNI());
			stmt.setString(4, c.getUsername());
			stmt.setString(5, c.getPassword());
			stmt.setBoolean(6, c.isAdmin());
			
			stmt.executeUpdate();
			
			carritoData.add(c.getMiCarrito(), c.getUsername());
		}
		catch (SQLException | CartException e) {
			// TODO Auto-generated catch block
			throw new ClientException("Error al agregar cliente",e,Level.ERROR);
		} 
		finally {
			try {
			if(stmt!=null)stmt.close();
            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ClientException("Oops! Ha ocurrido un error",e,Level.ERROR);
			}
		}
		
	}
	
	public ArrayList<Cliente> getAll() throws ProviderException, CartLineException, CartException, ArticleException, ClientException, PriceException{
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs=null;
		Statement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select * from cliente");
			if(rs!=null) {
				while(rs.next()) {
					Cliente c=new Cliente();
					
					c.setNombre(rs.getString("nombre"));
					c.setApellido(rs.getString("apellido"));
					c.setDNI(rs.getString("dni"));
					c.setPassword(rs.getString("password"));
					c.setUsername(rs.getString("username"));
					c.setAdmin(rs.getBoolean("admin"));
					
					c.setMiCarrito(carritoData.getOne("compraActual", c.getUsername()));
					
					clientes.add(c);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ClientException("Error al recuperar clientes",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ClientException("Oops! Ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return clientes;
	}
	
	public ArrayList<Cliente> getAllByAdmin(boolean isAdmin) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, PriceException{
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from cliente where admin=?");
			stmt.setBoolean(1, isAdmin);
			
			rs=stmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					Cliente c=new Cliente();
					
					c.setNombre(rs.getString("nombre"));
					c.setApellido(rs.getString("apellido"));
					c.setDNI(rs.getString("dni"));
					c.setPassword(rs.getString("password"));
					c.setUsername(rs.getString("username"));
					c.setAdmin(rs.getBoolean("admin"));
					
					c.setMiCarrito(carritoData.getOne("compraActual", c.getUsername()));
					
					clientes.add(c);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ClientException("Error al recuperar clientes",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ClientException("Oops! Ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return clientes;
	}
	
	public Cliente getOne(String username) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, PriceException {
		
		Cliente c=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from cliente where username=?");
			stmt.setString(1, username);
			rs=stmt.executeQuery();
			if(rs!=null&&rs.next()) {
					c=new Cliente();
					
					c.setNombre(rs.getString("nombre"));
					c.setApellido(rs.getString("apellido"));
					c.setDNI(rs.getString("dni"));
					c.setPassword(rs.getString("password"));
					c.setUsername(rs.getString("username"));
					c.setAdmin(rs.getBoolean("admin"));
					
					c.setMiCarrito(carritoData.getOne("compraActual", c.getUsername()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ClientException("Error al recuperar cliente",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ClientException("Oops! Ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return c;
	}
public Cliente getOneByUserYPassword(String username,String passEncrip ) throws ProviderException, CartLineException, CartException, ArticleException, ClientException, PriceException {
		
		Cliente c=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(
					"select * from cliente where username=? and password=?");
			
			stmt.setString(1, username);
			stmt.setString(2, passEncrip);
			rs=stmt.executeQuery();
			
			if(rs!=null&&rs.next()) {
					c=new Cliente();
					
					c.setNombre(rs.getString("nombre"));
					c.setApellido(rs.getString("apellido"));
					c.setDNI(rs.getString("dni"));
					c.setUsername(rs.getString("username"));
					c.setAdmin(rs.getBoolean("admin"));
					
					c.setMiCarrito(carritoData.getOne("compraActual", c.getUsername()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ClientException("Error al recuperar cliente",e,Level.ERROR);
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					throw new ClientException("Oops! Ha ocurrido un error",e,Level.ERROR);
				}
		}
		
		return c;
	}

	public void update(Cliente cliente) throws ClientException
	{
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update cliente set nombre=?, "
					+ "apellido=?, dni=?, admin=? where username=?");
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getDNI());
			stmt.setBoolean(4, cliente.isAdmin());
			stmt.setString(5, cliente.getUsername());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ClientException("Error al actualizar artículo", e, Level.ERROR);
		}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new ClientException("Oops, ha ocurrido un error", e, Level.ERROR);
			}	
		}	
	}
	
	public void delete(Cliente cliente) throws ClientException, CartException, CartLineException, SaleException, SaleLineException {
		
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConnection.getInstancia().getConn().prepareStatement("delete from cliente where username=?");
			stmt.setString(1, cliente.getUsername());
			
			carritoData.deleteAllByCliente(cliente.getMiCarrito(), cliente.getUsername());
			ventaData.deleteAllByCliente(cliente.getUsername());
			
			stmt.execute();
		}catch(SQLException e) {
			throw new ClientException("Error al eliminar cliente",e,Level.ERROR);
		}
		finally {
			try {
				if(stmt!=null) {stmt.close();}
				FactoryConnection.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ClientException("Oops, ha ocurrido un error",e,Level.ERROR);
			}
		}
	}
	
}
