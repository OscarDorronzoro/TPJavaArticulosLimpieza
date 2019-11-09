package util;

import org.apache.logging.log4j.Level;

public class ProviderException extends DoniaMaryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProviderException(String mensaje) {
		super(mensaje);
	}
	
	public ProviderException(String mensaje,Throwable innerException) {
		super(mensaje,innerException);
	}
	public ProviderException(String message, Throwable innerException,Level level) {
		super(message,innerException,level);
	}
	
}
