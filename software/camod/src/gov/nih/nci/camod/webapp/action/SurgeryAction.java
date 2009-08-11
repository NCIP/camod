/**
 * $Id: SurgeryAction.java,v 1.14 2008-08-14 16:59:10 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2007/10/31 17:13:12  pandyas
 * Modified comments for #8355 	Add comments field to every submission page
 *
 * Revision 1.12  2006/11/09 17:31:59  pandyas
 * Commented out debug code
 *
 * Revision 1.11  2006/10/27 16:34:15  pandyas
 * fixed printout on error - typo
 *
 * Revision 1.10  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.9  2005/11/09 00:17:25  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.8  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.7  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.6  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.5  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.4  2005/09/28 21:20:11  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.webapp.form.SurgeryForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * SugeryAction Class
 */

public class SurgeryAction extends BaseAction {

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

        log.debug("<SurgeryAction edit> Entering... ");
		
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        // Grab the current CarcinogenExposure we are working with related to this animalModel
        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");

		SurgeryForm surgeryForm = (SurgeryForm) form;

        log.info("<SurgeryAction editing> editing... " + "\n\t name: " + surgeryForm.getName()
				+ "\n\t otherName: " + surgeryForm.getOtherName() + "\n\t type: " + surgeryForm.getType()
				+ "\n\t regimen: " + surgeryForm.getRegimen() + "\n\t Comments: " + surgeryForm.getComments()
				+ "\n\t ageAtTreatmentUnit: " + surgeryForm.getAgeAtTreatmentUnit());

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
	        // retrieve animal model by it's id	        
	        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
	        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);			

            if ("Delete".equals(theAction)) {
                carcinogenExposureManager.remove(aCarcinogenExposureID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("surgeryother.delete.successful"));
				saveErrors(request, msg);

			} else {

                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, surgeryForm, theCarcinogenExposure);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("surgeryother.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get add a sugery action: ", e);

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

		log.debug("<SurgeryAction save> Entering... ");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		SurgeryForm surgeryForm = (SurgeryForm) form;

        log.info("<SurgeryAction save> Adding... " + "\n\t name: " + surgeryForm.getName()
				+ "\n\t otherName: " + surgeryForm.getOtherName() + "\n\t type: " + surgeryForm.getType()
				+ "\n\t regimen: " + surgeryForm.getRegimen() + "\n\t Comments: " + surgeryForm.getComments()
				+ "\n\t ageAtTreatmentUnit: " + surgeryForm.getAgeAtTreatmentUnit());

		/* Create all the manager objects needed for Screen */
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addCarcinogenExposure(animalModel, surgeryForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("surgeryother.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add an environmental factor: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}
}
