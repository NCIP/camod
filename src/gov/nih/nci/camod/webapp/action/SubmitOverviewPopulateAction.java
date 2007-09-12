/**
 *  @author dgeorge
 *  
 *  $Id: SubmitOverviewPopulateAction.java,v 1.5 2007-09-12 19:36:40 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.4  2007/07/31 12:02:55  pandyas
 *  VCDE silver level  and caMOD 2.3 changes
 *
 *  Revision 1.3  2006/10/17 16:11:00  pandyas
 *  modified during development of caMOD 2.2 - various
 *
 *  Revision 1.2  2006/08/17 18:10:20  pandyas
 *  Defect# 410: Externalize properties files - Code changes to get properties
 *
 *  Revision 1.1  2005/12/06 18:49:10  georgeda
 *  Defect #247 - real fix this time for the problem
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
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class SubmitOverviewPopulateAction extends BaseAction {

    /**
     * Change the state for the curation model
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        log.debug("Entering SubmitOverviewPopulateAction.execute");

        String theModelStatus = (String) inRequest.getSession().getAttribute(Constants.MODELSTATUS);

        if ("Incomplete".equals(theModelStatus)) {

            String theModelId = (String) inRequest.getSession().getAttribute(Constants.MODELID);

    		Properties camodProperties = new Properties();
    		String camodPropertiesFileName = null;

    		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
    		
    		try {
			
    		FileInputStream in = new FileInputStream(camodPropertiesFileName);
    		camodProperties.load(in);
	
    		} 
    		catch (FileNotFoundException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		} catch (IOException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		}
            String theCoordinator = camodProperties.getProperty("coordinator.username");

            AnimalModelStateForm theForm = new AnimalModelStateForm();

            // Set the fields
            theForm.setModelId(theModelId);
            theForm.setRemark("Model has been moved to complete");
            theForm.setAssignedTo(theCoordinator);
            theForm.setEvent(Constants.Admin.Actions.COMPLETE);

            inRequest.setAttribute(Constants.FORMDATA, theForm);
            
        }

        log.trace("Exiting AddCommentsPopulateAction.execute");

        return inMapping.findForward("submitOverview");
    }
    

}
