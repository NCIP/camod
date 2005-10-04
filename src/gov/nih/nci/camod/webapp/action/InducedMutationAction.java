package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * InducedMutationAction Class
 */
public final class InducedMutationAction extends BaseAction {
	
    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
    }

	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	
    	 return mapping.findForward("");
    }    
    
    
    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

        return mapping.findForward("");
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
        InducedMutationForm inducedMutationForm = (InducedMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, inducedMutationForm);
             
        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
    	
        log.info("<InducedMutationAction save> following Characteristics:" 
        		+ "\n\t getType: " + inducedMutationForm.getType()
                + "\n\t getOtherType: " + inducedMutationForm.getOtherType()              
                + "\n\t getCASNumber: " + inducedMutationForm.getCASNumber() 
                + "\n\t getGeneId: " + inducedMutationForm.getGeneId()
                + "\n\t getName: " + inducedMutationForm.getName()
                + "\n\t getDescription: " + inducedMutationForm.getDescription()
                + "\n\t getObservation: " + inducedMutationForm.getObservation()
                + "\n\t getMethodObservation: " + inducedMutationForm.getMethodOfObservation()
                + "\n\t getNumberMGI: " + inducedMutationForm.getNumberMGI()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.addGeneticDescription(theAnimalModel, inducedMutationForm);

            log.info("New InducedMutation created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("inducedmutation.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating InducedMutation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}