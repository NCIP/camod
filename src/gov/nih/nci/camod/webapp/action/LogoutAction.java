/**
 * 
 * $Id: LogoutAction.java,v 1.7 2008-06-13 17:35:00 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
		
		// Clean name and password in the input/output form bean
        LoginForm loginForm = (LoginForm) form;
        loginForm.setUsername(null);
        loginForm.setPassword(null);
		
		request.getSession().setAttribute( "camod.loggedon.username", null );
		request.getSession().invalidate();
		
		// Remove dialog name from session, effectively logging out
		request.getSession().removeAttribute("camod.loggedon.username");		
        
        // explicitly remove the JSESSIONID to prevent security issue
        Cookie[] cookieArray = request.getCookies(); 
        for(int i = 0; i < cookieArray.length; i++){         
            if(cookieArray[i].getName().equals("JSESSIONID") || cookieArray[i].getValue().equals("JSESSIONID")) {
                cookieArray[i].setMaxAge(0);
                // Setting value to null did not pass the Security Scan - next two attempts below          
                // Set Cookie to expire at some past date to get rid of it
                response.setHeader("Set-Cookie","name=JSESSIONID; expires=Wednesday, 09-Nov-99 23:12:40 GMT");                 
            log.info("removed value for JSESSIONID and JSESSIONID");
            } 
             
        }
		return mapping.findForward( "loggedOut" );
	}

}
