/**
 * 
 * $Id: LDAPUtil.java,v 1.7 2006-09-12 17:30:35 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/05/24 14:08:54  georgeda
 * Backed out static changes.
 *
 * Revision 1.3  2006/04/17 19:10:50  pandyas
 * Added $Id: LDAPUtil.java,v 1.7 2006-09-12 17:30:35 georgeda Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.io.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LDAPUtil
{
    static private final Log log = LogFactory.getLog(LDAPUtil.class);

    static public String getEmailAddressForUser(String inUsername)
    {
        String theSearchFilter = "(" + "cn" + "=" + inUsername + "*)";

        String theEmailAddress = "";

        try
        {
            // Get the e-mail resource from external location
            Properties ldapProperties = new Properties();
            String ldapPropertiesFileName = null;
            NamingEnumeration searchEnum = null;

            ldapPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
            log.info("<LDAPUtil> ldapPropertiesFileName: "+ ldapPropertiesFileName);

            if (ldapPropertiesFileName != null)
            {
                try
                {
                    FileInputStream in = new FileInputStream(ldapPropertiesFileName);
                    ldapProperties.load(in);

                    ldapProperties.getProperty("ldap.initial.context.factory");

                    //String theInitialContextFactory = 
                    Hashtable environment = new Hashtable();
                    environment.clear();
                    environment.put(Context.INITIAL_CONTEXT_FACTORY, ldapProperties.getProperty("ldap.initial.context.factory"));
                    environment.put(Context.PROVIDER_URL, ldapProperties.getProperty("ldap.provider.url"));
                    environment.put(Context.SECURITY_AUTHENTICATION, ldapProperties.getProperty("ldap.security.authentication"));
                    environment.put(Context.SECURITY_PROTOCOL, ldapProperties.getProperty("ldap.security.protocol"));
                    DirContext dirContext = new InitialDirContext(environment);

                    SearchControls searchControls = new SearchControls();
                    searchControls.setReturningAttributes(null);
                    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

                    searchEnum = dirContext.search(ldapProperties.getProperty("ldap.context"), theSearchFilter, searchControls);
                    dirContext.close();
                }
                catch (FileNotFoundException e)
                {
                    log.error("Caught exception finding file for properties: ", e);
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    log.error("Caught exception finding file for properties: ", e);
                    e.printStackTrace();
                }
            }
            else
            {
                // get the ldap properties file from project.  Is there a better way to catch file not found erro?
                ResourceBundle theBundle = ResourceBundle.getBundle("ldap");

                theBundle.getString(Constants.Ldap.INITIAL_CONTEXT_FACTORY_KEY);

                //String theInitialContextFactory = 
                Hashtable environment = new Hashtable();
                environment.clear();
                environment.put(Context.INITIAL_CONTEXT_FACTORY, theBundle.getString(Constants.Ldap.INITIAL_CONTEXT_FACTORY_KEY));
                environment.put(Context.PROVIDER_URL, theBundle.getString(Constants.Ldap.PROVIDER_URL_KEY));
                environment.put(Context.SECURITY_AUTHENTICATION, theBundle.getString(Constants.Ldap.SECURITY_AUTHENTICATION_KEY));
                environment.put(Context.SECURITY_PROTOCOL, theBundle.getString(Constants.Ldap.SECURITY_PROTOCOL_KEY));
                DirContext dirContext = new InitialDirContext(environment);

                SearchControls searchControls = new SearchControls();
                searchControls.setReturningAttributes(null);
                searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

                searchEnum = dirContext.search(theBundle.getString(Constants.Ldap.CONTEXT_KEY), theSearchFilter, searchControls);
                dirContext.close();

            }
            while (searchEnum.hasMore())
            {
                SearchResult searchResult = (SearchResult) searchEnum.next();

                Attributes theAttributes = searchResult.getAttributes();

                Enumeration theEnum = theAttributes.getAll();

                while (theEnum.hasMoreElements())
                {
                    Attribute theAttribute = (Attribute) theEnum.nextElement();

                    if (theAttribute.getID().equals("mail"))
                    {
                        theEmailAddress = theAttribute.get().toString();
                        break;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new IllegalArgumentException("User Name does not exist + " + inUsername);
        }

        return theEmailAddress;
    }

    public static void main(String[] args)
    {
        System.out.println("Email address: " + LDAPUtil.getEmailAddressForUser("georgeda"));
    }
}
