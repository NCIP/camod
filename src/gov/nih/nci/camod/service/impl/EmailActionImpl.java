package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.CurateableAction;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.Map;

public class EmailActionImpl extends BaseCurateableAction {

    /**
     * Protected constructor. Use the create method to get an instance.
     * 
     */
    protected EmailActionImpl() {
    }

    /**
     * Create an instance of this curatable action
     * 
     * @param inArgs
     *            the arguments to the action
     * @param inObject
     *            the curatable object to get information from
     */
    public CurateableAction create() {
        return new EmailActionImpl();
    }

    /**
     * Execute the curatable action
     * 
     * @param inArgs
     *            the arguments to the action
     * @param inObject
     *            the curatable object to get information from
     */
    public void execute(Map inArgs, Curateable inObject) {

        log.trace("Entering execute");

        log.debug("Arguments: " + inArgs);

        if (inArgs.containsKey(Constants.FORMDATA)) {

            AnimalModelStateForm theForm = (AnimalModelStateForm) inArgs.get(Constants.FORMDATA);

            AnimalModel theAnimalModel = (AnimalModel) inObject;

            Log theLog = LogManagerSingleton.instance().getCurrentByModel(theAnimalModel);

            Person thePerson = (Person) theLog.getSubmitter();

            // Get the e-mail for the user
            String[] theRecipients = { UserManagerSingleton.instance().getEmailForUser(thePerson.getUsername()) };

            // Build the message text
            String theMailSubject = "";
            String theMailText = theForm.getComment();

            // Customize the text based on the action.
            // TODO: Should be centralized.
            if (theForm.getEvent().equals(Constants.Admin.Actions.ASSIGN_SCREENER)) {
                theMailSubject = "You have been assigned screener for the following model: "
                        + theForm.getModelDescriptor();
            } else if (theForm.getEvent().equals(Constants.Admin.Actions.ASSIGN_EDITOR)) {
                theMailSubject = "You have been assigned editor for the following model: "
                        + theForm.getModelDescriptor();
            } else if (theForm.getEvent().equals(Constants.Admin.Actions.NEED_MORE_INFO)) {
                theMailSubject = "The editor is requesting more information for the following model: "
                        + theForm.getModelDescriptor();
            } else if (theForm.getEvent().equals(Constants.Admin.Actions.REJECT)) {
                theMailSubject = "The following model has been rejected: " + theForm.getModelDescriptor();
            } else if (theForm.getEvent().equals(Constants.Admin.Actions.APPROVE)) {
                theMailSubject = "The following model has been approved: " + theForm.getModelDescriptor();
            } else if (theForm.getEvent().equals(Constants.Admin.Actions.COMPLETE)) {
                theMailSubject = "The following model has been completed: " + theForm.getModelDescriptor();
            } else {
                theMailSubject = "The following model has changed: " + theForm.getModelDescriptor();
            }

            try {
                if (theRecipients.length > 0) {
                    MailUtil.sendMail(theRecipients, theMailSubject, theMailText, UserManagerSingleton
                            .instance().getEmailForController());
                } else {
                    log.warn("No e-mail address assigned to user: " + thePerson.getUsername());
                }

            } catch (Exception e) {
                log.error("Error sending e-mail:", e);
            }
        } else {
            log.error("Missing argument formdata");
        }
        log.trace("Exiting execute");
    }
}

/*
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/09/13 20:26:35  georgeda
 * More cleanup
 * Revision 1.3 2005/09/13 19:18:46 georgeda
 * Email updates and CSM integration Revision 1.2 2005/09/12 18:22:11 georgeda
 * Curation changes and addition of e-mail
 */
