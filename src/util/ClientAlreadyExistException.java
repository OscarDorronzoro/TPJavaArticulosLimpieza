package util;

public class ClientAlreadyExistException extends DoniaMaryException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientAlreadyExistException(String message) {
		super(message);
	}
	public ClientAlreadyExistException(String message, Throwable innerException) {
		super(message,innerException);
	}
}
