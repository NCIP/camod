/**
 * 
 * $Id: XenograftAction.java,v 1.20 2007-03-26 12:02:30 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.webapp.form.XenograftForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * XenograftAction Class
 */
public final class XenograftAction extends BaseAction
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
        log.info("<XenograftAction> Entering edit");

        // Create a form to edit
        XenograftForm xenograftForm = (XenograftForm) form;

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        String aXenograftID = request.getParameter("aXenograftID");

        log.info("<XenograftAction edit> following Characteristics:"
                    + "\n\t name: "
                    + xenograftForm.getXenograftName()
                    + "\n\t atccNumber: "
                    + xenograftForm.getAtccNumber()
                    + "\n\t ParentalCellLineName: "
                    + xenograftForm.getParentalCellLineName()
                    + "\n\t getCellAmount: "
                    + xenograftForm.getCellAmount()
                    + "\n\t getGrowthPeriod: "
                    + xenograftForm.getGrowthPeriod()                
                    + "\n\t getModificationDescription: "
                    + xenograftForm.getModificationDescription()
                    + "\n\t getGeneticManipulation: "
                    + xenograftForm.getGeneticManipulation()
                    + "\n\t getAdministrativeSite: "
                    + xenograftForm.getAdministrativeSite()
                    + "\n\t getOtherAdministrativeSite: "
                    + xenograftForm.getOtherAdministrativeSite()                
                    + "\n\t getGraftType: "
                    + xenograftForm.getGraftType()
                    + "\n\t getOtherGraftType: "
                    + xenograftForm.getOtherGraftType()
                    + "\n\t getDonorScientificName: "
                    + xenograftForm.getDonorScientificName()
                    + "\n\t getOtherDonorScientificName: "
                    + xenograftForm.getOtherDonorScientificName()                  
                    + "\n\t getDonorEthinicityStrain: "
                    + xenograftForm.getDonorEthinicityStrain()              
                    + "\n\t getOtherDonorEthinicityStrain: "
                    + xenograftForm.getOtherDonorEthinicityStrain()
                    + "\n\t getOrganTissueName(): "
                    + xenograftForm.getOrganTissueName()
                    + "\n\t organTissueCode: "
                    + xenograftForm.getOrganTissueCode()
                    + "\n\t organ(): "
                    + xenograftForm.getOrgan() 
                    + "\n\t ConditioningRegime(): "
                    + xenograftForm.getConditioningRegime() 
                    + "\n\t OtherConditioningRegime: "
                    + xenograftForm.getOtherConditioningRegime()
                    + "\n\t user: "
                    + (String) request.getSession().getAttribute(
                            "camod.loggedon.username"));
        
        try
        {
            XenograftManager xenograftManager = (XenograftManager) getBean("xenograftManager");

            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            if ("Delete".equals(theAction))
            {
                xenograftManager.remove(aXenograftID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.delete.successful"));
                saveErrors(request, msg);
            }
            else
            {
                Xenograft theXenograft = xenograftManager.get(aXenograftID);

                xenograftManager.update(xenograftForm, theXenograft, theAnimalModel);

                // Add a message to be displayed in submitOverview.jsp
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.edit.successful"));
                saveErrors(request, msg);
            }

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Xenograft", e);

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
        log.trace("<XenograftAction> Entering save");

        // Create a form to edit
        XenograftForm xenograftForm = (XenograftForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.info("<XenograftAction save> following Characteristics:" + "\n\t name: " 
                 + xenograftForm.getXenograftName() 
                 + "\n\t atccNumber: " + xenograftForm.getAtccNumber() 
                 + "\n\t ParentalCellLineName: " + xenograftForm.getParentalCellLineName() 
                 + "\n\t getCellAmount: " + xenograftForm.getCellAmount() + "\n\t getGrowthPeriod: " + xenograftForm.getGrowthPeriod() 
                 + "\n\t getModificationDescription: " + xenograftForm.getModificationDescription() 
                 + "\n\t getGeneticManipulation: " + xenograftForm.getGeneticManipulation() 
                 + "\n\t getAdministrativeSite: " + xenograftForm.getAdministrativeSite() 
                 + "\n\t getOtherAdministrativeSite: " + xenograftForm.getOtherAdministrativeSite() 
                 + "\n\t getGraftType: " + xenograftForm.getGraftType() 
                 + "\n\t getOtherGraftType: " + xenograftForm.getOtherGraftType() 
                 + "\n\t getDonorScientificName: " + xenograftForm.getDonorScientificName() 
                 + "\n\t getOtherDonorScientificName: " + xenograftForm.getOtherDonorScientificName() 
                 + "\n\t getDonorEthinicityStrain: " + xenograftForm.getDonorEthinicityStrain() 
                 + "\n\t getOtherDonorEthinicityStrain: " + xenograftForm.getOtherDonorEthinicityStrain() + xenograftForm.getOtherDonorEthinicityStrain() 
                 + "\n\t getOrganTissueName(): " + xenograftForm.getOrganTissueName() 
                 + "\n\t organTissueCode: " + xenograftForm.getOrganTissueCode() 
                 + "\n\t organ(): " + xenograftForm.getOrgan() 
                 + "\n\t ConditioningRegime(): " + xenograftForm.getConditioningRegime() 
                 + "\n\t OtherConditioningRegime: " + xenograftForm.getOtherConditioningRegime()                 
                 + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));
        
        try
        {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addXenograft(theAnimalModel, xenograftForm);

            log.info("New Xenograft created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.creation.successful"));
            saveErrors(request, msg);

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Xenograft", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("<XenograftAction> Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}