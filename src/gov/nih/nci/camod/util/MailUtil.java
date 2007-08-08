/**
 * 
 * $Id: MailUtil.java,v 1.13 2007-08-08 18:50:08 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/07/31 12:02:07  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.11  2006/10/17 16:11:52  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.10  2006/08/17 18:00:52  pandyas
 * Defect# 410: Externalize properties files - Code changes to get properties
 *
 * Revision 1.9  2006/04/17 19:10:50  pandyas
 * Added $Id: MailUtil.java,v 1.13 2007-08-08 18:50:08 pandyas Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.log.CommonsLogLogSystem;

/**
 * Static helper class for sending e-mail.  Propeteries are defined in "mail.properties"
 * 
 * @author georgeda
 *
 */
public class MailUtil {

    static private final Log log = LogFactory.getLog(MailUtil.class);

    static private ResourceBundle macroBundle = null;
    static private String lineseparator = System.getProperty("line.separator");

    private MailUtil() {
    }

    /**
     * @param inRecipients - the list of people to send e-mail to
     * @param inSubject - the subject of the e-mail
     * @param inMessage - the message to send
     * @param inFrom - who the message is from
     * 
     */
    static public void sendMail(String inRecipients[],
                                String inSubject,
                                String inMessage,
                                String inFrom)
            throws MessagingException {

        log.info("Entering sendMail");

        try {

            // Convert the bundle to a properties file.  Is there a better way to do this?
    		Properties camodProperties = new Properties();
    		String camodPropertiesFileName = null;

    		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.mailProperties");
   		
    		try {
    		FileInputStream in = new FileInputStream(camodPropertiesFileName);
    		camodProperties.load(in);
    		} 
    		catch (FileNotFoundException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		} catch (IOException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		}
    		

            boolean theDebugFlag = false;
            if (camodProperties.getProperty("mail.debug") != null) {
                try {
                    theDebugFlag = Boolean.getBoolean(camodProperties.getProperty("mail.debug"));
                } catch (Exception e) {
                    log.warn("Unable to set e-mail debug flag", e);
                }
            }

            // create some properties and get the default Session
            Session theSession = Session.getDefaultInstance(camodProperties, null);
            theSession.setDebug(theDebugFlag);

            // create a message
            Message theEmailMessage = new MimeMessage(theSession);
            log.info("sendMail method theEmailMessage: " + theEmailMessage.toString());

            // set the from and to address
            InternetAddress theAddressFrom = new InternetAddress(inFrom);
            theEmailMessage.setFrom(theAddressFrom);
            log.info("sendMail method theAddressFrom: " + theAddressFrom.toString());            

            InternetAddress[] theAddressTo = new InternetAddress[inRecipients.length];
            for (int i = 0; i < inRecipients.length; i++) {
                theAddressTo[i] = new InternetAddress(inRecipients[i]);
             
            }
            theEmailMessage.setRecipients(Message.RecipientType.TO, theAddressTo);
            log.info("sendMail method theAddressTo: " + theAddressTo.toString());            

            // Setting the Subject and Content Type
            theEmailMessage.setSubject(inSubject);
            log.info("sendMail method inSubject: " + inSubject.toString());             
            theEmailMessage.setContent(inMessage, "text/plain");

            log.info("sendMail method Sending email to: " + inRecipients);
            log.info("sendMail method email subject: " + inSubject);

            Transport.send(theEmailMessage);

        } catch (Exception e) {
            log.error("Unable to send e-mail", e);
        }

        log.info("Exiting sendMail");
    }

    /**
     * @param inRecipients - the list of people to send e-mail to
     * @param inSubject - the subject of the e-mail
     * @param inMessage - String to be used as an additional custom note within the body of content
     * @param inFrom - who the message is from
     * @param messageStds - an array of Strings to be used as keys to create the message to send
     *
     */
    static public void sendMail(String inRecipients[],
                                String inSubject,
                                String inMessage,
                                String inFrom,
                                String[] messageStds,
                                Map valuesForVariables)
            throws MessagingException {
        log.info("Entering sendMail(String, String, String, String, String[]) Map");

        try {

            // Get properteis from external file.  
    		Properties camodProperties = new Properties();
    		String camodPropertiesFileName = null;

    		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.mailProperties");
    		
    		try {
    		FileInputStream in = new FileInputStream(camodPropertiesFileName);
    		camodProperties.load(in);
    		} 
    		catch (FileNotFoundException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		} catch (IOException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		}

            boolean theDebugFlag = false;
            if (camodProperties.getProperty("mail.debug") != null) {
                try {
                    theDebugFlag = Boolean.getBoolean(camodProperties.getProperty("mail.debug"));
                } catch (Exception e) {
                    log.warn("Unable to set e-mail debug flag", e);
                }
            }

            // create some properties and get the default Session
            Session theSession = Session.getDefaultInstance(camodProperties, null);
            theSession.setDebug(theDebugFlag);

            // create a message
            Message theEmailMessage = new MimeMessage(theSession);
            log.info("(Map) sendMail method theEmailMessage: " + theEmailMessage);

            // set the from and to address
            InternetAddress theAddressFrom = new InternetAddress(inFrom);
            log.info("(Map) sendMail method theAddressFrom: " + theAddressFrom.toString());              
            theEmailMessage.setFrom(theAddressFrom);

            InternetAddress[] theAddressTo = new InternetAddress[inRecipients.length];
            for (int i = 0; i < inRecipients.length; i++) {
                theAddressTo[i] = new InternetAddress(inRecipients[i]);
            }
            theEmailMessage.setRecipients(Message.RecipientType.TO, theAddressTo);
            log.info("(Map) sendMail method theAddressTo: " + theAddressTo); 
            
            // Setting the Subject and Content Type
            theEmailMessage.setSubject(inSubject);
            log.info("sendMail method inSubject: " + inSubject.toString());    
            if (null == inMessage || "".equals(inMessage)) {
                theEmailMessage.setContent(buildContent(valuesForVariables,constructTemplateFromMacros(messageStds)), "text/plain");
            } else {
                theEmailMessage.setContent(buildContent(valuesForVariables,constructTemplateFromMacros(messageStds))
                        + lineseparator + lineseparator + inMessage , "text/plain");
            }

            log.info("<sendMail method> Sending email to: " + inRecipients);
            log.info("<sendMail method> email subject: " + inSubject);

            Transport.send(theEmailMessage);

        } catch (Exception e) {
            log.error("Unable to send e-mail", e);
        }

        log.info("Exiting sendMail(String, String, String, String, String[]) Map");
    }

