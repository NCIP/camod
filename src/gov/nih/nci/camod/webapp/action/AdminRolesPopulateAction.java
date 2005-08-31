package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AdminRolesPopulateAction extends BaseAction {

    /**
     * Populate the various animal model beans
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");

        // Add all the models by state
        List theList = theAnimalModelManager.getAllByState("Edited-need more info");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_MORE_INFO, theList);
        }

        theList = theAnimalModelManager.getAllByState("Editor-assigned");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_EDITING, theList);
        }

        theList = theAnimalModelManager.getAllByState("Screened-approved");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_EDITOR_ASSIGNMENT, theList);
        }

        theList = theAnimalModelManager.getAllByState("Screener-assigned");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_SCREENING, theList);
        }

        theList = theAnimalModelManager.getAllByState("Complete-not screened");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_SCREENER_ASSIGNMENT, theList);
        }

        // TODO: This isn't correct
        theList = null; // theAnimalModelManager.getAllByState("Incomplete");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.COMMENTS_NEEDING_REVIEW, theList);
        }

        return inMapping.findForward("next");
    }
}
