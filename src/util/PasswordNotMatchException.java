package util;

public class PasswordNotMatchException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException(String message) {
		super(message);
	}
	public PasswordNotMatchException(String message,Throwable innerException) {
		super(message,innerException);
	} 
}
