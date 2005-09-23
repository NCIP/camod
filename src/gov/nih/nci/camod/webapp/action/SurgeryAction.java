package gov.nih.nci.camod.webapp.action;

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
import gov.nih.nci.camod.webapp.form.SurgeryForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * SugeryAction Class
 */

public class SurgeryAction extends BaseAction {
	
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
        
        System.out.println( "<SurgeryAction edit> Entering... " );
        
        // Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );
		
        SurgeryForm surgeryForm = ( SurgeryForm ) form;
        
        System.out.println( "<SurgeryAction editing> editing... " +
        					"\n\t name: " + surgeryForm.getName() + 
        					"\n\t otherName: " + surgeryForm.getOtherName() +
        					"\n\t type: " + surgeryForm.getType() +
        					"\n\t regimen: " + surgeryForm.getRegimen() +
        					"\n\t ageUnit: " + surgeryForm.getAgeUnit() );        

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
        SexDistribution sexDistribution = sexDistributionManager.getByType(surgeryForm.getType());
		
		//save the treatment
		ts.setRegimen( surgeryForm.getRegimen() );
        ts.setSexDistribution( sexDistribution );        
    	//Append the ageunit onto the age at treatment variable
        ts.setAgeAtTreatment( surgeryForm.getAgeAtTreatment() + " " + surgeryForm.getAgeUnit() );
    	treatmentManager.save( ts );
				
        //Agent IS-A an EnvironmentalFactor
        Agent agent = ty.getAgent();
        agent.setName( surgeryForm.getName() );
        agent.setType( "Other" );
        agentManager.save( agent );
        
        //TherapeuticExperiment property is false, tells us that this is an environmentalFactor
        ty.setTherapeuticExperiment( new Boolean(false) );
        ty.setAgent( agent );
        ty.setTreatment( ts );
        therapyManager.save( ty );
        therapyList.set( therapyNumber, ty );
        
        animalModel.setTherapyCollection( therapyList );
        
        //Persist all changes to the db
        //animalModelManager.save( animalModel );            
		
        //Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "surgeryother.edit.successful" ) );
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
        
        System.out.println( "<SurgeryAction save> Entering... " );
    	
        // Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    
    	
        SurgeryForm surgeryForm = ( SurgeryForm ) form;
        
        System.out.println( "<SurgeryAction save> Adding... " +
        					"\n\t name: " + surgeryForm.getName() + 
        					"\n\t otherName: " + surgeryForm.getOtherName() +
        					"\n\t type: " + surgeryForm.getType() +
        					"\n\t regimen: " + surgeryForm.getRegimen() +
        					"\n\t ageUnit: " + surgeryForm.getAgeUnit() );        

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        SexDistributionManager sexDistributionManager = (SexDistributionManager) getBean( "sexDistributionManager" );
        TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
        AgentManager agentManager = ( AgentManager ) getBean( "agentManager" );
        
        AnimalModel animalModel = animalModelManager.get( modelID );
        
		//Set the gender
        SexDistribution sexDistribution = sexDistributionManager.getByType(surgeryForm.getType());
		
		//Set the treatment
		Treatment ts = new Treatment();
        ts.setRegimen( surgeryForm.getRegimen() );
        ts.setSexDistribution( sexDistribution );        
    	//Append the ageunit onto the age at treatment variable
        ts.setAgeAtTreatment( surgeryForm.getAgeAtTreatment() + " " + surgeryForm.getAgeUnit() );
    	treatmentManager.save( ts );
        
        //Agent IS-A an EnvironmentalFactor
        Agent agent = new Agent();
        agent.setName( surgeryForm.getName() );
        agent.setType( "Other" );
        agentManager.save( agent );
        
        //TherapeuticExperiment property is false, tells us that this is an environmentalFactor
        Therapy ty = new Therapy();
        ty.setTherapeuticExperiment( new Boolean(false) );
        ty.setAgent( agent );
        ty.setTreatment( ts );
		
        //Add therapy to animalModel
		animalModel.addTherapy( ty );
                
        //Persist all changes to the db
        animalModelManager.save( animalModel );              
    	
        //Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "surgeryother.creation.successful" ) );
        saveErrors( request, msg );
        
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}
