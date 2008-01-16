/**
 * 
 * $Id: TransplantAction.java,v 1.1 2008-01-16 18:29:57 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/10/31 17:46:57  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
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
import gov.nih.nci.camod.domain.Transplant;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TransplantManager;
import gov.nih.nci.camod.webapp.form.TransplantForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * TransplantAction Class
 */
public final class TransplantAction extends BaseAction
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
        log.debug("<TransplantAction> Entering edit");

        // Create a form to edit
        TransplantForm transplantForm = (TransplantForm) form;

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        String aTransplantID = request.getParameter("aTransplantID");

        log.debug("<TransplantAction edit> following Characteristics:"
                    + "\n\t name: "
                    + transplantForm.getName()
                    + "\n\t atccNumber: "
                    + transplantForm.getAtccNumber()
                    + "\n\t ParentalCellLineName: "
                    + transplantForm.getParentalCellLineName()
                    + "\n\t getCellAmount: "
                    + transplantForm.getCellAmount()
                    + "\n\t getGrowthPeriod: "
                    + transplantForm.getGrowthPeriod()                
                    + "\n\t getModificationDescription: "
                    + transplantForm.getModificationDescription()
                    + "\n\t getGeneticManipulation: "
                    + transplantForm.getGeneticManipulation()
                    + "\n\t getAdministrativeSite: "
                    + transplantForm.getAdministrativeSite()
                    + "\n\t getOtherAdministrativeSite: "
                    + transplantForm.getOtherAdministrativeSite()                
                    + "\n\t getSourceType: "
                    + transplantForm.getSourceType()
                    + "\n\t getOtherSourceType: "
                    + transplantForm.getOtherSourceType()
                    + "\n\t getDonorScientificName: "
                    + transplantForm.getDonorScientificName()
                    + "\n\t getOtherDonorScientificName: "
                    + transplantForm.getOtherDonorScientificName()                  
                    + "\n\t getDonorEthinicityStrain: "
                    + transplantForm.getDonorEthinicityStrain()              
                    + "\n\t getOtherDonorEthinicityStrain: "
                    + transplantForm.getOtherDonorEthinicityStrain()
                    + "\n\t getOrganTissueName(): "
                    + transplantForm.getOrganTissueName()
                    + "\n\t organTissueCode: "
                    + transplantForm.getOrganTissueCode()
                    + "\n\t organ(): "
                    + transplantForm.getOrgan() 
                    + "\n\t ConditioningRegimen(): "
                    + transplantForm.getConditioningRegimen() 
                    + "\n\t OtherConditioningRegimen: "
                    + transplantForm.getOtherConditioningRegimen()
                    + "\n\t Comments: "
                    + transplantForm.getComments()                    
                    + "\n\t user: "
                    + (String) request.getSession().getAttribute(
                            "camod.loggedon.username"));
        
        try
        {
        	TransplantManager transplantManager = (TransplantManager) getBean("transplantManager");

            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            if ("Delete".equals(theAction))
            {
            	transplantManager.remove(aTransplantID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transplant.delete.successful"));
                saveErrors(request, msg);
            }
            else
            {
            	Transplant theTransplant = transplantManager.get(aTransplantID);

                transplantManager.update(transplantForm, theTransplant, theAnimalModel);

                // Add a message to be displayed in submitOverview.jsp
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transplant.edit.successful"));
                saveErrors(request, msg);
            }

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Transplant", e);

            // Encountered an error saving the Transplant.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("<TransplantAction> Exiting edit");
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
        log.trace("<TransplantAction> Entering save");

        // Create a form to edit
        TransplantForm transplantForm = (TransplantForm) form;

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<TransplantAction save> following Characteristics:" + "\n\t name: " 
                 + transplantForm.getName() 
                 + "\n\t atccNumber: " + transplantForm.getAtccNumber() 
                 + "\n\t ParentalCellLineName: " + transplantForm.getParentalCellLineName() 
                 + "\n\t getCellAmount: " + transplantForm.getCellAmount() + "\n\t getGrowthPeriod: " + transplantForm.getGrowthPeriod() 
                 + "\n\t getModificationDescription: " + transplantForm.getModificationDescription() 
                 + "\n\t getGeneticManipulation: " + transplantForm.getGeneticManipulation() 
                 + "\n\t getAdministrativeSite: " + transplantForm.getAdministrativeSite() 
                 + "\n\t getOtherAdministrativeSite: " + transplantForm.getOtherAdministrativeSite() 
                 + "\n\t getSourceType: " + transplantForm.getSourceType() 
                 + "\n\t getOtherSourceType: " + transplantForm.getOtherSourceType() 
                 + "\n\t getDonorScientificName: " + transplantForm.getDonorScientificName() 
                 + "\n\t getOtherDonorScientificName: " + transplantForm.getOtherDonorScientificName() 
                 + "\n\t getDonorEthinicityStrain: " + transplantForm.getDonorEthinicityStrain() 
                 + "\n\t getOtherDonorEthinicityStrain: " + transplantForm.getOtherDonorEthinicityStrain() + transplantForm.getOtherDonorEthinicityStrain() 
                 + "\n\t getOrganTissueName(): " + transplantForm.getOrganTissueName() 
                 + "\n\t organTissueCode: " + transplantForm.getOrganTissueCode() 
                 + "\n\t organ(): " + transplantForm.getOrgan() 
                 + "\n\t ConditioningRegimen(): " + transplantForm.getConditioningRegimen() 
                 + "\n\t Comments: "  + transplantForm.getComments()                  
                 + "\n\t OtherConditioningRegimen: " + transplantForm.getOtherConditioningRegimen()                 
                 + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));
        
        try
        {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            theAnimalModelManager.addTransplant(theAnimalModel, transplantForm);

            log.debug("New Transplant created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transplant.creation.successful"));
            saveErrors(request, msg);

        }
        catch (Exception e)
        {
            log.error("Exception ocurred creating Transplant", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("<TransplantAction> Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}