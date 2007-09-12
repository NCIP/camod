/**
 * 
 * $Id: ChemicalDrugAction.java,v 1.18 2007-09-12 19:36:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2006/11/09 17:24:18  pandyas
 * Commented out debug code
 *
 * Revision 1.16  2006/05/23 17:01:14  pandyas
 * fixed save of ageOfTreatmentUntis - made code consistent
 *
 * Revision 1.15  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.14  2005/11/09 00:17:26  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.13  2005/11/02 21:47:01  georgeda
 * Fixed validate
 *
 * Revision 1.12  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.11  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.10  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.9  2005/10/20 20:37:16  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * ChemicalDrugAction Class
 */

public class ChemicalDrugAction extends BaseAction {

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

		log.debug("<ChemicalDrugAction> Entering 'edit' method");

		//System.out.println("<ChemicalDrugAction edit> Entering... ");

		// Grab the current CarcinogenExposure we are working with related to this animalModel
		String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");

        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		ChemicalDrugForm chemicalDrugForm = (ChemicalDrugForm) form;

		System.out.println("<ChemicalDrugAction editing> editing... " + "\n\t name: " + chemicalDrugForm.getName()
				+ "\n\t otherName: " + chemicalDrugForm.getOtherName() + "\n\t type: " + chemicalDrugForm.getType()
				+ "\n\t regimen: " + chemicalDrugForm.getRegimen() + "\n\t dosage: " + chemicalDrugForm.getDosage()
				+ "\n\t dosageUnit: " + chemicalDrugForm.getDosageUnit() + "\n\t ageAtTreatment: "
				+ chemicalDrugForm.getAgeAtTreatment() + "\n\t ageAtTreatmentUnit: " + chemicalDrugForm.getAgeAtTreatmentUnit()
				+ "\n\t administrativeRoute: " + chemicalDrugForm.getAdministrativeRoute());

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {	    
            // retrieve animal model by it's id         
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);                
            
            if ("Delete".equals(theAction)) {
                
                carcinogenExposureManager.remove(aCarcinogenExposureID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.delete.successful"));
				saveErrors(request, msg);

			} else {

                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, chemicalDrugForm, theCarcinogenExposure);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get add a chemical drug action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

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
		if (log.isDebugEnabled()) {
			log.debug("<ChemicalDrugAction> Entering 'save' method");
		}

		//System.out.println("<ChemicalDrugAction save> Entering... ");
        
        ChemicalDrugForm chemicalDrugForm = (ChemicalDrugForm) form;        

		System.out.println("<ChemicalDrugAction save> Adding... " + "\n\t name: " + chemicalDrugForm.getName()
				+ "\n\t otherName: " + chemicalDrugForm.getOtherName() + "\n\t type: " + chemicalDrugForm.getType()
				+ "\n\t regimen: " + chemicalDrugForm.getRegimen() 
                + "\n\t dosage: " + chemicalDrugForm.getDosage()
				+ "\n\t dosageUnit: " + chemicalDrugForm.getDosageUnit() 
				+ "\n\t ageAtTreatment: " + chemicalDrugForm.getAgeAtTreatment() 
                + "\n\t getAgeAtTreatmentUnit: " + chemicalDrugForm.getAgeAtTreatmentUnit()
                + "\n\t nscNumber: " + chemicalDrugForm.getNscNumber() + "\n\t CasNumber: "
				+ chemicalDrugForm.getCasNumber());

        /* Grab the current modelID from the session */
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);        

        /* Create all the manager objects needed for Screen */
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);

		try {
            log.debug("<ChemicalDrugAction> Entering try block"); 
			animalModelManager.addCarcinogenExposure(animalModel, chemicalDrugForm);

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add a chemical drug action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

}