    /**
     * Private method to construct the message body template from an array of keys
     *
     * @param macros is the list of keys for expansion
     * @return String with macros expanded to use as the body of an e-mail message
     */
    private static String constructTemplateFromMacros(String[] macros) {
        StringBuffer sb = new StringBuffer();
        try {
            /* exception caught here to ensure graceful failure */
            if (null==macroBundle) loadMacroBundle();
        } catch (MissingResourceException e) {
            log.error("Unable to load Macro ResourceBundle from within constructTemplateFromMacros(macros)", e);
            return "A problem was encountered in generating automated e-mail. Please contact System Administrator";
        }
        for (int i = 0; i < macros.length; i++) {
            sb.append(macroBundle.getString(macros[i])).append(lineseparator).append(lineseparator);
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
        log.info("loading Macro ResourceBundle");
        macroBundle = ResourceBundle.getBundle("mailmacros");
        log.info("done loading Macro ResourceBundle");
    }

    private static String buildContentWTemplateFile(TreeMap dict, String templateFile) {
        log.info("starting up in buildContentWTemplateFile");
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, CommonsLogLogSystem.class.getName());
            /* init runtime engine */
            ve.init();
            log.info("after Velocity init");
            /* make a Context and put data into it */
            VelocityContext context = new VelocityContext();
            Iterator iter = dict.keySet().iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                context.put(key,dict.get(key));
            }
            /* render template */
            StringWriter sw = new StringWriter();
            ve.mergeTemplate(templateFile,"ISO-8859-1",context, sw);
            return sw.toString();
        } catch (Exception e) {
            log.error("Unable to perform substitutions from within buildContentWTemplateFile(...)", e);
            return "A problem was encountered in generating automated e-mail. Please contact System Administrator";
        }
    }

    /**
     * Method to construct the content for e-mail messages based on a given template.
     * Variables are supported--i.e., variables within the template will have values substituted in the returned
     * output. Velocity is employed for this so the template must adhere to the Velocity Template Language.
     * Variables provided in the dictionary that are not used in the template are simply ignored. It is assumed
     * that the template will gracefully handle any variables for which there are no values found in the dictionary.
     *
     * @param myDictionary of key/value pairs to prescribe values for variables found in the template
     * @param mytemplate to be used to generate the text output
     * @return String output
     */
    private static String buildContent(Map myDictionary, String mytemplate) {
        log.info("starting up in buildContent");
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, CommonsLogLogSystem.class.getName());
            /* init runtime engine */
            ve.init();
            // the previous line throws java.lang.InstantiationException: org.apache.commons.logging.impl.Jdk14Logger
            // if ve is asked to set Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS to log.getClass().getName()
            // and then the "runtime.log.logsystem.log4j.category" property is set to "global"
            log.info("after Velocity init");
            /* make a Context and put data into it */
            VelocityContext context = new VelocityContext();
            Iterator iter = myDictionary.keySet().iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                context.put(key,myDictionary.get(key));
            }
            /* render template */
            StringWriter sw = new StringWriter();
            ve.evaluate(context, sw,"MailUtil.buildContent(...)", mytemplate);
            return sw.toString();
        } catch (Exception e) {
            log.error("Unable to perform substitutions from within MailUtil.buildContent(...)", e);
            return "A problem was encountered in generating automated e-mail. Please contact System Administrator";
        }
    }

    static public void main(String[] inArgs) {

        String[] theRecipients = new String[1];
        theRecipients[0] = new String("georgeda@mail.nih.gov");

        String[] theMessageKeys = {"approve","assign_editor","assign_screener", "need_more_info", "reject","complete","inactivate"};
        String[] macrosUsed = {"defaultbody","alternative1","alternative2"};
        TreeMap<String, String> valuesForVariables = new TreeMap<String, String>();
        valuesForVariables.put("name","Fibber McGhee");
        valuesForVariables.put("submitter","Fibber McGhee");
        valuesForVariables.put("profession","bustin gut");
        valuesForVariables.put("alternative","particularly relevant");
        valuesForVariables.put("possibility","personality");
        System.out.println(buildContentWTemplateFile(valuesForVariables,"email0.template"));
        System.out.println(lineseparator);
        System.out.println(buildContent(valuesForVariables, constructTemplateFromMacros(macrosUsed)));
        try {
            sendMail(theRecipients, "Testing", "Hello there!", "georgeda@mail.nih.gov");
            sendMail(theRecipients,
                    "Testing 2",
                    "Additional custom note goes here.",
                    "georgeda@mail.nih.gov",
                    theMessageKeys,
                    valuesForVariables);
        } catch (Exception e) {
            System.out.println("Caught exception" + e);
            e.printStackTrace();
        }
    }
}
