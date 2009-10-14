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
import gov.nih.nci.camod.webapp.form.BacteriaForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BacteriaPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form BacteriaForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.info("<BacteriaPopulateAction populate> Entered ");

		BacteriaForm bacteriaForm = (BacteriaForm) form;

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
				bacteriaForm.setName(Constants.Dropdowns.OTHER_OPTION);
				bacteriaForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				bacteriaForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative route
			// Added for MTB models that do not have a treatment id
			if (ce.getTreatment() != null ) {			
				if (ce.getTreatment().getAdminRouteAlternEntry() != null & ce.getTreatment().getAdminRouteAlternEntry().length() > 0) {
					log.info("ce.getTreatment().getAdminRouteAlternEntry: "  + ce.getTreatment().getAdminRouteAlternEntry().toString());
					bacteriaForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
					bacteriaForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
				} else {
					bacteriaForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
				}
	
				if (ce.getTreatment().getSexDistribution() != null) {
					bacteriaForm.setType(ce.getTreatment().getSexDistribution().getType());
				}
				bacteriaForm.setDosage(ce.getTreatment().getDosage());
	            bacteriaForm.setDosageUnit(ce.getTreatment().getDosageUnit());
				bacteriaForm.setRegimen(ce.getTreatment().getRegimen());
				bacteriaForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
	            bacteriaForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
			}
			
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	bacteriaForm.setComments(ce.getEnvironmentalFactor().getComments());
            }
            
		}
        log.info("<BacteriaPopulateAction populate> Got fields ");
		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitBacteria");
	}

	/**
	 * Populate the dropdown menus for submitBacteria
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

        log.debug("<BacteriaPopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		BacteriaForm bacteriaForm = (BacteriaForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, bacteriaForm);

		// setup dropdown menus
		this.dropdown(request, response);

        log.debug("<BacteriaPopulateAction dropdown> exiting... ");

		return mapping.findForward("submitBacteria");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<BacteriaPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.BACTERIADROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.BACTERIAUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
