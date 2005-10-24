/**
 *  
 *  $Id: SubmitAction.java,v 1.12 2005-10-24 13:28:17 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.11  2005/09/22 18:56:37  georgeda
 *  Get coordinator from user in properties file
 *
 *  Revision 1.10  2005/09/22 15:18:43  georgeda
 *  More changes
 *
 *  Revision 1.9  2005/09/16 15:52:55  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.ResourceBundle;

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

        System.out.println("<SubmitAction setModelConstants> modelID="
                + request.getParameter(Constants.Parameters.MODELID));

        String modelID = request.getParameter(Constants.Parameters.MODELID);

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

        String theForward = "AnimalModelTreePopulateAction";

        try {
            AnimalModel am = animalModelManager.get(modelID);

            request.getSession().setAttribute(Constants.MODELID, am.getId().toString());
            request.getSession().setAttribute(Constants.MODELDESCRIPTOR, am.getModelDescriptor());
            request.getSession().setAttribute(Constants.MODELSTATUS, am.getState());

            AnimalModelStateForm theForm = new AnimalModelStateForm();
            theForm.setModelId(am.getId().toString());

            // Get the coordinator
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            String theCoordinator = theBundle.getString(Constants.BundleKeys.COORDINATOR_USERNAME_KEY);
            theForm.setAssignedTo(theCoordinator);
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
