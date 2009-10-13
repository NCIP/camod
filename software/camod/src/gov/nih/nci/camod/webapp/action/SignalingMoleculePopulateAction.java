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
import gov.nih.nci.camod.webapp.form.SignalingMoleculeForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SignalingMoleculePopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form SignalingMoleculeForm Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.debug("<SignalingMoleculePopulateAction populate> Entered ");

		SignalingMoleculeForm signalingMoleculeForm = (SignalingMoleculeForm) form;

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
				signalingMoleculeForm.setName(Constants.Dropdowns.OTHER_OPTION);
				signalingMoleculeForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				signalingMoleculeForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative route
			if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
				signalingMoleculeForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
				signalingMoleculeForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
			} else {
				signalingMoleculeForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
			}

			if (ce.getTreatment().getSexDistribution() != null) {
				signalingMoleculeForm.setType(ce.getTreatment().getSexDistribution().getType());
			}
			signalingMoleculeForm.setDosage(ce.getTreatment().getDosage());
            signalingMoleculeForm.setDosageUnit(ce.getTreatment().getDosageUnit());
			signalingMoleculeForm.setRegimen(ce.getTreatment().getRegimen());
			signalingMoleculeForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
            signalingMoleculeForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
            
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	signalingMoleculeForm.setComments(ce.getEnvironmentalFactor().getComments());
            }
            
		}
        log.debug("<SignalingMoleculePopulateAction populate> Got fields ");
		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitSignalingMolecule");
	}

	/**
	 * Populate the dropdown menus for submitSignalingMolecule
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

        log.debug("<SignalingMoleculePopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		SignalingMoleculeForm signalingMoleculeForm = (SignalingMoleculeForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, signalingMoleculeForm);

		// setup dropdown menus
		this.dropdown(request, response);

        log.debug("<SignalingMoleculePopulateAction dropdown> exiting... ");

		return mapping.findForward("submitSignalingMolecule");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<SignalingMoleculePopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SIGNALINGMOLECULEDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SIGNALINGMOLECULEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
