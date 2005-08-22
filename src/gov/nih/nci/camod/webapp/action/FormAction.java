package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import gov.nih.nci.camod.webapp.util.DropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class FormAction extends BaseAction {
		
	/** 
	 * Pre-populate all field values in the form ModelCharacteristicsForm 
	 *  Used by submitModelCharacteristics.jsp
	 * 
	 */ 
	public ActionForward prepopulateModelCharacteristics(      ActionMapping mapping, 
															   ActionForm form,
													           HttpServletRequest request,
													           HttpServletResponse response)
	  throws Exception {	
		  
		System.out.println( "<FormAction prepopulateModelCharacteristics> Entering... " );

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
		  
		// TODO: Release date; get Calender working
		modelChar.setReleaseDate( "after" );
		modelChar.setCalendarReleaseDate( am.getAvailability().getReleaseDate().toString() );				
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		DropdownUtil drop = new DropdownUtil();
		List speciesStrainLst = drop.getSpeciesStrainList();
		List sexDistList = drop.getSexDistributionsList();
		
		//Store the values for the drop down menus in a Constants variable, used in the JSP
		request.getSession().setAttribute( Constants.SPECIESDROP, speciesStrainLst );
		request.getSession().setAttribute( Constants.STRAINDROP, speciesStrainLst );
		request.getSession().setAttribute( Constants.SEXDISTRIBUTIONDROP, sexDistList );
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, modelChar );
		
		return mapping.findForward("submitModelCharacteristics");

	}
}
