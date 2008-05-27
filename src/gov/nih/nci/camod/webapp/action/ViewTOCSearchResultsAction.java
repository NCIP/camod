/**
 * 
 * $Id: ViewTOCSearchResultsAction.java,v 1.6 2008-05-27 14:58:04 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2008/05/27 14:52:52  pandyas
 * Modified to prevent SQL injection
 * Cleaned parameter name before proceeding
 * Re: Apps Scan run 05/23/2008
 *
 * Revision 1.4  2008/05/21 19:06:53  pandyas
 * Modified TOC action to prevent SQL injection
 * Re: Apps Scan run 05/15/2008
 *
 * Revision 1.3  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.impl.*;
import gov.nih.nci.camod.util.NameValueList;
import gov.nih.nci.camod.util.SafeHTMLUtil;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ViewTOCSearchResultsAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.info("In ViewTOCSearchResultsAction.execute");

        String theForward = "next";

        try {
        	
            // Get and clean method to prevent SQL injection
            String methodName = request.getParameter("aTOCQueryKey");
            log.info("methodName: " + methodName);
            methodName = SafeHTMLUtil.clean(methodName);
            log.info("methodName: " + methodName);        	

            String theKey = (String) request.getParameter(Constants.Parameters.TOCQUERYKEY);
            log.info("theKey: " + theKey);
            
            NameValueList.generateTableOfContentsList();
            request.getSession().setAttribute(Constants.Dropdowns.SEARCHTOCDROP, NameValueList.getTableOfContentsList());

            if (!SafeHTMLUtil.isValidValue(theKey,Constants.Dropdowns.SEARCHTOCDROP,request))
            {
               // set theForward to failure - fail gracefully but do not let query continue
               // This is meant to prevent SQL injection into the key value of the TOC queries
            	theForward = "failure";
            }            
            
            // Handle external linkage
            if (request.getSession().getAttribute(Constants.TOCSearch.TOC_QUERY_RESULTS) == null) {
 
                // Get the TOC manager workflow
                TOCManager theTOCManager = new TOCManager(getServlet().getServletContext().getRealPath("/")
                        + Constants.TOCSearch.TOC_QUERY_FILE);

                List theResults = theTOCManager.process(); 
                request.getSession().setAttribute(Constants.TOCSearch.TOC_QUERY_RESULTS, theResults);
            }

            List theGroupList = (List) request.getSession().getAttribute(Constants.TOCSearch.TOC_QUERY_RESULTS);

            for (int i = 0; i < theGroupList.size(); i++) {
                TOCQueryGroup theQueryGroup = (TOCQueryGroup) theGroupList.get(i);
                List theQueryList = theQueryGroup.getQueries();
                for (int j = 0; j < theQueryList.size(); j++) {
                    TOCQuery theQuery = (TOCQuery) theQueryList.get(j);              

                    if (theQuery.getKey().equals(theKey)) {                   	
                        request.getSession().setAttribute(Constants.SEARCH_RESULTS, theQuery.getResults());
                        break;
                    }
                }
            }
            
        } catch (Exception e) {

            theForward = "failure";
            log.error("Caught an error running the canned query: ", e);

            // Encountered an error saving the model.
            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }
        return mapping.findForward(theForward);
    }
}
