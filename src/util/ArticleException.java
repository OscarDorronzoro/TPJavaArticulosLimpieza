package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArticleException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArticleException(String message) {
		super(message);
	}
	public ArticleException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public ArticleException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
