/**
 * 
 * $Id: SimpleSearchAction.java,v 1.2 2005-09-16 15:52:56 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.SimpleSearchForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * 
 * Action used to implement the search for animal models
 *
 */
public final class SimpleSearchAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        SimpleSearchForm ssf = (SimpleSearchForm) form;

        log.debug("Keyword: " + ssf.getKeyword());

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        try {
            List results = animalModelManager.search();
            request.getSession().setAttribute(Constants.SEARCH_RESULTS, results);
        } catch (Exception e) {
            log.error("Unable to fetch models ", e);
            request.getSession().setAttribute(Constants.SEARCH_RESULTS, new ArrayList());

            // Set the error message
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        // Forward control to the specified success URI
        return mapping.findForward("searchResults");
    }
}