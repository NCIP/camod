/**
 * 
 * $Id: NutritionalFactorAction.java,v 1.7 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/10/20 20:39:04  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.NutritionalFactorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * NutritionalFactorAction Class
 * 
 */
public class NutritionalFactorAction extends BaseAction {

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

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");

		// Create a form to edit
		NutritionalFactorForm nutritForm = (NutritionalFactorForm) form;

		System.out.println("<NutritionalFactorAction save> following Characteristics:" + "\n\t name: "
				+ nutritForm.getName() + "\n\t otherName: " + nutritForm.getOtherName() + "\n\t dosage: "
				+ nutritForm.getDosage() + "\n\t regimen: " + nutritForm.getRegimen() + "\n\t ageAtTreatment: "
				+ nutritForm.getAgeAtTreatment() + "\n\t type: " + nutritForm.getType() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if (theAction.equals("Delete")) {
				therapyManager.remove(aTherapyID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nutritionalfactor.delete.successful"));
				saveErrors(request, msg);

			} else {

				Therapy theTherapy = therapyManager.get(aTherapyID);
				therapyManager.update(nutritForm, theTherapy);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nutritionalfactor.edit.successful"));
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
		NutritionalFactorForm nutritForm = (NutritionalFactorForm) form;

		System.out.println("<NutritionalFactorAction save> following Characteristics:" + "\n\t name: "
				+ nutritForm.getName() + "\n\t otherName: " + nutritForm.getOtherName() + "\n\t dosage: "
				+ nutritForm.getDosage() + "\n\t regimen: " + nutritForm.getRegimen() + "\n\t ageAtTreatment: "
				+ nutritForm.getAgeAtTreatment() + "\n\t type: " + nutritForm.getType() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Grab the current modelID from the session */
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		/* Create all the manager objects needed for Screen */
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

		/* Set modelID in AnimalModel object */
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addTherapy(animalModel, nutritForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("nutritionalfactor.creation.successful"));
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
