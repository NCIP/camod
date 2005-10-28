package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * GenomicSegmentAction Class
 */
public final class GenomicSegmentAction extends BaseAction {

	/**
	 * Delete
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		return mapping.findForward("");
	}

	/**
	 * Cancel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward duplicate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("");
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
		log.trace("Entering edit");

		// Create a form to edit
		GenomicSegmentForm genomicSegmentForm = (GenomicSegmentForm) form;

		// Grab the current modelID from the session
		String aGenomicSegmentID = request.getParameter("aGenomicSegmentID");

		log.info("<GenomicSegmentAction save> following Characteristics:" + "\n\t getLocationOfIntegration: "
				+ genomicSegmentForm.getLocationOfIntegration() + "\n\t getOtherLocationOfIntegration: "
				+ genomicSegmentForm.getOtherLocationOfIntegration() + "\n\t getSegmentName: "
				+ genomicSegmentForm.getSegmentName() + "\n\t getOtherSegmentName: "
				+ genomicSegmentForm.getOtherSegmentName() + "\n\t getComments: " + genomicSegmentForm.getComments()
				+ "\n\t getCloneDesignator: " + genomicSegmentForm.getCloneDesignator() + "\n\t getNumberMGI: "
				+ genomicSegmentForm.getNumberMGI() + "\n\t getDescription: " + genomicSegmentForm.getDescription()
				+ "\n\t getFileServerLocation: " + genomicSegmentForm.getFileServerLocation() + "\n\t getTitle: "
				+ genomicSegmentForm.getTitle() + "\n\t"
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
			GenomicSegmentManager genomicSegmentManager = (GenomicSegmentManager) getBean("genomicSegmentManager");

			if (theAction.equals("Delete")) {
				
				log.info("GenomicSegment delete");
				
				genomicSegmentManager.remove(aGenomicSegmentID);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.delete.successful"));
				saveErrors(request, msg);

			} else {
				log.info("GenomicSegment edit");

				// retrieve model and update w/ new values

				GenomicSegment theGenomicSegment = genomicSegmentManager.get(aGenomicSegmentID);

				genomicSegmentManager.update(genomicSegmentForm, theGenomicSegment, request);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {
			log.error("Exception ocurred creating GenomicSegment", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("Exiting edit");
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

		log.trace("Entering save");

		// Create a form to edit
		GenomicSegmentForm genomicSegmentForm = (GenomicSegmentForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, genomicSegmentForm);

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

		log
				.info("<GenomicSegmentAction save> following Characteristics:" + "\n\t getLocationOfIntegration: "
						+ genomicSegmentForm.getLocationOfIntegration() + "\n\t getSegmentName: "
						+ genomicSegmentForm.getSegmentName() + "\n\t getOtherSegmentName: "
						+ genomicSegmentForm.getOtherSegmentName() + "\n\t getComments: "
						+ genomicSegmentForm.getComments() + "\n\t getCloneDesignator: "
						+ genomicSegmentForm.getCloneDesignator() + "\n\t getNumberMGI: "
						+ genomicSegmentForm.getNumberMGI() + "\n\t getDescription: "
						+ genomicSegmentForm.getDescription() + "\n\t getFileServerLocation: "
						+ genomicSegmentForm.getFileServerLocation() + "\n\t getTitle: "
						+ genomicSegmentForm.getTitle()
						+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		try {
			// retrieve model and update w/ new values
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

			theAnimalModelManager.addGeneticDescription(theAnimalModel, genomicSegmentForm, request);

			log.info("New GenomicSegment created");

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genomicsegment.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {
			log.error("Exception ocurred creating GenomicSegment", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("Exiting save");
		return mapping.findForward(theForward);
	}
}