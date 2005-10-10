/**
 *  @author dgeorge
 *  
 *  $Id: ViewModelSectionAction.java,v 1.1 2005-10-10 14:09:54 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
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

		log.trace("Entering ViewModelSectionAction.execute");

		// Get the attributes from the request
		String theForward = inRequest.getParameter(Constants.Parameters.MODELSECTIONNAME);

		log.debug("Forwarding to model section: " + theForward);

		log.trace("Exiting ChangeAnimalModelStatePopulateAction.execute");

		return inMapping.findForward(theForward);
	}
}
