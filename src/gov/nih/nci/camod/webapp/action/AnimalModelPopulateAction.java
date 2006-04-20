/**
 * 
 * $Id: AnimalModelPopulateAction.java,v 1.18 2006-04-20 18:11:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import gov.nih.nci.camod.webapp.util.DropdownOption;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AnimalModelPopulateAction extends BaseAction
{

    /**
     * Pre-populate all field values in the form ModelCharacteristicsForm
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward populate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        log.info("Entering AnimalModelPopulateAction.populate");

        // Create a form to edit
        ModelCharacteristicsForm modelCharForm = (ModelCharacteristicsForm) form;

        // Use the current animalModel based on the ID stored in the session
        String modelID = "" + request.getSession().getAttribute(Constants.MODELID);

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);

        modelCharForm.setModelDescriptor(am.getModelDescriptor());

        if (am.getPrincipalInvestigator() != null)
        {
            modelCharForm.setPrincipalInvestigator(am.getPrincipalInvestigator().getUsername());
        }
        else
        {
            modelCharForm.setPrincipalInvestigator(null);
        }

        if (am.getIsToolMouse().booleanValue())
        {
            modelCharForm.setIsToolMouse("yes");
        }
        else
        {
            modelCharForm.setIsToolMouse("no");
        }

        // Set to null as default to model the code for PI - follow same reason
        if (am.getStrain().getSpecies().getScientificName() != null)
        {
            log.info("<populate method> Species is: " + am.getStrain().getSpecies().getScientificName());
            modelCharForm.setScientificName(am.getStrain().getSpecies().getScientificName());
        }
        else
        {
            modelCharForm.setScientificName(null);
        }

        //If uncontrolledVocab is filled in 'Other' was selected, Set 'Other' explicitly
        if (am.getStrain().getNameUnctrlVocab() != null)
        {
            modelCharForm.setEthinicityStrain(Constants.Dropdowns.OTHER_OPTION);
            modelCharForm.setOtherEthnicityStrain(am.getStrain().getNameUnctrlVocab());
             
        }
        // If uncontrolledVocab is empty, just get strain field
        else
        {
            modelCharForm.setEthinicityStrain(am.getStrain().getName());
        }

        modelCharForm.setExperimentDesign(am.getExperimentDesign());
        if (am.getPhenotype().getSexDistribution() != null)
        {
            modelCharForm.setType(am.getPhenotype().getSexDistribution().getType());
        }
        modelCharForm.setBreedingNotes(am.getPhenotype().getBreedingNotes());
        modelCharForm.setDescription(am.getPhenotype().getDescription());
        modelCharForm.setUrl(am.getUrl());

        modelCharForm.setReleaseDate("after");

        // Try to format the date
        try
        {
            DateFormat theDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String theReleaseDate = theDateFormat.format(am.getAvailability().getReleaseDate());

            modelCharForm.setCalendarReleaseDate(theReleaseDate);
        }
        catch (Exception e)
        {
            log.error("Unable to format date from db.  Defaulting to 'immediate'", e);
            modelCharForm.setReleaseDate("immediate");
        }

        // Prepopulate all dropdown fields, set the global Constants 
        this.dropdown(request, response, modelCharForm);

        log.info("Exiting AnimalModelPopulateAction.populate");

        return mapping.findForward("submitModelCharacteristics");

    }

    /**
     * Populate the dropdown menus for createNewModel
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

        log.info("Entering AnimalModelPopulateAction.dropdown");

        ModelCharacteristicsForm theForm = (ModelCharacteristicsForm) form;

        // setup dropdown menus
        this.dropdown(request, response, theForm);

        log.info("Exiting AnimalModelPopulateAction.dropdown");

        return mapping.findForward("submitNewModel");
    }


    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelCharacteristicsForm inForm) throws Exception
    {

        log.info("Entering AnimalModelPopulateAction.dropdown");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SPECIESQUERYDROP, Constants.Dropdowns.ADD_BLANK_OPTION);

        // theSpecies will be null the first time (submitNewModel screen) - default to full list
        // but this is needed to populate the strain on the submitModelCharacteristics screen
        String theSpecies = inForm.getScientificName();
        if (theSpecies == null)
        {
            List speciesList = (List) request.getSession().getAttribute(Constants.Dropdowns.SPECIESQUERYDROP);
            DropdownOption theOption = (DropdownOption) speciesList.get(0);
            theSpecies = theOption.getValue();
        }

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, theSpecies);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORDROP, Constants.Dropdowns.ADD_BLANK_OPTION);

        log.info("Exiting AnimalModelPopulateAction.dropdown");
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

        ModelCharacteristicsForm modelChar = (ModelCharacteristicsForm) form;

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, modelChar.getScientificName());
        // Must Reset both fields when new species is chosen
        modelChar.setEthinicityStrain("");
        modelChar.setOtherEthnicityStrain("");
        String page = request.getParameter("page");

        if (page.equals("modelChar"))
            return mapping.findForward("submitModelCharacteristics");
        else
            return mapping.findForward("submitNewModel");
    }
}
