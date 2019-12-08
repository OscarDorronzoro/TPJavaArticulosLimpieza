package util;

import org.apache.logging.log4j.Level;

public class ClientNotFoundException extends ClientException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String message) {
		super(message);
	}
	public ClientNotFoundException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public ClientNotFoundException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}

}
