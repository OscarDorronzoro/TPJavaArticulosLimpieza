package util;

public class ClientNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String message) {
		super(message);
	}
	public ClientNotFoundException(String message, Exception innerException) {
		super(message,innerException);
	}

}