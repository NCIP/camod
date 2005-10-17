/**
 * @author dgeorge
 * 
 * $Id: AdminRolesAssignmentPopulateAction.java,v 1.1 2005-10-17 13:29:00 georgeda Exp $
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
import gov.nih.nci.camod.domain.PersonSearchResult;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.RolesAssignmentForm;

import java.util.ArrayList;
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
public class AdminRolesAssignmentPopulateAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering execute");

		// Generate the roles list
		List theRoles = new ArrayList();
		theRoles.add(Constants.Admin.Roles.ALL);
		theRoles.add(Constants.Admin.Roles.COORDINATOR);
		theRoles.add(Constants.Admin.Roles.EDITOR);
		theRoles.add(Constants.Admin.Roles.SCREENER);

		RolesAssignmentForm theForm = (RolesAssignmentForm) inForm;;
        theForm.setRoles(theRoles);
        
		inRequest.getSession().setAttribute(Constants.FORMDATA, theForm);

		if (theForm.getCurrentRole() != null) {

			try {

				// Get the models
				List theUsers = PersonManagerSingleton.instance().getByRole(theForm.getCurrentRole());
				
				// Create a display list
		        List theDisplayList = new ArrayList();
		        
		        // Add AnimalModel DTO's so that the paganation works quickly
		        for (int i = 0, j = theUsers.size(); i < j; i++) {
		            Person thePerson = (Person) theUsers.get(i);
		            theDisplayList.add(new PersonSearchResult(thePerson));
		        }
		        
				inRequest.getSession().setAttribute(Constants.ADMIN_ROLES_SEARCH_RESULTS, theDisplayList);
				
			} catch (Exception e) {
				log.error("Unable to get users for role: " + theForm.getCurrentRole());

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