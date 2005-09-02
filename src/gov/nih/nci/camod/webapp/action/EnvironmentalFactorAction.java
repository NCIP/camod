package gov.nih.nci.camod.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import gov.nih.nci.camod.Constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.actions.DispatchAction;

import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.Phenotype;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.domain.SexDistribution;

import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.EnvironmentalFactorManager;


/**
 * EnvironmentalFactorAction Class
 */
public final class EnvironmentalFactorAction extends BaseAction {

	/** Called from submitNewModel.jsp
	 * 
	 */ 
    public ActionForward save( ActionMapping mapping, 
    								   ActionForm form,
							           HttpServletRequest request,
							           HttpServletResponse response)
    throws Exception {
     
    	EnvironmentalFactorForm envirFact = ( EnvironmentalFactorForm ) form;
    	
		System.out.println( "<EnvironmentalFactorAction save> following Characteristics:" + 
																"\n\t name: " + envirFact.getName() + 
																"\n\t otherName: " + envirFact.getOtherName() + 
																"\n\t dosage: " + envirFact.getDosage() + 
																"\n\t administrativeRoute: " + envirFact.getAdministrativeRoute() +
																"\n\t regimen: " + envirFact.getRegimen() +
																"\n\t ageAtTreatment: " + envirFact.getAgeAtTreatment() +
																"\n\t type: " + envirFact.getType() +
																"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		Person person = new Person();
		
		//Set the current user as the person who entered this model
		person.setUsername( (String) request.getSession().getAttribute( Constants.CURRENTUSER ) );    		    	
			
		/* EnvironmentalFactor Object */
		EnvironmentalFactor environmentalFactor = new EnvironmentalFactor();		
		environmentalFactor.setName(envirFact.getName());
		
		// if ( envirFact.getOtherName() != null || ! envirFact.getOtherName().equals( "" ) ) 
		// {
		//			System.out.println( " Someone entered in a new environmental name " + envirFact.getOtherName() );
		//			System.out.println( " Sending a notice email to Ulli" );
		// TODO: call a email function
		//		}		
		
		/* Treatment Object */
		Treatment treatment = new Treatment();
		treatment.setDosage(envirFact.getDosage());
		treatment.setRegimen(envirFact.getRegimen());
		treatment.setAgeAtTreatment(envirFact.getAgeAtTreatment());		
		treatment.setAdministrativeRoute(envirFact.getAdministrativeRoute());
		
		// if ( envirFact.getOtherAdministrativeRoute() != null || ! envirFact.getOtherAdministrativeRoute().equals( "" ) ) 
		// {
		//			System.out.println( " Someone entered in a new environmental name " + envirFact.getOtherAdministrativeRoute() );
		//			System.out.println( " Sending a notice email to Ulli" );
		// TODO: call a email function
		//		}		
		
		/* SexDistribution Object */ 
		SexDistribution sexDistribution = new SexDistribution();
		sexDistribution.setType(envirFact.getType());
		
		EnvironmentalFactorManager environmentalFactorManager = (EnvironmentalFactorManager) getBean( "environmentalFactorManager" );
		//environmentalFactorManager.save( person, environmentalFactor, treatment, sexDistribution );
		//environmentalFactorManager.save( environmentalFactor, treatment, sexDistribution ); 
		
		/*  Setup global constants to use for submission / editing process */
		/*  Rework since save method does not return modelID */
		//AnimalModel am = animalModelManager.get( "" + modelID );	      	
    	//request.getSession().setAttribute( Constants.MODELID, am.getId().toString() );
    	//request.getSession().setAttribute( Constants.MODELDESCRIPTOR, am.getModelDescriptor() );
    	//request.getSession().setAttribute( Constants.MODELSTATUS, am.getState() );
		
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "creation.successful" ) );
        saveErrors( request, msg );
     
        return mapping.findForward("submitEnvironmentalFactors");		
    }	
	

    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }
        
    	EnvironmentalFactorForm envirFact = ( EnvironmentalFactorForm ) form;
    	
		System.out.println( "<EnvironmentalFactorAction save> following Characteristics:" + 
																"\n\t name: " + envirFact.getName() + 
																"\n\t otherName: " + envirFact.getOtherName() + 
																"\n\t dosage: " + envirFact.getDosage() + 
																"\n\t administrativeRoute: " + envirFact.getAdministrativeRoute() +
																"\n\t regimen: " + envirFact.getRegimen() +
																"\n\t ageAtTreatment: " + envirFact.getAgeAtTreatment() +
																"\n\t type: " + envirFact.getType() +
																"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		Person person = new Person();
		
		//Set the current user as the person who entered this model
		person.setUsername( (String) request.getSession().getAttribute( Constants.CURRENTUSER ) );    		    	
			
		//EnvironmentalFactor Object
		EnvironmentalFactor environmentalFactor = new EnvironmentalFactor();
		environmentalFactor.setName(envirFact.getName());
		
		// if ( envirFact.getOtherName() != null || ! envirFact.getOtherName().equals( "" ) ) 
		// {
		//			System.out.println( " Someone entered in a new environmental name " + envirFact.getOtherName() );
		//			System.out.println( " Sending a notice email to Ulli" );
		// TODO: call a email function
		//		}		
		
		//Treatment Object
		Treatment treatment = new Treatment();
		treatment.setDosage(envirFact.getDosage());
		treatment.setAdministrativeRoute(envirFact.getAdministrativeRoute());
		treatment.setRegimen(envirFact.getRegimen());
		treatment.setAgeAtTreatment(envirFact.getAgeAtTreatment());
		
		//SexDistribution Object to set gender 
		SexDistribution sexDistribution = new SexDistribution();
		sexDistribution.setType(envirFact.getType());
		
		EnvironmentalFactorManager environmentalFactorManager = (EnvironmentalFactorManager) getBean( "environmentalFactorManager" );
		//environmentalFactorManager.save( person, environmentalFactor, treatment, sexDistribution ); 
		
		// Setup global constants to use for submission / editing process
		//AnimalModel am = animalModelManager.get( "" + modelID );	      	
    	//request.getSession().setAttribute( Constants.MODELID, am.getId().toString() );
    	//request.getSession().setAttribute( Constants.MODELDESCRIPTOR, am.getModelDescriptor() );
    	//request.getSession().setAttribute( Constants.MODELSTATUS, am.getState() );
		
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "creation.successful" ) );
        saveErrors( request, msg );        

        return mapping.findForward( "submitEnvironmentalFactors" );
    }

	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	
    	 return mapping.findForward("submitEnvironmentalFactors");
    }    
    
    
    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

    	// Grab the current modelID from the session  
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID ); 
    	
    	EnvironmentalFactorForm envirFact = ( EnvironmentalFactorForm ) form;
    	
		System.out.println( "<EnvironmentalFactorAction save> following Characteristics:" + 
																"\n\t name: " + envirFact.getName() + 
																"\n\t otherName: " + envirFact.getOtherName() + 
																"\n\t dosage: " + envirFact.getDosage() + 
																"\n\t administrativeRoute: " + envirFact.getAdministrativeRoute() +
																"\n\t regimen: " + envirFact.getRegimen() +
																"\n\t ageAtTreatment: " + envirFact.getAgeAtTreatment() +
																"\n\t type: " + envirFact.getType() +
																"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		EnvironmentalFactorManager environmentalFactorManager = (EnvironmentalFactorManager) getBean( "environmentalFactorManager" );
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
		
		//retrieve model by it's id
		AnimalModel animalModel = animalModelManager.get( modelID );   	

        return mapping.findForward("submitEnvironmentalFactors");
    }


}