package com.uttara.contact;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail 
{

	private String SMTP_PORT = "465";
	private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private String SMTP_HOST_NAME = "smtp.gmail.com";
	private String contentType = "text/html";
	private Properties smtpProperties;
	
	public SendMail()
	{
		initProperties();
	}
	
	private void initProperties()
	{
		smtpProperties = new Properties();
		smtpProperties.put("mail.smtp.host", SMTP_HOST_NAME);
		smtpProperties.put("mail.smtp.auth", "true");
		smtpProperties.put("mail.debug", "true");
		smtpProperties.put("mail.smtp.port", SMTP_PORT);
		smtpProperties.put("mail.smtp.socketFactory.port", SMTP_PORT);
		smtpProperties.put ("mail.smtp.socketFactory.class", SSL_FACTORY);
		smtpProperties.put("mail.smtp.socketFactory.fallback","false");
	}
	
		
	public static String send(String to, final String from , final String pwd, String subject,String body)
	{
			SendMail tjm = new SendMail();
			try
			{
				Properties props = tjm.getSmtpProperties() ;
				// 	-- Attaching to default Session, or we could start 	a new one --
				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() 
				{
					protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(from, pwd);
					}
				});
				
				
				// -- Create a new message --
				Message msg = new MimeMessage(session);
				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress(from));
				msg.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(to, false));
				// -- Set the subject and body text --
				msg.setSubject(subject);
				msg.setText(body);
				msg.setSentDate(new Date());
				// -- Send the message –
				Transport.send(msg);
				System.out.println("Message sent OK.");
				
				return Constants.SUCCESS;
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				return ex.getMessage();
			}
		}
			
	
			public Properties getSmtpProperties() 
			{
				return smtpProperties;
			}
			
			public void setSmtpProperties(Properties smtpProperties) 
			{
				this.smtpProperties = smtpProperties;
			}
		
	
	
}
