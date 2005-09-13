package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.PublicationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PublicationPopulateAction extends BaseAction {
	
	/** 
	 * Pre-populate all field values in the form EnvironmentalFactorForm 
	 *  Used by submitEnvironmentalFactors
	 * 
	 */ 
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response)
	  throws Exception {
		
		System.out.println( "<PublicationPopulateAction populate> Entering populate() " );

		// Create a form to edit
		PublicationForm pubForm = ( PublicationForm ) form;
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aPubID = request.getParameter( "aPubID" );		
		
		/* Grab the current modelID from the session */
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );
        	
		// Use the current animalModel based on the ID stored in the session
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
		AnimalModel am = animalModelManager.get( modelID );	
		
		//retrieve the list of all therapies from the current animalModel
		List pubList = am.getPublicationCollection();
		
		Publication pub = new Publication();
		
		//find the specific one we need
		for ( int i=0; i<pubList.size(); i++ ) {
			pub = (Publication)pubList.get(i);
			if ( pub.getId().toString().equals( aPubID ) )
				break;
		}

		pubForm.setAuthors( pub.getAuthors() );

		pubForm.setTitle( pub.getTitle() );

		pubForm.setJournal( pub.getJournal() );
		pubForm.setVolume( pub.getVolume() );
		
		if ( pub.getYear() != null  )
			pubForm.setYear( pub.getYear().toString() );  
		if ( pub.getStartPage() != null )
			pubForm.setStartPage( pub.getStartPage().toString() );
		if( pub.getEndPage() != null )
			pubForm.setEndPage( pub.getEndPage().toString() );
		if( pub.getPmid() !=  null  )
			pubForm.setPmid( pub.getPmid().toString() );
		
		if( pub.isFirstTimeReported() )
			pubForm.setFirstTimeReported( "yes" );
		else
			pubForm.setFirstTimeReported( "no" );
		
		PublicationStatus pubStatus = pub.getPublicationStatus();		
        pubForm.setName( pubStatus.getName() );
        
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, pubForm );

		return mapping.findForward( "submitPublications" );
	}
	
	/**
	 * Populate the dropdown menus for submitEnvironmentalFactors
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
		
		//blank out the FORMDATA Constant field
		PublicationForm pubForm = ( PublicationForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, pubForm );
		
		this.dropdown( request, response );
		
		return mapping.findForward( "submitPublications" );
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
		
			System.out.println( "<PublicationPopulateAction dropdown> Entering void dropdown()" );
			
			//Prepopulate all dropdown fields, set the global Constants to the following
			NewDropdownUtil drop = new NewDropdownUtil();
		
			drop.populateDropdown( request, Constants.Dropdowns.PUBDROP, "" );

	}	
}