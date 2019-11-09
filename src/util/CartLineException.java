package util;

import org.apache.logging.log4j.Level;

public class CartLineException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartLineException(String message) {
		super(message);
	}
	public CartLineException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public CartLineException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
