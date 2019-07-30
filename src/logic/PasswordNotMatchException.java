package logic;

public class PasswordNotMatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException(String message) {
		super(message);
	}
	public PasswordNotMatchException(String message,Exception innerException) {
		super(message,innerException);
	} 
}
