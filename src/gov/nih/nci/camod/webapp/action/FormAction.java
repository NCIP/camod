package gov.nih.nci.camod.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;

import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.AnimalAvailability;

import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.AnimalModel;

public class FormAction extends BaseAction {
		
	/** Pre-populate all field values in the form for submitModelCharacteristics.jsp
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
			
			// TODO: Gender/Type SexDistrobution  - modelChar.setType();
			modelChar.setType( am.getPhenotype().getSexDistribution().getType() );
			
			modelChar.setBreedingNotes( am.getPhenotype().getBreedingNotes() );		
			modelChar.setDescription( am.getPhenotype().getDescription() );
			
			modelChar.setUrl( am.getUrl() );
			  
			// TODO: Release date
			modelChar.setReleaseDate( "after" );
			modelChar.setCalendarReleaseDate( am.getAvailability().getReleaseDate().toString() );

			//List amList = animalModelManager.getAnimalModels();
			//AnimalModel am2 = (AnimalModel) amList.get( 0 );
			//am2.getSpecies().getScientificName();
			
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
