/**
 * 
 * $Id: EnvironmentalFactorAction.java,v 1.20 2007-10-31 17:09:46 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.19  2006/05/23 16:59:32  pandyas
 * added printout for debug
 *
 * Revision 1.18  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.17  2005/11/09 00:17:26  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.16  2005/11/02 21:48:17  georgeda
 * Fixed validate
 *
 * Revision 1.15  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.14  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.13  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.12  2005/10/19 19:27:28  pandyas
 * fixed println statement
 *
 * Revision 1.11  2005/09/28 21:20:11  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * EnvironmentalFactorAction Class
 */
public final class EnvironmentalFactorAction extends BaseAction {

	/**
	 * Called from submitEnvironmentalFactors.jsp
	 * 
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

		System.out.println("<EnvironmentalFactorAction save> following Characteristics:" + "\n\t name: "
				+ envForm.getName() + "\n\t otherName: " + envForm.getOtherName() + "\n\t dosage: "
				+ envForm.getDosage() + "\n\t envForm.getDosageUnit()" + envForm.getDosageUnit() + "\n\t administrativeRoute: " + envForm.getAdministrativeRoute()
				+ "\n\t regimen: " + envForm.getRegimen() + "\n\t ageAtTreatment: " + envForm.getAgeAtTreatment()
				 + "\n\t getAgeAtTreatmentUnit(): " + envForm.getAgeAtTreatmentUnit()
                + "\n\t type: " + envForm.getType() + "\n\t Comments: " + envForm.getComments()
                + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Grab the current modelID from the session */
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		/* Create all the manager objects needed for Screen */
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addCarcinogenExposure(animalModel, envForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("environmentalfactor.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add an environmental factor: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

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

		log.debug("Entering 'edit' method");

		// Grab the current CarcinogenExposure we are working with related to this
		// animalModel
		String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");
		
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		// Create a form to edit
		EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

		System.out.println("<EnvironmentalFactorAction save> following Characteristics:" + "\n\t name: "
				+ envForm.getName() + "\n\t otherName: " + envForm.getOtherName() + "\n\t dosage: "
				+ envForm.getDosage()  + "\n\t envForm.getDosageUnit()" + envForm.getDosageUnit() 
                + "\n\t administrativeRoute: " + envForm.getAdministrativeRoute()
				+ "\n\t regimen: " + envForm.getRegimen() + "\n\t ageAtTreatment: " + envForm.getAgeAtTreatment()
				+ "\n\t getAgeAtTreatmentUnit(): " + envForm.getAgeAtTreatmentUnit()
                + "\n\t type: " + envForm.getType() + "\n\t Comments: " + envForm.getComments()
                + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
            if ("Delete".equals(theAction)) {
                
                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
                
                carcinogenExposureManager.remove(aCarcinogenExposureID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("environmentalfactor.delete.successful"));
				saveErrors(request, msg);

			} else {
		        // retrieve animal model by it's id	        
		        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
		        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);				

                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, envForm, theCarcinogenExposure);
                
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("environmentalfactor.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get an environmental factor: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

}