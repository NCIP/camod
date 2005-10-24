/**
 *  @author dgeorge
 *  
 *  $Id: ChangeAnimalModelStatePopulateAction.java,v 1.9 2005-10-24 13:28:17 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.8  2005/09/22 15:17:36  georgeda
 *  More changes
 *
 *  Revision 1.7  2005/09/19 14:21:47  georgeda
 *  Added interface for URL parameters
 *
 *  Revision 1.6  2005/09/19 13:38:42  georgeda
 *  Cleaned up parameter passing
 *
 *  Revision 1.5  2005/09/19 13:09:52  georgeda
 *  Added header
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.impl.LogManagerSingleton;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ChangeAnimalModelStatePopulateAction extends BaseAction {

	/**
	 * Change the state for the curation model
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering ChangeAnimalModelStatePopulateAction.execute");

		String theForward = "next";

		try {

			// Get the attributes from the request
			String theModelId = inRequest.getParameter(Constants.Parameters.MODELID);
			String theEvent = inRequest.getParameter(Constants.Parameters.EVENT);

			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

			// Set up the form
			AnimalModelStateForm theForm = (AnimalModelStateForm) inForm;
			theForm.setEvent(theEvent);
			theForm.setModelId(theModelId);
			theForm.setModelDescriptor(theAnimalModel.getModelDescriptor());

			// Null out the list in case it had been already set
			inRequest.getSession().setAttribute(Constants.Dropdowns.USERSFORROLEDROP, null);

			log.debug("The model id: " + theModelId + " and event: " + theEvent);

			// Setting the action. This is used to customize the jsp display
			if (theEvent.equals(Constants.Admin.Actions.ASSIGN_SCREENER)) {
				inRequest.setAttribute("action", "Assigning Screener to ");
				NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.USERSFORROLEDROP,
						Constants.Admin.Roles.SCREENER);
			} else if (theEvent.equals(Constants.Admin.Actions.ASSIGN_EDITOR)) {
				inRequest.setAttribute("action", "Assigning Editor to ");
				NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.USERSFORROLEDROP,
						Constants.Admin.Roles.EDITOR);
			} else if (theEvent.equals(Constants.Admin.Actions.NEED_MORE_INFO)) {

				// Assign to the current person
				Log theLog = LogManagerSingleton.instance().getCurrentByModel(theAnimalModel);
				theForm.setAssignedTo(theLog.getSubmitter().getUsername());
				inRequest.setAttribute("action", "Requesting more information for ");

			} else if (theEvent.equals(Constants.Admin.Actions.REJECT)) {

				// Assign to the coordinator
				ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
				String theCoordinator = theBundle.getString(Constants.BundleKeys.COORDINATOR_USERNAME_KEY);
				theForm.setAssignedTo(theCoordinator);

				inRequest.setAttribute("action", "Rejecting ");
			} else if (theEvent.equals(Constants.Admin.Actions.APPROVE)) {

				// Assign to the coordinator
				ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
				String theCoordinator = theBundle.getString(Constants.BundleKeys.COORDINATOR_USERNAME_KEY);
				theForm.setAssignedTo(theCoordinator);
				
				inRequest.setAttribute("action", "Approving ");
			} else {
				throw new IllegalArgumentException("Unknown event type: " + theEvent);
			}
		} catch (Exception e) {

			log.error("Caught an exception populating the data: ", e);

			// Encountered an error
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);

			theForward = "failure";
		}
		log.trace("Exiting ChangeAnimalModelStatePopulateAction.execute");

		return inMapping.findForward(theForward);
	}
}
