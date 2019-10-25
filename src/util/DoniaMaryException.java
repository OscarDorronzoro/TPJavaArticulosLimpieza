package util;

public class DoniaMaryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DoniaMaryException(String message) {
		super(message);
	}
	public DoniaMaryException(String message, Throwable innerException) {
		super(message,innerException);
	}

}
