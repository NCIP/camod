/**
 * @author dgeorge
 * 
 * $Id: AdminUserSettingsPopulateAction.java,v 1.2 2006-05-15 15:43:56 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/21 20:47:04  georgeda
 * Initial revision
 *
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import gov.nih.nci.camod.webapp.form.UserSettingsForm;

import java.util.Iterator;
import java.util.Set;

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
public class AdminUserSettingsPopulateAction extends BaseAction
{
    /**
     * Action used to populate the various admin lists for the curation process
     */
    public ActionForward execute(ActionMapping inMapping,
                                 ActionForm inForm,
                                 HttpServletRequest inRequest,
                                 HttpServletResponse inResponse) throws Exception
    {
        log.trace("Entering execute");

        UserSettingsForm theForm = (UserSettingsForm) inForm;

        try
        {
            String theUsername = (String) inRequest.getSession().getAttribute(Constants.CURRENTUSER);
            Person thePerson = PersonManagerSingleton.instance().getByUsername(theUsername);
            
            theForm.setUsername(theUsername);
            theForm.setFirstName(thePerson.getFirstName());
            theForm.setLastName(thePerson.getLastName());

            // Fill in the e-mail and contact info
            Set theContactInfos = thePerson.getContactInfoCollection();
            if (theContactInfos != null && theContactInfos.size() > 0)
            {
                Iterator theIterator = theContactInfos.iterator();
                ContactInfo theContactInfo = (ContactInfo) theIterator.next();
                theForm.setAffiliation(theContactInfo.getInstitute());
                theForm.setPhone(theContactInfo.getPhone());
            }

            theForm.setEmail(UserManagerSingleton.instance().getEmailForUser(theUsername));

            // If we set this, then we won't validate for any PI information
            theForm.setPrincipalInvestigator(true);
        }
        catch (Exception e)
        {
            log.error("Unable to get user settings: ", e);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(inRequest, theMsg);
        }

        log.trace("Exiting execute");

        return inMapping.findForward("next");
    }
}