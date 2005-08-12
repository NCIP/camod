/*
 * LoginAction.java
 *
 * Created on June 24, 2005, 11:31 AM
 *
 */

package gov.nih.nci.camod.webapp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.LoginForm;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSException;

public final class LoginAction extends BaseAction {      
    
    private static Log log = LogFactory.getLog( LoginAction.class );
    //static final Logger log = Logger.getLogger(LoginAction.class.getName());
    static AuthenticationManager authMgr = null;
    static AuthorizationManager Mgr = null;
    
    /*
    * Authenticates the user using the CSM AuthenticationManager and stores in
    * the session.
    * 
    * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
    *      org.apache.struts.action.ActionForm,
    *      javax.servlet.http.HttpServletRequest,
    *      javax.servlet.http.HttpServletResponse)
    */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
	throws IOException, ServletException
	{
        LoginForm loginForm = (LoginForm) form; 
         
        log.debug("Logon Username: " + loginForm.getUsername());
        log.debug("Logon Passwod: " + loginForm.getPassword());
        log.debug("System Config file is: " + System.getProperty( "gov.nih.nci.security.configFile" ) );
        
        //check login credentials using Authentication Mangager
        boolean loginOK = false;                
        try {
            loginOK = getAuthenticationManager().login( loginForm.getUsername(), loginForm.getPassword() );
        } catch ( CSException ex ) {
            loginOK = false;
            log.debug( "The user was denied access to the csm application.", ex );
            System.out.println( "CSException " + ex );
        }

        String forward = Constants.FAILURE;        
        System.out.println( "<LogonAction.java> execute: (authenticating...) username:" + loginForm.getUsername() + " password:" + loginForm.getPassword() );                        
 
        if ( loginOK ) {
                System.out.println("<LogonAction.java> SUCESSFUL LOGIN ");
                forward = Constants.SUCCESS;
                
               // ActionMessages messages = new ActionMessages();
               // messages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "access.application.success" ) );
               // saveMessages( request, messages );
                
                request.getSession().setAttribute( "camod.loggedon.username", loginForm.getUsername() );
                
        } else {
                System.out.println("<LogonAction.java> FAILURE LOGIN ");
                forward = Constants.FAILURE;
                
                ActionErrors errors = new ActionErrors();
                errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "errors.validation.header" ) );
                saveErrors( request, errors );
        }                
        
      /* 
       try {
			AuthorizationManager Mgr = SecurityServiceProvider.getAuthorizationManager( Constants.UPT_CONTEXT_NAME ); 
			User currentUser = Mgr.getUser( loginForm.getUsername() );
			currentUser.setPhoneNumber("555-555-5555");
			currentUser.setFirstName("caMod");
			currentUser.setLastName("Demo");
			System.out.println("Username: " + currentUser.getUserId() + "\nFirstName: " + currentUser.getFirstName() + "\nLastName: " + currentUser.getLastName() + "\nPhoneNumber: " + currentUser.getPhoneNumber() + "\nPassword: " + currentUser.getPassword() );
			
			UserProvisioningManager Upm = (UserProvisioningManager) SecurityServiceProvider.getAuthorizationManager( Constants.UPT_CONTEXT_NAME );
			Upm.modifyUser( currentUser );
			
        } catch ( CSException ex ) { System.out.println( "CSException " + ex); }
        */
        
        // Forward control to the specified success URI
        return mapping.findForward( forward );
    }
    
    /**
     * Returns the AuthenticationManager for the CSM RI. This method follows the
     * singleton pattern so that only one AuthenticationManager is created for
     * the CSM RI.
     * 
     * @return
     * @throws CSException
     */
    protected AuthenticationManager getAuthenticationManager()
                    throws CSException {
            if ( authMgr == null ) {
                    synchronized ( LoginAction.class ) {
                            if ( authMgr == null ) {
                                    authMgr = SecurityServiceProvider.getAuthenticationManager( Constants.UPT_CONTEXT_NAME );                                   
                            }
                    }
            }
            return authMgr;
    }
}