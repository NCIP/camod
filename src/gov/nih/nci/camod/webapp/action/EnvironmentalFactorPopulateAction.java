/**
 * 
 * $Id: EnvironmentalFactorPopulateAction.java,v 1.13 2005-11-02 21:48:09 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
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
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
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

        System.out.println("<EnvironmentalFactorPopulateAction populate> Entering populate() ");

        // Create a form to edit
        EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

        // Grab the current Therapy we are working with related to this
        // animalModel
        String aTherapyID = request.getParameter("aTherapyID");

        TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");
        Therapy therapy = therapyManager.get(aTherapyID);

        // Handle back-arrow on the delete
        if (therapy == null) {
            request.setAttribute("aTherapyID", null);
        } else {

            request.setAttribute("aTherapyID", aTherapyID);

            // Set the otherName and/or the selected name attribute
            if (therapy.getAgent().getNameUnctrlVocab() != null) {
                envForm.setName(Constants.Dropdowns.OTHER_OPTION);
                envForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
            } else {
                envForm.setName(therapy.getAgent().getName());
            }

            // Set the other administrative route and/or the selected
            // administrative
            // route
            if (therapy.getTreatment().getAdminRouteUnctrlVocab() != null) {
                envForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
                envForm.setOtherAdministrativeRoute(therapy.getTreatment().getAdminRouteUnctrlVocab());
            } else {
                envForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
            }

            if (therapy.getTreatment().getSexDistribution() != null) {
                envForm.setType(therapy.getTreatment().getSexDistribution().getType());
            }
            envForm.setDosage(therapy.getTreatment().getDosage());
            envForm.setRegimen(therapy.getTreatment().getRegimen());
            envForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());

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

        System.out.println("<EnvironmentalFactorPopulateAction dropdown> Entering void dropdown()");

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
