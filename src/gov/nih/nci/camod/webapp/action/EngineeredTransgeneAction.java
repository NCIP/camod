/**
 * 
 * $Id: EngineeredTransgeneAction.java,v 1.13 2007-04-04 13:19:07 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * EngineeredTransgeneAction Class
 */
public final class EngineeredTransgeneAction extends BaseAction {

    /**
     * Edit
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        log.trace("Entering edit");

        // Create a form to edit
        EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;

        // Grab the current modelID from the session
        String aEngineeredTransgeneID = engineeredTransgeneForm.getTransgeneId();

        log.info("<EngineeredTransgeneAction save> following Characteristics:"

                 + "\n\t getIsRandom: " + engineeredTransgeneForm.getIsRandom()
                 + "\n\t getLocationOfIntegration: " + engineeredTransgeneForm.getLocationOfIntegration()
                 + "\n\t getName: " + engineeredTransgeneForm.getName() + "\n\t getScientificName: "
                + engineeredTransgeneForm.getScientificName() + "\n\t getOtherScientificName: "
                + engineeredTransgeneForm.getOtherScientificName()

                + "\n\t getTranscriptional1_name: " + engineeredTransgeneForm.getTranscriptional1_name()
                + "\n\t getTranscriptional1_species: " + engineeredTransgeneForm.getTranscriptional1_species()
                + "\n\t getTranscriptional1_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional1_otherSpecies()

                + "\n\t getTranscriptional2_name: " + engineeredTransgeneForm.getTranscriptional2_name()
                + "\n\t getTranscriptional2_species: " + engineeredTransgeneForm.getTranscriptional2_species()
                + "\n\t getTranscriptional2_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional2_otherSpecies()

                + "\n\t getTranscriptional3_name: " + engineeredTransgeneForm.getTranscriptional3_name()
                + "\n\t getTranscriptional3_species: " + engineeredTransgeneForm.getTranscriptional3_species()
                + "\n\t getTranscriptional3_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional3_otherSpecies()

                + "\n\t getPolyASignal_name: " + engineeredTransgeneForm.getPolyASignal_name()
                + "\n\t getPolyASignal_species: " + engineeredTransgeneForm.getPolyASignal_species()
                + "\n\t getPolyASignal_otherSpecies: " + engineeredTransgeneForm.getPolyASignal_otherSpecies()

                + "\n\t getSpliceSites_name: " + engineeredTransgeneForm.getSpliceSites_name()
                + "\n\t getSpliceSites_species: " + engineeredTransgeneForm.getSpliceSites_species()
                + "\n\t getSpliceSites_otherSpecies: " + engineeredTransgeneForm.getSpliceSites_otherSpecies()

                + "\n\t getGeneFunctions: " + engineeredTransgeneForm.getGeneFunctions() 
                + "\n\t getMgiId: " + engineeredTransgeneForm.getMgiId()
                + "\n\t getZfinId: " + engineeredTransgeneForm.getRgdId()
                + "\n\t getRgdId: " + engineeredTransgeneForm.getMgiId()                
                + "\n\t getFunction: " + engineeredTransgeneForm.getFunction()
                + "\n\t getConditionedBy: " + engineeredTransgeneForm.getConditionedBy() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription() + "\n\t getComments: "
                + engineeredTransgeneForm.getComments() + "\n\t getFileServerLocation: "
                + engineeredTransgeneForm.getFileServerLocation() + "\n\t getTitle: "
                + engineeredTransgeneForm.getTitle() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription()

                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {

            EngineeredTransgeneManager engineeredTransgeneManager = (EngineeredTransgeneManager) getBean("engineeredTransgeneManager");

            if ("Delete".equals(theAction)) {

                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

                engineeredTransgeneManager.remove(aEngineeredTransgeneID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("engineeredtransgene.delete.successful"));
                saveErrors(request, msg);

            } else {

                log.info("EngineeredTransgene edit");
                // retrieve model and update w/ new values

                Transgene theEngineeredTransgene = engineeredTransgeneManager.get(aEngineeredTransgeneID);

                engineeredTransgeneManager.update(engineeredTransgeneForm, theEngineeredTransgene, request);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("engineeredtransgene.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred editing an EngineeredTransgene", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
        } catch (Exception e) {
            log.error("Exception ocurred editing EngineeredTransgene", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
        return mapping.findForward(theForward);
    }

    /**
     * Save
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("Entering save");

        // Create a form to edit
        EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<EngineeredTransgeneAction save> following Characteristics:"
                + "\n\t getIsRandom: " + engineeredTransgeneForm.getIsRandom()
                + "\n\t getLocationOfIntegration: " + engineeredTransgeneForm.getLocationOfIntegration()
                + "\n\t getName: " + engineeredTransgeneForm.getName() + "\n\t getScientificName: "
                + engineeredTransgeneForm.getScientificName() + "\n\t getOtherScientificName: "
                + engineeredTransgeneForm.getOtherScientificName()

                + "\n\t getTranscriptional1_name: " + engineeredTransgeneForm.getTranscriptional1_name()
                + "\n\t getTranscriptional1_species: " + engineeredTransgeneForm.getTranscriptional1_species()
                + "\n\t getTranscriptional1_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional1_otherSpecies()

                + "\n\t getTranscriptional2_name: " + engineeredTransgeneForm.getTranscriptional2_name()
                + "\n\t getTranscriptional2_species: " + engineeredTransgeneForm.getTranscriptional2_species()
                + "\n\t getTranscriptional2_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional2_otherSpecies()

                + "\n\t getTranscriptional3_name: " + engineeredTransgeneForm.getTranscriptional3_name()
                + "\n\t getTranscriptional3_species: " + engineeredTransgeneForm.getTranscriptional3_species()
                + "\n\t getTranscriptional3_otherSpecies: "
                + engineeredTransgeneForm.getTranscriptional3_otherSpecies()

                + "\n\t getPolyASignal_name: " + engineeredTransgeneForm.getPolyASignal_name()
                + "\n\t getPolyASignal_species: " + engineeredTransgeneForm.getPolyASignal_species()
                + "\n\t getPolyASignal_otherSpecies: " + engineeredTransgeneForm.getPolyASignal_otherSpecies()

                + "\n\t getSpliceSites_name: " + engineeredTransgeneForm.getSpliceSites_name()
                + "\n\t getSpliceSites_species: " + engineeredTransgeneForm.getSpliceSites_species()
                + "\n\t getSpliceSites_otherSpecies: " + engineeredTransgeneForm.getSpliceSites_otherSpecies()

                + "\n\t getGeneFunctions: " + engineeredTransgeneForm.getGeneFunctions() 
                + "\n\t getMgiId: " + engineeredTransgeneForm.getMgiId()
                + "\n\t getZfinId: " + engineeredTransgeneForm.getZfinId() 
                + "\n\t getRgdId: " + engineeredTransgeneForm.getRgdId() 
                + "\n\t getFunction: " + engineeredTransgeneForm.getFunction()
                + "\n\t getConditionedBy: " + engineeredTransgeneForm.getConditionedBy() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription() + "\n\t getComments: "
                + engineeredTransgeneForm.getComments() + "\n\t getFileServerLocation: "
                + engineeredTransgeneForm.getFileServerLocation() + "\n\t getTitle: "
                + engineeredTransgeneForm.getTitle() + "\n\t getDescription: "
                + engineeredTransgeneForm.getDescription()

                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, engineeredTransgeneForm, request);

            log.info("New EngineeredTransgene created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("engineeredtransgene.creation.successful"));
            saveErrors(request, msg);

        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred saving an EngineeredTransgene", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
        } catch (Exception e) {
            log.error("Exception ocurred creating EngineeredTransgene", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward(theForward);
    }
}