/**
 * 
 * $Id: GenomicSegmentAction.java,v 1.18 2007-04-04 13:19:08 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.16  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * GenomicSegmentAction Class
 */
public final class GenomicSegmentAction extends BaseAction {

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

        // Create a form to edit
        GenomicSegmentForm genomicSegmentForm = (GenomicSegmentForm) form;

        // Grab the current modelID from the session
        String aGenomicSegmentID = genomicSegmentForm.getSegmentId();

        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<GenomicSegmentAction save> following Characteristics:" + "\n\t getLocationOfIntegration: "
                + genomicSegmentForm.getLocationOfIntegration() + "\n\t getIsRandom() "
                + genomicSegmentForm.getIsRandom() + "\n\t getSegmentName: "
                + genomicSegmentForm.getSegmentName() + "\n\t getOtherSegmentName: "
                + genomicSegmentForm.getOtherSegmentName() + "\n\t getComments: " + genomicSegmentForm.getComments()
                + "\n\t getCloneDesignator: " + genomicSegmentForm.getCloneDesignator() 
                + "\n\t getMgiId: "  + genomicSegmentForm.getMgiId()
                + "\n\t getZfinId: "  + genomicSegmentForm.getZfinId() 
                + "\n\t getRgdId: "  + genomicSegmentForm.getRgdId()                 
                + "\n\t getDescription: " + genomicSegmentForm.getDescription()
                + "\n\t getFileServerLocation: " + genomicSegmentForm.getFileServerLocation() + "\n\t getTitle: "
                + genomicSegmentForm.getTitle() + "\n\t"
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {

            GenomicSegmentManager genomicSegmentManager = (GenomicSegmentManager) getBean("genomicSegmentManager");

            if ("Delete".equals(theAction)) {

                log.info("GenomicSegment delete");

                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

                genomicSegmentManager.remove(aGenomicSegmentID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.delete.successful"));
                saveErrors(request, msg);

            } else {
                log.info("GenomicSegment edit");

                // retrieve animal model by it's id
                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);

                // retrieve model and update w/ new values

                GenomicSegment theGenomicSegment = genomicSegmentManager.get(aGenomicSegmentID);

                genomicSegmentManager.update(theAnimalModel, genomicSegmentForm, theGenomicSegment, request);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred editing GenomicSegment", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
        } catch (Exception e) {
            log.error("Exception ocurred editing GenomicSegment", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
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

        log.trace("Entering save");

        // Create a form to edit
        GenomicSegmentForm genomicSegmentForm = (GenomicSegmentForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, genomicSegmentForm);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log
                .info("<GenomicSegmentAction save> following Characteristics:" + "\n\t getLocationOfIntegration: "
                        + genomicSegmentForm.getLocationOfIntegration() + "\n\t getSegmentName: "
                        + genomicSegmentForm.getSegmentName() + "\n\t getOtherSegmentName: "
                        + genomicSegmentForm.getOtherSegmentName() + "\n\t getComments: "
                        + genomicSegmentForm.getComments() + "\n\t getCloneDesignator: "
                        + genomicSegmentForm.getCloneDesignator() + "\n\t getMgiId: "
                        + genomicSegmentForm.getMgiId() 
                        + "\n\t getZfinId: "  + genomicSegmentForm.getZfinId() 
                        + "\n\t getRgdId: "  + genomicSegmentForm.getRgdId()                        
                        + "\n\t getDescription: "
                        + genomicSegmentForm.getDescription() + "\n\t getFileServerLocation: "
                        + genomicSegmentForm.getFileServerLocation() + "\n\t getTitle: "
                        + genomicSegmentForm.getTitle()
                        + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneticDescription(theAnimalModel, genomicSegmentForm, request);

            log.info("New GenomicSegment created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.creation.successful"));
            saveErrors(request, msg);

        } catch (IllegalArgumentException e) {
            log.error("Exception ocurred creating GenomicSegment", e);

            theForward = "input";

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
        } catch (Exception e) {
            log.error("Exception ocurred creating GenomicSegment", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward(theForward);
    }
}