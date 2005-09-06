package gov.nih.nci.camod.webapp.action;

import java.util.List;

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
import gov.nih.nci.camod.webapp.form.HormoneForm;
import gov.nih.nci.camod.webapp.form.HormoneForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * HormoneAction Class
 */

public class HormoneAction extends BaseAction {
	
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
        
        System.out.println( "<HormoneAction edit> Entering... " );
        
        // Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );
		
        HormoneForm hormoneForm = ( HormoneForm ) form;
        
        System.out.println( "<HormoneAction editing> editing... " +
        					"\n\t name: " + hormoneForm.getName() + 
        					"\n\t otherName: " + hormoneForm.getOtherName() +
        					"\n\t regimen: " + hormoneForm.getRegimen() +
        					"\n\t dosage: " + hormoneForm.getDosage() +    
        					"\n\t doseUnit: " + hormoneForm.getDoseUnit() );        

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
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
      		
		//save the treatment
		ts.setRegimen( hormoneForm.getRegimen() );
		
    	//Append the ageunit onto the age at treatment variable
        ts.setDosage( hormoneForm.getDosage() + " " + hormoneForm.getDoseUnit() );
    	treatmentManager.save( ts );
				
        //Agent IS-A an EnvironmentalFactor
        Agent agent = ty.getAgent();
        agent.setName( hormoneForm.getName() );
        agent.setType( "Hormone" );
        agentManager.save( agent );
        
        //TherapeuticExperiment property is false, tells us that this is an environmentalFactor
        ty.setTherapeuticExperiment( false );
        ty.setAgent( agent );
        ty.setTreatment( ts );
        therapyManager.save( ty );
        therapyList.set( therapyNumber, ty );
        
        animalModel.setTherapyCollection( therapyList );     
		
        //Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "hormone.edit.successful" ) );
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
        
        System.out.println( "<HormoneAction save> Entering... " );
    	
        // Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    
    	
        HormoneForm hormoneForm = ( HormoneForm ) form;
        
        System.out.println( "<HormoneAction save> Adding... " +
        					"\n\t name: " + hormoneForm.getName() + 
        					"\n\t otherName: " + hormoneForm.getOtherName() +
        					"\n\t regimen: " + hormoneForm.getRegimen() +
        					"\n\t ageUnit: " + hormoneForm.getDosage() );        

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
        AgentManager agentManager = ( AgentManager ) getBean( "agentManager" );
        
        AnimalModel animalModel = animalModelManager.get( modelID );
        		
		//Set the treatment
		Treatment ts = new Treatment();
        ts.setRegimen( hormoneForm.getRegimen() );      
    	//Append the doseUnit onto the dosage at treatment variable
        ts.setDosage( hormoneForm.getDosage() + " " + hormoneForm.getDoseUnit() );
    	treatmentManager.save( ts );
        
        //Agent IS-A an EnvironmentalFactor
        Agent agent = new Agent();
        agent.setName( hormoneForm.getName() );
        agent.setType( "Hormone" );
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
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "hormone.creation.successful" ) );
        saveErrors( request, msg );
        
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}
