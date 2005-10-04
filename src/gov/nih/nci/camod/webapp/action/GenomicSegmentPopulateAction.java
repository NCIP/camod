package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GenomicSegmentPopulateAction extends BaseAction {
	
    public ActionForward populate( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<GenomicSegmentPopulateAction populate> Entering populate() " );
	
	    GenomicSegmentForm GenomicSegmentForm = (GenomicSegmentForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, GenomicSegmentForm);       
	
	    // setup dropdown menus
	    this.dropdown(request, response);
	    
	    return mapping.findForward( "submitGenomicSegment" );
	}
	
	public ActionForward dropdown( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<GenomicSegmentPopulateAction dropdown> Entering dropdown()" );
	
	    // blank out the FORMDATA Constant field
	    GenomicSegmentForm GenomicSegmentForm = (GenomicSegmentForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, GenomicSegmentForm);       
	    
	    // setup dropdown menus
	    this.dropdown(request, response);
	    
	   return mapping.findForward( "submitGenomicSegment" );
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
	
	    System.out.println( "<GenomicSegmentPopulateAction dropdown> Entering void dropdown()" );
	    
	    NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.GENOMICSEGMENTDROP, "" );
	    
	    System.out.println( "<GenomicSegmentPopulateAction dropdown> Exiting void dropdown()" );
	}	
}