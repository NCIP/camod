package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.SurgeryForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SurgeryPopulateAction extends BaseAction {
	
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
	  
		System.out.println( "<SurgeryPopulateAction populate> ... " );
		
		SurgeryForm surgeryForm = ( SurgeryForm ) form;
		
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
		
		//System.out.println( "sexDistro=" + ty.getTreatment().getSexDistribution().getType() );
		surgeryForm.setType( ty.getTreatment().getSexDistribution().getType() );
		surgeryForm.setAgeAtTreatment( ty.getTreatment().getAgeAtTreatment() );
		//surgeryForm.setAgeUnit();
		surgeryForm.setName( ty.getAgent().getName() );
		surgeryForm.setRegimen(ty.getTreatment().getRegimen() );
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, surgeryForm );
		
      return mapping.findForward( "submitSurgeryOther" );
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
		
		System.out.println( "<SurgeryPopulateAction dropdown> ... " );
		
		//blank out the FORMDATA Constant field
		SurgeryForm surgeryForm = ( SurgeryForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, surgeryForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<SurgeryPopulateAction> exiting... " );
		
		return mapping.findForward( "submitSurgeryOther" );
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
		
		System.out.println( "<SurgeryPopulateAction dropdown> Entering... " );
	
		//Prepopulate all dropdown fields, set the global Constants to the following
		//DropdownUtil drop = new DropdownUtil();
		NewDropdownUtil drop = new NewDropdownUtil();
		
		//ServletContext application = servlet.getServletConfig().getServletContext();
					
		drop.populateDropdown( request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );			 		
		drop.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );
		drop.populateDropdown( request, Constants.Dropdowns.SURGERYDROP, "" );
		
		//Store the values for the drop down menus in a Constants variable, used in the JSP
		//request.getSession().setAttribute( Constants.Dropdowns.SEXDISTRIBUTIONDROP, sexDistList );	
		//request.getSession().setAttribute( Constants.Dropdowns.AGEUNITSDROP, ageUnitsList );
		//request.getSession().setAttribute( Constants.Dropdowns.SURGERYDROP, surgeryOtherList );
	}	
}
