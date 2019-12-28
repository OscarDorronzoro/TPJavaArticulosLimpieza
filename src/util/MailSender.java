package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.Level;
 
public class MailSender {
	
	public static MailSender instance;
	private Properties props;
	
	public static MailSender getInstance() throws MailSendException{
		if (instance==null){
			instance=new MailSender();
		}
		return instance;
	}
	
	private MailSender() throws MailSendException {
		
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream("app.properties");
		try {
			props = new Properties();
			props.load(inputStream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new MailSendException("Error al cargar propiedades de mail",e,Level.ERROR);
		}
		
	}
	
	public void send(String to, String subject, String body) throws MailSendException{

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication(username, password);
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to)); //"adrianmeca@gmail.com"
			message.setSubject(subject); //"Testing Subject"
			message.setText(body); //"Dear Mail Crawler,\n\n No spam to my email, please!"

			Transport.send(message);

		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			throw new MailSendException("Error al enviar mail",e,Level.ERROR);
		}
	}

 
}
