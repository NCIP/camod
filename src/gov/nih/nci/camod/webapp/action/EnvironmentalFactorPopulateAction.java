package gov.nih.nci.camod.webapp.action;

import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.service.EnvironmentalFactorManager;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;
import gov.nih.nci.camod.webapp.util.DropdownUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EnvironmentalFactorPopulateAction extends BaseAction {

	/** 
	 * Pre-populate all field values in the form EnvironmentalFactorForm 
	 *  Used by submitEnvironmentalFactors
	 * 
	 */ 
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response)
	  throws Exception {
		
		System.out.println( "<EnvironmentalFactorPopulateAction populate> Entering... " );

		// Create a form to edit
		EnvironmentalFactorForm envirFact = ( EnvironmentalFactorForm ) form;
		
		// Use the current animalModel based on the ID stored in the session
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
		AnimalModel am = animalModelManager.get( modelID );	
		
		//TODO figure out how to deal with collection relationships
		//envirFact.setName(am.getEnvironmentalFactorCollection().getClass().getName() );

		//envirFact.setDosage(am.);		
		envirFact.setAdministrativeRoute("");
		envirFact.setRegimen("");
		envirFact.setAgeAtTreatment("");
		
		envirFact.setType( am.getPhenotype().getSexDistribution().getType());

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, envirFact );		

		return mapping.findForward("submitEnvironmentalFactors");

	}
	
	/**
	 * Populate the dropdown menus for submitEnvironmentalFactors
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown( ActionMapping mapping, 
			   					   ActionForm form,
			   					   HttpServletRequest request,
			   					   HttpServletResponse response )
	  throws Exception {	
		
		System.out.println( "<EnvironmentalFactorPopulateAction dropdown> ... " );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		return mapping.findForward( "submitEnvironmentalFactors" );
	}	

	/**
	 * Populate all drowpdowns for this type of form 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown( HttpServletRequest request,
						  HttpServletResponse response )
	  throws Exception {
		
			System.out.println( "<EnvironmentalFactorPopulateAction dropdown> Entering... " );
		
			//Prepopulate all dropdown fields, set the global Constants to the following
			DropdownUtil drop = new DropdownUtil();
			
/*			TO DO - Implement dropdowns
 			//Environmental Factors dropdown
			List envFactorsList = ;
			
			//Administrative Routes dropdown
			//Dose units dropdown
			
			List ageLst = drop.getAgeList();
			List sexDistList = drop.getSexDistributionsList();
			
			//TODO: Get specific list for speciesName ( for submitModelCharacteristics ) 
			List strainList = drop.getStrainList( speciesList.get(0).toString() );
			
			ServletContext application = servlet.getServletConfig().getServletContext();			
			String configFileName = application.getRealPath( "/config/SexDistributions.txt" );
			
			List sexDistList = drop.getDropdownListFromFile( configFileName );			 
				
			//Store the values for the drop down menus in a Constants variable, used in the JSP
			request.getSession().setAttribute( Constants.ENVIRONFACTORDROP, envFactorList ); 
		    request.getSession().setAttribute( Constants.SPECIESDROP, speciesStrainLst );
			request.getSession().setAttribute( Constants.AGEDROP, ageLst );
			request.getSession().setAttribute( Constants.SEXDISTRIBUTIONDROP, sexDistList );

*/
	}	
}
