package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AnimalModelPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form ModelCharacteristicsForm 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response )
	  throws Exception {	
		  
		System.out.println( "<AnimalModelPopulateAction populate> Entering... " );

		// Create a form to edit
		ModelCharacteristicsForm modelChar = ( ModelCharacteristicsForm ) form;
		
		// Use the current animalModel based on the ID stored in the session
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );	
		
		modelChar.setModelDescriptor( am.getModelDescriptor() );
	
		ContactInfo info = (ContactInfo) am.getPrincipalInvestigator().getContactInfoCollection().get(0);
		modelChar.setEmail( info.getEmail() );
		
		if ( am.getIsToolMouse().booleanValue() )
			modelChar.setIsToolMouse( "yes" );
		else
			modelChar.setIsToolMouse( "no" );
		
		modelChar.setScientificName( am.getSpecies().getScientificName() );
		modelChar.setEthinicityStrain( am.getSpecies().getEthnicityStrain() );
		
		modelChar.setExperimentDesign( am.getExperimentDesign() );
		
		modelChar.setType( am.getPhenotype().getSexDistribution().getType() );
		
		modelChar.setBreedingNotes( am.getPhenotype().getBreedingNotes() );		
		modelChar.setDescription( am.getPhenotype().getDescription() );
		
		modelChar.setUrl( am.getUrl() );
		  
		// TODO: Release date; get Calender working
		modelChar.setReleaseDate( "after" );
		modelChar.setCalendarReleaseDate( am.getAvailability().getReleaseDate().toString() );				
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, modelChar );
		
		return mapping.findForward( "submitModelCharacteristics" );

	}
	
	/**
	 * Populate the dropdown menus for createNewModel
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
		
		System.out.println( "<AnimalModelPopulateAction dropdown> ... " );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		return mapping.findForward( "submitNewModel" );
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
		
			System.out.println( "<AnimalModelPopulateAction dropdown> Entering... " );
		
			//Prepopulate all dropdown fields, set the global Constants to the following
			//DropdownUtil drop = new DropdownUtil()
            NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.SPECIESDROP, "" );
			
			//TODO: Get specific list for speciesName ( for submitModelCharacteristics ) 
			List speciesList = (List) request.getAttribute( Constants.Dropdowns.SPECIESDROP );			
			System.out.println( "speciesList.get(0):" + speciesList.size()  );
			
            NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.STRAINDROP, speciesList.get(0).toString() );						
			
            NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );			 
				
			//Store the values for the drop down menus in a Constants variable, used in the JSP
			//request.getSession().setAttribute( Constants.Dropdowns.SPECIESDROP, speciesList );
			request.getSession().setAttribute( Constants.Dropdowns.STRAINDROP, "" );
			//request.getSession().setAttribute( Constants.Dropdowns.SEXDISTRIBUTIONDROP, sexDistList );		  		
	}
	
}
