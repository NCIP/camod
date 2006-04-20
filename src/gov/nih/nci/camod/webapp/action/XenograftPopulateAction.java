/**
 * 
 * $Id: XenograftPopulateAction.java,v 1.22 2006-04-20 18:11:16 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.21  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.20  2005/12/12 17:33:37  georgeda
 * Defect #265, store host/origin species in correct places
 *
 * Revision 1.19  2005/11/28 22:52:05  pandyas
 * Defect #186: Added organ/tissue to Xenograft page, modified search page to display multiple Xenografts with headers, modified XenograftManagerImpl so it does not create or save an organ object if not organ is selected
 *
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
import gov.nih.nci.camod.domain.Species;
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

public class XenograftPopulateAction extends BaseAction
{

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        // Create a form to edit
        XenograftForm xenograftForm = (XenograftForm) form;

        // Grab the current Xenograft we are working with related to this AM
        String aXenograftID = request.getParameter("aXenograftID");

        Xenograft xeno = XenograftManagerSingleton.instance().get(aXenograftID);

        if (xeno == null)
        {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        }
        else
        {
            request.setAttribute("aXenograftID", aXenograftID);
            xenograftForm.setXenograftName(xeno.getXenograftName());

            // Set the other flag or the selected administrative site from the DB
            if (xeno.getAdminSiteUnctrlVocab() != null)
            {
                xenograftForm.setAdministrativeSite(Constants.Dropdowns.OTHER_OPTION);
                xenograftForm.setOtherAdministrativeSite(xeno.getAdminSiteUnctrlVocab());
            }
            else
            {
                xenograftForm.setAdministrativeSite(xeno.getAdministrativeSite());
            }

            xenograftForm.setGeneticManipulation(xeno.getGeneticManipulation());
            xenograftForm.setModificationDescription(xeno.getModificationDescription());
            xenograftForm.setParentalCellLineName(xeno.getParentalCellLineName());
            xenograftForm.setAtccNumber(xeno.getAtccNumber());
            xenograftForm.setCellAmount(xeno.getCellAmount());
            xenograftForm.setGrowthPeriod(xeno.getGrowthPeriod());

            if (xeno.getDonorSpecies() != null)
            {
                log.info("Species: " + xeno.getDonorSpecies().getScientificName());
                xenograftForm.setDonorScientificName(xeno.getDonorSpecies().getScientificName());
            }
            else
            {
                xenograftForm.setDonorScientificName(null);
            }

            // strain is required as of caMod 2.1, not before
            if (xeno.getStrain() != null)
            {
                //If uncontrolledVocab is filled in 'Other' was selected, Set 'Other' explicitly
                if (xeno.getStrain().getNameUnctrlVocab() != null)
                {
                    xenograftForm.setDonorEthinicityStrain(Constants.Dropdowns.OTHER_OPTION);
                    xenograftForm.setOtherDonorEthinicityStrain(xeno.getStrain().getNameUnctrlVocab());

                }
                // If uncontrolledVocab is empty, just get strain field
                else
                {
                    xenograftForm.setDonorEthinicityStrain(xeno.getStrain().getName());
                }
            }

            // since we are always querying from concept code (save and edit),
            // simply display EVSPreferredDescription
            if (xeno.getOrgan() != null)
            {
                //SIMA TODO: get display name to work again
                //xenograftForm.setOrgan(xeno.getOrgan().getEVSPreferredDescription());
                xenograftForm.setOrgan(xeno.getOrgan().getName());
                xenograftForm.setOrganTissueCode(xeno.getOrgan().getConceptCode());
            }

            // Set the other flag or the normal graft type
            if (xeno.getGraftTypeUnctrlVocab() != null)
            {
                xenograftForm.setGraftType(Constants.Dropdowns.OTHER_OPTION);
                xenograftForm.setOtherGraftType(xeno.getGraftTypeUnctrlVocab());
            }
            else
            {
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
    public ActionForward dropdown(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        log.info("<XenograftPopulateAction dropdown> Entering dropdown() ");

        XenograftForm theForm = (XenograftForm) form;

        // setup dropdown menus
        this.dropdown(request, response, theForm);

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
    private void dropdown(HttpServletRequest request,
                          HttpServletResponse response,
                          XenograftForm inForm) throws Exception
    {

        log.info("<XenograftPopulateAction dropdown> Entering void dropdown()");

        // Retrieve the AnimalModel current Species and Strain set in ModelCharacteristics
        // This is displayed on the JSP page right above the HOST STRAIN / SPECIES
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);

        Species theAMSpecies = animalModel.getStrain().getSpecies();
        request.getSession().setAttribute(Constants.Dropdowns.MODELSPECIES, theAMSpecies.getScientificName());

        if (animalModel.getStrain() != null)
        {
            request.getSession().setAttribute(Constants.Dropdowns.MODELSTRAIN, animalModel.getStrain().getName());
        }
        else
        {
            request.getSession().setAttribute(Constants.Dropdowns.MODELSTRAIN, animalModel.getStrain().getNameUnctrlVocab());
        }

        // Set the Speceis drop drop for the Xenograft
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SPECIESQUERYDROP, Constants.Dropdowns.ADD_BLANK_OPTION);

        // theSpecies will be null the first time (submitXenograft screen) - default to full list of strains
        // but this is needed to populate the strain on the submitXenograft screen
        String theSpecies = inForm.getDonorScientificName();
        if (theSpecies == null)
        {
            List speciesList = (List) request.getSession().getAttribute(Constants.Dropdowns.SPECIESQUERYDROP);
            DropdownOption theOption = (DropdownOption) speciesList.get(0);
            theSpecies = theOption.getValue();
        }

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, theSpecies);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GRAFTTYPEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.XENOGRAFTADMINSITESDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);

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
    public ActionForward setStrainDropdown(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {

        XenograftForm xenograftForm = (XenograftForm) form;

        //request.setAttribute("aXenograftID", request.getParameter("aXenograftID"));

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, xenograftForm.getDonorScientificName());
        // Must Reset both fields when new species is chosen or edited
        xenograftForm.setDonorEthinicityStrain("");
        xenograftForm.setOtherDonorEthinicityStrain("");

        return mapping.findForward("submitTransplantXenograft");
    }

}
