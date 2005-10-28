package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.webapp.form.XenograftForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * XenograftAction Class
 */
public final class XenograftAction extends BaseAction {

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

		log.debug("<XenograftAction> Entering edit");

		// Create a form to edit
		XenograftForm xenograftForm = (XenograftForm) form;

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
		String aXenograftID = request.getParameter("aXenograftID");

		log.info("<XenograftAction edit> following Characteristics:" + "\n\t name: " + xenograftForm.getName()
				+ "\n\t ATTCNumber: " + xenograftForm.getATCCNumber() + "\n\t ParentalCellLineName: "
				+ xenograftForm.getParentalCellLineName() + "\n\t getCellAmount: " + xenograftForm.getCellAmount()
				+ "\n\t getHarvestDate: " + xenograftForm.getHarvestDate() + "\n\t getModificationDescription: "
				+ xenograftForm.getModificationDescription() + "\n\t getGeneticManipulation: "
				+ xenograftForm.getGeneticManipulation() + "\n\t getAdministrativeSite: "
				+ xenograftForm.getAdministrativeSite() + "\n\t getGraftType: " + xenograftForm.getGraftType()
				+ "\n\t getOtherGraftType: " + xenograftForm.getOtherGraftType() + "\n\t getHostScientificName: "
				+ xenograftForm.getHostScientificName() + "\n\t getHostEthinicityStrain: "
				+ xenograftForm.getHostEthinicityStrain() + "\n\t getOtherHostEthinicityStrain: "
				+ xenograftForm.getOtherHostEthinicityStrain() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
			XenograftManager xenograftManager = (XenograftManager) getBean("xenograftManager");

			if (theAction.equals("Delete")) {
				xenograftManager.remove(aXenograftID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.delete.successful"));
				saveErrors(request, msg);

			} else {

				// retrieve model and update w/ new values
				AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
				AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

				Xenograft theXenograft = xenograftManager.get(aXenograftID);

				xenograftManager.update(xenograftForm, theXenograft, theAnimalModel);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {
			log.error("Exception ocurred creating Xenograft", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);

			theForward = "failure";
		}

		log.debug("<XenograftAction> Exiting edit");
		return mapping.findForward(theForward);
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

		log.trace("<XenograftAction> Entering save");

		// Create a form to edit
		XenograftForm xenograftForm = (XenograftForm) form;

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

		log.info("<XenograftAction save> following Characteristics:" + "\n\t name: " + xenograftForm.getName()
				+ "\n\t ATTCNumber: " + xenograftForm.getATCCNumber() + "\n\t ParentalCellLineName: "
				+ xenograftForm.getParentalCellLineName() + "\n\t getCellAmount: " + xenograftForm.getCellAmount()
				+ "\n\t getHarvestDate: " + xenograftForm.getHarvestDate() + "\n\t getModificationDescription: "
				+ xenograftForm.getModificationDescription() + "\n\t getGeneticManipulation: "
				+ xenograftForm.getGeneticManipulation() + "\n\t getAdministrativeSite: "
				+ xenograftForm.getAdministrativeSite() + "\n\t getGraftType: " + xenograftForm.getGraftType()
				+ "\n\t getOtherGraftType: " + xenograftForm.getOtherGraftType() + "\n\t getHostScientificName: "
				+ xenograftForm.getHostScientificName() + "\n\t getHostEthinicityStrain: "
				+ xenograftForm.getHostEthinicityStrain() + "\n\t getOtherHostEthinicityStrain: "
				+ xenograftForm.getOtherHostEthinicityStrain() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		try {
			// retrieve model and update w/ new values
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

			theAnimalModelManager.addXenograft(theAnimalModel, xenograftForm);

			log.info("New Xenograft created");

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {
			log.error("Exception ocurred creating Xenograft", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("<XenograftAction> Exiting save");
		return mapping.findForward("AnimalModelTreePopulateAction");
	}

	public ActionForward SetStrainDropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String speciesName = request.getParameter("speciesName");

		System.out.println("<SetStrainDropdown execute> speciesName: " + speciesName);

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, speciesName);

		XenograftForm xenograftForm = (XenograftForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, xenograftForm);
		return mapping.findForward("submitTransplantXenograft");
	}
}