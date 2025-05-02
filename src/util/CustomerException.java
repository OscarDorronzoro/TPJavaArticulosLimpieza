package util;

import org.apache.logging.log4j.Level;

public class CustomerException extends DoniaMaryException {
	private static final long serialVersionUID = 2194227535351058980L;
	
	public CustomerException(String message) {
		super(message);
	}
	public CustomerException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public CustomerException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
