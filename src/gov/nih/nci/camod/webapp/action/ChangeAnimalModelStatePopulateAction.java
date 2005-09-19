/**
 *  @author dgeorge
 *  
 *  $Id: ChangeAnimalModelStatePopulateAction.java,v 1.6 2005-09-19 13:38:42 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.5  2005/09/19 13:09:52  georgeda
 *  Added header
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ChangeAnimalModelStatePopulateAction extends BaseAction {

    /**
     * Change the state for the curation model
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering ChangeAnimalModelStatePopulateAction.execute");

        // Get the attributes from the request
        String theModelId = inRequest.getParameter("ModelId");
        String theEvent = inRequest.getParameter("event");

        // Get the user manager bean to handle any role information
        UserManager theUserManager = (UserManager) getBean("userManager");

        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

        // Set up the form
        AnimalModelStateForm theForm = new AnimalModelStateForm();
        theForm.setEvent(theEvent);
        theForm.setModelId(theModelId);
        theForm.setModelDescriptor(theAnimalModel.getModelDescriptor());
        inRequest.setAttribute("formdata", theForm);

        log.debug("The model id: " + theModelId + " and event: " + theEvent);

        // Setting the action. This is used to customize the jsp display
        if (theEvent.equals(Constants.Admin.Actions.ASSIGN_SCREENER)) {
            inRequest.setAttribute("action", "Assigning Screener to ");
            theForm.setAssignees(theUserManager.getUsersForRole(Constants.Admin.Roles.SCREENER));
        } else if (theEvent.equals(Constants.Admin.Actions.ASSIGN_EDITOR)) {
            inRequest.setAttribute("action", "Assigning Editor to ");
            theForm.setAssignees(theUserManager.getUsersForRole(Constants.Admin.Roles.EDITOR));
        } else if (theEvent.equals(Constants.Admin.Actions.NEED_MORE_INFO)) {
            inRequest.setAttribute("action", "Requesting more information for ");
        } else if (theEvent.equals(Constants.Admin.Actions.REJECT)) {
            inRequest.setAttribute("action", "Rejecting ");
        } else if (theEvent.equals(Constants.Admin.Actions.APPROVE)) {
            inRequest.setAttribute("action", "Approving ");
        } else {
            throw new IllegalArgumentException("Unknown event type: " + theEvent);
        }

        log.trace("Exiting ChangeAnimalModelStatePopulateAction.execute");

        return inMapping.findForward("next");
    }
}
