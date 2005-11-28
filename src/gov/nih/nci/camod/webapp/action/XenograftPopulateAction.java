/**
 * 
 * $Id: XenograftPopulateAction.java,v 1.19 2005-11-28 22:52:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.18  2005/11/28 13:51:20  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.17  2005/11/18 22:50:02  georgeda
 * Defect #184.  Cleanup editing of old models
 *
 * Revision 1.16  2005/11/14 14:22:37  georgeda
 * Cleanup
 *
 * Revision 1.15  2005/11/11 16:28:26  pandyas
 * expanded species list
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.impl.XenograftManagerSingleton;
import gov.nih.nci.camod.webapp.form.XenograftForm;
import gov.nih.nci.camod.webapp.util.DropdownOption;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class XenograftPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // Create a form to edit
        XenograftForm xenograftForm = (XenograftForm) form;

        // Grab the current Xenograft we are working with related to this
        // animalModel
        String aXenograftID = request.getParameter("aXenograftID");

        Xenograft xeno = XenograftManagerSingleton.instance().get(aXenograftID);

        if (xeno == null) {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {
            request.setAttribute("aXenograftID", aXenograftID);
            xenograftForm.setName(xeno.getName());

            // Set the other administrative site and/or the selected admin site
            // We need to comapre the stored value with the config file because
            // we are
            // storing 'Other' AdministrativeSite directly into the database
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.XENOGRAFTADMINSITESDROP, "");
            List xenoAdminSiteDropList = (List) request.getSession().getAttribute(
                    Constants.Dropdowns.XENOGRAFTADMINSITESDROP);

            // If AdministrativeSite is from list set the value
            if (xenoAdminSiteDropList.contains(xeno.getAdministrativeSite())) {
                xenograftForm.setAdministrativeSite(xeno.getAdministrativeSite());
            } else if (xeno.getAdministrativeSite() != null) {

                // If AdministrativeSite is not from list set the "other" and
                // enable the text field
                xenograftForm.setAdministrativeSite(Constants.Dropdowns.OTHER_OPTION);
                xenograftForm.setOtherAdministrativeSite(xeno.getAdministrativeSite());
            }

            xenograftForm.setGeneticManipulation(xeno.getGeneticManipulation());
            xenograftForm.setModificationDescription(xeno.getModificationDescription());
            xenograftForm.setParentalCellLineName(xeno.getParentalCellLineName());
            xenograftForm.setATCCNumber(xeno.getAtccNumber());
            xenograftForm.setCellAmount(xeno.getCellAmount());

            Taxon tax = xeno.getHostSpecies();
            if (tax != null) {
                xenograftForm.setHostScientificName(tax.getScientificName());

                if (tax.getEthnicityStrainUnctrlVocab() != null) {
                    xenograftForm.setHostEthinicityStrain(Constants.Dropdowns.OTHER_OPTION);
                    xenograftForm.setOtherHostEthinicityStrain(tax.getEthnicityStrainUnctrlVocab());
                } else {
                    xenograftForm.setHostEthinicityStrain(tax.getEthnicityStrain());
                }
            }
            
            // since we are always querying from concept code (save and edit),
            // simply display EVSPreferredDescription
            if (xeno.getOrgan() != null) {            
            xenograftForm.setOrgan(xeno.getOrgan().getEVSPreferredDescription());
            System.out.println("setOrgan= " + xeno.getOrgan().getEVSPreferredDescription());

            xenograftForm.setOrganTissueCode(xeno.getOrgan().getConceptCode());
            System.out.println("OrganTissueCode= " + xeno.getOrgan().getConceptCode());
            }

            // Set the other flag or the normal graft type
            if (xeno.getGraftTypeUnctrlVocab() != null) {
                xenograftForm.setGraftType(Constants.Dropdowns.OTHER_OPTION);
                xenograftForm.setOtherGraftType(xeno.getGraftTypeUnctrlVocab());
            } else {
                xenograftForm.setGraftType(xeno.getGraftType());
            }

        }

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response, xenograftForm);

        return mapping.findForward("submitTransplantXenograft");

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

        log.info("<XenograftPopulateAction dropdown> Entering dropdown() ");

        // setup dropdown menus
        this.dropdown(request, response, (XenograftForm) form);

        log.info("<XenograftPopulateAction dropdown> before return submitTransplantXenograft ");

        return mapping.findForward("submitTransplantXenograft");

    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    private void dropdown(HttpServletRequest request, HttpServletResponse response, XenograftForm form)
            throws Exception {

        log.info("<XenograftPopulateAction dropdown> Entering void dropdown()");

        // Retrieve the AnimalModel current Species and Strain set in
        // ModelCharacteristics
        // This is displayed on the JSP page right above the HOST STRAIN /
        // SPECIES
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);

        Taxon theTaxon = animalModel.getSpecies();
        request.getSession().setAttribute(Constants.Dropdowns.MODELSPECIES, theTaxon.getScientificName());

        if (theTaxon.getEthnicityStrain() != null) {
            request.getSession().setAttribute(Constants.Dropdowns.MODELSTRAIN, theTaxon.getEthnicityStrain());
        } else {
            request.getSession()
                    .setAttribute(Constants.Dropdowns.MODELSTRAIN, theTaxon.getEthnicityStrainUnctrlVocab());
        }

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HOSTSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

        String theSpecies = form.getHostScientificName();
        if (theSpecies == null) {
            List speciesList = (List) request.getSession().getAttribute(Constants.Dropdowns.HOSTSPECIESDROP);
            DropdownOption theOption = (DropdownOption) speciesList.get(0);
            theSpecies = theOption.getValue();
        }

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, theSpecies);

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GRAFTTYPEDROP,
                Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.XENOGRAFTADMINSITESDROP,
                Constants.Dropdowns.ADD_BLANK_AND_OTHER);

        log.info("<XenograftPopulateAction dropdown> Exiting void dropdown()");
    }

    /**
     * Repopulate the Strain dropdown with it's matching species
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward setStrainDropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        XenograftForm xenograftForm = (XenograftForm) form;

        request.setAttribute("aXenograftID", request.getParameter("aXenograftID"));
        NewDropdownUtil
                .populateDropdown(request, Constants.Dropdowns.STRAINDROP, xenograftForm.getHostScientificName());

        return mapping.findForward("submitTransplantXenograft");
    }

}
