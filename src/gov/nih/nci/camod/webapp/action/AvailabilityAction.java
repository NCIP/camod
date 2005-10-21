package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.AvailabilityManager;
import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * AvailabilityAction Class
 */
public class AvailabilityAction extends BaseAction {

    /**
     * Delete
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
    }
    
    /**
     * Cancel
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward("");
    }
    
    /**
     * Edit
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("<AvailabilityAction> Entering edit");

        // Create a form to edit
        AvailabilityForm availabilityForm = (AvailabilityForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        String aAvailabilityID = request.getParameter("aAvailabilityID");

        log.info("<AvailabilityAction edit> following Characteristics:" 
        		+ "\n\t name: " + availabilityForm.getName()
                + "\n\t stockNuber: " + availabilityForm.getStockNumber() 
                + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            AvailabilityManager availabilityManager = (AvailabilityManager) getBean("availabilityManager");
            AnimalAvailability theAvailability = availabilityManager.get(aAvailabilityID);

            availabilityManager.update(availabilityForm, theAvailability, theAnimalModel);        	
        	
            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("availability.creation.successful"));
            saveErrors(request, msg);
            
        } catch (Exception e) {
            log.error("Exception ocurred creating Availability", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);

            theForward = "failure";
        }

        log.trace("<AvailabilityAction> Exiting edit");
        return mapping.findForward(theForward);        	
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

        log.trace("<AvailabilityAction> Entering save");

        // Create a form to edit
        AvailabilityForm availabilityForm = (AvailabilityForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<AvailabilityAction edit> following Characteristics:" 
        		+ "\n\t name: " + availabilityForm.getName()
                + "\n\t stockNuber: " + availabilityForm.getStockNumber() 
                + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addAvailability(theAnimalModel, availabilityForm);

            log.info("New Availability created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("availability.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating Availability", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("<AvailabilityAction> Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }    
	
}
