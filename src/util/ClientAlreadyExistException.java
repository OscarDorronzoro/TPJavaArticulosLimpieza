package util;

import org.apache.logging.log4j.Level;

public class ClientAlreadyExistException extends DoniaMaryException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientAlreadyExistException(String message) {
		super(message);
	}
	public ClientAlreadyExistException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public ClientAlreadyExistException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}
}
