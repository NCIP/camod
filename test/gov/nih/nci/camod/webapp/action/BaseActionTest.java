/*
 * BaseActionTest.java
 *
 * $Id: BaseActionTest.java,v 1.1 2005-12-28 16:41:57 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;

import java.util.*;

import servletunit.struts.CactusStrutsTestCase;

public class BaseActionTest extends CactusStrutsTestCase {

    protected ResourceBundle myBundle = ResourceBundle.getBundle("test");
    
    public BaseActionTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected void setLoggedIn() {
        String theUsername = myBundle.getString("username");       
        getRequest().getSession().setAttribute(Constants.CURRENTUSER,theUsername); 
    }
    
    protected void setAllRoles() {
        
        List theRoles = new ArrayList();
        theRoles.add(Constants.Admin.Roles.COORDINATOR);
        theRoles.add(Constants.Admin.Roles.EDITOR);
        theRoles.add(Constants.Admin.Roles.SCREENER);
        theRoles.add(Constants.Admin.Roles.SUPER_USER);
          
        getRequest().getSession().setAttribute(Constants.CURRENTUSERROLES,theRoles); 
    }
    
    
    protected void setUnprotectedMethod(String inMethod) {
        
        // set the request parameters
        addRequestParameter("unprotected_method", inMethod);
    }
    
    protected void setProtectedMethod(String inMethod) {
        
        // set the request parameters
        addRequestParameter("method", inMethod);
    }
}
