package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InducedMutationPopulateAction extends BaseAction {	

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
	
	    System.out.println("<InducedMutationPopulateAction populate> Entering populate() ");
	
	    InducedMutationForm inducedMutationForm = (InducedMutationForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, inducedMutationForm);       
	
        // setup dropdown menus
        this.dropdown(request, response);
        
	    return mapping.findForward("submitInducedMutation");
	}
	
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
	
	    System.out.println("<InducedMutationPopulateAction dropdown> Entering dropdown() ");
	
	    // blank out the FORMDATA Constant field
	    InducedMutationForm inducedMutationForm = (InducedMutationForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, inducedMutationForm);       
        
	    // setup dropdown menus
        this.dropdown(request, response);
        
	   return mapping.findForward("submitInducedMutation");
	} 
	
	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown( HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println("<InducedMutationPopulateAction dropdown> Entering void dropdown()");
	    
	    NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.INDUCEDMUTATIONDROP, "");
	    
	    System.out.println("<InducedMutationPopulateAction dropdown> Exiting void dropdown()");
	}	

}
