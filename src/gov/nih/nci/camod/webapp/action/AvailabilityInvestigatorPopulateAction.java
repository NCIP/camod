/**
 *
 * @author pandyas
 * 
 * $Id: AvailabilityInvestigatorPopulateAction.java,v 1.5 2005-10-31 13:46:28 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/10/28 17:45:10  georgeda
 * PI no longer required
 *
 * Revision 1.3  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.2  2005/10/27 16:27:06  georgeda
 * More validation
 *
 * Revision 1.1  2005/10/26 20:14:34  pandyas
 * implemented model availability
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.impl.AvailabilityManagerSingleton;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AvailabilityInvestigatorPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<AvailabilityInvestigatorPopulateAction populate> Entering ");

		// Create a form to edit
		AvailabilityForm availabilityForm = (AvailabilityForm) form;

		// Grab the current Availability we are working with related to this
		// animalModel
		String aAvailabilityID = request.getParameter("aAvailabilityID");

		AnimalAvailability avilablity = AvailabilityManagerSingleton.instance().get(aAvailabilityID);
		System.out.println("avilablity (id and name): " + avilablity);

		if (avilablity == null) {
			request.setAttribute("aAvailabilityID", null);
		} else {
			
			request.setAttribute("aAvailabilityID", aAvailabilityID);
			// display strin name
			availabilityForm.setName(avilablity.getName());

			if (avilablity.getStockNumber() != null && avilablity.getStockNumber().length() > 0) {

				// get the username from the PI-id stored under stock_number in
				// AnimalAvailability table
				Person thePerson = PersonManagerSingleton.instance().get(avilablity.getStockNumber());
				System.out.println("thePerson: " + thePerson);

				availabilityForm.setStockNumber(thePerson.getUsername());
				System.out.println("thePerson.getUsername(): " + thePerson.getUsername());
			}
		}
		
		// setup dropdown menus
		this.dropdown(request, response);
		
		System.out.println("<AvailabilityInvestigatorPopulateAction populate> Exiting ");

		return mapping.findForward("submitInvestigator");
	}

	/**
	 * Populate the dropdown menus for 4 submission screens for Animal
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

		System.out.println("<AvailabilityInvestigatorPopulateAction ActionForward dropdown> Entering ");

		// blank out the FORMDATA Constant field
		AvailabilityForm availabilityForm = (AvailabilityForm) form;

		String theSource = (String) request.getParameter("lab");
		availabilityForm.setSource(theSource);

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<AvailabilityInvestigatorPopulateAction ActionForward dropdown> Exiting ");

		return mapping.findForward("submitInvestigator");

	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<AvailabilityInvestigatorPopulateAction dropdown> Entering ");

		// Prepopulate dropdown field on submitInvestigator screen only
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORDROP,
				Constants.Dropdowns.ADD_BLANK_OPTION);
	}

}
