/**
 * 
 * $Id: LogoutAction.java,v 1.4 2006-04-17 19:09:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		request.getSession().setAttribute( "camod.loggedon.username", null );
		request.getSession().invalidate();
		return mapping.findForward( "loggedOut" );
	}

}
