package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DoniaMaryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DoniaMaryException(String message) {
		super(message);
	}
	public DoniaMaryException(String message, Throwable innerException) {
		super(message,innerException);
	}
	
	public DoniaMaryException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException);
		Logger logger = LogManager.getLogger(getClass());
		
		String mensaje=message;
		Throwable causa = innerException;
		
		logger.log(errorLevel,message +"\n-----------------------------\n"+this.getStackTrace().toString());
		while(causa.getCause()!=null) {
			causa=causa.getCause();
			mensaje=causa.getMessage();
			logger.log(errorLevel,mensaje);			
		}
		
	}

}
