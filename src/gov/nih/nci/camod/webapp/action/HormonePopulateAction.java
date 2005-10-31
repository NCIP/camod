/**
 * 
 * $Id: HormonePopulateAction.java,v 1.10 2005-10-31 13:46:28 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.8  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.7  2005/10/20 20:38:41  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.HormoneForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HormonePopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form HormoneForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<HormonePopulateAction populate> ... ");

		HormoneForm hormoneForm = (HormoneForm) form;

		String aTherapyID = request.getParameter("aTherapyID");
		
		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");
		Therapy therapy = therapyManager.get(aTherapyID);

		// Handle back-arrow on the delete
		if (therapy == null) {
			request.setAttribute("aTherapyID", null);
		} else {

			request.setAttribute("aTherapyID", aTherapyID);

			// Set the otherName and/or the selected name attribute
			if (therapy.getAgent().getNameUnctrlVocab() != null) {
				hormoneForm.setName(Constants.Dropdowns.OTHER_OPTION);
				hormoneForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
			} else {
				hormoneForm.setName(therapy.getAgent().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative
			// route
			if (therapy.getTreatment().getAdminRouteUnctrlVocab() != null) {
				hormoneForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
				hormoneForm.setOtherAdministrativeRoute(therapy.getTreatment().getAdminRouteUnctrlVocab());
			} else {
				hormoneForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
			}

			if (therapy.getTreatment().getSexDistribution() != null) {
				hormoneForm.setType(therapy.getTreatment().getSexDistribution().getType());
			}
			hormoneForm.setDosage(therapy.getTreatment().getDosage());
			hormoneForm.setRegimen(therapy.getTreatment().getRegimen());
			hormoneForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());

		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitHormone");
	}

	/**
	 * Populate the dropdown menus for submitHormone
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

		System.out.println("<HormonePopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		HormoneForm hormoneForm = (HormoneForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, hormoneForm);

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<HormonePopulateAction dropdown> exiting... ");

		return mapping.findForward("submitHormone");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<HormonePopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HORMONEDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HORMONEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
