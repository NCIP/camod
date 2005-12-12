package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.impl.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ViewTOCSearchResultsAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("In ViewTOCSearchResultsAction.populate");

        String theForward = "next";

        try {

            String theKey = (String) request.getParameter(Constants.Parameters.TOCQUERYKEY);

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
