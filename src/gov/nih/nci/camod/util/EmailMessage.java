package gov.nih.nci.camod.util;


import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailMessage
{
  String emailServer = "smtp.nih.nci.com";
  String emailSender = "sender@company.com";
  String emailSubject = "caMOD subject";
  String emailRecipient = "receiver@company.com";
  String emailMessage = "Exception Thrown";

  public void sendEmailMessage( String server, String sender, String recipient, String subject, String message )
  {
	 emailServer = server;
	 emailSender = sender;
	 emailSubject = subject;
	 emailRecipient = recipient;
	 emailMessage = message;
	  
     // Send email
	 try {
		 this.sendEmail();
	 } catch( Exception e)
	 { System.out.println( "EXCEPTION in sendEmailMessage! " + e );
	 }
  }

  private void sendEmail()
  	throws Exception 
  {
    // Set the host SMTP address.
    Properties props = new Properties();
    props.put("mail.smtp.host", emailServer);

    // Get the default mail session.
    Session session = Session.getDefaultInstance(props, null);

    // Create a message.
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(emailSender));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
    message.setSubject(emailSubject);
    message.setSentDate(new Date());
    message.setText(emailMessage);

    System.out.println( "<sendEmail> Sending message..." );
    // Send the message.
    Transport.send(message);
  }
}