package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
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

        AnimalModelStateForm theForm = new AnimalModelStateForm();

        String theModelId = inRequest.getParameter("ModelId");
        String theEvent = inRequest.getParameter("event");

        theForm.setEvent(theEvent);
        theForm.setModelId(theModelId);

        UserManager theUserManager = (UserManager) getBean("userManager");

        inRequest.setAttribute("formdata", theForm);

        if ("assign_screener".equals(theEvent)) {
            inRequest.setAttribute("action", "Assigning Screener to ");
            inRequest.setAttribute("asignees", theUserManager.getUsersForRole(Constants.Admin.Roles.SCREENER));
        } else if ("assign_editor".equals(theEvent)) {
            inRequest.setAttribute("action", "Assigning Editor to ");
            inRequest.setAttribute("asignees", theUserManager.getUsersForRole(Constants.Admin.Roles.EDITOR));
        } else if ("need_more_info".equals(theEvent)) {
            inRequest.setAttribute("action", "Requesting more information for ");
        } else if ("rejected".equals(theEvent)) {
            inRequest.setAttribute("action", "Rejecting ");
        } else {
            inRequest.setAttribute("action", "Approving ");
        }
        return inMapping.findForward("next");
    }
}
