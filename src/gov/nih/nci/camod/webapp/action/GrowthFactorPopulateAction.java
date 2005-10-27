/**
 * 
 * $Id: GrowthFactorPopulateAction.java,v 1.7 2005-10-27 19:25:06 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/10/20 20:38:17  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class GrowthFactorPopulateAction extends BaseAction {
	
	/** 
	 * Pre-populate all field values in the form SurgeryForm 
	 *  Used by <jspName>
	 * 
	 */ 
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response)
	  throws Exception {
	  
		System.out.println( "<GrowthFactorPopulateAction populate> ... " );
		
		GrowthFactorForm growthFactorForm = ( GrowthFactorForm ) form;
		
		String aTherapyID = request.getParameter( "aTherapyID" );
		
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
		
        // Set the otherName and/or the selected name attribute
        if (therapy.getAgent().getNameUnctrlVocab() != null) {
        	growthFactorForm.setName(Constants.Dropdowns.OTHER_OPTION);        	
        	growthFactorForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
        } else {
        	growthFactorForm.setName(therapy.getAgent().getName());
        }
        
        // Set the other administrative route and/or the selected administrative route
        if (therapy.getTreatment().getAdminRouteUnctrlVocab() != null) {
        	growthFactorForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);        	
        	growthFactorForm.setOtherAdministrativeRoute(therapy.getTreatment().getAdminRouteUnctrlVocab());
        } else {
        	growthFactorForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
        }        
		
		growthFactorForm.setType( therapy.getTreatment().getSexDistribution().getType() );
		growthFactorForm.setAgeAtTreatment( therapy.getTreatment().getAgeAtTreatment() );
		growthFactorForm.setDosage( therapy.getTreatment().getDosage() );
		growthFactorForm.setRegimen(therapy.getTreatment().getRegimen() );
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, growthFactorForm );
		
      return mapping.findForward( "submitGrowthFactors" );
	}	
	
	/**
	 * Populate the dropdown menus for submitSurgeryOther
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
		
		System.out.println( "<GrowthFactorPopulateAction dropdown> ... " );
		
		//blank out the FORMDATA Constant field
		GrowthFactorForm growthFactorForm = ( GrowthFactorForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, growthFactorForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<GrowthFactorPopulateAction> exiting... " );
		
		return mapping.findForward( "submitGrowthFactors" );
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
		
		System.out.println( "<GrowthFactorPopulateAction dropdown> Entering... " );
	
		//Prepopulate all dropdow2n fields, set the global Constants to the following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP, Constants.Dropdowns.ADD_BLANK );					
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, Constants.Dropdowns.ADD_BLANK );			 		
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.GROWTHFACTORDOSEUNITSDROP, "" );
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.GROWTHFACTORDROP, Constants.Dropdowns.ADD_BLANK );		
	}		

}
