/**
 * 
 * $Id: GrowthFactorPopulateAction.java,v 1.10 2005-11-03 13:59:10 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.6  2005/10/20 20:38:17  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GrowthFactorPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form SurgeryForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<GrowthFactorPopulateAction populate> ... ");

		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;

		String aTherapyID = request.getParameter("aTherapyID");
		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");
		Therapy therapy = therapyManager.get(aTherapyID);

		// Handle back-arrow on the delete
		if (therapy == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {

			request.setAttribute("aTherapyID", aTherapyID);
			
			// Set the otherName and/or the selected name attribute
			if (therapy.getAgent().getNameUnctrlVocab() != null) {
				growthFactorForm.setName(Constants.Dropdowns.OTHER_OPTION);
				growthFactorForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
			} else {
				growthFactorForm.setName(therapy.getAgent().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative
			// route
			if (therapy.getTreatment().getAdminRouteUnctrlVocab() != null) {
				growthFactorForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
				growthFactorForm.setOtherAdministrativeRoute(therapy.getTreatment().getAdminRouteUnctrlVocab());
			} else {
				growthFactorForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
			}

			if (therapy.getTreatment().getSexDistribution() != null) {
				growthFactorForm.setType(therapy.getTreatment().getSexDistribution().getType());
			}
			growthFactorForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());
			growthFactorForm.setDosage(therapy.getTreatment().getDosage());
			growthFactorForm.setRegimen(therapy.getTreatment().getRegimen());
		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitGrowthFactors");
	}

	/**
	 * Populate the dropdown menus for submitSurgeryOther
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

		System.out.println("<GrowthFactorPopulateAction dropdown> ... ");

		// blank out the FORMDATA Constant field
		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, growthFactorForm);

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<GrowthFactorPopulateAction> exiting... ");

		return mapping.findForward("submitGrowthFactors");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<GrowthFactorPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdow2n fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GROWTHFACTORDOSEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GROWTHFACTORDROP, Constants.Dropdowns.ADD_BLANK);
	}

}
