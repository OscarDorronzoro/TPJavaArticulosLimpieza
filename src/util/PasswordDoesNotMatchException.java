package util;

import org.apache.logging.log4j.Level;

public class PasswordDoesNotMatchException extends ClientException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordDoesNotMatchException(String message) {
		super(message);
	}
	public PasswordDoesNotMatchException(String message,Throwable innerException) {
		super(message,innerException);
	} 
	public PasswordDoesNotMatchException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}
}
