package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class FormAction extends BaseAction {
		
	/** Pre-populate all field values in the form ModelCharacteristicsForm
	 * 
	 *  Used by submitModelCharacteristics.jsp
	 * 
	 */ 
	public ActionForward prepopulateModelCharacteristics(    ActionMapping mapping, 
															   ActionForm form,
													           HttpServletRequest request,
													           HttpServletResponse response)
	  throws Exception {	
		  
		System.out.println( "<FormAction prepopulateModelCharacteristics> Entering... " );
		
		if( checkIfLoggedIn( request ) )
		{
			// Create a form to edit
			ModelCharacteristicsForm modelChar = ( ModelCharacteristicsForm ) form;
			
			// Use the current animalModel based on the ID stored in the session
			String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
			AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
			AnimalModel am = animalModelManager.getAnimalModel( modelID );	
			
			modelChar.setModelDescriptor( am.getModelDescriptor() );
		
			ContactInfo info = (ContactInfo) am.getPrincipalInvestigator().getContactInfoCollection().get(0);
			modelChar.setEmail( info.getEmail() );
			
			if ( am.getIsToolMouse() )
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
			  
			// TODO: Release date
			modelChar.setReleaseDate( "after" );
			modelChar.setCalendarReleaseDate( am.getAvailability().getReleaseDate().toString() );

			// TODO: Create a java class for populating all different dropdown menus, as well as CONSTANTS to hold the values in
			//Get values for dropdown lists for Species, Strains
			//TaxonManager taxonManager = (TaxonManager) getBean( "taxonManager" );
			//List speciesList = taxonManager.getTaxons();		
			//Taxon tax = new Taxon();			
			//tax.getScientificName();			
			//Taxon species = speciesList.get(0);			
			//List strainsList = taxonManager.getStrains( );
			
			//Store the Form in session to be used by the JSP
			request.getSession().setAttribute( Constants.FORMDATA, modelChar );
			
			return mapping.findForward("success");
		} else {
			return mapping.findForward("loginrequired");	
		}
	}
		
		/** Used to check camod.loggedon.username attribute to see if any user is logged in 
		 * 
		 */
		public boolean checkIfLoggedIn( HttpServletRequest request )
		{
			if ( request.getSession().getAttribute("camod.loggedon.username") == null ) {				
		        
		        ActionErrors errors = new ActionErrors();
		        errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.login.required" ) );
		        saveErrors( request, errors );
		                     
				return false;
			} else 
				return true;
		}
}
