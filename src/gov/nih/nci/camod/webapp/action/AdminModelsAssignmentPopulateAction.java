/**
 * @author dgeorge
 * 
 * $Id: AdminModelsAssignmentPopulateAction.java,v 1.2 2005-10-10 14:10:48 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/19 19:53:51  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.ModelAssignmentForm;
import gov.nih.nci.common.persistence.Search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Used to populate the list of animal models needing action for the various
 * roles
 * 
 */
public class AdminModelsAssignmentPopulateAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering execute");

		// Get the curation manager workflow XML
		CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext().getRealPath("/")
				+ Constants.Admin.MODEL_CURATION_WORKFLOW);

		ModelAssignmentForm theForm = (ModelAssignmentForm) inForm;
		theForm.setStates(theCurationManager.getAllStateNames());

		inRequest.setAttribute(Constants.FORMDATA, theForm);

		if (theForm.getCurrentState() != null) {
			try {
				AnimalModel theQBEModel = new AnimalModel();
				theQBEModel.setState(theForm.getCurrentState());

				List theModels = Search.query(theQBEModel);
				inRequest.setAttribute("models", theModels);
			} catch (Exception e) {
				log.error("Unable to get models for state: " + theForm.getCurrentState());

				// Encountered an error saving the model.
				// created a new model successfully
				ActionMessages theMsg = new ActionMessages();
				theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
				saveErrors(inRequest, theMsg);
			}
		}

		log.trace("Exiting execute");

		return inMapping.findForward("next");
	}
}