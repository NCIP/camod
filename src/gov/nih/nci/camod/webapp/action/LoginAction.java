/**
 * 
 * $Id: LoginAction.java,v 1.17 2008-01-15 19:31:55 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2007/12/17 18:03:22  pandyas
 * Removed * in searchFilter used for getting e-mail from LDAP
 * Apps Support ticket was submitted (31169 - incorrect e-mail associated with my caMOD account) stating:
 *
 * Cheryl Marks submitted a ticket to NCICB Application Support in which she requested that the e-mail address associated with her account in the "User Settings" screen in caMOD be corrected. She has attempted to correct it herself, but because the program queries the LDAP Server for the e-mail address, her corrections were not retained.
 *
 * Revision 1.15  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.14  2007/03/20 14:08:48  pandyas
 * Added logging to debug QA tier
 *
 * Revision 1.13  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.12  2006/05/17 14:16:52  schroedn
 * Added columnOrder to retain the column order on search results
 *
 * Revision 1.11  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.10  2006/04/28 19:26:17  schroedn
 * Defect # 238
 * On login sets the user's search result column settings
 *
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
import gov.nih.nci.camod.service.SavedQueryManager;
import gov.nih.nci.camod.service.ResultSettingsManager;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import gov.nih.nci.camod.webapp.form.LoginForm;

import java.io.IOException;
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

        String theUsername = loginForm.getUsername().toLowerCase();
        
        // check login credentials using Authentication Mangager
        boolean loginOK = UserManagerSingleton.instance().login(theUsername, loginForm.getPassword(), request);

        String forward = "failure";

        if (loginOK) {
            log.info("Successful login");
            
            forward = "success";
            request.getSession().setAttribute(Constants.CURRENTUSER, theUsername);
            
		    //Used for sidebar, number of saved queries
            SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");   
            
            //Used to pre-load settings for search result columns
            ResultSettingsManager resultSettingsManager = (ResultSettingsManager) getBean("resultSettingsManager");
            
            try {
                List savedQueriesList = savedQueryManager.getSavedQueriesByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                request.getSession().setAttribute(Constants.NUMBEROFSAVEDQUERIES, String.valueOf(savedQueriesList.size()) );
                
                //Pre-load columns for search results
                String[] columns = Constants.SEARCHRESULTCOLUMNSDEFAULT;
                String itemsPerPage = "" + Constants.ITEMSPERPAGEDEFAULT;
                
                ResultSettings inResultSettings = resultSettingsManager.getByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
        
                if ( inResultSettings != null ) 
                {
                    Set<ResultSettingsColumns> resultSettingsColumnsList = inResultSettings.getResultSettingsColumns();
                    Iterator <ResultSettingsColumns> setIter = resultSettingsColumnsList.iterator();                  
                    itemsPerPage = "" + inResultSettings.getItemsPerPage();
                    String[] theColumns = new String[resultSettingsColumnsList.size()];
                    
                    while ( setIter.hasNext() )
                    {                       
                        ResultSettingsColumns theResultSettingsColumns = (ResultSettingsColumns) setIter.next();
                        theColumns[theResultSettingsColumns.getColumnOrder()] = theResultSettingsColumns.getColumnName();
                    }  
                    
                    request.getSession().setAttribute( Constants.ITEMSPERPAGE, itemsPerPage );        
                    request.getSession().setAttribute( Constants.SEARCHRESULTCOLUMNS, theColumns );

                } else {                
                    request.getSession().setAttribute( Constants.ITEMSPERPAGE, itemsPerPage );        
                    request.getSession().setAttribute( Constants.SEARCHRESULTCOLUMNS, columns );
                }
                
            } catch (Exception e) {
                log.debug( "User search result settings load failed" );
            }
            
        } else {
            log.debug("Login failed");
            request.getSession().setAttribute(Constants.LOGINFAILED, "true");
        }

        // Forward control to the specified success URI
        return mapping.findForward(forward);
    }
}