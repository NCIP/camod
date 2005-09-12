package gov.nih.nci.camod.service.impl;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Static helper class for sending e-mail.  Propeteries are defined in "mail.properties"
 * 
 * @author georgeda
 *
 */
public class MailUtilityImpl {

    static private final Log log = LogFactory.getLog(MailUtilityImpl.class);

    private MailUtilityImpl() {
    }

    /**
     * @param inRecipients - the list of people to send e-mail to
     * @param inSubject - the subject of the e-mail
     * @param inMessage - the message to send
     * @param inFrom - who the message is from
     * 
     */
    public static void sendMail(String inRecipients[], String inSubject, String inMessage, String inFrom)
            throws MessagingException {

        log.trace("Entering sendMail");
        
        try {

            // Convert the bundle to a properties file.  Is there a better way to do this?
            ResourceBundle theBundle = ResourceBundle.getBundle("mail");
            Properties theProperties = new Properties();

            Enumeration theKeys = theBundle.getKeys();

            while (theKeys.hasMoreElements()) {
                String theKey = (String) theKeys.nextElement();
                theProperties.put(theKey, theBundle.getObject(theKey));
            }

            boolean theDebugFlag = false;
            if (theProperties.getProperty("mail.debug") != null) {
                try {
                    theDebugFlag = Boolean.getBoolean(theProperties.getProperty("mail.debug"));
                } catch (Exception e) {
                    log.warn("Unable to set e-mail debug flag", e);
                }
            }

            // create some properties and get the default Session
            Session theSession = Session.getDefaultInstance(theProperties, null);
            theSession.setDebug(theDebugFlag);

            // create a message
            Message theEmailMessage = new MimeMessage(theSession);

            // set the from and to address
            InternetAddress theAddressFrom = new InternetAddress(inFrom);
            theEmailMessage.setFrom(theAddressFrom);

            InternetAddress[] theAddressTo = new InternetAddress[inRecipients.length];
            for (int i = 0; i < inRecipients.length; i++) {
                theAddressTo[i] = new InternetAddress(inRecipients[i]);
            }
            theEmailMessage.setRecipients(Message.RecipientType.TO, theAddressTo);

            // Setting the Subject and Content Type
            theEmailMessage.setSubject(inSubject);
            theEmailMessage.setContent(inMessage, "text/plain");

            log.debug("Sending email to: " + inRecipients);
            log.debug("email subject: " + inSubject);
           
            Transport.send(theEmailMessage);

        } catch (Exception e) {
            log.error("Unable to send e-mail", e);
        }
        
        log.trace("Exiting sendMail");
    }

    static public void main(String[] inArgs) {

        String[] theRecipients = new String[1];
        theRecipients[0] = new String("georgeda@mail.nih.gov");

        try {

            sendMail(theRecipients, "Testing", "Hello there!", "georgeda@mail.nih.gov");
        } catch (Exception e) {
            System.out.println("Caught exception" + e);
            e.printStackTrace();
        }
    }
}
