package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.XenograftForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class XenograftPopulateAction extends BaseAction{
	
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

		System.out.println( "<XenograftPopulateAction populate> Entering populate() " );

		// Create a form to edit
		XenograftForm xenograftForm = ( XenograftForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, xenograftForm );
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aXenograftID = request.getParameter( "aXenograftID" );		
		
		// Grab the current modelID from the session 
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );
		
		System.out.println( "<XenograftPopulateAction populate> Grab the current modelID= " + modelID );
		
		List xenograftList = am.getXenograftCollection();
		Xenograft xeno = new Xenograft();
		
        for (int i = 0; i < xenograftList.size(); i++) {        	
        	xeno  = (Xenograft) xenograftList.get(i);                                              
			if ( xeno.getId().toString().equals( aXenograftID) )
				break;
        }
		
        xenograftForm.setName( xeno.getName() );
        xenograftForm.setAdministrativeSite( xeno.getAdministrativeSite() );
        xenograftForm.setGeneticManipulation( xeno.getGeneticManipulation() );
        xenograftForm.setModificationDescription( xeno.getModificationDescription() );
        xenograftForm.setParentalCellLineName( xeno.getParentalCellLineName() );
        xenograftForm.setATCCNumber( xeno.getAtccNumber() );
        xenograftForm.setCellAmount( xeno.getCellAmount() );
        
        Taxon tax = xeno.getHostSpecies();        
        xenograftForm.setHostScientificName( tax.getCommonName() );
        xenograftForm.setHostEthinicityStrain( tax.getEthnicityStrain() );
        xenograftForm.setOtherHostEthinicityStrain( tax.getEthnicityStrainUnctrlVocab() );
        
        // String inputFormatString = "dd/MM/yyyy hh:mm a";
        String outputFormatString = "MM/dd/yyyy";
        
        if ( xeno.getHarvestDate() != null) {
        	if ( !xeno.getHarvestDate().equals(""))
        	{
                // Format the date object into the new format
                DateFormat sdf = new SimpleDateFormat(outputFormatString);  
                String formattedDate = sdf.format(  xeno.getHarvestDate() );
                xenograftForm.setHarvestDate( formattedDate );        		
        	}
        }
        
        xenograftForm.setGraftType( xeno.getGraftType() );
        xenograftForm.setOtherGraftType( xeno.getGraftTypeUnctrlVocab() );
                
        Taxon inTaxon = am.getSpecies();
        request.getSession().setAttribute( Constants.Dropdowns.MODELSPECIES, inTaxon.getCommonName() );
        request.getSession().setAttribute( Constants.Dropdowns.MODELSTRAIN, inTaxon.getEthnicityStrain() );
        
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, xenograftForm );		
		
		return mapping.findForward( "submitTransplantXenograft" );

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
		
		System.out.println( "<XenograftPopulateAction dropdown> Entering dropdown() " );
		
		//blank out the FORMDATA Constant field
		XenograftForm xenograftForm = ( XenograftForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, xenograftForm );
		
		//Retrieve the AnimalModel current Species and Strain set in ModelCharacteristics
		//This is displayed on the JSP page right above the HOST STRAIN / SPECIES
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);
        
        Taxon inTaxon = animalModel.getSpecies();
        request.getSession().setAttribute( Constants.Dropdowns.MODELSPECIES, inTaxon.getCommonName() );
        request.getSession().setAttribute( Constants.Dropdowns.MODELSTRAIN, inTaxon.getEthnicityStrain() );
        
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<XenograftPopulateAction dropdown> before return submitRadiation " );
		
		return mapping.findForward( "submitTransplantXenograft" );
		
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
		
			System.out.println( "<XenograftPopulateAction dropdown> Entering void dropdown()" );
				
			//Prepopulate all dropdown fields, set the global Constants to the following
			NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.SPECIESDROP, "" );			
			NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.STRAINDROP, "" );		
			NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );
			NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.GRAFTTYPEDROP, "" );
			
			System.out.println( "<XenograftPopulateAction dropdown> Exiting void dropdown()" );
	}
}
