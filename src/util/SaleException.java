package util;

import org.apache.logging.log4j.Level;

public class SaleException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SaleException(String message) {
		super(message);
	}
	public SaleException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public SaleException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}
}
