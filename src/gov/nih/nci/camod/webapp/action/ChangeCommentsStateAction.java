/**
 *  @author dgeorge
 *  
 *  $Id: ChangeCommentsStateAction.java,v 1.2 2005-11-28 13:49:16 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/10/10 14:09:55  georgeda
 *  Initial revision
 *
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Comments;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.service.CommentsManager;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.LogManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.CommentsStateForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ChangeCommentsStateAction extends BaseAction {

    /**
     * Change the state for a comment
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering ChangeCommentsStateAction.execute");

        // The user didn't press the cancel button
        if (!isCancelled(inRequest)) {

            try {
                // Get the curation manager workflow XML
                CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext()
                        .getRealPath("/")
                        + Constants.Admin.COMMENT_CURATION_WORKFLOW);

                // Get the comments object
                CommentsStateForm theForm = (CommentsStateForm) inForm;

                CommentsManager theCommentsManager = (CommentsManager) getBean("commentsManager");

                Comments theComments = theCommentsManager.get(theForm.getCommentsId());

                // Did the id match?
                if (theComments != null) {

                    log.debug("Current state of comment: " + theComments.getState());

                    theCurationManager.changeState(theComments, theForm.getEvent());

                    log.debug("New state of model: " + theComments.getState());

                    // Save the associated log/comment to track the curation
                    // state
                    LogManager theLogManager = (LogManager) getBean("logManager");
                    Log theLog = theLogManager.create(theForm.getAssignedTo(), theComments.getCancerModel().getId()
                            .toString(), theComments.getState(), theForm.getNote());

                    theLog.setComment(theComments);

                    theLogManager.save(theLog);
                } else {
                    throw new IllegalArgumentException("Unknown comments id: " + theForm.getCommentsId());
                }
            } catch (IllegalArgumentException e) {
                log.error(
                        "Caught illegal argument exception changing comment state.  Probably back arrow and resubmit.",
                        e);
            } catch (Exception e) {
                log.error("Caught exception changing animal model state", e);

                // Encountered an error changing the state
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(inRequest, msg);

            }
        }

        log.trace("Exiting ChangeCommentsStateAction.execute");

        return inMapping.findForward("next");
    }
}