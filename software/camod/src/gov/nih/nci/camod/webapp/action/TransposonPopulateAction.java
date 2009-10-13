/**
 * 
 * $Id:$
 * 
 * $Log:$
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.TransposonForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TransposonPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form TransposonForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.debug("<TransposonPopulateAction populate> Entered ");

		TransposonForm transposonForm = (TransposonForm) form;

        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");
		
        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        CarcinogenExposure ce = carcinogenExposureManager.get(aCarcinogenExposureID);      

		// Handle back-arrow on the delete
		if (ce == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {

            request.setAttribute("aCarcinogenExposureID", aCarcinogenExposureID);

			// Set the otherName and/or the selected name attribute
			if (ce.getEnvironmentalFactor().getNameAlternEntry() != null) {
				transposonForm.setName(Constants.Dropdowns.OTHER_OPTION);
				transposonForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				transposonForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative route
			if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
				transposonForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
				transposonForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
			} else {
				transposonForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
			}

			if (ce.getTreatment().getSexDistribution() != null) {
				transposonForm.setType(ce.getTreatment().getSexDistribution().getType());
			}
			transposonForm.setDosage(ce.getTreatment().getDosage());
            transposonForm.setDosageUnit(ce.getTreatment().getDosageUnit());
			transposonForm.setRegimen(ce.getTreatment().getRegimen());
			transposonForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
            transposonForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
            
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	transposonForm.setComments(ce.getEnvironmentalFactor().getComments());
            }
            
		}
        log.debug("<TransposonPopulateAction populate> Got fields ");
		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitTransposon");
	}

	/**
	 * Populate the dropdown menus for submitTransposon
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

        log.debug("<TransposonPopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		TransposonForm transposonForm = (TransposonForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, transposonForm);

		// setup dropdown menus
		this.dropdown(request, response);

        log.debug("<TransposonPopulateAction dropdown> exiting... ");

		return mapping.findForward("submitTransposon");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<TransposonPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.TRANSPOSONDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.TRANSPOSONUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
