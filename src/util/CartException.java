package util;

import org.apache.logging.log4j.Level;

public class CartException extends DoniaMaryException {
	private static final long serialVersionUID = 8456936965988452080L;
	
	public CartException(String message) {
		super(message);
	}
	public CartException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public CartException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}
}
