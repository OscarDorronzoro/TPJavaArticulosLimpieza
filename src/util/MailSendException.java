package util;

import org.apache.logging.log4j.Level;

public class MailSendException extends DoniaMaryException {
	private static final long serialVersionUID = -8656950274392201478L;
	
	public MailSendException(String message) {
		super(message);
	}
	public MailSendException(String message, Throwable innerException) {
		super(message,innerException);
	}
	public MailSendException(String message, Throwable innerException, Level errorLevel){
		super(message,innerException,errorLevel);
	}
}
