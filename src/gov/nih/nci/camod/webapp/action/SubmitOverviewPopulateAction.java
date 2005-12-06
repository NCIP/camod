/**
 *  @author dgeorge
 *  
 *  $Id: SubmitOverviewPopulateAction.java,v 1.1 2005-12-06 18:49:10 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
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
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class SubmitOverviewPopulateAction extends BaseAction {

    /**
     * Change the state for the curation model
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.trace("Entering SubmitOverviewPopulateAction.execute");

        String theModelStatus = (String) inRequest.getSession().getAttribute(Constants.MODELSTATUS);

        if ("Incomplete".equals(theModelStatus)) {

            String theModelId = (String) inRequest.getSession().getAttribute(Constants.MODELID);

            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            String theCoordinator = theBundle.getString(Constants.BundleKeys.COORDINATOR_USERNAME_KEY);

            AnimalModelStateForm theForm = new AnimalModelStateForm();

            // Set the fields
            theForm.setModelId(theModelId);
            theForm.setNote("Model has been moved to complete");
            theForm.setAssignedTo(theCoordinator);
            theForm.setEvent(Constants.Admin.Actions.COMPLETE);

            inRequest.setAttribute(Constants.FORMDATA, theForm);
        }

        log.trace("Exiting AddCommentsPopulateAction.execute");

        return inMapping.findForward("submitOverview");
    }
}
