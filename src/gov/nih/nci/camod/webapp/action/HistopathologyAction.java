/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyAction.java,v 1.7 2005-11-07 13:57:16 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/11/04 14:44:25  georgeda
 * Cleaned up histopathology/assoc metastasis
 *
 * Revision 1.5  2005/11/03 22:29:12  georgeda
 * Added validation/delete capabilities to histo screens
 *
 * Revision 1.4  2005/11/03 21:48:07  georgeda
 * Cleanup
 *
 * Revision 1.3  2005/11/03 18:54:10  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisForm;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerForm;
import gov.nih.nci.camod.webapp.form.HistopathologyForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * HistopathologyAction Class
 */

public class HistopathologyAction extends BaseAction {

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
	public ActionForward editHistopathology(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<HistopathologyAction> Entering 'edit' method");

		// Grab the current aHistopathID from the session
		String aHistopathologyID = request.getParameter("aHistopathologyID");

		// Create a form to edit
		HistopathologyForm histopathologyForm = (HistopathologyForm) form;

		log.debug("<HistopathologyAction edit> following Characteristics:" + "\n\t  HistopathID: " + aHistopathologyID
				+ "\n\t organTissueName: " + histopathologyForm.getOrganTissueName() + "\n\t organTissueCode: "
				+ histopathologyForm.getOrganTissueCode() + "\n\t diseaseName: "
				+ histopathologyForm.getDiagnosisName() + "\n\t diseaseCode: " + histopathologyForm.getDiagnosisCode()
				+ "\n\t diagnosisName: " + histopathologyForm.getDiagnosisName() + "\n\t ageOfOnset: "
				+ histopathologyForm.getAgeOfOnset() + "\n\t weightOfTumor: " + histopathologyForm.getWeightOfTumor()
				+ "\n\t volumeOfTumor: " + histopathologyForm.getVolumeOfTumor() + "\n\t tumorIncidenceRate: "
				+ histopathologyForm.getTumorIncidenceRate() + "\n\t survivalInfo: "
				+ histopathologyForm.getMethodOfObservation() + "\n\t grossDescription: "
				+ histopathologyForm.getGrossDescription() + "\n\t microscopicDescription: "
				+ histopathologyForm.getMicroscopicDescription() + "\n\t observation: "
				+ histopathologyForm.getObservation() + "\n\t methodOfObservation: "
				+ histopathologyForm.getMethodOfObservation() + "\n\t comparativeData: "
				+ histopathologyForm.getComparativeData() + "\n\t comments: " + histopathologyForm.getComments()
				+ "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		HistopathologyManager histopathologyManager = (HistopathologyManager) getBean("histopathologyManager");
		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if ("Delete".equals(theAction)) {
				histopathologyManager.remove(aHistopathologyID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("histopathology.delete.successful"));
				saveErrors(request, msg);

			} else {

				Histopathology theHistopathology = histopathologyManager.get(aHistopathologyID);
				histopathologyManager.updateHistopathology(histopathologyForm, theHistopathology);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("histopathology.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get a Histpathology action: ", e);

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
	public ActionForward editMetastasis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<HistopathologyAction> Entering 'editMetastasis' method");

		// Grab the current aHistopathID from the session
		String aHistopathologyID = request.getParameter("histopathologyID");

		// Grab the current aAssocMetastasisID we are working with related to
		// this animalModel
		String aAssociatedMetastasisID = request.getParameter("aAssociatedMetastasisID");
		System.out.println("aAssocMetastasisID: " + aAssociatedMetastasisID);

		// Create a form to edit
		AssociatedMetastasisForm assocMetastasisForm = (AssociatedMetastasisForm) form;

		log.debug("<HistopathologyAction editMetastasis> following Characteristics:" + "\n\t ParentHistopathID: "
				+ aHistopathologyID + "\n\t aAssociatedMetastasisID: " + aAssociatedMetastasisID
				+ "\n\t organTissueName: " + assocMetastasisForm.getOrganTissueName() + "\n\t organTissueCode: "
				+ assocMetastasisForm.getOrganTissueCode() + "\n\t diseaseName: "
				+ assocMetastasisForm.getDiagnosisName() + "\n\t diseaseCode: "
				+ assocMetastasisForm.getDiagnosisCode() + "\n\t diagnosisName: "
				+ assocMetastasisForm.getDiagnosisName() + "\n\t ageOfOnset: " + assocMetastasisForm.getAgeOfOnset()
				+ "\n\t weightOfTumor: " + assocMetastasisForm.getWeightOfTumor() + "\n\t volumeOfTumor: "
				+ assocMetastasisForm.getVolumeOfTumor() + "\n\t tumorIncidenceRate: "
				+ assocMetastasisForm.getTumorIncidenceRate() + "\n\t survivalInfo: "
				+ assocMetastasisForm.getMethodOfObservation() + "\n\t grossDescription: "
				+ assocMetastasisForm.getGrossDescription() + "\n\t microscopicDescription: "
				+ assocMetastasisForm.getMicroscopicDescription() + "\n\t observation: "
				+ assocMetastasisForm.getObservation() + "\n\t methodOfObservation: "
				+ assocMetastasisForm.getMethodOfObservation() + "\n\t comparativeData: "
				+ assocMetastasisForm.getComparativeData() + "\n\t comments: " + assocMetastasisForm.getComments()
				+ "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		HistopathologyManager histopathologyManager = (HistopathologyManager) getBean("histopathologyManager");
		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if ("Delete".equals(theAction)) {
				histopathologyManager.remove(aAssociatedMetastasisID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocmetastasis.delete.successful"));
				saveErrors(request, msg);

			} else {

				Histopathology theAssociatedMetastasis = histopathologyManager.get(aAssociatedMetastasisID);
				// Pass in the histopathology_id (aAssociatedMetastasisID) of
				// the current metastatsis also
				histopathologyManager.updateAssociatedMetastasis(assocMetastasisForm, theAssociatedMetastasis);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocmetastasis.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get a Histpathology action: ", e);

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
	public ActionForward editClinicalMarker(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<HistopathologyAction> Entering 'editClinicalMarker' method");

		// Grab the current aHistopathID from the session
		String aHistopathologyID = request.getParameter("aHistopathologyID");

		// Grab the current aAssocMetastasisID we are working with related to
		// this animalModel
		String aClinicalMarkerID = request.getParameter("aClinicalMarkerID");
		log.debug("aClinicalMarkerID: " + aClinicalMarkerID);

		// Create a form to edit
		ClinicalMarkerForm clinicalMarkerForm = (ClinicalMarkerForm) form;

		log.debug("<HistopathologyAction saveClinicalMarker> following Characteristics:" + "\n\t ParentHistopathID: "
				+ aHistopathologyID + "\n\t ClinicalMarkerID: " + aClinicalMarkerID + "\n\t Name: "
				+ clinicalMarkerForm.getName() + "\n\t Value: " + clinicalMarkerForm.getValue() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		HistopathologyManager histopathManager = (HistopathologyManager) getBean("histopathManager");

		try {
			Histopathology theHistopath = histopathManager.get(aHistopathologyID);
			histopathManager.updateClinicalMarker(clinicalMarkerForm, theHistopath);

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("clinicalmarker.edit.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get a Histpathology action: ", e);

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
	public ActionForward saveHistopathology(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<HistopathologyAction> Entering 'save' method");

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

		// Create a form to edit
		HistopathologyForm histopathologyForm = (HistopathologyForm) form;

		log.debug("<HistopathologyAction save> following Characteristics:" + "\n\t organTissueName: "
				+ histopathologyForm.getOrganTissueName() + "\n\t organTissueCode: "
				+ histopathologyForm.getOrganTissueCode() + "\n\t diseaseName: "
				+ histopathologyForm.getDiagnosisName() + "\n\t diseaseCode: " + histopathologyForm.getDiagnosisCode()
				+ "\n\t diagnosisName: " + histopathologyForm.getDiagnosisName() + "\n\t ageOfOnset: "
				+ histopathologyForm.getAgeOfOnset() + "\n\t weightOfTumor: " + histopathologyForm.getWeightOfTumor()
				+ "\n\t volumeOfTumor: " + histopathologyForm.getVolumeOfTumor() + "\n\t tumorIncidenceRate: "
				+ histopathologyForm.getTumorIncidenceRate() + "\n\t survivalInfo: "
				+ histopathologyForm.getMethodOfObservation() + "\n\t grossDescription: "
				+ histopathologyForm.getGrossDescription() + "\n\t microscopicDescription: "
				+ histopathologyForm.getMicroscopicDescription() + "\n\t observation: "
				+ histopathologyForm.getObservation() + "\n\t methodOfObservation: "
				+ histopathologyForm.getMethodOfObservation() + "\n\t comparativeData: "
				+ histopathologyForm.getComparativeData() + "\n\t comments: " + histopathologyForm.getComments()
				+ "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		try {
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

			theAnimalModelManager.addHistopathology(theAnimalModel, histopathologyForm);

			log.info("New histopathology created");

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("histopathology.creation.successful"));
			saveErrors(request, msg);

			log.info("<HistopathologyAction> Exiting 'save' method");

		} catch (Exception e) {

			log.error("Unable to get add an histopathology: ", e);

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
	public ActionForward saveMetastasis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<HistopathologyAction> Entering 'saveMetastasis' method");

		// Create a form to edit
		AssociatedMetastasisForm assocMetastasisForm = (AssociatedMetastasisForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, assocMetastasisForm);

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

		// Grab the current aHistopathID from the session
		String aHistopathID = request.getParameter("aHistopathologyID");

		log.debug("<HistopathologyAction saveMetastasis> following Characteristics:" + "\n\t ParentHistopathID: "
				+ aHistopathID + "\n\t organTissueName: " + assocMetastasisForm.getOrganTissueName()
				+ "\n\t organTissueCode: " + assocMetastasisForm.getOrganTissueCode() + "\n\t diseaseName: "
				+ assocMetastasisForm.getDiagnosisName() + "\n\t diseaseCode: "
				+ assocMetastasisForm.getDiagnosisCode() + "\n\t diagnosisName: "
				+ assocMetastasisForm.getDiagnosisName() + "\n\t ageOfOnset: " + assocMetastasisForm.getAgeOfOnset()
				+ "\n\t weightOfTumor: " + assocMetastasisForm.getWeightOfTumor() + "\n\t volumeOfTumor: "
				+ assocMetastasisForm.getVolumeOfTumor() + "\n\t tumorIncidenceRate: "
				+ assocMetastasisForm.getTumorIncidenceRate() + "\n\t survivalInfo: "
				+ assocMetastasisForm.getMethodOfObservation() + "\n\t grossDescription: "
				+ assocMetastasisForm.getGrossDescription() + "\n\t microscopicDescription: "
				+ assocMetastasisForm.getMicroscopicDescription() + "\n\t observation: "
				+ assocMetastasisForm.getObservation() + "\n\t methodOfObservation: "
				+ assocMetastasisForm.getMethodOfObservation() + "\n\t comparativeData: "
				+ assocMetastasisForm.getComparativeData() + "\n\t comments: " + assocMetastasisForm.getComments()
				+ "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		try {

			HistopathologyManager histopathologyManager = (HistopathologyManager) getBean("histopathologyManager");

			// retrieve model and update w/ new values
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

			Histopathology theParentHistopathology = histopathologyManager.get(aHistopathID);

			theAnimalModelManager.addAssociatedMetastasis(theAnimalModel, theParentHistopathology, assocMetastasisForm);

			log.info("New metastasis created");

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocmetastasis.creation.successful"));
			saveErrors(request, msg);

			log.info("<HistopathologyAction> Exiting 'saveMetastasis' method");

		} catch (Exception e) {
			log.error("Exception ocurred creating AssociatedExpression", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("Exiting save");
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
	public ActionForward saveClinicalMarker(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("<HistopathologyAction> Entering 'saveClinicalMarker' method");

		// Create a form to edit
		ClinicalMarkerForm clinicalMarkerForm = (ClinicalMarkerForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, clinicalMarkerForm);

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

		// Grab the current aHistopathID from the session
		String aHistopathID = request.getParameter("aHistopathologyID");

		System.out.println("<HistopathologyAction saveClinicalMarker> following Characteristics:"
				+ "\n\t ParentHistopathID: " + aHistopathID + "\n\t Name: " + clinicalMarkerForm.getName()
				+ "\n\t Value: " + clinicalMarkerForm.getValue() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		try {
			// retrieve model and update w/ new values
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

			theAnimalModelManager.addClinicalMarker(theAnimalModel, clinicalMarkerForm);

			log.info("New clinical marker created");

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("clinicalmarker.creation.successful"));
			saveErrors(request, msg);

			log.info("<HistopathologyAction> Exiting 'saveClinicalMarker' method");

		} catch (Exception e) {
			log.error("Exception ocurred creating AssociatedExpression", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("Exiting save");
		return mapping.findForward(theForward);
	}

}
