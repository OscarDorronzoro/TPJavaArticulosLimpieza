package data;

import java.sql.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.logging.log4j.Level;

import util.DBException;

public class FactoryConnection {

	private static FactoryConnection Instancia;
	private int conectados = 0;
	private Connection conn = null;
	private Properties props;
	
	private FactoryConnection() throws DBException {
		this.props = new Properties();
		
		try {
			this.props.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("app.properties"), "UTF-8"));
			Class.forName(this.props.getProperty("db.driver"));
		} catch (ClassNotFoundException e) {
			throw new DBException("DB driver not found", e, Level.ERROR);
		} catch (UnsupportedEncodingException e) {
			throw new DBException("Unsopported encoding for properties file", e, Level.ERROR);
		} catch (IOException e) {
			throw new DBException("Error reading properties file", e, Level.ERROR);
		}
	}
	
	public static FactoryConnection getInstancia() throws DBException {
		if (FactoryConnection.Instancia == null) {
			FactoryConnection.Instancia = new FactoryConnection();
		}
		return FactoryConnection.Instancia;
	}
	
	public Connection getConn() throws DBException {
		try {
			if(conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(
					"jdbc:mysql://"
					+this.props.getProperty("db.host")
					+":"+this.props.getProperty("db.port")
					+"/"+this.props.getProperty("db.db_name")
					,this.props.getProperty("db.user")
					,this.props.getProperty("db.password")
				);
				conectados = 0;
			}
		} catch (SQLException e) {
			throw new DBException("Can't establish connection to DB", e, Level.ERROR);
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() throws DBException {
		conectados--;
		try {
			if (conectados <= 0) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DBException("Error when closing connection to DB", e, Level.ERROR);
		}
	}

}
