/**
 *  
 *  $Id: SubmitAction.java,v 1.9 2005-09-16 15:52:55 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class SubmitAction extends BaseAction {

    /**
     * called from SubmitModels.jsp from list of models links
     * 
     */
    public ActionForward setModelConstants(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<SubmitAction setModelConstants> modelID=" + request.getParameter("aModelID"));

        String modelID = request.getParameter("aModelID");

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

        String theForward = "AnimalModelTreePopulateAction";

        try {
            AnimalModel am = animalModelManager.get(modelID);

            request.getSession().setAttribute(Constants.MODELID, am.getId().toString());
            request.getSession().setAttribute(Constants.MODELDESCRIPTOR, am.getModelDescriptor());
            request.getSession().setAttribute(Constants.MODELSTATUS, am.getState());

            UserManager theUserManager = (UserManager) getBean("userManager");

            AnimalModelStateForm theForm = new AnimalModelStateForm();
            theForm.setModelId(am.getId().toString());

            // Set up the form. Should be only one controller
            List theRoles = theUserManager.getUsersForRole(Constants.Admin.Roles.CONTROLLER);
            theForm.setAssignedTo((String) theRoles.get(0));
            request.setAttribute(Constants.FORMDATA, theForm);

        } catch (Exception e) {
            log.error("Exception occurred in setModelConstants", e);

            // Encountered an error saving the model.
            // created a new model successfully
            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);

            theForward = "failure";
        }
        return mapping.findForward(theForward);
    }
}
