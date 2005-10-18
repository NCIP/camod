package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.RadiationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class RadiationPopulateAction extends BaseAction {
	
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

		System.out.println( "<RadiationPopulateAction populate> Entering populate() " );

		// Create a form to edit
		RadiationForm radiationForm = ( RadiationForm ) form;
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );		
		
		// Grab the current modelID from the session 
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );	
		
		//retrieve the list of all therapies from the current animalModel
		List therapyList = am.getTherapyCollection();
		
		Therapy therapy = new Therapy();
		
		//find the specific one we need
		for ( int i=0; i<therapyList.size(); i++ )
		{
			therapy = (Therapy)therapyList.get(i);
			if ( therapy.getId().toString().equals( aTherapyID) )
				break;
		}
		
        // Set the otherName and/or the selected name attribute
        if (therapy.getAgent().getNameUnctrlVocab() != null) {
        	radiationForm.setName(Constants.Dropdowns.OTHER_OPTION);        	
        	radiationForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
        } else {
        	radiationForm.setName(therapy.getAgent().getName());
        }		
		
		radiationForm.setType(therapy.getTreatment().getSexDistribution().getType());
		radiationForm.setDosage(therapy.getTreatment().getDosage());
		radiationForm.setRegimen(therapy.getTreatment().getRegimen());
		radiationForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
		radiationForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, radiationForm );

		return mapping.findForward("submitRadiation");

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
		
		System.out.println( "<RadiationPopulateAction dropdown> Entering dropdown() " );
		
		//blank out the FORMDATA Constant field
		RadiationForm radiationForm = ( RadiationForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, radiationForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<RadiationPopulateAction dropdown> before return submitRadiation " );
		
		return mapping.findForward( "submitRadiation" );
		
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
		
			System.out.println( "<RadiationPopulateAction dropdown> Entering void dropdown()" );
					
			//Prepopulate all dropdown fields, set the global Constants to the following
            NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.RADIATIONDROP, "" );

            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP, "" );
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.RADIATIONUNITSDROP, "" );
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "" );
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );
			
			System.out.println( "<RadiationPopulateAction dropdown> Exiting void dropdown()" );

	}		

}
