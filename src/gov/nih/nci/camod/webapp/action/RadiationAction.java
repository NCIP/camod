/**
 * 
 * $Id: RadiationAction.java,v 1.13 2006-04-17 19:09:41 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2005/11/09 00:17:26  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.11  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.10  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.9  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/20 20:39:32  pandyas
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
import gov.nih.nci.camod.webapp.form.RadiationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * RadiationAction Class
 */

public class RadiationAction extends BaseAction {

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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

        // Grab the current CarcinogenExposure we are working with related to this animalModel
        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");

		// Create a form to edit
		RadiationForm radiationForm = (RadiationForm) form;

		System.out.println("<EnvironmentalFactorAction save> following Characteristics:" + "\n\t name: "
				+ radiationForm.getName() + "\n\t otherName: " + radiationForm.getOtherName() + "\n\t dosage: "
				+ radiationForm.getDosage() 
                 + "\n\t dosageUnit: " + radiationForm.getDosageUnit()
                + "\n\t administrativeRoute: " + radiationForm.getAdministrativeRoute()
				+ "\n\t regimen: " + radiationForm.getRegimen() + "\n\t ageAtTreatment: " 
				+ radiationForm.getAgeAtTreatment() 
                + radiationForm.getAgeAtTreatmentUnit()
                + "\n\t type: " + radiationForm.getType() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
            // Grab the current modelID from the session
            String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            if ("Delete".equals(theAction)) {
                carcinogenExposureManager.remove(aCarcinogenExposureID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("radiation.delete.successful"));
				saveErrors(request, msg);

			} else {
		        				
                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, radiationForm, theCarcinogenExposure);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("radiation.edit.successful"));
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
			log.debug("Entering 'save' method");
		}
		RadiationForm radiationForm = (RadiationForm) form;

		System.out.println("<EnvironmentalFactorAction save> following Characteristics:" + "\n\t name: "
				+ radiationForm.getName() + "\n\t otherName: " + radiationForm.getOtherName() + "\n\t dosage: "
				+ radiationForm.getDosage() 
                 + "\n\t dosageUnit: " + radiationForm.getDosageUnit()
                + "\n\t administrativeRoute: " + radiationForm.getAdministrativeRoute()
				+ "\n\t regimen: " + radiationForm.getRegimen() + "\n\t ageAtTreatment: "
				+ radiationForm.getAgeAtTreatment() + radiationForm.getAgeAtTreatmentUnit()
                + "\n\t type: " + radiationForm.getType() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Grab the current modelID from the session */
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		/* Create all the manager objects needed for Screen */
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

		/* Set modelID in AnimalModel object */
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addCarcinogenExposure(animalModel, radiationForm);

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("radiation.creation.successful"));
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
