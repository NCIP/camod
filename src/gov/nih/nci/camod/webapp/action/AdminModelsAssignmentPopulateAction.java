/**
 * @author dgeorge
 * 
 * $Id: AdminModelsAssignmentPopulateAction.java,v 1.5 2005-10-24 13:28:17 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/10/17 18:20:08  georgeda
 * Sort models
 *
 * Revision 1.3  2005/10/17 13:29:12  georgeda
 * Updates
 *
 * Revision 1.2  2005/10/10 14:10:48  georgeda
 * Changes for comment curation
 *
 * Revision 1.1  2005/09/19 19:53:51  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.AnimalModelSearchResult;
import gov.nih.nci.camod.webapp.form.CurationAssignmentForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import gov.nih.nci.common.persistence.Search;

import java.util.ArrayList;
import java.util.Collections;
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

		CurationAssignmentForm theForm = (CurationAssignmentForm) inForm;
		
		try {

			NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.CURATIONSTATESDROP,
					Constants.Admin.MODEL_CURATION_WORKFLOW);

			if (theForm.getCurrentState() != null) {

				AnimalModel theQBEModel = new AnimalModel();
				theQBEModel.setState(theForm.getCurrentState());

				// Get the models
				List theModels = Search.query(theQBEModel);

				// Create a display list
				List theDisplayList = new ArrayList();

				// Add AnimalModel DTO's so that the paganation works quickly
				for (int i = 0, j = theModels.size(); i < j; i++) {
					AnimalModel theAnimalModel = (AnimalModel) theModels.get(i);
					theDisplayList.add(new AnimalModelSearchResult(theAnimalModel));
				}

				Collections.sort(theDisplayList);

				inRequest.getSession().setAttribute(Constants.ADMIN_MODEL_SEARCH_RESULTS, theDisplayList);

			}
			
		} catch (Exception e) {
			log.error("Unable to get models for state: " + theForm.getCurrentState());

			// Encountered an error
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);
		}

		log.trace("Exiting execute");

		return inMapping.findForward("next");
	}
}