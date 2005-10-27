/**
 * 
 * $Id: HormonePopulateAction.java,v 1.8 2005-10-27 19:25:06 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/10/20 20:38:41  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.HormoneForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class HormonePopulateAction extends BaseAction {

	/** 
	 * Pre-populate all field values in the form HormoneForm 
	 *  Used by <jspName>
	 * 
	 */ 
	public ActionForward populate( ActionMapping mapping, 
								   ActionForm form,
						           HttpServletRequest request,
						           HttpServletResponse response)
	  throws Exception {
	  
		System.out.println( "<HormonePopulateAction populate> ... " );
		
		HormoneForm hormoneForm = ( HormoneForm ) form;
		
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
        	hormoneForm.setName(Constants.Dropdowns.OTHER_OPTION);        	
        	hormoneForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
        } else {
        	hormoneForm.setName(therapy.getAgent().getName());
        }
        
        // Set the other administrative route and/or the selected administrative route
        if (therapy.getTreatment().getAdminRouteUnctrlVocab() != null) {
        	hormoneForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);        	
        	hormoneForm.setOtherAdministrativeRoute(therapy.getTreatment().getAdminRouteUnctrlVocab());
        } else {
        	hormoneForm.setAdministrativeRoute(therapy.getTreatment().getAdministrativeRoute());
        }         
		
		hormoneForm.setDosage( therapy.getTreatment().getDosage() );
		hormoneForm.setRegimen(therapy.getTreatment().getRegimen() );
		hormoneForm.setType(therapy.getTreatment().getSexDistribution().getType());
		hormoneForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());		
		
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, hormoneForm );
		
      return mapping.findForward( "submitHormone" );
	}	

	
	/**
	 * Populate the dropdown menus for submitHormone
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
		
		System.out.println( "<HormonePopulateAction dropdown> entering... " );
		
		//blank out the FORMDATA Constant field
		HormoneForm hormoneForm = ( HormoneForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, hormoneForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<HormonePopulateAction dropdown> exiting... " );
		
		return mapping.findForward( "submitHormone" );
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
		
		System.out.println( "<HormonePopulateAction dropdown> Entering... " );
	
		//Prepopulate all dropdown fields, set the global Constants to the following

        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.HORMONEDROP, Constants.Dropdowns.ADD_BLANK );
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.HORMONEUNITSDROP, "" );
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );        
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP, Constants.Dropdowns.ADD_BLANK );
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, Constants.Dropdowns.ADD_BLANK );        
	}	
}
