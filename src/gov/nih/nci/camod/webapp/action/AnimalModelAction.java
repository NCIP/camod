package gov.nih.nci.camod.webapp.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.Nomenclature;
import gov.nih.nci.camod.domain.GenotypeSummary;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Phenotype;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Availability;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.AnimalModel;

public final class AnimalModelAction extends BaseAction {
	
	/** Called from submitNewModel.jsp
	 * 
	 */ 
    public ActionForward saveNewModel( ActionMapping mapping, 
    								   ActionForm form,
							           HttpServletRequest request,
							           HttpServletResponse response)
    throws Exception {
     
    	if( checkIfLoggedIn( request ) )
		{
	    	ModelCharacteristicsForm modelChar = ( ModelCharacteristicsForm ) form;
	    	
			System.out.println( "<AnimalModelAction saveNewModel> New Model Being created with following Characteristics:" + 
																	"\n\t description: " + modelChar.getDescription() + 
																	"\n\t breedingNotes: " + modelChar.getBreedingNotes() + 
																	"\n\t email: " + modelChar.getEmail() + 
																	"\n\t ethinicityStrain: " + modelChar.getEthinicityStrain() +
																	"\n\t experimentDesign: " + modelChar.getExperimentDesign() + 
																	"\n\t isToolMouse: " + modelChar.getIsToolMouse() + 
																	"\n\t modelDescriptor: " + modelChar.getModelDescriptor() +
																	"\n\t name: " + modelChar.getName() + 
																	"\n\t releaseDate: " + modelChar.getReleaseDate() +
																	"\n\t scientificName: " + modelChar.getScientificName() +
																	"\n\t summary: " + modelChar.getSummary() +
																	"\n\t type: " + modelChar.getType() + 
																	"\n\t url: " + modelChar.getUrl() +
																	"\n\t calendarReleaseDate: " + modelChar.getCalendarReleaseDate() + 
																	"\n\t currentUser: " + (String) request.getSession().getAttribute("camod.loggedon.username") );
			
			Person person = new Person();
			
			//Set the current user as the person who entered this model
			person.setUsername( (String) request.getSession().getAttribute("camod.loggedon.username") );    		    	
				
			AnimalModel animalModel = new AnimalModel();
			
			//set the isToolMouse variable
			if ( modelChar.getIsToolMouse().equals("yes") )
					animalModel.setIsToolMouse( true );
			else 
					animalModel.setIsToolMouse( false );
			
			animalModel.setUrl( modelChar.getUrl() );    	
			
			//Model Name/Descriptor
			animalModel.setModelDescriptor( modelChar.getModelDescriptor() );		
			animalModel.setExperimentDesign( modelChar.getExperimentDesign() );
			
			//By default a new model's state is set to incomplete
			animalModel.setState("incomplete");
			
			ContactInfo contactInfo = new ContactInfo();
			contactInfo.setEmail( modelChar.getEmail() );
			
			Taxon taxon = new Taxon();
			
			//Species
			taxon.setScientificName( modelChar.getScientificName() );
			
			//Strain
			taxon.setEthnicityStrain( modelChar.getEthinicityStrain() );
			
			//if ( modelChar.getOtherEthinicityStrain() != null || ! modelChar.getOtherEthinicityStrain().equals( "" ) ) 
		//	{
	//			System.out.println( "<AnimalModelAction saveNewModel> Someone entered in a new Strain " + modelChar.getOtherEthinicityStrain() );
	//			System.out.println( "<AnimalModelAction saveNewModel> Sending a notice email to Ulli" );
				//TODO: call a email function
	//		}
				
			//phenotype description
			Phenotype phenotype = new Phenotype();
			phenotype.setDescription( modelChar.getDescription() );
			phenotype.setBreedingNotes( modelChar.getBreedingNotes() );
			
			//Set the gender
			SexDistribution sexDistribution = new SexDistribution();
			sexDistribution.setType( modelChar.getType() );
			
			Availability availability = new Availability();  
			
			// Set the for the enteredDate the current date, when this model was created
			availability.setEnteredDate( new Date() );
			
			SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
			Date releaseDate = new Date();
	
			if ( modelChar.getReleaseDate().equals( "immediately" ) ) {
				availability.setReleaseDate( releaseDate );
				System.out.println( "<AnimalModelAction saveNewModel> Current Date: " + formatter.format( releaseDate ) );    		
			} else {
				// TODO: add popup calender to submitNewModel.jsp and convert the string to Date object here
				// modelChar.getCalendarReleaseDate();
				availability.setReleaseDate( releaseDate );
			}						
			
	        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
			Long modelID = animalModelManager.saveAnimalModel( person, contactInfo, animalModel, taxon, phenotype, sexDistribution, availability );    				    
	    	
			// Setup global constants to use for submission / editing process
			AnimalModel am = animalModelManager.getAnimalModel( "" + modelID );	      	
	    	request.getSession().setAttribute( Constants.MODELID, am.getId() );
	    	request.getSession().setAttribute( Constants.MODELDESCRIPTOR, am.getModelDescriptor() );
	    	request.getSession().setAttribute( Constants.MODELSTATUS, am.getState() );
	    	
	    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
	        ActionMessages msg = new ActionMessages();
	        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "creation.successful" ) );
	        saveErrors( request, msg );
	     
