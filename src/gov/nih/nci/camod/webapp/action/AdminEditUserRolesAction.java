/**
 *  @author dgeorge
 *  
 *  $Id: AdminEditUserRolesAction.java,v 1.2 2005-11-01 15:54:46 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/10/17 13:28:45  georgeda
 *  Initial revision
 *
 *  Revision 1.8  2005/10/10 14:12:01  georgeda
 *  Changes for comment curation
 *
 *  Revision 1.7  2005/09/16 15:52:56  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.Role;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.RolesAssignmentForm;
import gov.nih.nci.common.persistence.Search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AdminEditUserRolesAction extends BaseAction {

	/**
	 * Change the state for the animal model
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering AdminEditUserRolesAction.execute");

		// The user didn't press the cancel button
		if (!isCancelled(inRequest)) {

			try {
				// Get the animal model
				RolesAssignmentForm theForm = (RolesAssignmentForm) inForm;

				Person thePerson = PersonManagerSingleton.instance().get(theForm.getPersonId());

				// Did the id match?
				if (thePerson != null) {

					log.debug("Changing the roles for user: " + thePerson.displayName());

					List theRolesList = new ArrayList();

					if (theForm.isCoordinator() == true) {
						Role theQBERole = new Role();
						theQBERole.setName(Constants.Admin.Roles.COORDINATOR);
						List theQBEList = Search.query(theQBERole);

						if (theQBEList.size() > 0) {
							theRolesList.add(theQBEList.get(0));
						}
					}
					if (theForm.isEditor() == true) {
						Role theQBERole = new Role();
						theQBERole.setName(Constants.Admin.Roles.EDITOR);
						List theQBEList = Search.query(theQBERole);

						if (theQBEList.size() > 0) {
							theRolesList.add(theQBEList.get(0));
						}
					}
					if (theForm.isScreener() == true) {
						Role theQBERole = new Role();
						theQBERole.setName(Constants.Admin.Roles.SCREENER);
						List theQBEList = Search.query(theQBERole);

						if (theQBEList.size() > 0) {
							theRolesList.add(theQBEList.get(0));
						}
					}

					thePerson.setRoleCollection(theRolesList);
                    PersonManagerSingleton.instance().save(thePerson);
				}
			} catch (Exception e) {

				log.error("Caught an setting the user roles: ", e);

				// Encountered an error saving the model.
				ActionMessages theMsg = new ActionMessages();
				theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
				saveErrors(inRequest, theMsg);
			}
		}

		log.trace("Exiting AdminEditUserRolesAction.execute");

		return inMapping.findForward("next");
	}
}