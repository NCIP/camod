/**
 * 
 * $Id: GeneDeliveryAction.java,v 1.20 2007-09-12 19:36:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.19  2007/07/23 17:40:42  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.18  2007/06/26 16:14:58  pandyas
 * Fixed save when organ cleared from text entry and by use of the clear button for trees
 *
 * Revision 1.17  2007/05/10 02:20:49  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.16  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.15  2005/11/09 00:17:25  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.14  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.13  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.12  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
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

        log.debug("<GeneDeliveryAction> Entering edit");

        // Create a form to edit
        GeneDeliveryForm geneDeliveryForm = (GeneDeliveryForm) form;

        // Grab the current aCarcinogenExposureID
        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<GeneDeliveryAction edit> following Characteristics:" + "\n\t ViralVector: "
                + geneDeliveryForm.getViralVector() + "\n\t OtherViralVector: "
                + geneDeliveryForm.getOtherViralVector() + "\n\t GeneInVirus: " + geneDeliveryForm.getGeneInVirus()
                + "\n\t Regimen: " + geneDeliveryForm.getRegimen() 
                + "\n\t organTissueCode: " + geneDeliveryForm.getOrganTissueCode() 
                + "\n\t organTissueName: "  + geneDeliveryForm.getOrganTissueName() 
                + "\n\t organ: "  + geneDeliveryForm.getOrgan()                 
                + "\n\t user: "  + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {
            // retrieve animal model by it's id         
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);                

            GeneDeliveryManager theGeneDeliveryManager = (GeneDeliveryManager) getBean("geneDeliveryManager");
            if ("Delete".equals(theAction)) {

                theGeneDeliveryManager.remove(aCarcinogenExposureID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.delete.successful"));
                saveErrors(request, msg);

            } else {

                GeneDelivery theGeneDelivery = theGeneDeliveryManager.get(aCarcinogenExposureID);
                theGeneDeliveryManager.update(theAnimalModel, geneDeliveryForm, theGeneDelivery);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (Exception e) {

            log.error("Exception occurred creating GeneDelivery", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("<GeneDeliveryAction> Exiting edit");
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

        log.debug("<GeneDeliveryAction save> following Characteristics:" + "\n\t ViralVector: "
                + geneDeliveryForm.getViralVector() + "\n\t OtherViralVector: "
                + geneDeliveryForm.getOtherViralVector() + "\n\t GeneInVirus: " + geneDeliveryForm.getGeneInVirus()
                + "\n\t Regimen: " + geneDeliveryForm.getRegimen() 
                + "\n\t organ: " + geneDeliveryForm.getOrgan()                
                + "\n\t organTissueCode: " + geneDeliveryForm.getOrganTissueCode() 
                + "\n\t organTissueName: " + geneDeliveryForm.getOrganTissueName() 
                + "\n\t organ: "  + geneDeliveryForm.getOrgan()                     
                + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGeneDelivery(theAnimalModel, geneDeliveryForm);

            log.debug("New GeneDelivery created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception occurred creating GeneDelivery", e);

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