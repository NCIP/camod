package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import gov.nih.nci.camod.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SimpleSearchPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.trace("In AdvancedSearchPopulateAction.populate");

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NEWSPECIESDROP,
				Constants.Dropdowns.ADD_BLANK_OPTION);

		return mapping.findForward("next");
	}

}
