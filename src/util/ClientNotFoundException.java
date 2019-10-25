package util;

public class ClientNotFoundException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String message) {
		super(message);
	}
	public ClientNotFoundException(String message, Throwable innerException) {
		super(message,innerException);
	}

}
