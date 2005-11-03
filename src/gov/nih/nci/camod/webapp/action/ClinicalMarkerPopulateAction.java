/**
 * 
 * @author pandyas
 * 
 * $Id: ClinicalMarkerPopulateAction.java,v 1.2 2005-11-03 18:54:10 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.action;

import java.util.List;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ClinicalMarker;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClinicalMarkerPopulateAction extends BaseAction {
	
	/** 
	 * Pre-populate all field values in the form <FormName> 
	 *  Used by <jspName>
	 * 
	 */ 
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response)
	  throws Exception {
		
		System.out.println( "<HistopathologyPopulateAction populate> Entered" );
		
		ClinicalMarkerForm clinicalMarkerForm = (ClinicalMarkerForm) form;

       	// Grab the current aHistopathID from the session
       	String aHistopathID = request.getParameter("histopathologyID");
       	System.out.println( "aHistopathID: " +aHistopathID );
    	
    	String aClinicalMarkerID = request.getParameter( "clinicalMarkerID" );		
    	System.out.println( "aClinicalMarkerID: = " +aClinicalMarkerID);  
    	
        HistopathologyManager theHistopathologyManager = (HistopathologyManager) getBean( "theHistopathologyManager" );
        Histopathology theHistopathology = theHistopathologyManager.get( aHistopathID ); 
        List clinicalMarkerList = theHistopathology.getClinicalMarkerCollection();

        //Find the clinical marker we are dealing with
        for( int i=0; i < clinicalMarkerList.size(); i++ ) 
        {
        	ClinicalMarker theClinicalMarker =  (ClinicalMarker)clinicalMarkerList.get(i);
        	
        	if( theClinicalMarker.getId().toString().equals( aClinicalMarkerID ) ) {
        		
        		clinicalMarkerForm.setName(theClinicalMarker.getName());
        		clinicalMarkerForm.setValue(theClinicalMarker.getValue());
        		
        	}
   	
        }
        
        // Prepopulate all dropdown fields, set the global Constants to the following
        this.dropdown(request, response); 

		return mapping.findForward("submitClinicalMarkers");

	}

	/**
	 * Populate the dropdown menus 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown( ActionMapping mapping, 
			   					   ActionForm form,
			   					   HttpServletRequest request,
			   					   HttpServletResponse response )
	  throws Exception {
		
		System.out.println( "<ClinicalMarkerPopulateAction dropdown> Entering ActionForward dropdown()" );
		
		//blank out the FORMDATA Constant field
		ClinicalMarkerForm clinicalMarkerForm = (ClinicalMarkerForm) form;
		
		request.getSession().setAttribute( Constants.FORMDATA, clinicalMarkerForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<ClinicalMarkerPopulateAction dropdown> Exiting ActionForward dropdown()" );		
		
		return mapping.findForward( "submitClinicalMarkers" );
	}	
	
	/**
	 * Populate all drowpdowns for this type of form 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown( HttpServletRequest request,
						  HttpServletResponse response )
	  throws Exception {
		
			System.out.println( "<ClinicalMarkerPopulateAction dropdown> Entering void dropdown()" );
		
			//Prepopulate all dropdown fields, set the global Constants to the following
			NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CLINICALMARKERSDROP, "" );
			
			System.out.println( "<ClinicalMarkerPopulateAction dropdown> Exiting void dropdown()" );			
	}	

}
