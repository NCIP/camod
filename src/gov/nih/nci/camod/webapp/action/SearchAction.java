/**
 * 
 * $Id: SearchAction.java,v 1.3 2005-11-16 19:43:30 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/07 21:14:24  georgeda
 * Changed the forward action
 *
 * Revision 1.1  2005/10/03 13:52:04  georgeda
 * Search changes
 *
 * Revision 1.2  2005/09/16 15:52:56  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.io.IOException;
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
public final class SearchAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        SearchForm theForm = (SearchForm) form;

        log.debug("Keyword: " + theForm.getKeyword());
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        String theForward = "next";

        // Clear the form
        if ("Clear".equals(theAction)) {
            theForm.allFieldsReset();
            theForward = "back";
        }

        // Do the search
        else {
            
            try {
                AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

                List results = animalModelManager.search(theForm);
                request.getSession().setAttribute(Constants.SEARCH_RESULTS, results);
            } catch (Exception e) {

                // Set the error message
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(request, msg);
            }
        }

        return mapping.findForward(theForward);
    }
}