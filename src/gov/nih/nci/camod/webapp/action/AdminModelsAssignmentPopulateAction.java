/**
 * @author dgeorge
 * 
 * $Id: AdminModelsAssignmentPopulateAction.java,v 1.1 2005-09-19 19:53:51 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.ModelAssignmentForm;

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
public class AdminModelsAssignmentPopulateAction extends BaseAction {

    /**
     * Action used to populate the various admin lists for the curation process
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering execute");

        // Get the curation manager workflow XML
        CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext().getRealPath("/")
                + Constants.Admin.MODEL_CURATION_WORKFLOW);

        ModelAssignmentForm theForm = (ModelAssignmentForm) inForm;
        theForm.setStates(theCurationManager.getAllStateNames());

        inRequest.setAttribute(Constants.FORMDATA, theForm);

        if (theForm.getCurrentState() != null) {
            try {
                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                List theModels = theAnimalModelManager.getAllByState(theForm.getCurrentState());
                inRequest.setAttribute("models", theModels);
            } catch (Exception e) {
                log.error("Unable to get models for state: " + theForm.getCurrentState());

                // Encountered an error saving the model.
                // created a new model successfully
                ActionMessages theMsg = new ActionMessages();
                theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(inRequest, theMsg);
            }
        }

        log.trace("Exiting execute");

        return inMapping.findForward("next");
    }
}