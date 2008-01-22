/**
 * 
 * $Id: TransplantationAction.java,v 1.1 2007-10-31 17:46:57 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.3  2007/08/07 18:28:05  pandyas
 * Renamed to GRAFT as per VCDE comments
 *
 * Revision 1.2  2007/08/01 18:03:59  pandyas
 * VCDE changes
 *
 * Revision 1.1  2007/07/31 12:05:26  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
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
import gov.nih.nci.camod.domain.Transplantation;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TransplantationManager;
import gov.nih.nci.camod.webapp.form.TransplantationForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * TransplantationAction Class
 */
public final class TransplantationAction extends BaseAction
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
        log.debug("<TransplantationAction> Entering edit");

        // Create a form to edit
        TransplantationForm transplantationForm = (TransplantationForm) form;

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        String aTransplantID = request.getParameter("aTransplantID");

        log.debug("<TransplantationAction edit> following Characteristics:"
                    + "\n\t name: "
                    + transplantationForm.getName()
                    + "\n\t atccNumber: "
                    + transplantationForm.getAtccNumber()
                    + "\n\t ParentalCellLineName: "
                    + transplantationForm.getParentalCellLineName()
                    + "\n\t getCellAmount: "
                    + transplantationForm.getCellAmount()
                    + "\n\t getGrowthPeriod: "
                    + transplantationForm.getGrowthPeriod()                
                    + "\n\t getModificationDescription: "
                    + transplantationForm.getModificationDescription()
                    + "\n\t getGeneticManipulation: "
                    + transplantationForm.getGeneticManipulation()
                    + "\n\t getAdministrativeSite: "
                    + transplantationForm.getAdministrativeSite()
                    + "\n\t getOtherAdministrativeSite: "
                    + transplantationForm.getOtherAdministrativeSite()                
                    + "\n\t getSourceType: "
                    + transplantationForm.getSourceType()
                    + "\n\t getOtherSourceType: "
                    + transplantationForm.getOtherSourceType()
                    + "\n\t getDonorScientificName: "
                    + transplantationForm.getDonorScientificName()
                    + "\n\t getOtherDonorScientificName: "
                    + transplantationForm.getOtherDonorScientificName()                  
                    + "\n\t getDonorEthinicityStrain: "
                    + transplantationForm.getDonorEthinicityStrain()              
                    + "\n\t getOtherDonorEthinicityStrain: "
                    + transplantationForm.getOtherDonorEthinicityStrain()
                    + "\n\t getOrganTissueName(): "
                    + transplantationForm.getOrganTissueName()
                    + "\n\t organTissueCode: "
                    + transplantationForm.getOrganTissueCode()
                    + "\n\t organ(): "
                    + transplantationForm.getOrgan() 
                    + "\n\t ConditioningRegimen(): "
                    + transplantationForm.getConditioningRegimen() 
                    + "\n\t OtherConditioningRegimen: "
                    + transplantationForm.getOtherConditioningRegimen()
                    + "\n\t Comments: "
                    + transplantationForm.getComments()                    
                    + "\n\t user: "
                    + (String) request.getSession().getAttribute(
                            "camod.loggedon.username"));
        
        try
        {
        	TransplantationManager transplantationManager = (TransplantationManager) getBean("transplantationManager");

            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            if ("Delete".equals(theAction))
            {
            	transplantationManager.remove(aTransplantID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transplantation.delete.successful"));
                saveErrors(request, msg);
            }
            else
            {
            	Transplantation theTransplantation = transplantationManager.get(aTransplantID);

                transplantationManager.update(transplantationForm, theTransplantation, theAnimalModel);

                // Add a message to be displayed in submitOverview.jsp
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transplantation.edit.successful"));
                saveErrors(request, msg);
            }

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Transplantation", e);

            // Encountered an error saving the Transplantation.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("<TransplantationAction> Exiting edit");
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
        log.trace("<TransplantationAction> Entering save");

        // Create a form to edit
        TransplantationForm transplantationForm = (TransplantationForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<TransplantationAction save> following Characteristics:" + "\n\t name: " 
                 + transplantationForm.getName() 
                 + "\n\t atccNumber: " + transplantationForm.getAtccNumber() 
                 + "\n\t ParentalCellLineName: " + transplantationForm.getParentalCellLineName() 
                 + "\n\t getCellAmount: " + transplantationForm.getCellAmount() + "\n\t getGrowthPeriod: " + transplantationForm.getGrowthPeriod() 
                 + "\n\t getModificationDescription: " + transplantationForm.getModificationDescription() 
                 + "\n\t getGeneticManipulation: " + transplantationForm.getGeneticManipulation() 
                 + "\n\t getAdministrativeSite: " + transplantationForm.getAdministrativeSite() 
                 + "\n\t getOtherAdministrativeSite: " + transplantationForm.getOtherAdministrativeSite() 
                 + "\n\t getSourceType: " + transplantationForm.getSourceType() 
                 + "\n\t getOtherSourceType: " + transplantationForm.getOtherSourceType() 
                 + "\n\t getDonorScientificName: " + transplantationForm.getDonorScientificName() 
                 + "\n\t getOtherDonorScientificName: " + transplantationForm.getOtherDonorScientificName() 
                 + "\n\t getDonorEthinicityStrain: " + transplantationForm.getDonorEthinicityStrain() 
                 + "\n\t getOtherDonorEthinicityStrain: " + transplantationForm.getOtherDonorEthinicityStrain() + transplantationForm.getOtherDonorEthinicityStrain() 
                 + "\n\t getOrganTissueName(): " + transplantationForm.getOrganTissueName() 
                 + "\n\t organTissueCode: " + transplantationForm.getOrganTissueCode() 
                 + "\n\t organ(): " + transplantationForm.getOrgan() 
                 + "\n\t ConditioningRegimen(): " + transplantationForm.getConditioningRegimen() 
                 + "\n\t Comments: "  + transplantationForm.getComments()                  
                 + "\n\t OtherConditioningRegimen: " + transplantationForm.getOtherConditioningRegimen()                 
                 + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));
        
        try
        {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addTransplantation(theAnimalModel, transplantationForm);

            log.debug("New Transplantation created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transplantation.creation.successful"));
            saveErrors(request, msg);

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Transplantation", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("<TransplantationAction> Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}