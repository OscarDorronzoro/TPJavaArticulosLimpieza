package util;

import org.apache.logging.log4j.Level;

public class DBException extends DoniaMaryException {
	private static final long serialVersionUID = 4624108245720736259L;
	
	public DBException(String message) {
		super(message);
	}
	public DBException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public DBException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
