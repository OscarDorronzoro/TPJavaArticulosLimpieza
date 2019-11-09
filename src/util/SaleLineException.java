package util;

import org.apache.logging.log4j.Level;

public class SaleLineException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SaleLineException(String message) {
		super(message);
	}
	public SaleLineException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public SaleLineException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}
}
