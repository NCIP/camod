/**
 * 
 * $Id: HormonePopulateAction.java,v 1.14 2007-10-31 18:12:17 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2006/11/09 17:28:17  pandyas
 * Commented out debug code
 *
 * Revision 1.12  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
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
 * Revision 1.7  2005/10/20 20:38:41  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
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

        log.debug("<HormonePopulateAction populate> Entered ");

		HormoneForm hormoneForm = (HormoneForm) form;

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
				hormoneForm.setName(Constants.Dropdowns.OTHER_OPTION);
				hormoneForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
			} else {
				hormoneForm.setName(ce.getEnvironmentalFactor().getName());
			}

			// Set the other administrative route and/or the selected
			// administrative route
			// Added for MTB models that do not have a treatment id
			if (ce.getTreatment() != null ) {			
				if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
					hormoneForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
					hormoneForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
				} else {
					hormoneForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
				}
	
				if (ce.getTreatment().getSexDistribution() != null) {
					hormoneForm.setType(ce.getTreatment().getSexDistribution().getType());
				}
				hormoneForm.setDosage(ce.getTreatment().getDosage());
	            hormoneForm.setDosageUnit(ce.getTreatment().getDosageUnit());
				hormoneForm.setRegimen(ce.getTreatment().getRegimen());
				hormoneForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
	            hormoneForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());
			}
			
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	hormoneForm.setComments(ce.getEnvironmentalFactor().getComments());
            }
            
		}
        log.debug("<HormonePopulateAction populate> Got fields ");
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

        log.debug("<HormonePopulateAction dropdown> entering... ");

		// blank out the FORMDATA Constant field
		HormoneForm hormoneForm = (HormoneForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, hormoneForm);

		// setup dropdown menus
		this.dropdown(request, response);

        log.debug("<HormonePopulateAction dropdown> exiting... ");

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

        log.debug("<HormonePopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the following

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HORMONEDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HORMONEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
	}
}
