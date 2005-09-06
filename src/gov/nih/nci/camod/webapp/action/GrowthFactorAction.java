package gov.nih.nci.camod.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.SexDistributionManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.service.TreatmentManager;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;

/**
 * GrowthFactorAction Class
 */

public class GrowthFactorAction extends BaseAction {
	
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

        return mapping.findForward("");
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
    	
    	 return mapping.findForward("");
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
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }
        
        System.out.println( "<GrowthFactorAction edit> Entering... " );
        
        // Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );
		
        GrowthFactorForm growthFactorForm = ( GrowthFactorForm ) form;
        
        System.out.println( "<GrowthFactorAction editing> editing... " +
							"\n\t name: " + growthFactorForm.getName() + 
							"\n\t otherName: " + growthFactorForm.getOtherName() +
							"\n\t type: " + growthFactorForm.getType() +
							"\n\t regimen: " + growthFactorForm.getRegimen() +
							"\n\t dosage: " + growthFactorForm.getDosage() +
							"\n\t doseUnit: " + growthFactorForm.getDoseUnit() +
							"\n\t ageAtTreatment: " + growthFactorForm.getAgeAtTreatment() +
							"\n\t ageUnit: " + growthFactorForm.getAgeUnit() );       

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        SexDistributionManager sexDistributionManager = (SexDistributionManager) getBean( "sexDistributionManager" );
        TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
        AgentManager agentManager = ( AgentManager ) getBean( "agentManager" );
        TherapyManager therapyManager = ( TherapyManager ) getBean("therapyManager" );
        
        AnimalModel animalModel = animalModelManager.get( modelID );
		
        //retrieve the list of all therapies from the current animalModel
		List therapyList = animalModel.getTherapyCollection();
		
		Therapy ty = new Therapy();
		int therapyNumber = 0;
		
		//find the specific one we need
		for ( int i=0; i<therapyList.size(); i++ )
		{
			ty = (Therapy)therapyList.get(i);
			System.out.println( " searching ... id=" + ty.getId().toString() + " is it equal? " + aTherapyID );
			if ( ty.getId().toString().equals( aTherapyID) )
			{				
				therapyNumber = i;
				System.out.println( "found a match!");
				break;
			}
		}       
		
		//Set the treatment
		Treatment ts = ty.getTreatment();
        
    	//Set the gender
		SexDistribution sexDistribution = ts.getSexDistribution();
		sexDistribution.setType( growthFactorForm.getType() );		
		//save the sexdistro
		sexDistributionManager.save( sexDistribution );
		
		//save the treatment
		ts.setRegimen( growthFactorForm.getRegimen() );
        ts.setSexDistribution( sexDistribution );        
    	//Append the ageunit onto the age at treatment variable
        ts.setAgeAtTreatment( growthFactorForm.getAgeAtTreatment() + " " + growthFactorForm.getAgeUnit() );
        ts.setDosage( growthFactorForm.getDosage() + " " + growthFactorForm.getDoseUnit() );
    	treatmentManager.save( ts );
				
        //Agent IS-A an EnvironmentalFactor
        Agent agent = ty.getAgent();
        agent.setName( growthFactorForm.getName() );
        agent.setType( "Growth Factor" );
        agentManager.save( agent );
        
        //TherapeuticExperiment property is false, tells us that this is an environmentalFactor
        ty.setTherapeuticExperiment( false );
        ty.setAgent( agent );
        ty.setTreatment( ts );
        therapyManager.save( ty );
        therapyList.set( therapyNumber, ty );
        
        animalModel.setTherapyCollection( therapyList );
        
        //Persist all changes to the db
        //animalModelManager.save( animalModel );            
		
        //Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "growthFactor.edit.successful" ) );
        saveErrors( request, msg );
        
        return mapping.findForward("AnimalModelTreePopulateAction");
    }

    /**
     * Save
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        
        System.out.println( "<GrowthFactorAction save> Entering... " );
    	
        // Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    
    	
        GrowthFactorForm growthFactorForm = ( GrowthFactorForm ) form;
        
        System.out.println( "<GrowthFactorAction save> Adding... " +
        					"\n\t name: " + growthFactorForm.getName() + 
        					"\n\t otherName: " + growthFactorForm.getOtherName() +
        					"\n\t type: " + growthFactorForm.getType() +
        					"\n\t regimen: " + growthFactorForm.getRegimen() +
        					"\n\t dosage: " + growthFactorForm.getDosage() +
        					"\n\t doseUnit: " + growthFactorForm.getDoseUnit() +
        					"\n\t ageAtTreatment: " + growthFactorForm.getAgeAtTreatment() +
        					"\n\t ageUnit: " + growthFactorForm.getAgeUnit() );        

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        SexDistributionManager sexDistributionManager = (SexDistributionManager) getBean( "sexDistributionManager" );
        TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
        AgentManager agentManager = ( AgentManager ) getBean( "agentManager" );
        
        AnimalModel animalModel = animalModelManager.get( modelID );
        
		//Set the gender
		SexDistribution sexDistribution = new SexDistribution();
		sexDistribution.setType( growthFactorForm.getType() );		
		//save the sexdistro
		sexDistributionManager.save( sexDistribution );
		
		//Set the treatment
		Treatment ts = new Treatment();
        ts.setRegimen( growthFactorForm.getRegimen() );
        ts.setSexDistribution( sexDistribution );        
    	//Append the ageunit onto the age at treatment variable
        ts.setAgeAtTreatment( growthFactorForm.getAgeAtTreatment() + " " + growthFactorForm.getAgeUnit() );
        ts.setDosage( growthFactorForm.getDosage() + " " + growthFactorForm.getDoseUnit() );
    	treatmentManager.save( ts );
        
        //Agent IS-A an EnvironmentalFactor
        Agent agent = new Agent();
        agent.setName( growthFactorForm.getName() );
        agent.setType( "Growth Factor" );
        agentManager.save( agent );
        
        //TherapeuticExperiment property is false, tells us that this is an environmentalFactor
        Therapy ty = new Therapy();
        ty.setTherapeuticExperiment( false );
        ty.setAgent( agent );
        ty.setTreatment( ts );
		
        //Add therapy to animalModel
		animalModel.addTherapy( ty );
                
        //Persist all changes to the db
        animalModelManager.save( animalModel );              
    	
        //Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "growthFactor.creation.successful" ) );
        saveErrors( request, msg );
        
        return mapping.findForward( "AnimalModelTreePopulateAction" );
    }
}
