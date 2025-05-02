package util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DoniaMaryException extends Exception {
	private static final long serialVersionUID = -3976907145340633495L;

	public DoniaMaryException(String message) {
		super(message);
	}
	public DoniaMaryException(String message, Throwable innerException) {
		super(message,innerException);
	}
	
	public DoniaMaryException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException);
		Logger logger = LogManager.getLogger(getClass());
		
		StringWriter sw = new StringWriter();
		String stackTrace = "";
		if (innerException != null) {
			innerException.printStackTrace(new PrintWriter(sw));
			stackTrace = sw.toString();
		}
		
		String mensaje = message;
		Throwable causa = innerException;
		
		logger.log(errorLevel, mensaje + "\n\t" + stackTrace);
		while(causa != null && causa.getCause() != null) {
			causa = causa.getCause();
			mensaje = causa.getMessage();
			causa.printStackTrace(new PrintWriter(sw));
			stackTrace = sw.toString();
			logger.log(errorLevel,mensaje + "\n\t" + stackTrace);			
		}
		
	}

}
