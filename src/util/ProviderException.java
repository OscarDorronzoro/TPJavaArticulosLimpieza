package util;

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
	
}
