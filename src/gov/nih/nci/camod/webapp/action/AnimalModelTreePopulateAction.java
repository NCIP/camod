package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.AnimalModelManager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AnimalModelTreePopulateAction extends BaseAction  {
	/**
	 * Create the links for the submission subMenu
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response )
	  throws Exception {	
		  
		System.out.println( "<AnimalModelTreePopulateAction populate> Entering... " );
				
    	// Grab the current modelID from the session
    	String modelID = (String) request.getSession().getAttribute( Constants.MODELID );    	
	
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	
        
        AnimalModel animalModel = animalModelManager.get( modelID );	 
        
        
        // Print the list of EnvironmentalFactors for the Cardiogenic Interventions Section
        List tyList = animalModel.getTherapyCollection();
        
        List surgeryList = new ArrayList();
        
        if( tyList == null || tyList.size() == 0 ){
        	System.out.println( "nothing!" );
        } else {
	        for ( int i=0; i < tyList.size(); i++ )
	        {
	        	Therapy ty = (Therapy)tyList.get(i);
	        	
	        	//check to see if it is an EnvironmentalFactor
	        	if ( ty.getTherapeuticExperiment() == false ) {	        		
	        		Agent agent = ty.getAgent();
	        		Treatment ts = ty.getTreatment();
	        		SexDistribution sexDistribution = ts.getSexDistribution();
	        	
	        		System.out.println("EnvironmentalFactor("+i+")\n\t name=" + agent.getName() +
	        													 "\n\t id=" + ts.getId() +
	        													 "\n\t type=" + agent.getType() +
	        													 "\n\t regimen=" + ts.getRegimen() +
	        													 "\n\t sex=" + sexDistribution.getType() +
	        													 "\n\t age=" + ts.getAgeAtTreatment() );
	        		
	        		if ( agent.getType().equals( "Other") ) {
	        			System.out.println( "added therapy to surgeryList" );
	        			surgeryList.add( ty );
	        		}	        			        		
	        	}	 	        	
	        }
        }
		
        request.getSession().setAttribute( Constants.Submit.SURGERYOTHER_LIST, surgeryList );
		
		return mapping.findForward("submitOverview");
	}
}
