package util;

import org.apache.logging.log4j.Level;

public class CategoryException extends DoniaMaryException {
	private static final long serialVersionUID = -2234503903785641009L;
	
	public CategoryException(String message) {
		super(message);
	}
	public CategoryException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public CategoryException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
