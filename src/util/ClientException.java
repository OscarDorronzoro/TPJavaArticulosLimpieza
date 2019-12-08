package util;

import org.apache.logging.log4j.Level;

public class ClientException extends DoniaMaryException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientException(String message) {
		super(message);
	}
	public ClientException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public ClientException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
