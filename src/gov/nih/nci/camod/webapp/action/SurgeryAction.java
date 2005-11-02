/**
 * $Id: SurgeryAction.java,v 1.7 2005-11-02 19:02:08 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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

		System.out.println("<SurgeryAction edit> Entering... ");
		
		//	Grab the current modelID we are working with
        String modelID = request.getParameter("aModelID");		

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");

		SurgeryForm surgeryForm = (SurgeryForm) form;

		System.out.println("<SurgeryAction editing> editing... " + "\n\t name: " + surgeryForm.getName()
				+ "\n\t otherName: " + surgeryForm.getOtherName() + "\n\t type: " + surgeryForm.getType()
				+ "\n\t regimen: " + surgeryForm.getRegimen() + "\n\t ageUnit: " + surgeryForm.getAgeUnit());

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
	        // retrieve animal model by it's id	        
	        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
	        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);			

            if ("Delete".equals(theAction)) {
				therapyManager.remove(aTherapyID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("surgeryother.delete.successful"));
				saveErrors(request, msg);

			} else {

				Therapy theTherapy = therapyManager.get(aTherapyID);
				therapyManager.update(theAnimalModel, surgeryForm, theTherapy);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("surgeryother.edit.successful"));
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

		System.out.println("<SurgeryAction save> Entering... ");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		SurgeryForm surgeryForm = (SurgeryForm) form;

		System.out.println("<SurgeryAction save> Adding... " + "\n\t name: " + surgeryForm.getName()
				+ "\n\t otherName: " + surgeryForm.getOtherName() + "\n\t type: " + surgeryForm.getType()
				+ "\n\t regimen: " + surgeryForm.getRegimen() + "\n\t ageUnit: " + surgeryForm.getAgeUnit());

		/* Create all the manager objects needed for Screen */
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addTherapy(animalModel, surgeryForm);

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
