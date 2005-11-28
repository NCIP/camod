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

public class AnimalModelPopulateAction extends BaseAction {

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
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("Entering AnimalModelPopulateAction.populate");

        // Create a form to edit
        ModelCharacteristicsForm modelChar = (ModelCharacteristicsForm) form;

        // Use the current animalModel based on the ID stored in the session
        String modelID = "" + request.getSession().getAttribute(Constants.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);

        modelChar.setModelDescriptor(am.getModelDescriptor());

        if (am.getPrincipalInvestigator() != null) {
            modelChar.setPrincipalInvestigator(am.getPrincipalInvestigator().getUsername());
        } else {
            modelChar.setPrincipalInvestigator(null);
        }

        if (am.getIsToolMouse().booleanValue()) {
            modelChar.setIsToolMouse("yes");
        } else {
            modelChar.setIsToolMouse("no");
        }

        modelChar.setScientificName(am.getSpecies().getScientificName());
        modelChar.setEthinicityStrain(am.getSpecies().getEthnicityStrain());

        if (am.getSpecies().getEthnicityStrainUnctrlVocab() != null) {
            modelChar.setEthinicityStrain(Constants.Dropdowns.OTHER_OPTION);
            modelChar.setEthnicityStrainUnctrlVocab(am.getSpecies().getEthnicityStrainUnctrlVocab());
        }

        modelChar.setExperimentDesign(am.getExperimentDesign());
        if (am.getPhenotype().getSexDistribution() != null) {
            modelChar.setType(am.getPhenotype().getSexDistribution().getType());
        }
        modelChar.setBreedingNotes(am.getPhenotype().getBreedingNotes());
        modelChar.setDescription(am.getPhenotype().getDescription());
        modelChar.setUrl(am.getUrl());

        modelChar.setReleaseDate("after");

        // Try to format the date
        try {
            DateFormat theDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String theReleaseDate = theDateFormat.format(am.getAvailability().getReleaseDate());

            modelChar.setCalendarReleaseDate(theReleaseDate);
        } catch (Exception e) {
            log.error("Unable to format date from db.  Defaulting to 'immediate'", e);
            modelChar.setReleaseDate("immediate");
        }

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response, modelChar);

        log.trace("Exiting AnimalModelPopulateAction.populate");

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
    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("Entering AnimalModelPopulateAction.dropdown");

        ModelCharacteristicsForm theForm = (ModelCharacteristicsForm) form;

        this.dropdown(request, response, theForm);

        log.trace("Exiting AnimalModelPopulateAction.dropdown");

        return mapping.findForward("submitNewModel");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response, ModelCharacteristicsForm inForm)
            throws Exception {

        log.trace("Entering AnimalModelPopulateAction.dropdown");

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HOSTSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

        String theSpecies = inForm.getScientificName();
        if (theSpecies == null) {
            List speciesList = (List) request.getSession().getAttribute(Constants.Dropdowns.HOSTSPECIESDROP);
            DropdownOption theOption = (DropdownOption) speciesList.get(0);
            theSpecies = theOption.getValue();
        }

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, theSpecies);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

        log.trace("Exiting AnimalModelPopulateAction.dropdown");
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

        // XenograftForm xenograftForm = ( XenograftForm ) form;
        ModelCharacteristicsForm modelChar = (ModelCharacteristicsForm) form;

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, modelChar.getScientificName());
        modelChar.setEthinicityStrain("");
        String page = request.getParameter("page");

        if (page.equals("modelChar"))
            return mapping.findForward("submitModelCharacteristics");
        else
            return mapping.findForward("submitNewModel");
    }
}
