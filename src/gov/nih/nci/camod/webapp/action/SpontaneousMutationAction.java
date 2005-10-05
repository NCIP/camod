package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.SpontaneousMutationManager;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * SpontaneousMutationAction Class
 */

public class SpontaneousMutationAction extends BaseAction {
	
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
    	
        log.trace("Entering save");

        // Create a form to edit
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, spontaneousMutationForm);
             
        // Grab the current SpontaneousMutation we are working with related to this
        String aSpontaneousMutationID = request.getParameter("aSpontaneousMutationID");

        log.info("<SpontaneousMutationAction edit> following Characteristics:" 
        		+ "\n\t name: " + spontaneousMutationForm.getName()
                + "\n\t getNumberMGI: " + spontaneousMutationForm.getNumberMGI()
                + "\n\t getObservation: " + spontaneousMutationForm.getObservation() 
                + "\n\t getMethodofObservation: " + spontaneousMutationForm.getMethodOfObservation()
                + "\n\t getComments: " + spontaneousMutationForm.getComments()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        SpontaneousMutationManager spontaneousMutationManager = (SpontaneousMutationManager) getBean("spontaneousMutationManager");
        
        try {
        	
        	SpontaneousMutation theSpontaneousMutation = spontaneousMutationManager.get( aSpontaneousMutationID );
        	spontaneousMutationManager.update( spontaneousMutationForm, theSpontaneousMutation );
        	
            log.info( "SpontaneousMutation edited" );

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("spontaneousmutation.edit.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating SpontaneousMutation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
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
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, spontaneousMutationForm);
             
        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<SpontaneousMutationAction save> following Characteristics:" 
        		+ "\n\t name: " + spontaneousMutationForm.getName()
                + "\n\t getNumberMGI: " + spontaneousMutationForm.getNumberMGI()
                + "\n\t getObservation: " + spontaneousMutationForm.getObservation() 
                + "\n\t getMethodofObservation: " + spontaneousMutationForm.getMethodOfObservation()
                + "\n\t getComments: " + spontaneousMutationForm.getComments()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.addGeneticDescription(theAnimalModel, spontaneousMutationForm);

            log.info("New SpontaneousMutation created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("spontaneousmutation.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating SpontaneousMutation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}
