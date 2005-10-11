/**
 *  @author dgeorge
 *  
 *  $Id: AddCommentsAction.java,v 1.1 2005-10-11 18:15:25 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Comments;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.service.CommentsManager;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.service.impl.LogManagerSingleton;
import gov.nih.nci.camod.webapp.form.CommentsForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AddCommentsAction extends BaseAction {

    /**
     * Change the state for a comment
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering AddCommentsAction.execute");

        // The user didn't press the cancel button
        if (!isCancelled(inRequest)) {

            CommentsForm theForm = (CommentsForm) inForm;

            log.info("ModelId: " + theForm.getModelId());
            log.info("Remark: " + theForm.getRemark());
            log.info("Section Name: " + theForm.getSectionName());

            // Set any necessary form data
            String theCurrentUser = (String) inRequest.getSession().getAttribute(Constants.CURRENTUSER);
            theForm.setSubmitter(theCurrentUser);
            
            // Get the curation manager workflow XML
            CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext().getRealPath(
                    "/")
                    + Constants.Admin.COMMENT_CURATION_WORKFLOW);

            theForm.setState(theCurationManager.getDefaultState());
            
            // Create the comment
            CommentsManager theCommentsManager = (CommentsManager) getBean("commentsManager");
            Comments theComments = theCommentsManager.create(theForm);

            // Create the associated log and set the comment
            Log theLog = LogManagerSingleton.instance().create(theCurrentUser, theForm.getModelId(),
                    theCurationManager.getDefaultState(), "Initial creation of a comment");

            theLog.setComment(theComments);
            
            // Save the log and comment
            LogManagerSingleton.instance().save(theLog);
        }

        log.trace("Exiting ChangeCommentsStateAction.execute");

        return inMapping.findForward("next");
    }
}