package util;

import org.apache.logging.log4j.Level;

public class CustomerAlreadyExistException extends DoniaMaryException{
	private static final long serialVersionUID = 2881499754524511828L;
	
	public CustomerAlreadyExistException(String message) {
		super(message);
	}
	public CustomerAlreadyExistException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public CustomerAlreadyExistException(String message, Throwable innerException, Level level) {
		super(message,innerException,level);
	}
}
