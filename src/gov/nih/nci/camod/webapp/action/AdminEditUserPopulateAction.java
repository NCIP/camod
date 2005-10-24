/**
 * @author dgeorge
 * 
 * $Id: AdminEditUserPopulateAction.java,v 1.3 2005-10-24 13:28:17 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/17 16:30:24  georgeda
 * Cleanup
 *
 * Revision 1.1  2005/10/17 13:28:45  georgeda
 * Initial revision
 *
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.EditUserForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * 
 * Used to populate the form used to assign roles to a user.
 * 
 */
public class AdminEditUserPopulateAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering execute");

		EditUserForm theForm = (EditUserForm) inForm;

		String theAction = (String) inRequest.getParameter("action");

		String theForward = "next";

		// Add a brand new user
		if (theAction.equals("Add")) {
			theForm.setLastName(null);
			theForm.setFirstName(null);
			theForm.setUsername(null);
			theForm.setId(null);
			theForm.setPrincipalInvestigator(false);
		} 
		
		// Update an existing user
		else {
			try {

				Person thePerson = PersonManagerSingleton.instance().get(theForm.getId());

				if (thePerson == null) {
					throw new IllegalArgumentException("Unknown user id: " + theForm.getId());
				}

				theForm.setLastName(thePerson.getLastName());
				theForm.setFirstName(thePerson.getFirstName());
				theForm.setUsername(thePerson.getUsername());

				Boolean isPI = thePerson.getIsPrincipalInvestigator();

				if (isPI != null) {
					theForm.setPrincipalInvestigator(isPI.booleanValue());
				} else {
					theForm.setPrincipalInvestigator(false);
				}

			} catch (Exception e) {

				theForward = "failure";
				log.error("Unable to get user: ", e);

				// Encountered an error saving the model.
				// created a new model successfully
				ActionMessages theMsg = new ActionMessages();
				theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
				saveErrors(inRequest, theMsg);
			}
		}
		log.trace("Exiting execute");

		return inMapping.findForward(theForward);
	}
}