			return mapping.findForward("createsuccessful");
		} else {
			return mapping.findForward( "loginrequired" );
		}
    }
    
    
	/** Called from subSubmitMenu.jsp
	 * 
	 */ 
    public ActionForward editExistingModel( ActionMapping mapping, 
    									    ActionForm form,
							          	    HttpServletRequest request,
							          	    HttpServletResponse response)
    throws Exception {
    
    	// Grab the current modelID from the session  
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );
    	
    	//TODO: ...
    	
    	return mapping.findForward("editsuccessful");
    }
    
    
	/** Creates an exact duplicate of a AnimalModel 
	 *  Called from subSubmitMenu.jsp
	 * 
	 */ 
    public ActionForward duplicateModel( 	ActionMapping mapping, 
										    ActionForm form,
							          	    HttpServletRequest request,
							          	    HttpServletResponse response)
    throws Exception {
    
    	System.out.println( "<AnimalModelAction duplicateModel> modelID=" + request.getParameter( "modelID" ) );
		
    	if( checkIfLoggedIn( request ) )
		{
    		String modelID = request.getParameter( "modelID" );
    		
    		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
    		
    		//retrieve model by it's id
    		AnimalModel am = animalModelManager.getAnimalModel( modelID );	  
	    	
	    	//create a new model
	 		animalModelManager.saveAnimalModel( am );
	    		        
    		return mapping.findForward( "duplicatesuccessful" );
		} else {
			return mapping.findForward( "loginrequired" );
		}
    }
    
	/** Delete a AnimalModel based on it's id 
	 * 
	 */
    public ActionForward deleteModel( ActionMapping mapping, 
									  ActionForm form,
							          HttpServletRequest request,
							          HttpServletResponse response)
	throws Exception {
		
		System.out.println( "<AnimalModelAction DeleteModel> modelID=" + request.getParameter( "modelID" ) );
		
		if( checkIfLoggedIn( request ) )
		{
			String modelID = request.getParameter( "modelID" );
			
			AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
			animalModelManager.removeAnimalModel( modelID );
			
	    	//Add a message to be displayed in submitModles saying you've deleted a model  
	        ActionMessages msg = new ActionMessages();
	        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "delete.successful" ) );
	        saveErrors( request, msg );
			
			return mapping.findForward( "modeldeleted" );
		} else {
			return mapping.findForward( "loginrequired" );
		}	
    }
    
    
    /** checkIfLoggedIn checks to see if a user is logged in   
     *  Used to check camod.loggedon.username attribute to see if any user is logged in 
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