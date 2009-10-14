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
import gov.nih.nci.camod.webapp.form.PlasmidForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PlasmidPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form PlasmidForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.debug("<PlasmidPopulateAction populate> Entered ");

		PlasmidForm plasmidForm = (PlasmidForm) form;

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
				plasmidForm.setName(Constants.Dropdowns.OTHER_OPTION);
				plasmidForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				plasmidForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative route
			// Added for MTB models that do not have a treatment id
			if (ce.getTreatment() != null ) {			
				if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
					plasmidForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
					plasmidForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
				} else {
					plasmidForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
				}
	
				if (ce.getTreatment().getSexDistribution() != null) {
					plasmidForm.setType(ce.getTreatment().getSexDistribution().getType());
				}
				plasmidForm.setDosage(ce.getTreatment().getDosage());
	            plasmidForm.setDosageUnit(ce.getTreatment().getDosageUnit());
				plasmidForm.setRegimen(ce.getTreatment().getRegimen());
				plasmidForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
	            plasmidForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
			}
			
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	plasmidForm.setComments(ce.getEnvironmentalFactor().getComments());
            }
            
		}
        log.debug("<PlasmidPopulateAction populate> Got fields ");
		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitPlasmid");
	}

	/**
	 * Populate the dropdown menus for submitPlasmid
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

        log.debug("<PlasmidPopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		PlasmidForm plasmidForm = (PlasmidForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, plasmidForm);

		// setup dropdown menus
		this.dropdown(request, response);

        log.debug("<PlasmidPopulateAction dropdown> exiting... ");

		return mapping.findForward("submitPlasmid");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<PlasmidPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PLASMIDDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PLASMIDUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
