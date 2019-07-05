package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Cliente;

public class ClienteData {
	
	
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
}
