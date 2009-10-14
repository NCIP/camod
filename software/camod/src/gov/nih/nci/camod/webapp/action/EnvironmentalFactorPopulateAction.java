/**
 * 
 * $Id: EnvironmentalFactorPopulateAction.java,v 1.17 2008-08-14 16:50:48 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2007/10/31 18:10:07  pandyas
 * Fixed #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.15  2006/04/17 19:09:41  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.14  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.13  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.12  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.11  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.10  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.9  2005/10/20 20:37:53  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class EnvironmentalFactorPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form EnvironmentalFactorForm Used by
     * submitEnvironmentalFactors
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("<EnvironmentalFactorPopulateAction populate> Entering populate() ");

        // Create a form to edit
        EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

        // Grab the current Therapy we are working with related to this
        // animalModel
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
                envForm.setName(Constants.Dropdowns.OTHER_OPTION);
                envForm.setOtherName(ce.getEnvironmentalFactor().getNameAlternEntry());
            } else {
                envForm.setName(ce.getEnvironmentalFactor().getName());
            }

            // Set the other administrative route and/or the selected
            // administrative route
			// Added for MTB models that do not have a treatment id
			if (ce.getTreatment() != null ) {            
	            if (ce.getTreatment().getAdminRouteAlternEntry() != null) {
	                envForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
	                envForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteAlternEntry());
	            } else {
	                envForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
	            }
	
	            if (ce.getTreatment().getSexDistribution() != null) {
	                envForm.setType(ce.getTreatment().getSexDistribution().getType());
	            }
	            envForm.setDosage(ce.getTreatment().getDosage());
	            envForm.setDosageUnit(ce.getTreatment().getDosageUnit());
	            envForm.setRegimen(ce.getTreatment().getRegimen());
	            envForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
	            envForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit()); 
			}
			
            if (ce.getEnvironmentalFactor().getComments() != null)
            {
            	envForm.setComments(ce.getEnvironmentalFactor().getComments());
            }             

        }

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response);

        return mapping.findForward("submitEnvironmentalFactors");

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

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitEnvironmentalFactors");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ENVIRONFACTORDROP, Constants.Dropdowns.ADD_BLANK);

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ENVFACTORUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
                Constants.Dropdowns.ADD_BLANK);

    }
}
