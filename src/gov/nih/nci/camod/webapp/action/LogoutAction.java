/**
 * 
 * $Id: LogoutAction.java,v 1.10 2008-06-23 18:07:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2008/06/16 13:12:22  pandyas
 * Modified to prevent Security issues
 * Invalidate relevant session identifiers when a user signs out
 * Re: Apps Scan run 06/12/2008
 *
 * Revision 1.8  2008/06/13 17:58:18  pandyas
 * Modified to prevent Security issues
 * Invalidate relevant session identifiers when a user signs out
 * Re: Apps Scan run 06/12/2008
 *
 * Revision 1.7  2008/06/13 17:35:00  pandyas
 * Modified to prevent Security issues
 * Invalidate relevant session identifiers when a user signs out
 * Re: Apps Scan run 06/12/2008
 *
 * Revision 1.6  2008/06/11 17:44:57  pandyas
 * Modified to prevent security issue
 * Invalidate relevant session identifiers when a user signs out
 * Scan run on 06/10/2008
 *
 * Revision 1.5  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.4  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.webapp.form.LoginForm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends BaseAction {
	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws IOException, ServletException
	{
		System.out.println( "<LogoutAction execute> Logging Off" );
		
        // Original code before security scan changes
		request.getSession().setAttribute( "camod.loggedon.username", null );
		//If we wanted to remove everything from the session that might be stored for the
		// user, we could simply invalidate the session instead
		request.getSession().invalidate();
		
		//	Remove attribute set in LoginAction
		HttpSession session = request.getSession(true);
        session.removeAttribute("validUserKey");
        
		// Remove user login key set in LoginAction
		request.getSession().removeAttribute("validUserKey");
    	log.info("Removed Cookie named validUserCookie" );		
        
        //  Example to secure a cookie, i.e. instruct the browser to
        //  Send the cookie using a secure protocol
        Cookie[] cookieArray = request.getCookies(); 
        for(int i = 0; i < cookieArray.length; i++){  
        	log.info("Cookie name: " + cookieArray[i].getName());
        	log.info("Cookie value: " + cookieArray[i].getValue()); 
        	log.info("Cookie MaxAge: " + cookieArray[i].getMaxAge());          	
        	if(cookieArray[i].getName().equals("validUserKey")) {       		
            	cookieArray[i].setValue("000000"); 
            	log.info("Set validUserKey to 00000");
            	log.info("Cookie name: " + cookieArray[i].getName());
            	log.info("Cookie value: " + cookieArray[i].getValue());             	
        	}
        }
		return mapping.findForward( "loggedOut" );
	}

}
