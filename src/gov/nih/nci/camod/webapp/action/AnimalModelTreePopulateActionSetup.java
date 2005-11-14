/**
 *  @author 
 *  
 *  $Id: AnimalModelTreePopulateActionSetup.java,v 1.2 2005-11-14 14:20:38 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/10/31 13:46:28  georgeda
 *  Updates to handle back arrow
 *  
 */
package gov.nih.nci.camod.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Populate the lists necessary to display an animal model.
 * 
 */
public class AnimalModelTreePopulateActionSetup extends BaseAction {

	/**
	 * Create the links for the submission subMenu
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Object theErrors = request.getAttribute(org.apache.struts.Globals.ERROR_KEY);

		if (theErrors != null) {
			request.getSession().setAttribute(org.apache.struts.Globals.ERROR_KEY, theErrors);
		}
		return mapping.findForward("next");
	}
}
