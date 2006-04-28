/**
 * 
 * $Id: LoginAction.java,v 1.10 2006-04-28 19:26:17 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2005/12/06 19:51:39  georgeda
 * Defect #255 - add SSL
 *
 * Revision 1.8  2005/12/06 14:50:45  georgeda
 * Defect #253, change the lowecase to the login action so that roles match
 *
 * Revision 1.7  2005/11/28 18:33:35  georgeda
 * Defect #104.  Clicking on a model and then using the backarrow works after login
 *
 * Revision 1.6  2005/09/22 15:18:13  georgeda
 * More changes
 *
 * Revision 1.5  2005/09/16 15:52:55  georgeda
 * Changes due to manager re-write
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ResultSettings;
import gov.nih.nci.camod.domain.ResultSettingsColumns;
import gov.nih.nci.camod.service.QueryStorageManager;
import gov.nih.nci.camod.service.ResultSettingsManager;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import gov.nih.nci.camod.webapp.form.LoginForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class LoginAction extends BaseAction {

    /**
     * Authenticates the user using the CSM AuthenticationManager and stores in
     * the session.
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        LoginForm loginForm = (LoginForm) form;

        log.debug("Logon Username: " + loginForm.getUsername());
        log.debug("System Config file is: " + System.getProperty("gov.nih.nci.security.configFile"));

        String theUsername = loginForm.getUsername().toLowerCase();
        
        // check login credentials using Authentication Mangager
        boolean loginOK = UserManagerSingleton.instance().login(theUsername, loginForm.getPassword(), request);

        String forward = "failure";

        if (loginOK) {
            log.debug("Successful login");
            forward = "success";
            request.getSession().setAttribute(Constants.CURRENTUSER, theUsername);
            
		    //Used for sidebar, number of saved queries
            QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");   
            
            //Used to pre-load settings for search result columns
            ResultSettingsManager resultSettingsManager = (ResultSettingsManager) getBean("resultSettingsManager");
            
            try {
                List savedQueriesList = queryStorageManager.getSavedQueriesByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                request.getSession().setAttribute(Constants.NUMBEROFSAVEDQUERIES, String.valueOf(savedQueriesList.size()) );
                
                //Pre-load columns for search results
                String[] columns = { "Model Descriptor", "Tumor Sites", "Species"  }; 
                String itemsPerPage = "15";
                ResultSettings rSettings = resultSettingsManager.getByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                List selectedList = new ArrayList();
               
                if ( rSettings != null ) {
                    Set<ResultSettingsColumns> set = rSettings.getResultSettingsColumns();
                    Iterator <ResultSettingsColumns> setIter = set.iterator();                  
                    itemsPerPage = "" + rSettings.getItemsPerPage();
                    
                    while ( setIter.hasNext() )
                    {
                        ResultSettingsColumns it = (ResultSettingsColumns) setIter.next();
                        selectedList.add( it.getColumnName() );
                    }  
                    
                    request.getSession().setAttribute( Constants.ITEMSPERPAGE, itemsPerPage );        
                    request.getSession().setAttribute( Constants.SEARCHRESULTCOLUMNS, (String[])selectedList.toArray( new String[0] ) );

                } else {                
                    request.getSession().setAttribute( Constants.ITEMSPERPAGE, itemsPerPage );        
                    request.getSession().setAttribute( Constants.SEARCHRESULTCOLUMNS, columns );
                }
                
            } catch (Exception e) {}
            
        } else {
            log.debug("Login failed");
            request.getSession().setAttribute(Constants.LOGINFAILED, "true");
        }

        // Forward control to the specified success URI
        return mapping.findForward(forward);
    }
}