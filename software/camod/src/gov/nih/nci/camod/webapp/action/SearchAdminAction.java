/**
 * 
 * $Id: SearchAdminAction.java,v 1.3 2007-09-12 19:36:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2007/08/07 15:36:17  pandyas
 * Fixed clear button on adminEditModels.jsp
 *
 * Revision 1.1  2007/07/31 12:02:55  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.CurationAssignmentForm;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Action used to implement the search for animal models in adminEditModels section
 * 
 */
public final class SearchAdminAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm inForm,
			HttpServletRequest inRequest, HttpServletResponse inResponse)
			throws IOException, ServletException {

		log.debug("<SearchAdminAction> entered ");

		CurationAssignmentForm theForm = (CurationAssignmentForm)inForm;
		theForm.toString();
		
		String theAction = (String) inRequest
		.getParameter(Constants.Parameters.ACTION);
		String theForward = "next";

		// Clear the form
		if ("Clear".equals(theAction)) {
			theForm.allFieldsReset();
			theForward = "back";
		} else {
			// Do the search
			try {
					log.debug("<SearchAction> In search loop: ");
					AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
	
					// Perform the search
					List results = animalModelManager.searchAdmin(theForm);
					log.debug("SearchAdminAction results.size(): " + results.size());
	
					// Set admin search results constant
					inRequest.getSession().setAttribute(Constants.ADMIN_MODEL_SEARCH_RESULTS,
							results);
					log.debug("SearchAdminAction set results to Constant ");				
	
				} catch (Exception e) {
					log.info(e);
					// Set the error message
					ActionMessages msg = new ActionMessages();
					msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							"errors.admin.message"));
					saveErrors(inRequest, msg);
			}
		}
	
		return mapping.findForward(theForward);
		 
	}//end of execute
}  // end of class