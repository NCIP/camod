/**
 * @author dgeorge
 * 
 * $Id: AdminUserSettingsAction.java,v 1.2 2005-10-24 13:28:17 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/21 20:47:04  georgeda
 * Initial revision
 *
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.UserSettingsForm;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * 
 * Send an e-mail indicating that a user has requested a change to his/her
 * settings
 * 
 */
public class AdminUserSettingsAction extends BaseAction {

    /**
     * Action used to populate the various admin lists for the curation process
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering execute");

        String theForward = "next";
        // The user didn't press the cancel button
        if (isCancelled(inRequest)) {
            theForward = "cancel";
        } else {
            UserSettingsForm theForm = (UserSettingsForm) inForm;

            try {

                // Get from default bundle
                ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
                String theUsersToNotify = theBundle.getString(Constants.BundleKeys.USER_UPDATE_NOTIFY_KEY);

                StringTokenizer theTokenizer = new StringTokenizer(theUsersToNotify, ",");

                List theNotifyList = new ArrayList();
                while (theTokenizer.hasMoreElements()) {
                    theNotifyList.add(theTokenizer.nextToken());
                }
                String[] theRecipients = new String[theNotifyList.size()];
                for (int i = 0; i < theNotifyList.size(); i++) {
                    theRecipients[i] = (String) theNotifyList.get(i);
                }

                // Build the message text
                String theMailSubject = "caMOD: Update requested for the following user: " + theForm.getFirstName() + " "
                        + theForm.getLastName();
                String[] theMessageKeys = { "update_user" };

                if (theRecipients.length > 0) {

                    // gather variable values to build the e-mail content with
                    Map valuesForVariables = new TreeMap();
                    valuesForVariables.put("firstName", theForm.getFirstName());
                    valuesForVariables.put("lastName", theForm.getLastName());
                    valuesForVariables.put("email", theForm.getEmail());
                    valuesForVariables.put("phone", theForm.getPhone());
                    valuesForVariables.put("affiliation", theForm.getAffiliation());
                    valuesForVariables.put("username", theForm.getUsername());

                    // launch the email
                    MailUtil.sendMail(theRecipients, theMailSubject, "", theForm.getEmail(), theMessageKeys,
                            valuesForVariables);
                } else {
                    theForward = "failure";
                    log.warn("No e-mail address for user updates");
                }
            } catch (Exception e) {

                theForward = "failure";
                log.error("Unable to send e-mail for new user: ", e);

                ActionMessages theMsg = new ActionMessages();
                theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(inRequest, theMsg);
            }
        }
        log.trace("Exiting execute");

        return inMapping.findForward(theForward);
    }
}