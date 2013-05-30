/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: LogoutAction.java,v 1.11 2008-08-14 16:54:33 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2008/06/23 18:07:06  pandyas
 * Modified to prevent Security issues
 * Invalidate relevant session identifiers when a user signs out
 * Re: Apps Scan run 06/17/2008
 *
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

import java.io.IOException;
import java.util.Enumeration;import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends BaseAction {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
	{
		log.debug("Entered LogoutAction: " );
		
		String sID = request.getHeader("Referer");
    	
    	// prevents Referer Header injection
    	if ( sID != null && sID != "" && !sID.contains("camod")) {
    		return (mapping.findForward("failure"));
    	}
    	
		// Remove the logged in credential attributes
		request.getSession().removeAttribute("camod.loggedon.username");
		request.getSession().removeAttribute("camod.loggedon.userroles");		
		request.getSession().removeAttribute("loggedin");
		
		// If we wanted to remove everything from the session that might be stored for the
		// user, we could simply invalidate the session instead
		request.getSession().invalidate();
		
       
		return mapping.findForward( "loggedOut" );
	}

}
