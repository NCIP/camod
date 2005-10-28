/**
 * $Id: HormoneAction.java,v 1.5 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/09/28 21:20:12  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.HormoneForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * HormoneAction Class
 */

public class HormoneAction extends BaseAction {

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

		System.out.println("<HormoneAction edit> Entering... ");

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");

		HormoneForm hormoneForm = (HormoneForm) form;

		System.out.println("<HormoneAction editing> editing... " + "\n\t name: " + hormoneForm.getName()
				+ "\n\t otherName: " + hormoneForm.getOtherName() + "\n\t regimen: " + hormoneForm.getRegimen()
				+ "\n\t dosage: " + hormoneForm.getDosage() + "\n\t doseUnit: " + hormoneForm.getDoseUnit());

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if (theAction.equals("Delete")) {
				therapyManager.remove(aTherapyID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hormone.delete.successful"));
				saveErrors(request, msg);

			} else {
				
				Therapy theTherapy = therapyManager.get(aTherapyID);
				therapyManager.update(hormoneForm, theTherapy);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hormone.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to edit a hormone: ", e);

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

		System.out.println("<HormoneAction save> Entering... ");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		HormoneForm hormoneForm = (HormoneForm) form;

		System.out.println("<HormoneAction save> Adding... " + "\n\t name: " + hormoneForm.getName()
				+ "\n\t otherName: " + hormoneForm.getOtherName() + "\n\t regimen: " + hormoneForm.getRegimen()
				+ "\n\t ageUnit: " + hormoneForm.getDosage());

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addTherapy(animalModel, hormoneForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hormone.creation.successful"));
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
