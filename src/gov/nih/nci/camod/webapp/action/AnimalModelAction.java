package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Availability;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.Phenotype;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.util.EmailMessage;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public final class AnimalModelAction extends BaseAction {
	
	/**
	 * Save a new AnimalModel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward save( ActionMapping mapping, 
							   ActionForm form,
					           HttpServletRequest request,
					           HttpServletResponse response)
    throws Exception {
     
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
																"\n\t currentUser: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		Person person = new Person();
		
		//Set the current user as the person who entered this model
		person.setUsername( (String) request.getSession().getAttribute( Constants.CURRENTUSER ) );    		    	
			
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
		
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setEmail( modelChar.getEmail() );
		
		Taxon taxon = new Taxon();
		
		//Species
		taxon.setScientificName( modelChar.getScientificName() );
		
		//Strain
		taxon.setEthnicityStrain( modelChar.getEthinicityStrain() );
		
		 if ( modelChar.getOtherEthinicityStrain() != null || ! modelChar.getOtherEthinicityStrain().equals( "" ) ) 
		 {
			System.out.println( "<AnimalModelAction saveNewModel> Someone entered in a new Strain " + modelChar.getOtherEthinicityStrain() );
			System.out.println( "<AnimalModelAction saveNewModel> Sending a notice email to : " );
			
			//call a email function
			EmailMessage msg = new EmailMessage();
			String strNewStrainMessage = "A new strain was entered " + modelChar.getOtherEthinicityStrain();
			
			// TODO: Send to multiple Recipients
			//(server, sender, recipient, subject, messageBody)
			msg.sendEmailMessage( Constants.Email.SERVER, Constants.Email.SENDER, Constants.Email.RECIPIENT, "Test Message subject", strNewStrainMessage );
			
			//set flag, add to strains
			//TODO: Edit dropdown to not include flagged settings... display only if directly connected to current model
		}
			
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
		
		//By default a new model's state is set to incomplete
		animalModel.setState("Incomplete");			
		
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
		Long modelID = animalModelManager.save( person, contactInfo, animalModel, taxon, phenotype, sexDistribution, availability );    				    
    	
		// Setup global constants to use for submission / editing process
		AnimalModel am = animalModelManager.get( "" + modelID );	      	
    	request.getSession().setAttribute( Constants.MODELID, am.getId().toString() );
    	request.getSession().setAttribute( Constants.MODELDESCRIPTOR, am.getModelDescriptor() );
    	request.getSession().setAttribute( Constants.MODELSTATUS, am.getState() );
    	
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "creation.successful" ) );
        saveErrors( request, msg );
     
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
    
    
	/** 
	 * Used to update a animalModel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit( ActionMapping mapping, 
    									    ActionForm form,
							          	    HttpServletRequest request,
							          	    HttpServletResponse response)
    throws Exception {
    
    	// Grab the current modelID from the session  
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );

    	ModelCharacteristicsForm modelChar = ( ModelCharacteristicsForm ) form;
    	
		System.out.println( "<AnimalModelAction editExistingModel> New Model Being created with following Characteristics:" + 
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
																"\n\t currentUser: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
		
		//retrieve model by it's id
		AnimalModel animalModel = animalModelManager.get( modelID );	  
		
		//set the isToolMouse variable
		if ( modelChar.getIsToolMouse().equals("yes") )
			animalModel.setIsToolMouse( true );
		else 
			animalModel.setIsToolMouse( false );
		
		animalModel.setUrl( modelChar.getUrl() );    	
		
		//Model Name/Descriptor
		animalModel.setModelDescriptor( modelChar.getModelDescriptor() );		
		animalModel.setExperimentDesign( modelChar.getExperimentDesign() );
		
		//Set contactInfo
		ContactInfo contactInfo = (ContactInfo) animalModel.getPrincipalInvestigator().getContactInfoCollection().get(0);
		contactInfo.setEmail( modelChar.getEmail() );						
							
		//Species & Strain
		Taxon taxon = animalModel.getSpecies();			
		taxon.setScientificName( modelChar.getScientificName() );			
		taxon.setEthnicityStrain( modelChar.getEthinicityStrain() );
		
		animalModel.setSpecies( taxon );

		// TODO: if the user inputs "Other", add this to the Taxon 
		//if ( modelChar.getOtherEthinicityStrain() != null || ! modelChar.getOtherEthinicityStrain().equals( "" ) ) 
		// {
		//			System.out.println( "<AnimalModelAction saveNewModel> Someone entered in a new Strain " + modelChar.getOtherEthinicityStrain() );
		//			System.out.println( "<AnimalModelAction saveNewModel> Sending a notice email to Ulli" );
		// TODO: call a email function
		//		}
			
		//Phenotype description
		Phenotype phenotype = animalModel.getPhenotype();
		phenotype.setDescription( modelChar.getDescription() );
		phenotype.setBreedingNotes( modelChar.getBreedingNotes() );
								
		//Set the gender
		SexDistribution sexDistribution = phenotype.getSexDistribution();
		sexDistribution.setType( modelChar.getType() );
		phenotype.setSexDistribution( sexDistribution );
		
		// Setting the phenotype sets gender, description and breedingNotes
		animalModel.setPhenotype( phenotype );
		
		Availability availability = new Availability();  
		
		// Set the for the enteredDate the current date, when this model was created
		availability.setEnteredDate( new Date() );
		
		SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
		Date releaseDate = new Date();

		if ( modelChar.getReleaseDate().equals( "immediately" ) ) {
			availability.setReleaseDate( releaseDate );
			System.out.println( "<AnimalModelAction editExistingModel> Current Date: " + formatter.format( releaseDate ) );    		
		} else {
			// TODO: add popup calender to submitNewModel.jsp and convert the string to Date object here
			// modelChar.getCalendarReleaseDate();
			availability.setReleaseDate( releaseDate );
		}						    		    	
    	animalModel.setAvailability( availability );
    	
		// Save the changes to the AnimalModel		
		animalModelManager.save( animalModel );    				    
    	
		// Setup global constants to use for submission / editing process
		animalModel = animalModelManager.get( "" + modelID );	      	
    	request.getSession().setAttribute( Constants.MODELID, animalModel.getId().toString() );
    	request.getSession().setAttribute( Constants.MODELDESCRIPTOR, animalModel.getModelDescriptor() );
    	request.getSession().setAttribute( Constants.MODELSTATUS, animalModel.getState() );
    	
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "modelchar.edit.successful" ) );
        saveErrors( request, msg );
     
	   return mapping.findForward( "AnimalModelTreePopulateAction" );  	    
    }
    
    
	/**
	 * Creates an exact duplicate of a AnimalModel 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate( 	ActionMapping mapping, 
									    ActionForm form,
						          	    HttpServletRequest request,
						          	    HttpServletResponse response)
    throws Exception {
    
    	System.out.println( "<AnimalModelAction duplicateModel> modelID=" + request.getParameter( "aModelID" ) );
		
		String modelID = request.getParameter( "aModelID" );
		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
		
		//retrieve model by it's id
		AnimalModel animalModel = animalModelManager.get( modelID );	  
    	
    	//create a new model
 		animalModelManager.save( animalModel );
    		        
		return mapping.findForward( "duplicatesuccessful" );
    }
    
	/**
	 * Delete a AnimalModel based on it's id  
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward delete( ActionMapping mapping, 
									  ActionForm form,
							          HttpServletRequest request,
							          HttpServletResponse response)
	throws Exception {
		
		System.out.println( "<AnimalModelAction DeleteModel> modelID=" + request.getParameter( "aModelID" ) );
		
		// Retrieve the parameter passed by the URL
		String modelID = request.getParameter( "aModelID" );
		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		animalModelManager.remove( modelID );
		
    	//Add a message to be displayed in submitModles saying you've deleted a model  
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "delete.successful" ) );
        saveErrors( request, msg );
		
		return mapping.findForward( "modeldeleted" );

    }
   
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward returnUserModels(  ActionMapping mapping, 
											ActionForm form,
									        HttpServletRequest request,
									        HttpServletResponse response ) 
	{
		System.out.println( "<UserAction ReturnUserModels> Entering... " );
		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
		List amList = animalModelManager.getAll( (String) request.getSession().getAttribute("camod.loggedon.username") );	 
		
		//sort list by modelDescriptor, ignoring case
		Collections.sort( amList, new _sortAnimalModels() );
		
		request.getSession().setAttribute( Constants.USERMODELLIST, amList );
		
		return mapping.findForward( "submitModels" );
	}
}

class _sortAnimalModels implements java.util.Comparator {

	public void SortAnimalModels() {}
	
	public int compare( Object oo1, Object oo2 ) 
	{
		AnimalModel o1 = (AnimalModel) oo1;    	
		AnimalModel o2 = (AnimalModel) oo2;
		
		if ( o1.getModelDescriptor().compareToIgnoreCase( o2.getModelDescriptor() ) > 0 )
			return 1;
		else
			if ( o1.getModelDescriptor().compareToIgnoreCase( o2.getModelDescriptor() ) < 0 )
				return -1;
			else
				return 0;
	}   
}