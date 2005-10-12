package gov.nih.nci.camod.util;

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
 public class MailUtil {

    static private final Log log = LogFactory.getLog(MailUtil.class);

    static private ResourceBundle macroBundle = null;

    private MailUtil() {
    }

    /**
     * @param inRecipients - the list of people to send e-mail to
     * @param inSubject - the subject of the e-mail
     * @param inMessage - the message to send
     * @param inFrom - who the message is from
     * 
     */
    static public void sendMail(String inRecipients[], String inSubject, String inMessage, String inFrom)
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

    /**
     * @param inRecipients - the list of people to send e-mail to
     * @param inSubject - the subject of the e-mail
     * @param inMessage - String to be used as an additional custom note within the body of content
     * @param inFrom - who the message is from
     * @param messageStds - an array of Strings to be used as keys to create the message to send
     *
     */
    static public void sendMail(String inRecipients[], String inSubject, String inMessage, String inFrom, String[] messageStds)
            throws MessagingException {
        log.trace("Entering sendMail(String, String, String, String[])");

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
            if (null == inMessage || "".equals(inMessage)) {
                theEmailMessage.setContent(constructMessageFromMacros(messageStds), "text/plain");
            } else {
                theEmailMessage.setContent(constructMessageFromMacros(messageStds) + "\n\n" + inMessage , "text/plain");
            }

            log.debug("Sending email to: " + inRecipients);
            log.debug("email subject: " + inSubject);

            Transport.send(theEmailMessage);

        } catch (Exception e) {
            log.error("Unable to send e-mail", e);
        }

        log.trace("Exiting sendMail(String, String, String, String[])");
    }

    /**
     * Private method to construct the message body from an array of keys
     *
     * @param macros is the list of keys for expansion
     * @return String with macros expanded to use as the body of an e-mail message
     */
    private static String constructMessageFromMacros(String[] macros) {
        StringBuffer sb = new StringBuffer();
        try {
            if (null==macroBundle) loadMacroBundle();
        } catch (MissingResourceException e) {
            log.error("Unable to load Macro ResourceBundle from within constructMessageFromMacros(macros)", e);
            return "A problem was encountered in generating automated e-mail. Please contact System Administrator";
        }
        for (int i = 0; i < macros.length; i++) {
            sb.append(macroBundle.getString(macros[i])).append("\n");
        }
        return sb.toString();
    }

    /**
     * Private method to load the ResourceBundle where keys are mapped to e-mail strings. The
     * implementation here, rather than as part of the member property declaration, makes
     * it easier to figure out what is going right or wrong (e.g., missing properties file)
     * with log entries.
     */
    private static void loadMacroBundle() {
        log.trace("loading Macro ResourceBundle");
        macroBundle = ResourceBundle.getBundle("mailmacros");
        log.trace("done loading Macro ResourceBundle");
    }

    static public void main(String[] inArgs) {

        String[] theRecipients = new String[1];
        theRecipients[0] = new String("georgeda@mail.nih.gov");

        String[] theMessageKeys = {"defaultbody","alternative1","alternative2"};

        try {
            sendMail(theRecipients, "Testing", "Hello there!", "georgeda@mail.nih.gov");
            sendMail(theRecipients, "Testing 2", "Additional custom note goes here.", "georgeda@mail.nih.gov", theMessageKeys);
        } catch (Exception e) {
            System.out.println("Caught exception" + e);
            e.printStackTrace();
        }
    }
}
