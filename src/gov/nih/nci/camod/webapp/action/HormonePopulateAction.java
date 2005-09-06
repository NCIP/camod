package gov.nih.nci.camod.webapp.action;

import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.HormoneForm;
import gov.nih.nci.camod.webapp.form.SurgeryForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
	  
		System.out.println( "<SurgeryPopulateAction populate> ... " );
		
		HormoneForm hormoneForm = ( HormoneForm ) form;
		
		String aTherapyID = request.getParameter( "aTherapyID" );
		
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );	
		
		//retrieve the list of all therapies from the current animalModel
		List therapyList = am.getTherapyCollection();
		
		Therapy ty = new Therapy();
		
		//find the specific one we need
		for ( int i=0; i<therapyList.size(); i++ )
		{
			ty = (Therapy)therapyList.get(i);
			if ( ty.getId().toString().equals( aTherapyID) )
				break;
		}
		
		hormoneForm.setDosage( ty.getTreatment().getDosage() );
		hormoneForm.setName( ty.getAgent().getName() );
		hormoneForm.setRegimen(ty.getTreatment().getRegimen() );
		
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
		NewDropdownUtil drop = new NewDropdownUtil();
							
		drop.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );
		drop.populateDropdown( request, Constants.Dropdowns.HORMONEDROP, "" );
		drop.populateDropdown( request, Constants.Dropdowns.DOSAGEUNITSDROP, "" );
	}	
}
