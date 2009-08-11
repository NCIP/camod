/**
 *  @author dgeorge
 *  
 *  $Id: AddCommentsPopulateAction.java,v 1.3 2008-08-14 16:41:08 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.2  2006/11/09 17:20:38  pandyas
 *  Commented out debug code
 *
 *  Revision 1.1  2005/10/11 18:15:25  georgeda
 *  More comment changes
 *
 *  Revision 1.8  2005/09/22 15:17:36  georgeda
 *  More changes
 *
 *  Revision 1.7  2005/09/19 14:21:47  georgeda
 *  Added interface for URL parameters
 *
 *  Revision 1.6  2005/09/19 13:38:42  georgeda
 *  Cleaned up parameter passing
 *
 *  Revision 1.5  2005/09/19 13:09:52  georgeda
 *  Added header
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.CommentsForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AddCommentsPopulateAction extends BaseAction {

    /**
     * Change the state for the curation model
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering AddCommentsPopulateAction.execute");

        // Get the attributes from the request
        String theModelId = inRequest.getParameter(Constants.Parameters.MODELID);
        String theSectionName = inRequest.getParameter(Constants.Parameters.MODELSECTIONNAME);

        // Set up the form
        CommentsForm theForm = new CommentsForm();
        theForm.setModelId(theModelId);
        theForm.setSectionName(theSectionName);
        inRequest.setAttribute(Constants.FORMDATA, theForm);

        log.debug("The model id: " + theModelId + " and section: " + theSectionName);

        log.trace("Exiting AddCommentsPopulateAction.execute");

        return inMapping.findForward("next");
    }
}
