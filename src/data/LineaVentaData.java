package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entities.Linea;
import entities.Proveedor;
import util.ArticleException;
import util.CategoryException;
import util.DBException;
import util.PriceException;
import util.ProviderException;
import util.SaleLineException;

public class LineaVentaData extends LineaData {

	public void add(Linea linea, int nroVenta) throws SaleLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into linea_venta "
					+ "(nro_venta,cantidad,cod_articulo,cuit_proveedor) values(?,?,?,?)");
			
			stmt.setInt(1, nroVenta);
			stmt.setInt(2,linea.getCantidad());
			stmt.setInt(3, linea.getArticulo().getCodArticulo());
			
			ArrayList<Proveedor> proveedores = linea.getArticulo().getProveedores();
			stmt.setString(4, proveedores.get((int)(Math.random()*proveedores.size())).getCuit()); // proveedor elegido aleatoriamente
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new SaleLineException("Error when adding new sale line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleLineException("Error when establishing connection to DB, to add new sale line", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleLineException("Error when finishing adding new sale line", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleLineException("Error when closing connection to DB, after adding new sale line", e, Level.ERROR);
			}
		}
		
	}
	
	public ArrayList<Linea> getAllByVenta(int nroVenta) throws ProviderException, ArticleException, SaleLineException, PriceException, CategoryException{
		
		ArrayList<Linea> lineas = new ArrayList<Linea>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from linea_venta where nro_venta=?");
			stmt.setInt(1,nroVenta);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Linea linea = new Linea();
					
					linea.setArticulo(this.getArticuloData().getOne(rs.getInt("cod_articulo")));
					linea.setCantidad(rs.getInt("cantidad"));
					linea.setProveedor(this.getProveedorData().getOne(rs.getString("cuit_proveedor")));
					
					lineas.add(linea);					
				}
			}
		}
		catch (SQLException e) {
			throw new SaleLineException("Error when getting all sale lines by sale", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleLineException("Error when establishing connection to DB, to get all sale lines by sale", e, Level.ERROR);
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
				throw new SaleLineException("Error when finishing getting all sale lines by sale", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleLineException("Error when closing connection to DB, after getting all sale lines by sale", e, Level.ERROR);
			}
		}
		
		return lineas;
	}
	
	public void delete(int nroVenta,Linea linea) throws SaleLineException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("delete from linea_venta where nro_venta=? and cod_articulo=?"
					+ " and cuit_proveedor=?");
			stmt.setInt(1,nroVenta);
			stmt.setInt(2,linea.getArticulo().getCodArticulo());
			stmt.setString(3,linea.getProveedor().getCuit());
			
			stmt.execute();
			
		}
		catch (SQLException e) {
			throw new SaleLineException("Error when deleting sale line", e, Level.ERROR);
		}
		catch (DBException e) {
			throw new SaleLineException("Error when establishing connection to DB, to delete sale line", e, Level.ERROR);
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				FactoryConnection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				throw new SaleLineException("Error when finishing deleting sale line", e, Level.ERROR);
			}
			catch (DBException e) {
				throw new SaleLineException("Error when closing connection to DB, after deleting sale line", e, Level.ERROR);
			}	
		}
		
		
	}
}
