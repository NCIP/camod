/**
 * 
 * $Id: TargetedModificationAction.java,v 1.18 2007-09-12 19:36:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2007/07/31 12:13:42  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.16  2007/07/23 17:37:58  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.15  2007/04/04 13:19:05  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.14  2007/03/26 12:02:29  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.13  2006/04/20 14:04:50  pandyas
 * changed Modification Type to getOrCreate
 *
 * Revision 1.12  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


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

        log.debug("<TargetedModificationAction> Entering edit");

        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;

        // Grab the current SpontaneousMutation we are working with related to
        // this
        String aTargetedModificationID = targetedModificationForm.getModificationId();

        log.debug("<TargetedModificationAction save> following Characteristics:" + "\n\t getName: "
                + targetedModificationForm.getName() + "\n\t getModificationType: "
                + targetedModificationForm.getModificationType() + "\n\t getOtherModificationType: "
                + targetedModificationForm.getOtherModificationType() + "\n\t getGeneId: "
                + targetedModificationForm.getGeneId() + "\n\t getEsCellLineName: "
                + targetedModificationForm.getEsCellLineName() + "\n\t getBlastocystName: "
                + targetedModificationForm.getBlastocystName() + "\n\t getConditionedBy: "
                + targetedModificationForm.getConditionedBy() + "\n\t getDescription: "
                + targetedModificationForm.getDescription() + "\n\t getComments: "
                + targetedModificationForm.getComments() 
                + "\n\t getMgiId: "  + targetedModificationForm.getMgiId() 
                + "\n\t getZfinId: "  + targetedModificationForm.getZfinId() 
                + "\n\t getRgdId: "  + targetedModificationForm.getRgdId()                 
                + "\n\t getUrl: "
                + targetedModificationForm.getUrl() + "\n\t getTitle: "
                + targetedModificationForm.getTitle() + "\n\t getDescriptionOfConstruct: "
                + targetedModificationForm.getDescriptionOfConstruct() + "\n\t getConstructSequence(): "
                + targetedModificationForm.getConstructSequence()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        TargetedModificationManager targetedModificationManager = (TargetedModificationManager) getBean("targetedModificationManager");
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        String theForward = "AnimalModelTreePopulateAction";
        try {
            // retrieve animal model by it's id
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);

            if ("Delete".equals(theAction)) {

                targetedModificationManager.remove(aTargetedModificationID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.delete.successful"));
                saveErrors(request, msg);

            } else {
                TargetedModification theTargetedModification = targetedModificationManager.get(aTargetedModificationID);
                targetedModificationManager.update(theAnimalModel, targetedModificationForm, theTargetedModification,
                        request);

                log.debug("TargetedModification edited");

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred editing a TargetedModification", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception occurred creating a TargetedModification", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("< TargetedModificationAction> Exiting edit");

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

        log.debug("<TargetedModificationAction> Entering save");

        // Create a form to edit
        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<TargetedModificationAction save> following Characteristics:" + "\n\t getName: "
                + targetedModificationForm.getName() + "\n\t getModificationType: "
                + targetedModificationForm.getModificationType() + "\n\t getOtherModificationType: "
                + targetedModificationForm.getOtherModificationType() + "\n\t getGeneId: "
                + targetedModificationForm.getGeneId() + "\n\t getEsCellLineName: "
                + targetedModificationForm.getEsCellLineName() + "\n\t getBlastocystName: "
                + targetedModificationForm.getBlastocystName() + "\n\t getConditionedBy: "
                + targetedModificationForm.getConditionedBy() + "\n\t getDescription: "
                + targetedModificationForm.getDescription() + "\n\t getComments: "
                + targetedModificationForm.getComments() + "\n\t getMgiId: "
                + targetedModificationForm.getMgiId() 
                + "\n\t getZfinId: "  + targetedModificationForm.getZfinId() 
                + "\n\t getRgdId: "  + targetedModificationForm.getRgdId()                   
                + "\n\t getUrl: "
                + targetedModificationForm.getUrl() + "\n\t getTitle: "
                + targetedModificationForm.getTitle() + "\n\t getDescriptionOfConstruct: "
                + targetedModificationForm.getDescriptionOfConstruct() + "\n\t getConstructSequence(): "
                + targetedModificationForm.getConstructSequence()
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, targetedModificationForm, request);

            log.debug("New TargetedModification created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("targetedmodification.creation.successful"));
            saveErrors(request, msg);

        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred saving a TargetedModification", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating TargetedModification", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("<TargetedModificationAction> Exiting save");
        return mapping.findForward(theForward);
    }
}