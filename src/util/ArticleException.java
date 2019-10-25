package util;

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
}
