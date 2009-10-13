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
import gov.nih.nci.camod.webapp.form.AntibodyForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AntibodyPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form AntibodyForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.info("<AntibodyPopulateAction populate> Entered ");

        log.info("<AntibodyPopulateAction populate> Entered ");
        log.info("<AntibodyPopulateAction populate> Entered ");
        log.info("<AntibodyPopulateAction populate> Entered ");
        

		AntibodyForm antibodyForm = (AntibodyForm) form;

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
		        log.info("ce.getEnvironmentalFactor().getNameAlternEntry(): " + ce.getEnvironmentalFactor().getNameAlternEntry());				
				antibodyForm.setName(Constants.Dropdowns.OTHER_OPTION);
				antibodyForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				antibodyForm.setName(ce.getEnvironmentalFactor().getName());
		        log.info("ce.getEnvironmentalFactor().getName(): " + ce.getEnvironmentalFactor().getName());				
			}

			// Set the other administrative route and/or the selected
			// administrative route
			// Added for MTB models that do not have a treatment id
			if (ce.getTreatment() != null ) {
				if (ce.getTreatment().getAdminRouteAlternEntry() != null & ce.getTreatment().getAdminRouteAlternEntry().length() > 0) {
					log.info("ce.getTreatment().getAdminRouteAlternEntry(): " + ce.getTreatment().getAdminRouteAlternEntry());				
					antibodyForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
					antibodyForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
				} else {
					antibodyForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
					log.info("ce.getTreatment().getAdminRouteAlternEntry(): " + ce.getTreatment().getAdminRouteAlternEntry());				
				}
	
				if (ce.getTreatment().getSexDistribution() != null) {
					antibodyForm.setType(ce.getTreatment().getSexDistribution().getType());
				}
		    }
			
			if (ce.getTreatment() != null ) {
				antibodyForm.setDosage(ce.getTreatment().getDosage());
	            antibodyForm.setDosageUnit(ce.getTreatment().getDosageUnit());
				antibodyForm.setRegimen(ce.getTreatment().getRegimen());
				antibodyForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
	            antibodyForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
			}
            
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	antibodyForm.setComments(ce.getEnvironmentalFactor().getComments());
            }
            
		}
        log.debug("<AntibodyPopulateAction populate> Got fields ");
		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitAntibody");
	}

	/**
	 * Populate the dropdown menus for submitAntibody
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

        log.debug("<AntibodyPopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		AntibodyForm antibodyForm = (AntibodyForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, antibodyForm);

		// setup dropdown menus
		this.dropdown(request, response);

        log.debug("<AntibodyPopulateAction dropdown> exiting... ");

		return mapping.findForward("submitAntibody");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<AntibodyPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ANTIBODYDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ANTIBODYUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
