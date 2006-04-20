/**
 * 
 * $Id: LDAPUtil.java,v 1.4 2006-04-20 14:59:41 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/17 19:10:50  pandyas
 * Added $Id: LDAPUtil.java,v 1.4 2006-04-20 14:59:41 georgeda Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Utility class used to get e-mail information from LDAP repository
 *
 */
public class LDAPUtil
{
    private static final Log ourLog = LogFactory.getLog(LDAPUtil.class);

    // Only get once.  Slow operation
    static private DirContext ourDirContext = null;

    // Initialize the static directory context
    static private synchronized void initialize() throws NamingException
    {
        // Convert the bundle to a properties file.  Is there a better way to do this?
        ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);

        //String theInitialContextFactory = 
        Hashtable<Object, Object> environment = new Hashtable<Object, Object>();
        environment.clear();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, theBundle.getString(Constants.Ldap.INITIAL_CONTEXT_FACTORY_KEY));
        environment.put(Context.PROVIDER_URL, theBundle.getString(Constants.Ldap.PROVIDER_URL_KEY));
        environment.put(Context.SECURITY_AUTHENTICATION, theBundle.getString(Constants.Ldap.SECURITY_AUTHENTICATION_KEY));
        environment.put(Context.SECURITY_PROTOCOL, theBundle.getString(Constants.Ldap.SECURITY_PROTOCOL_KEY));

        ourDirContext = new InitialDirContext(environment);
    }

    // Singleton method.  Get the global directory context
    static private synchronized DirContext getDirContext() throws NamingException
    {
        if (ourDirContext == null)
        {
            initialize();
        }
        return ourDirContext;
    }

    /**
     * Get the e-mail address for the user
     * 
     * @param inUsername
     *            The LDAP username
     * 
     * @return the e-mail address of the user as contained in LDAP
     * 
     * 
     * @throws IllegalArgumentException
     *                if the user is not known
     * @throws Exception
     *                if any other error occurs
     */
    static public synchronized String getEmailAddressForUser(String inUsername) throws IllegalArgumentException, Exception
    {
        String theSearchFilter = "(" + "cn" + "=" + inUsername + "*)";
        String theEmailAddress = "";

        try
        {
            // Convert the bundle to a properties file.  Is there a better way to do this?
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            theBundle.getString(Constants.Ldap.INITIAL_CONTEXT_FACTORY_KEY);

            SearchControls searchControls = new SearchControls();
            searchControls.setReturningAttributes(null);
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration searchEnum = getDirContext().search(theBundle.getString(Constants.Ldap.CONTEXT_KEY), theSearchFilter,
                                                                  searchControls);
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
        catch (NamingException ne)
        {
            ne.printStackTrace();
            ourLog.error("Naming exception getting user: ", ne);

            throw new IllegalArgumentException("User Name does not exist + " + inUsername);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            ourLog.error("Unknown exception getting user: ", ex);

            // Unknown error; force reinitialize of directory context next time method is called.
            ourDirContext = null;

            throw ex;
        }

        return theEmailAddress;
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Email address: " + LDAPUtil.getEmailAddressForUser("georgeda"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
