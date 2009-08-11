/**
 * 
 * $Id: RadiationPopulateAction.java,v 1.15 2007-10-31 18:33:41 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2006/11/09 17:31:03  pandyas
 * Commented out debug code
 *
 * Revision 1.13  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.12  2005/12/21 15:45:33  georgeda
 * Defect #283 - fixed population of other admin route
 *
 * Revision 1.11  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.10  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.9  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.8  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.7  2005/10/20 20:39:44  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.RadiationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RadiationPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        log.debug("<RadiationPopulateAction populate> Entering populate() ");

		// Create a form to edit
		RadiationForm radiationForm = (RadiationForm) form;

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
				radiationForm.setName(Constants.Dropdowns.OTHER_OPTION);
				radiationForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				radiationForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative
			// route
			if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
				radiationForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
				radiationForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
			} else {
				radiationForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
			}

			if (ce.getTreatment().getSexDistribution() != null) {
				radiationForm.setType(ce.getTreatment().getSexDistribution().getType());
			}
			radiationForm.setDosage(ce.getTreatment().getDosage());
            radiationForm.setDosageUnit(ce.getTreatment().getDosageUnit());
			radiationForm.setRegimen(ce.getTreatment().getRegimen());
			radiationForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
            
            if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
                radiationForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
                radiationForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
            }
            
			radiationForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
            radiationForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit()); 
            
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	radiationForm.setComments(ce.getEnvironmentalFactor().getComments());
            }              

		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitRadiation");

	}

	/**
	 * Populate the dropdown menus for submitEnvironmentalFactors
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

		log.debug("<RadiationPopulateAction dropdown> Entering dropdown() ");

		// blank out the FORMDATA Constant field
		RadiationForm radiationForm = (RadiationForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, radiationForm);

		// setup dropdown menus
		this.dropdown(request, response);

		log.debug("<RadiationPopulateAction dropdown> before return submitRadiation ");

		return mapping.findForward("submitRadiation");

	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("<RadiationPopulateAction dropdown> Entering void dropdown()");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.RADIATIONDROP, Constants.Dropdowns.ADD_BLANK);

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.RADIATIONUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);

		log.debug("<RadiationPopulateAction dropdown> Exiting void dropdown()");

	}

}
