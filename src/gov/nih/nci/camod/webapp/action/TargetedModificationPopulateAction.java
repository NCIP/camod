package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TargetedModificationPopulateAction extends BaseAction {
	
    public ActionForward populate( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<TargetedModificationPopulateAction populate> Entering populate() " );
	
	    TargetedModificationForm TargetedModificationForm = (TargetedModificationForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, TargetedModificationForm);       
	
	    // setup dropdown menus
	    this.dropdown(request, response);
	    
	    return mapping.findForward( "submitTargetedModification" );
	}

	public ActionForward dropdown( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<TargetedModificationPopulateAction dropdown> Entering dropdown()" );
	
	    // blank out the FORMDATA Constant field
	    TargetedModificationForm TargetedModificationForm = (TargetedModificationForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, TargetedModificationForm);       
	    
	    // setup dropdown menus
	    this.dropdown(request, response);
	    
	   return mapping.findForward( "submitTargetedModification" );
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
	
	    System.out.println( "<TargetedModificationPopulateAction dropdown> Entering void dropdown()" );
	    
	    NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.TARGETEDMODIFICATIONDROP, "" );
	    
	    System.out.println( "<TargetedModificationPopulateAction dropdown> Exiting void dropdown()" );
	}	
}