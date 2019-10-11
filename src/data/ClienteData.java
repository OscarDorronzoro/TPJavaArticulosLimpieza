package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Cliente;

public class ClienteData {
	
	public void add(Cliente c) {
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
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
			if(stmt!=null)stmt.close();
            FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
        	e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Cliente> getAll(){
		
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
					
					clientes.add(c);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return clientes;
	}
	
	public ArrayList<Cliente> getAllByAdmin(boolean isAdmin){
		
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
					
					clientes.add(c);					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return clientes;
	}
	
	public Cliente getOne(String username) {
		
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return c;
	}
public Cliente getOneByUserYPassword(String username,String passEncrip ) {
		
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					FactoryConnection.getInstancia().releaseConn();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return c;
	}
}
