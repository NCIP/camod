package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.LogManager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AdminRolesPopulateAction extends BaseAction {

    /**
     * Action used to populate the various admin lists for the curation process
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.info("Entering AdminRolesPopulateAction.execute");

        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");

        // Get the list of roles for the current user
        List theRoles = (List) inRequest.getSession().getAttribute(Constants.CURRENTUSERROLES);
        String theUsername = (String) inRequest.getSession().getAttribute(Constants.CURRENTUSER);

        List theList = null;

        // Editor specific curation states
        if (theRoles.contains(Constants.Admin.Roles.EDITOR)) {

            // Add all the models by state for a user
            theList = theAnimalModelManager.getAllByState("Edited-need more info");
            if (theList != null && !theList.isEmpty()) {

                List theReturnList = getModelsForUser(theUsername, theList);
                inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_MORE_INFO, theReturnList);
            }

            theList = theAnimalModelManager.getAllByState("Editor-assigned");
            if (theList != null && !theList.isEmpty()) {
                List theReturnList = getModelsForUser(theUsername, theList);
                inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_EDITING, theReturnList);
            }
        }

        // Controller specific curation states
        if (theRoles.contains(Constants.Admin.Roles.CONTROLLER)) {

            // Add all the models by state for a user
            theList = theAnimalModelManager.getAllByState("Screened-approved");
            if (theList != null && !theList.isEmpty()) {
                List theReturnList = getModelsForUser(theUsername, theList);
                inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_EDITOR_ASSIGNMENT, theReturnList);
            }

            theList = theAnimalModelManager.getAllByState("Complete-not screened");
            if (theList != null && !theList.isEmpty()) {
                List theReturnList = getModelsForUser(theUsername, theList);
                inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_SCREENER_ASSIGNMENT, theReturnList);
            }
        }

        // Screener specific curation states
        if (theRoles.contains(Constants.Admin.Roles.SCREENER)) {

            // Add all the models by state for a user
            theList = theAnimalModelManager.getAllByState("Screener-assigned");
            if (theList != null && !theList.isEmpty()) {
                List theReturnList = getModelsForUser(theUsername, theList);
                inRequest.setAttribute(Constants.Admin.MODELS_NEEDING_SCREENING, theReturnList);
            }
        }

        // TODO: This isn't correct
        theList = null; // theAnimalModelManager.getAllByState("Incomplete");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.COMMENTS_NEEDING_REVIEW, theList);
        }

        log.info("Exiting AdminRolesPopulateAction.execute");

        return inMapping.findForward("next");
    }

    // Get the models for a specific user. We need to check the log table to see
    // if the log entry associated w/ this
    // model and state is associated w/ this user
    private List getModelsForUser(String inUsername, List inModelList) {

        log.info("Entering AdminRolesPopulateAction.getModelsForUser");

        LogManager theLogManager = (LogManager) getBean("logManager");

        List theReturnList = new ArrayList();

        log.debug("Number of records found: " + inModelList.size());

        for (int i = 0; i < inModelList.size(); i++) {

            AnimalModel theAnimalModel = (AnimalModel) inModelList.get(i);

            // Is there an associated LOG for this user?
            Log theLog = theLogManager.getCurrentByModelAndAssigned(theAnimalModel.getId().toString(), theAnimalModel
                    .getState(), inUsername);

            // If it's associated, the user needs to do something to the
            // record
            if (theLog != null) {
                theReturnList.add(theAnimalModel);
            }
        }

        log.debug("Number of records for user " + inUsername + " : " + theReturnList.size());

        log.info("Exiting AdminRolesPopulateAction.getModelsForUser");

        return theReturnList;
    }
}
