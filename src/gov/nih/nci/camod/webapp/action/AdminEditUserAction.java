/**
 * @author dgeorge
 * 
 * $Id: AdminEditUserAction.java,v 1.1 2005-10-17 13:28:45 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.webapp.form.EditUserForm;

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
public class AdminEditUserAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering execute");

		EditUserForm theForm = (EditUserForm) inForm;

		inRequest.getSession().setAttribute(Constants.FORMDATA, theForm);

		String theForward = "next";

		if (theForm.getId() == null) {
			theForward = processAdd(theForm, inRequest);
		} else {
			theForward = processEdit(theForm, inRequest);
		}
		return inMapping.findForward(theForward);
	}

	String processEdit(EditUserForm inForm, HttpServletRequest inRequest) {
		String theForward = "next";

		// The user didn't press the cancel button
		if (!isCancelled(inRequest)) {
			try {

				Person thePerson = PersonManagerSingleton.instance().get(inForm.getId());

				if (thePerson == null) {
					ActionMessages theMsg = new ActionMessages();
					theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
					saveErrors(inRequest, theMsg);
					throw new IllegalArgumentException("Unknown userid: " + inForm.getId());
				}

				if (inForm.getUsername() != null) {
					Person theExistingPerson = PersonManagerSingleton.instance().getByUsername(inForm.getUsername());
					if (theExistingPerson != null &&!theExistingPerson.equals(thePerson)) {
						ActionMessages theMsg = new ActionMessages();
						theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("admin.user.usernameexists"));
						saveErrors(inRequest, theMsg);

						throw new IllegalArgumentException("User with username: " + inForm.getUsername()
								+ " already exists");
					}
				}

				thePerson.setLastName(inForm.getLastName());
				thePerson.setFirstName(inForm.getFirstName());
				thePerson.setUsername(inForm.getUsername());
				thePerson.setIsPrincipalInvestigator(new Boolean(inForm.isPrincipalInvestigator()));

				PersonManagerSingleton.instance().save(thePerson);
				
			} catch (IllegalArgumentException e) {
				theForward = "baddata";
				log.error("Bad user data: ", e);
			} catch (Exception e) {

				log.error("Unable to get user: ", e);

				// Encountered an error saving the model.
				// created a new model successfully
				ActionMessages theMsg = new ActionMessages();
				theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
				saveErrors(inRequest, theMsg);
			}
		}
		log.trace("Exiting execute");

		return theForward;
	}

	String processAdd(EditUserForm inForm, HttpServletRequest inRequest) {
		String theForward = "next";
		try {

			if (inForm.getUsername() != null) {
				Person theExistingPerson = PersonManagerSingleton.instance().getByUsername(inForm.getUsername());
				if (theExistingPerson != null) {
					ActionMessages theMsg = new ActionMessages();
					theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("admin.user.usernameexists"));
					saveErrors(inRequest, theMsg);

					throw new IllegalArgumentException("User with username: " + inForm.getUsername()
							+ " already exists");
				}
			}

			Person thePerson = new Person();
			thePerson.setLastName(inForm.getLastName());
			thePerson.setFirstName(inForm.getFirstName());
			thePerson.setUsername(inForm.getUsername());
			thePerson.setIsPrincipalInvestigator(new Boolean(inForm.isPrincipalInvestigator()));

			PersonManagerSingleton.instance().save(thePerson);
		} catch (IllegalArgumentException e) {
			theForward = "baddata";
			log.error("Bad user data: ", e);
		} catch (Exception e) {

			log.error("Unable to get user: ", e);

			// Encountered an error saving the model.
			// created a new model successfully
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);
		}

		log.trace("Exiting execute");

		return theForward;
	}
}