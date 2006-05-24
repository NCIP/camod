/**
 * 
 * $Id: LDAPUtil.java,v 1.5 2006-05-24 14:08:54 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/17 19:10:50  pandyas
 * Added $Id: LDAPUtil.java,v 1.5 2006-05-24 14:08:54 georgeda Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.util.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class LDAPUtil {

    static public String getEmailAddressForUser(String inUsername) {
        String theSearchFilter = "(" + "cn" + "=" + inUsername + "*)";

        String theEmailAddress = "";
        
        try {
            // Convert the bundle to a properties file.  Is there a better way to do this?
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            
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

            NamingEnumeration searchEnum = dirContext.search(theBundle.getString(Constants.Ldap.CONTEXT_KEY), theSearchFilter, searchControls);
            dirContext.close();
            
            while (searchEnum.hasMore()) {
                
                SearchResult searchResult = (SearchResult) searchEnum.next();
              
                Attributes theAttributes = searchResult.getAttributes();

                Enumeration theEnum = theAttributes.getAll();

                while (theEnum.hasMoreElements()) {
                    Attribute theAttribute = (Attribute) theEnum.nextElement();

                    if (theAttribute.getID().equals("mail"))
                    {
                        theEmailAddress = theAttribute.get().toString();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("User Name does not exist + " + inUsername);
        }
        
        return theEmailAddress;
    }
    
    public static void main(String[] args) {
        System.out.println("Email address: " + LDAPUtil.getEmailAddressForUser("georgeda"));
    }
}
