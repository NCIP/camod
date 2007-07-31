/**
 * 
 * $Id: GraftAction.java,v 1.1 2007-07-31 12:05:26 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.21  2007/04/04 13:19:27  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.20  2007/03/26 12:02:30  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.19  2006/05/23 20:43:10  georgeda
 * Fixed error introduced in OM change
 *
 * Revision 1.18  2006/05/19 18:48:26  pandyas
 * Added printout values for fields not listed
 *
 * Revision 1.17  2006/05/19 16:40:32  pandyas
 * Defect #249 - add other to species on the Xenograft screen, simply added new variable to list to print out
 *
 * Revision 1.16  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.15  2005/12/28 16:44:44  pandyas
 * removed harvest date - unused
 *
 * Revision 1.14  2005/11/28 22:51:19  pandyas
 * Defect #186: Added organ/tissue to Xenograft page so I modified printout to include organCode (minor change for debugging)
 *
 * Revision 1.13  2005/11/11 16:28:03  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Graft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.GraftManager;
import gov.nih.nci.camod.webapp.form.GraftForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * XenograftAction Class
 */
public final class GraftAction extends BaseAction
{

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
    public ActionForward edit(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception
    {
        log.info("<GraftAction> Entering edit");

        // Create a form to edit
        GraftForm graftForm = (GraftForm) form;

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        String aGraftID = request.getParameter("aGraftID");

        log.info("<GraftAction edit> following Characteristics:"
                    + "\n\t name: "
                    + graftForm.getName()
                    + "\n\t atccNumber: "
                    + graftForm.getAtccNumber()
                    + "\n\t ParentalCellLineName: "
                    + graftForm.getParentalCellLineName()
                    + "\n\t getCellAmount: "
                    + graftForm.getCellAmount()
                    + "\n\t getGrowthPeriod: "
                    + graftForm.getGrowthPeriod()                
                    + "\n\t getModificationDescription: "
                    + graftForm.getModificationDescription()
                    + "\n\t getGeneticManipulation: "
                    + graftForm.getGeneticManipulation()
                    + "\n\t getAdministrativeSite: "
                    + graftForm.getAdministrativeSite()
                    + "\n\t getOtherAdministrativeSite: "
                    + graftForm.getOtherAdministrativeSite()                
                    + "\n\t getGraftType: "
                    + graftForm.getGraftType()
                    + "\n\t getOtherGraftType: "
                    + graftForm.getOtherGraftType()
                    + "\n\t getDonorScientificName: "
                    + graftForm.getDonorScientificName()
                    + "\n\t getOtherDonorScientificName: "
                    + graftForm.getOtherDonorScientificName()                  
                    + "\n\t getDonorEthinicityStrain: "
                    + graftForm.getDonorEthinicityStrain()              
                    + "\n\t getOtherDonorEthinicityStrain: "
                    + graftForm.getOtherDonorEthinicityStrain()
                    + "\n\t getOrganTissueName(): "
                    + graftForm.getOrganTissueName()
                    + "\n\t organTissueCode: "
                    + graftForm.getOrganTissueCode()
                    + "\n\t organ(): "
                    + graftForm.getOrgan() 
                    + "\n\t ConditioningRegimen(): "
                    + graftForm.getConditioningRegimen() 
                    + "\n\t OtherConditioningRegimen: "
                    + graftForm.getOtherConditioningRegimen()
                    + "\n\t user: "
                    + (String) request.getSession().getAttribute(
                            "camod.loggedon.username"));
        
        try
        {
            GraftManager graftManager = (GraftManager) getBean("graftManager");

            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            if ("Delete".equals(theAction))
            {
                graftManager.remove(aGraftID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("graft.delete.successful"));
                saveErrors(request, msg);
            }
            else
            {
                Graft theGraft = graftManager.get(aGraftID);

                graftManager.update(graftForm, theGraft, theAnimalModel);

                // Add a message to be displayed in submitOverview.jsp
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("graft.edit.successful"));
                saveErrors(request, msg);
            }

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Graft", e);

            // Encountered an error saving the xenograft.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("<XenograftAction> Exiting edit");
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
    public ActionForward save(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception
    {
        log.trace("<GraftAction> Entering save");

        // Create a form to edit
        GraftForm graftForm = (GraftForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<GraftAction save> following Characteristics:" + "\n\t name: " 
                 + graftForm.getName() 
                 + "\n\t atccNumber: " + graftForm.getAtccNumber() 
                 + "\n\t ParentalCellLineName: " + graftForm.getParentalCellLineName() 
                 + "\n\t getCellAmount: " + graftForm.getCellAmount() + "\n\t getGrowthPeriod: " + graftForm.getGrowthPeriod() 
                 + "\n\t getModificationDescription: " + graftForm.getModificationDescription() 
                 + "\n\t getGeneticManipulation: " + graftForm.getGeneticManipulation() 
                 + "\n\t getAdministrativeSite: " + graftForm.getAdministrativeSite() 
                 + "\n\t getOtherAdministrativeSite: " + graftForm.getOtherAdministrativeSite() 
                 + "\n\t getGraftType: " + graftForm.getGraftType() 
                 + "\n\t getOtherGraftType: " + graftForm.getOtherGraftType() 
                 + "\n\t getDonorScientificName: " + graftForm.getDonorScientificName() 
                 + "\n\t getOtherDonorScientificName: " + graftForm.getOtherDonorScientificName() 
                 + "\n\t getDonorEthinicityStrain: " + graftForm.getDonorEthinicityStrain() 
                 + "\n\t getOtherDonorEthinicityStrain: " + graftForm.getOtherDonorEthinicityStrain() + graftForm.getOtherDonorEthinicityStrain() 
                 + "\n\t getOrganTissueName(): " + graftForm.getOrganTissueName() 
                 + "\n\t organTissueCode: " + graftForm.getOrganTissueCode() 
                 + "\n\t organ(): " + graftForm.getOrgan() 
                 + "\n\t ConditioningRegimen(): " + graftForm.getConditioningRegimen() 
                 + "\n\t OtherConditioningRegimen: " + graftForm.getOtherConditioningRegimen()                 
                 + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));
        
        try
        {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addGraft(theAnimalModel, graftForm);

            log.info("New Graft created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("graft.creation.successful"));
            saveErrors(request, msg);

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Graft", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("<GraftAction> Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}