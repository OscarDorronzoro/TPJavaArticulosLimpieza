package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogErrores {

	private Logger logger = Logger.getLogger("miLogger");
	
    public String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
	//https://logging.apache.org/log4j/2.0/download.html
	public void registrarError(Exception exception) {
		String stackTrace=this.getStackTrace(exception);
		this.logger.log(Level.SEVERE, stackTrace);
		
		// Para sobre escribir pase un true para agregar al final, false para sobre escribir
        // todo el archivo
        Handler fileHandler;
		try {
			fileHandler = new FileHandler("./LogErrores/bitacora.log", false);
			// El formateador indica como presentar los datos, en este caso usaremos el formaro sencillo
	        // el cual es mas facil de leer, si no usamos esto el log estara en formato xml por defecto
	        SimpleFormatter simpleFormatter = new SimpleFormatter();

	        // Se especifica que formateador usara el manejador (handler) de archivo
	        fileHandler.setFormatter(simpleFormatter);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
	}
}
