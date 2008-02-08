/**
 *  @author dgeorge
 *  
 *  $Id: ViewModelSectionAction.java,v 1.3 2008-02-08 16:49:00 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.2  2007/12/17 18:03:22  pandyas
 *  Removed * in searchFilter used for getting e-mail from LDAP
 *  Apps Support ticket was submitted (31169 - incorrect e-mail associated with my caMOD account) stating:
 *
 *  Cheryl Marks submitted a ticket to NCICB Application Support in which she requested that the e-mail address associated with her account in the "User Settings" screen in caMOD be corrected. She has attempted to correct it herself, but because the program queries the LDAP Server for the e-mail address, her corrections were not retained.
 *
 *  Revision 1.1  2005/10/10 14:09:54  georgeda
 *  Initial revision
 *
 *  Revision 1.8  2005/09/22 15:17:36  georgeda
 *  More changes
 *
 *  Revision 1.7  2005/09/19 14:21:47  georgeda
 *  Added interface for URL parameters
 *
 *  Revision 1.6  2005/09/19 13:38:42  georgeda
 *  Cleaned up parameter passing
 *
 *  Revision 1.5  2005/09/19 13:09:52  georgeda
 *  Added header
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ViewModelSectionAction extends BaseAction {

	/**
	 * Change the state for the curation model
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.debug("Entering ViewModelSectionAction.execute");

		// Get the attributes from the request
		String theForward = inRequest.getParameter(Constants.Parameters.MODELSECTIONNAME);

		log.debug("Forwarding to model section: " + theForward);

		log.debug("Exiting ViewModelSectionAction.execute");

		return inMapping.findForward(theForward);
	}
}
