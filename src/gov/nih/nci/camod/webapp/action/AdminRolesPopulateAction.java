/**
 * @author dgeorge
 * 
 * $Id: AdminRolesPopulateAction.java,v 1.7 2005-09-16 15:52:55 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * 
 * Used to populate the list of animal models needing action for the various
 * roles
 * 
 */
public class AdminRolesPopulateAction extends BaseAction {

    /**
     * Action used to populate the various admin lists for the curation process
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering execute");

        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");

        // Get the list of roles for the current user
        List theRoles = (List) inRequest.getSession().getAttribute(Constants.CURRENTUSERROLES);

        // Get the user
        PersonManager thePersonManager = (PersonManager) getBean("personManager");
        String theUsername = (String) inRequest.getSession().getAttribute(Constants.CURRENTUSER);
        Person theUser = (Person) thePersonManager.getByUsername(theUsername);

        List theList = null;

        // Editor specific curation states
        if (theRoles.contains(Constants.Admin.Roles.EDITOR)) {

            addModelsToRequest(inRequest, theAnimalModelManager, theUser, "Edited-need more info",
                    Constants.Admin.MODELS_NEEDING_MORE_INFO);
            addModelsToRequest(inRequest, theAnimalModelManager, theUser, "Editor-assigned",
                    Constants.Admin.MODELS_NEEDING_EDITING);
        }

        // Controller specific curation states
        if (theRoles.contains(Constants.Admin.Roles.CONTROLLER)) {

            addModelsToRequest(inRequest, theAnimalModelManager, theUser, "Screened-approved",
                    Constants.Admin.MODELS_NEEDING_EDITOR_ASSIGNMENT);

            addModelsToRequest(inRequest, theAnimalModelManager, theUser, "Complete-not screened",
                    Constants.Admin.MODELS_NEEDING_SCREENER_ASSIGNMENT);
        }

        // Screener specific curation states
        if (theRoles.contains(Constants.Admin.Roles.SCREENER)) {
            addModelsToRequest(inRequest, theAnimalModelManager, theUser, "Screener-assigned",
                    Constants.Admin.MODELS_NEEDING_SCREENING);
        }

        // TODO: This isn't correct
        theList = null; // theAnimalModelManager.getAllByState("Incomplete");
        if (theList != null && !theList.isEmpty()) {
            inRequest.setAttribute(Constants.Admin.COMMENTS_NEEDING_REVIEW, theList);
        }

        log.trace("Exiting execute");

        return inMapping.findForward("next");
    }

    // Check that the user owns these records and if so, add to the request
    // using the passed in key
    private void addModelsToRequest(HttpServletRequest inRequest, AnimalModelManager inManager, Person inUser,
            String inState, String inKey) {

        log.trace("Entering addModelsToRequest");

        try {
            // Add all the models by state for a user
            List theList = inManager.getAllByState(inState);
            log.debug("Total models for state: " + inState + " size: " + theList.size());

            // I found some models in this state
            if (theList != null && !theList.isEmpty()) {

                // Only add the ones associated to the user
                List theUserList = getModelsForUser(inUser, theList);
                if (theUserList.size() > 0) {
                    inRequest.setAttribute(inKey, theUserList);
                }
            }
        } catch (Exception e) {

            log.error("Unable to get models for state: " + inState);

            // Encountered an error saving the model.
            // created a new model successfully
            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(inRequest, theMsg);

        }
        log.trace("Exiting addModelsToRequest");
    }

    // Get the models for a specific user. We need to check the log table to see
    // if the log entry associated w/ this
    // model and state is associated w/ this user
    private List getModelsForUser(Person inUser, List inModelList) throws Exception {

        log.trace("Entering getModelsForUser");

        LogManager theLogManager = (LogManager) getBean("logManager");

        List theReturnList = new ArrayList();

        for (int i = 0; i < inModelList.size(); i++) {

            AnimalModel theAnimalModel = (AnimalModel) inModelList.get(i);

            // Is there an associated LOG for this user?
            Log theLog = theLogManager.getCurrentByModelAndAssigned(theAnimalModel, inUser);

            // If it's associated, the user needs to do something to the
            // record. Create the array
            if (theLog != null) {
                theReturnList.add(theAnimalModel);
            }
        }

        log.debug("Number of records for user " + inUser.getUsername() + " : " + theReturnList.size());

        log.trace("Exiting getModelsForUser");

        return theReturnList;
    }
}