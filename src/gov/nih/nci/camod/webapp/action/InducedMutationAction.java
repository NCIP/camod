/**
 * 
 * $Id: InducedMutationAction.java,v 1.15 2007-09-12 19:36:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2007/07/23 17:40:43  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.13  2007/04/04 13:19:07  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/05/04 19:27:37  pandyas
 * Changed GeneticAlterationCollection to GeneticAlteration relationship from SpontaneousMutation and InducedMutation objects
 *
 * Revision 1.10  2006/04/17 19:09:41  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.InducedMutationManager;
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

        log.trace("Entering edit");        
        
        InducedMutationForm inducedMutationForm = (InducedMutationForm) form;
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID); 
        
        log.debug("<TargetedModificationAction edit> following Characteristics:" 
        		+ "\n\t getType: "  + inducedMutationForm.getType() 
                + "\n\t getOtherType: " + inducedMutationForm.getOtherType()
                + "\n\t getCasNumber: " + inducedMutationForm.getCasNumber() 
                + "\n\t getGeneId: " + inducedMutationForm.getGeneId() + "\n\t getName: " + inducedMutationForm.getName()
                + "\n\t getDescription: " + inducedMutationForm.getDescription() 
                + "\n\t getObservation: " + inducedMutationForm.getObservation() 
                + "\n\t getMethodObservation: " + inducedMutationForm.getMethodOfObservation() 
                 + "\n\t getMgiId: "  + inducedMutationForm.getMgiId() 
                + "\n\t getZfinId: "  + inducedMutationForm.getZfinId() 
                + "\n\t getRgdId: "  + inducedMutationForm.getRgdId()                 
                + "\n\t"
                + (String) request.getSession().getAttribute("camod.loggedon.username"));        

        // Grab the current SpontaneousMutation we are working with related to
        // this
        String aInducedMutationID = request.getParameter("aInducedMutationID");

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {
        	

            InducedMutationManager inducedMutationManager = (InducedMutationManager) getBean("inducedMutationManager");

            if ("Delete".equals(theAction)) {
                
                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
                
                inducedMutationManager.remove(aInducedMutationID, theAnimalModel);

                log.debug("InducedMutation deleted");

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("inducedmutation.delete.successful"));
                saveErrors(request, msg);

            } else {
    	        // retrieve animal model by it's id	        
    	        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
    	        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);             	

                InducedMutation theInducedMutation = inducedMutationManager.get(aInducedMutationID);
                inducedMutationManager.update(theAnimalModel, inducedMutationForm, theInducedMutation);

                log.debug("InducedMutation edited");

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("inducedmutation.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (Exception e) {
            log.error("Exception ocurred editing InducedMutation", e);

            // Encountered an error
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
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

        log.debug("<InducedMutationAction save> Entering save");

        // Create a form to edit
        InducedMutationForm inducedMutationForm = (InducedMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, inducedMutationForm);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<InducedMutationAction save> following Characteristics:" + "\n\t getType: "  + inducedMutationForm.getType() 
                + "\n\t getOtherType: " + inducedMutationForm.getOtherType()
                + "\n\t getCasNumber: " + inducedMutationForm.getCasNumber() 
                + "\n\t getGeneId: " + inducedMutationForm.getGeneId() + "\n\t getName: " + inducedMutationForm.getName()
                + "\n\t getDescription: " + inducedMutationForm.getDescription() 
                + "\n\t getObservation: " + inducedMutationForm.getObservation() 
                + "\n\t getMethodObservation: " + inducedMutationForm.getMethodOfObservation() 
                + "\n\t getMgiId: "  + inducedMutationForm.getMgiId() 
                + "\n\t getZfinId: "  + inducedMutationForm.getZfinId() 
                + "\n\t getRgdNumber: "  + inducedMutationForm.getRgdId()                 
                + "\n\t"
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, inducedMutationForm);

            log.debug("New InducedMutation created");

            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("inducedmutation.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception occurred creating InducedMutation", e);

            // Encountered an error
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}