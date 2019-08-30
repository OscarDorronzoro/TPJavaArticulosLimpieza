package util;

public class ClientAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientAlreadyExistException(String message) {
		super(message);
	}
	public ClientAlreadyExistException(String message, Exception innerException) {
		super(message,innerException);
	}
}
