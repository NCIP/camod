/**
 *  @author dgeorge
 *  
 *  $Id: ChangeCommentsStatePopulateAction.java,v 1.1 2005-10-10 14:09:56 georgeda Exp $
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
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.camod.webapp.form.CommentsStateForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ChangeCommentsStatePopulateAction extends BaseAction {

	/**
	 * Change the state for the curation model
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering ChangeCommentStatePopulateAction.execute");

		String theForward = "next";

		try {

			// Get the attributes from the request
			String theCommentsId = inRequest.getParameter(Constants.Parameters.COMMENTSID);
			String theModelId = inRequest.getParameter(Constants.Parameters.MODELID);
			String theEvent = inRequest.getParameter("event");

			// Get the user manager bean to handle any role information
			UserManager theUserManager = (UserManager) getBean("userManager");

			AnimalModelManager theManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theManager.get(theModelId);

			// Set up the form
			CommentsStateForm theForm = new CommentsStateForm();
			theForm.setEvent(theEvent);
			theForm.setModelId(theModelId);
			theForm.setCommentsId(theCommentsId);
			theForm.setModelDescriptor(theAnimalModel.getModelDescriptor());
			inRequest.setAttribute("formdata", theForm);

			log.debug("The comments id: " + theCommentsId + " and event: " + theEvent);

			// Setting the action. This is used to customize the jsp display
			if (theEvent.equals(Constants.Admin.Actions.ASSIGN_SCREENER)) {
				inRequest.setAttribute("action", "Assigning Screener to ");
				theForm.setAssignees(theUserManager.getUsersForRole(Constants.Admin.Roles.SCREENER));
			} else if (theEvent.equals(Constants.Admin.Actions.REJECT)) {
				inRequest.setAttribute("action", "Rejecting ");
			} else if (theEvent.equals(Constants.Admin.Actions.APPROVE)) {
				inRequest.setAttribute("action", "Approving ");
			} else {
				throw new IllegalArgumentException("Unknown event type: " + theEvent);
			}
		} catch (Exception e) {

			log.error("Caught an exception populating the data: ", e);

			// Encountered an error saving the model.
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);

			theForward = "failure";
		}
		log.trace("Exiting ChangeCommentStatePopulateAction.execute");

		return inMapping.findForward(theForward);
	}
}
