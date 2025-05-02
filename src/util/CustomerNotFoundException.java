package util;

import org.apache.logging.log4j.Level;

public class CustomerNotFoundException extends CustomerException {
	private static final long serialVersionUID = 823054242361197004L;
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	public CustomerNotFoundException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public CustomerNotFoundException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}

}
