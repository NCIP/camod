package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * TargetedModificationAction Class
 */
public final class TargetedModificationAction extends BaseAction {
	
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
        TargetedModificationForm TargetedModificationForm = (TargetedModificationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, TargetedModificationForm);
             
        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<TargetedModificationAction save> following Characteristics:" 
        		+ "\n\t getType: " + TargetedModificationForm.getName()
                + "\n\t getOtherType: " + TargetedModificationForm.getModificationType()              
                + "\n\t getCASNumber: " + TargetedModificationForm.getOtherModificationType() 
                + "\n\t getGeneId: " + TargetedModificationForm.getGeneId()
                + "\n\t getName: " + TargetedModificationForm.getEsCellLineName()
                + "\n\t getDescription: " + TargetedModificationForm.getBlastocystName()
                + "\n\t getObservation: " + TargetedModificationForm.getConditionedBy()
                + "\n\t getMethodObservation: " + TargetedModificationForm.getDescription()
                + "\n\t getNumberMGI: " + TargetedModificationForm.getComments()
                + "\n\t getObservation: " + TargetedModificationForm.getNumberMGI()
                + "\n\t getMethodObservation: " + TargetedModificationForm.getFileServerLocation()
                + "\n\t getNumberMGI: " + TargetedModificationForm.getTitle()
                + "\n\t getDescriptionOfConstruct: " + TargetedModificationForm.getDescriptionOfConstruct()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.addGeneticDescription( theAnimalModel, TargetedModificationForm );

            log.info("New TargetedModification created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating TargetedModification", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}