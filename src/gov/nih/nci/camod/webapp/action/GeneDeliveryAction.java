/**
 * 
 * $Id: GeneDeliveryAction.java,v 1.12 2005-10-28 14:50:55 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.10  2005/10/20 20:22:19  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * GeneDeliveryAction Class
 */
public final class GeneDeliveryAction extends BaseAction {

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

        log.info("<GeneDeliveryAction> Entering edit");

        // Create a form to edit
        GeneDeliveryForm geneDeliveryForm = (GeneDeliveryForm) form;

        // Grab the current therapy
        String aTherapyID = request.getParameter("aTherapyID");

        log.info("<GeneDeliveryAction edit> following Characteristics:" + "\n\t ViralVector: "
                + geneDeliveryForm.getViralVector() + "\n\t OtherViralVector: "
                + geneDeliveryForm.getOtherViralVector() + "\n\t GeneInVirus: " + geneDeliveryForm.getGeneInVirus()
                + "\n\t Regimen: " + geneDeliveryForm.getRegimen() + "\n\t organTissueCode: "
                + geneDeliveryForm.getOrganTissueCode() + "\n\t organTissueName: "
                + geneDeliveryForm.getOrganTissueName() + "\n\t user: "
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {

            GeneDeliveryManager theGeneDeliveryManager = (GeneDeliveryManager) getBean("geneDeliveryManager");
            if ("Delete".equals(theAction)) {

                theGeneDeliveryManager.remove(aTherapyID);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.delete.successful"));
                saveErrors(request, msg);

            } else {

                GeneDelivery theGeneDelivery = theGeneDeliveryManager.get(aTherapyID);
                theGeneDeliveryManager.update(geneDeliveryForm, theGeneDelivery);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (Exception e) {

            log.error("Exception ocurred creating GeneDelivery", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.info("<GeneDeliveryAction> Exiting edit");
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

        log.debug("<GeneDeliveryAction> Entering save");

        // Create a form to edit
        GeneDeliveryForm geneDeliveryForm = (GeneDeliveryForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<GeneDeliveryAction save> following Characteristics:" + "\n\t ViralVector: "
                + geneDeliveryForm.getViralVector() + "\n\t OtherViralVector: "
                + geneDeliveryForm.getOtherViralVector() + "\n\t GeneInVirus: " + geneDeliveryForm.getGeneInVirus()
                + "\n\t Regimen: " + geneDeliveryForm.getRegimen() + "\n\t organTissueCode: "
                + geneDeliveryForm.getOrganTissueCode() + "\n\t organTissueName: "
                + geneDeliveryForm.getOrganTissueName() + "\n\t user: "
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneDelivery(theAnimalModel, geneDeliveryForm);

            log.info("New GeneDelivery created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating GeneDelivery", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);

            theForward = "failure";
        }

        log.trace("<GeneDeliveryAction> Exiting save");
        return mapping.findForward(theForward);
    }
}