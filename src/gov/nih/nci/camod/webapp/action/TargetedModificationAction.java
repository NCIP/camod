package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * TargetedModificationAction Class
 */
public final class TargetedModificationAction extends BaseAction {

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
        
		//	Grab the current modelID we are working with
        String modelID = request.getParameter("aModelID");        

        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, targetedModificationForm);

        // Grab the current SpontaneousMutation we are working with related to
        // this
        String aTargetedModificationID = request.getParameter("aTargetedModificationID");

        log.info("<TargetedModificationAction save> following Characteristics:" + "\n\t getName: "
                + targetedModificationForm.getName() + "\n\t getModificationType: "
                + targetedModificationForm.getModificationType() + "\n\t getOtherModificationType: "
                + targetedModificationForm.getOtherModificationType() + "\n\t getGeneId: "
                + targetedModificationForm.getGeneId() + "\n\t getEsCellLineName: "
                + targetedModificationForm.getEsCellLineName() + "\n\t getBlastocystName: "
                + targetedModificationForm.getBlastocystName() + "\n\t getConditionedBy: "
                + targetedModificationForm.getConditionedBy() + "\n\t getDescription: "
                + targetedModificationForm.getDescription() + "\n\t getComments: "
                + targetedModificationForm.getComments() + "\n\t getNumberMGI: "
                + targetedModificationForm.getNumberMGI() + "\n\t getFileServerLocation: "
                + targetedModificationForm.getFileServerLocation() + "\n\t getTitle: "
                + targetedModificationForm.getTitle() + "\n\t getDescriptionOfConstruct: "
                + targetedModificationForm.getDescriptionOfConstruct()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        TargetedModificationManager targetedModificationManager = (TargetedModificationManager) getBean("targetedModificationManager");
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {
	        // retrieve animal model by it's id	        
	        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
	        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);        	

            if ("Delete".equals(theAction)) {

                targetedModificationManager.remove(aTargetedModificationID);
                
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.delete.successful"));
                saveErrors(request, msg);
                
            } else {
                TargetedModification theTargetedModification = targetedModificationManager.get(aTargetedModificationID);
                targetedModificationManager.update(theAnimalModel, targetedModificationForm, theTargetedModification, request);

                log.info("TargetedModification edited");

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (Exception e) {
            log.error("Exception ocurred creating SpontaneousMutation", e);

            // Encountered an error saving the model.
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

        log.trace("Entering save");

        // Create a form to edit
        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, targetedModificationForm);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<TargetedModificationAction save> following Characteristics:" + "\n\t getName: "
                + targetedModificationForm.getName() + "\n\t getModificationType: "
                + targetedModificationForm.getModificationType() + "\n\t getOtherModificationType: "
                + targetedModificationForm.getOtherModificationType() + "\n\t getGeneId: "
                + targetedModificationForm.getGeneId() + "\n\t getEsCellLineName: "
                + targetedModificationForm.getEsCellLineName() + "\n\t getBlastocystName: "
                + targetedModificationForm.getBlastocystName() + "\n\t getConditionedBy: "
                + targetedModificationForm.getConditionedBy() + "\n\t getDescription: "
                + targetedModificationForm.getDescription() + "\n\t getComments: "
                + targetedModificationForm.getComments() + "\n\t getNumberMGI: "
                + targetedModificationForm.getNumberMGI() + "\n\t getFileServerLocation: "
                + targetedModificationForm.getFileServerLocation() + "\n\t getTitle: "
                + targetedModificationForm.getTitle() + "\n\t getDescriptionOfConstruct: "
                + targetedModificationForm.getDescriptionOfConstruct()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, targetedModificationForm, request);

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