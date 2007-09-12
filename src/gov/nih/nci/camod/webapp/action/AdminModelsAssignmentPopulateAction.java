/**
 * @author dgeorge
 * 
 * $Id: AdminModelsAssignmentPopulateAction.java,v 1.9 2007-09-12 19:36:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2007/07/31 12:02:38  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.7  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.6  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.5  2005/10/24 13:28:17  georgeda
 * Cleanup changes
 *
 * Revision 1.4  2005/10/17 18:20:08  georgeda
 * Sort models
 *
 * Revision 1.3  2005/10/17 13:29:12  georgeda
 * Updates
 *
 * Revision 1.2  2005/10/10 14:10:48  georgeda
 * Changes for comment curation
 *
 * Revision 1.1  2005/09/19 19:53:51  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Used to populate the list of animal models needing action for the various
 * roles
 * 
 */
public class AdminModelsAssignmentPopulateAction extends BaseAction {

	/**
	 * Action used to populate the various admin lists for the curation process
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.debug("<AdminModelsAssignmentPopulateAction> Entering execute");
		
		try {

			NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.CURATIONSTATESWITHBLANKDROP,
					Constants.Admin.MODEL_CURATION_WORKFLOW);
			
	        NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.EXTERNALSOURCEQUERYDROP,
	                Constants.Dropdowns.ADD_BLANK);		
	        
			NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.USERSFORSCREENERROLEDROP,
					Constants.Admin.Roles.SCREENER);
			
			NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.USERSFOREDITORROLEDROP,
					Constants.Admin.Roles.EDITOR);	
			
			NewDropdownUtil.populateDropdown(inRequest,
					Constants.Dropdowns.NONHUMANSPECIESDROP,
					Constants.Dropdowns.ADD_BLANK_OPTION);	
	        
	        NewDropdownUtil.populateDropdown(inRequest, Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP,
	                Constants.Dropdowns.ADD_BLANK);		        


			
		} catch (Exception e) {
			log.error("Unable to populate dropdowns for models in adminEditModels: ");

			// Encountered an error
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(inRequest, theMsg);
		}

		log.debug("<AdminModelsAssignmentPopulateAction> Exiting execute");

		return inMapping.findForward("next");
	}
}