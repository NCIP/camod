package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.SearchForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

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

        // Reset the non-simple-search options
        SearchForm theSearchForm = (SearchForm) form;
        theSearchForm.simpleSearchReset();

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NEWSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

        return mapping.findForward("next");
    }

}
