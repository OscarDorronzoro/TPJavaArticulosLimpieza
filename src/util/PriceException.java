package util;

import org.apache.logging.log4j.Level;

public class PriceException extends DoniaMaryException {
	private static final long serialVersionUID = -3974172753677982988L;
	
	public PriceException(String message) {
		super(message);
	}
	public PriceException(String message,Throwable innerException) {
		super(message,innerException);
	}
	public PriceException(String message,Throwable innerException,Level level) {
		super(message,innerException,level);
	}

}
