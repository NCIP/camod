package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.NutritionalFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class NutritionalFactorPopulateAction extends BaseAction{
	
	/** 
	 * Pre-populate all field values in the form <FormName> 
	 *  Used by <jspName>
	 * 
	 */ 
		public ActionForward populate( ActionMapping mapping, 
									   ActionForm form,
							           HttpServletRequest request,
							           HttpServletResponse response)
		  throws Exception {

			System.out.println( "<NutritionalFactorPopulateAction populate> Entering populate() " );

			// Create a form to edit
			NutritionalFactorForm nutritForm = ( NutritionalFactorForm ) form;
			
	    	// Grab the current Therapy we are working with related to this animalModel
	    	String aTherapyID = request.getParameter( "aTherapyID" );		
			
			// Grab the current modelID from the session 
			String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
			AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
			AnimalModel am = animalModelManager.get( modelID );	
			
			//retrieve the list of all therapies from the current animalModel
			List therapyList = am.getTherapyCollection();
			
			Therapy therapy = new Therapy();
			
			//find the specific one we need
			for ( int i=0; i<therapyList.size(); i++ )
			{
				therapy = (Therapy)therapyList.get(i);
				if ( therapy.getId().toString().equals( aTherapyID) )
					break;
			}
			
			nutritForm.setType(therapy.getTreatment().getSexDistribution().getType());
			nutritForm.setDosage(therapy.getTreatment().getDosage());
			nutritForm.setRegimen(therapy.getTreatment().getRegimen());
			nutritForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());
			
			nutritForm.setName(therapy.getAgent().getName());
			nutritForm.setRegimen(therapy.getTreatment().getRegimen());
			
			//Prepopulate all dropdown fields, set the global Constants to the following
			this.dropdown( request, response );

			//Store the Form in session to be used by the JSP
			request.getSession().setAttribute( Constants.FORMDATA, nutritForm );

			return mapping.findForward("submitNutritionalFactors");
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
			
			System.out.println( "<NutritionalFactorPopulateAction dropdown> Entering dropdown() " );
			
			//blank out the FORMDATA Constant field
			NutritionalFactorForm nutritForm = ( NutritionalFactorForm ) form;
			request.getSession().setAttribute( Constants.FORMDATA, nutritForm );
			
			//setup dropdown menus
			this.dropdown( request, response );
			
			System.out.println( "<NutritionalFactorPopulateAction dropdown> before return submitRadiation " );
			
			return mapping.findForward( "submitNutritionalFactors" );
			
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
			
				System.out.println( "<NutritionalFactorPopulateAction dropdown> Entering void dropdown()" );
							
				//Prepopulate all dropdown fields, set the global Constants to the following
                NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.NUTRITIONFACTORDROP, "" );

                NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NUTFACTORUNITSDROP, "" );
                NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "" );
                NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );
				
				System.out.println( "<NutritionalFactorPopulateAction dropdown> Exiting void dropdown()" );

		}	
}
