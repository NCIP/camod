package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ChangeAnimalModelStateAction extends BaseAction {

    /**
     * Change the state for the animal model
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering ChangeAnimalModelStateAction.execute");

        // The user didn't press the cancel button
        if (!isCancelled(inRequest)) {

            // Get the curation manager workflow XML
            CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext().getRealPath(
                    "/")
                    + "/config/CurationConfig.xml");

            // Get the animal model
            AnimalModelStateForm theForm = (AnimalModelStateForm) inForm;
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theForm.getModelId());

            // Did the id match?
            if (theAnimalModel != null) {
                
                log.debug("Current state of model: " + theAnimalModel.getState());
        
                theCurationManager.changeState(theAnimalModel, theForm.getEvent());
                theAnimalModelManager.save(theAnimalModel);
                
                log.debug("New state of model: " + theAnimalModel.getState());
                
                LogManager theLogManager = (LogManager) getBean("logManager");

                // Save the associated log comment to track the curation state
                theLogManager.save(theForm.getAssignedTo(), theForm.getModelId(), theAnimalModel.getState(), theForm
                        .getComment());
                
                // Do any association actions since we've sucessfully changed state
                theCurationManager.applyActionsForState(theAnimalModel);
            }
        }
        
        log.trace("Exiting ChangeAnimalModelStateAction.execute");
        
        return inMapping.findForward("next");
    }
}
