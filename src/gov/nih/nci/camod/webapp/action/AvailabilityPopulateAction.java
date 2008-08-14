/**
 *
 * @author pandyas
 * 
 * $Id: AvailabilityPopulateAction.java,v 1.9 2008-08-14 16:48:28 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2007/03/26 12:02:30  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.7  2006/11/09 17:23:01  pandyas
 * Commented out debug code
 *
 * Revision 1.6  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.5  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.4  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.3  2005/10/27 16:27:06  georgeda
 * More validation
 *
 * Revision 1.2  2005/10/26 20:14:34  pandyas
 * implemented model availability
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.service.impl.AvailabilityManagerSingleton;
import gov.nih.nci.camod.webapp.form.AvailabilityForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AvailabilityPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Create a form to edit
		AvailabilityForm availabilityForm = (AvailabilityForm) form;

		String theSource = (String) request.getParameter("lab");
		availabilityForm.setSource(theSource);
		
		// Grab the current Availability we are working with related to this
		// animalModel
		String aAvailabilityID = request.getParameter("aAvailabilityID");

		AnimalAvailability avilablity = AvailabilityManagerSingleton.instance().get(aAvailabilityID);

		// Handle back arrow in browser
		if (avilablity == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {
			request.setAttribute("aAvailabilityID", aAvailabilityID);
			availabilityForm.setName(avilablity.getName());
			availabilityForm.setStockNumber(avilablity.getStockNumber());
		}

		return mapping.findForward("next");
	}

	/**
	 * Populate the dropdown menus for all submission screens for Animal
	 * Availability
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AvailabilityForm availabilityForm = (AvailabilityForm) form;

		String theSource = (String) request.getParameter("lab");
		availabilityForm.setSource(theSource);

		return mapping.findForward("next");

	}

}
