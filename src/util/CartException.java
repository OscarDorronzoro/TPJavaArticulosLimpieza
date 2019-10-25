package util;

public class CartException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CartException(String message) {
		super(message);
	}
	public CartException(String message, Throwable innerException) {
		super(message,innerException);
	}
}
