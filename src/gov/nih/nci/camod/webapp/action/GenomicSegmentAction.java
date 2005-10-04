package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * GenomicSegmentAction Class
 */
public final class GenomicSegmentAction extends BaseAction {
	
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
        GenomicSegmentForm GenomicSegmentForm = (GenomicSegmentForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, GenomicSegmentForm);
             
        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<GenomicSegmentAction save> following Characteristics:" 
        		+ "\n\t getLocationOfIntegration: " + GenomicSegmentForm.getLocationOfIntegration()
                + "\n\t getSegmentName: " + GenomicSegmentForm.getSegmentName()              
                + "\n\t getOtherSegmentName: " + GenomicSegmentForm.getOtherSegmentName() 
                + "\n\t getComments: " + GenomicSegmentForm.getComments()
                + "\n\t getCloneDesignator: " + GenomicSegmentForm.getCloneDesignator()
                + "\n\t getNumberMGI: " + GenomicSegmentForm.getNumberMGI()
                + "\n\t getDescription: " + GenomicSegmentForm.getDescription()
                + "\n\t getFileServerLocation: " + GenomicSegmentForm.getFileServerLocation()
                + "\n\t getTitle: " + GenomicSegmentForm.getTitle()          
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.addGeneticDescription( theAnimalModel, GenomicSegmentForm );

            log.info("New GenomicSegment created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating GenomicSegment", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}