/**
 * @author dgeorge
 * 
 * $Id: AdminEditUserRolesPopulateAction.java,v 1.1 2005-10-17 13:28:45 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
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
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import gov.nih.nci.camod.webapp.form.RolesAssignmentForm;

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
 * Used to populate the form used to assign roles to a user.
 * 
 */
public class AdminEditUserRolesPopulateAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering execute");

		RolesAssignmentForm theForm = (RolesAssignmentForm) inForm;

		// Get the attributes from the request
		String thePersonId = inRequest.getParameter(Constants.Parameters.PERSONID);

		inRequest.getSession().setAttribute(Constants.FORMDATA, theForm);

		String theForward = "next";
		try {

			Person thePerson = PersonManagerSingleton.instance().get(thePersonId);

			if (thePerson == null) {
				throw new IllegalArgumentException("Unknown user.  Id: " + thePersonId);
			}

			theForm.setPersonId(thePersonId);
			theForm.setDisplayName(thePerson.displayName());
			List theRoles = UserManagerSingleton.instance().getRolesForUser(thePerson.getUsername());

			for (int i = 0, j = theRoles.size(); i < j; i++) {
				
				// Set the booleans
				String theRole = (String) theRoles.get(i);
				if (Constants.Admin.Roles.COORDINATOR.equals(theRole)) {
					theForm.setCoordinator(true);
				} else if (Constants.Admin.Roles.EDITOR.equals(theRole)) {
					theForm.setEditor(true);
				} else if (Constants.Admin.Roles.SCREENER.equals(theRole)) {
					theForm.setScreener(true);
				}
			}
		} catch (Exception e) {
			
			theForward = "failure";
			log.error("Unable to get roles for user");

			// Encountered an error saving the model.
			// created a new model successfully
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);
		}

		log.trace("Exiting execute");

		return inMapping.findForward(theForward);
	}
}