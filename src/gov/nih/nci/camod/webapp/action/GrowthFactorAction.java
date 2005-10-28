/**
 * $Id: GrowthFactorAction.java,v 1.7 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/10/19 19:26:19  pandyas
 * added admin route to growth factor
 *
 * Revision 1.5  2005/09/28 21:20:12  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * GrowthFactorAction Class
 */

public class GrowthFactorAction extends BaseAction {

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

		System.out.println("<GrowthFactorAction edit> Entering... ");

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");

		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;

		System.out.println("<GrowthFactorAction editing> editing... " + "\n\t name: " + growthFactorForm.getName()
				+ "\n\t otherName: " + growthFactorForm.getOtherName() + "\n\t type: " + growthFactorForm.getType()
				+ "\n\t regimen: " + growthFactorForm.getRegimen() + "\n\t dosage: " + growthFactorForm.getDosage()
				+ "\n\t doseUnit: " + growthFactorForm.getDoseUnit() + "\n\t ageAtTreatment: "
				+ growthFactorForm.getAgeAtTreatment() + "\n\t ageUnit: " + growthFactorForm.getAgeUnit());

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if (theAction.equals("Delete")) {
				therapyManager.remove(aTherapyID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("growthFactor.delete.successful"));
				saveErrors(request, msg);

			} else {

				Therapy theTherapy = therapyManager.get(aTherapyID);
				therapyManager.update(growthFactorForm, theTherapy);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("growthFactor.edit.successful"));
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

		System.out.println("<GrowthFactorAction save> Entering... ");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		GrowthFactorForm growthFactorForm = (GrowthFactorForm) form;

		System.out.println("<GrowthFactorAction save> Adding... " + "\n\t name: " + growthFactorForm.getName()
				+ "\n\t otherName: " + growthFactorForm.getOtherName() + "\n\t type: " + growthFactorForm.getType()
				+ "\n\t regimen: " + growthFactorForm.getRegimen() + "\n\t dosage: " + growthFactorForm.getDosage()
				+ "\n\t doseUnit: " + growthFactorForm.getDoseUnit() + "\n\t administrativeRoute: "
				+ growthFactorForm.getAdministrativeRoute() + "\n\t ageAtTreatment: "
				+ growthFactorForm.getAgeAtTreatment() + "\n\t ageUnit: " + growthFactorForm.getAgeUnit()
				+ "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addTherapy(animalModel, growthFactorForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("growthFactor.creation.successful"));
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
