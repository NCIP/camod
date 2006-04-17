/**
 * 
 * $Id: SurgeryPopulateAction.java,v 1.9 2006-04-17 19:09:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.SurgeryForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SurgeryPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form SurgeryForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<SurgeryPopulateAction populate> ... ");

		SurgeryForm surgeryForm = (SurgeryForm) form;

        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        CarcinogenExposure ce = carcinogenExposureManager.get(aCarcinogenExposureID);      


		// Handle back-arrow on the delete
		if (ce == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {

            request.setAttribute("aCarcinogenExposureID", aCarcinogenExposureID);


			// Set the otherName and/or the selected name attribute
			if (ce.getEnvironmentalFactor().getNameUnctrlVocab() != null) {
				surgeryForm.setName(Constants.Dropdowns.OTHER_OPTION);
				surgeryForm.setOtherName(ce.getEnvironmentalFactor().getNameUnctrlVocab());
			} else {
				surgeryForm.setName(ce.getEnvironmentalFactor().getName());
			}

			if (ce.getTreatment().getSexDistribution() != null) {
				surgeryForm.setType(ce.getTreatment().getSexDistribution().getType());
			}
			surgeryForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
            surgeryForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());            
			surgeryForm.setRegimen(ce.getTreatment().getRegimen());

		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);
		
		return mapping.findForward("submitSurgeryOther");
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

		System.out.println("<SurgeryPopulateAction dropdown> ... ");

		// blank out the FORMDATA Constant field
		SurgeryForm surgeryForm = (SurgeryForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, surgeryForm);

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<SurgeryPopulateAction> exiting... ");

		return mapping.findForward("submitSurgeryOther");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<SurgeryPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SURGERYDROP, Constants.Dropdowns.ADD_BLANK);
	}
}
