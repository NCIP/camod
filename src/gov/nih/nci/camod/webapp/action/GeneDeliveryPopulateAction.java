package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;
import gov.nih.nci.camod.webapp.form.RadiationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GeneDeliveryPopulateAction extends BaseAction{
	
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

		System.out.println( "<GeneDeliveryPopulateAction populate> Entering populate() " );

		// Create a form to edit
		GeneDeliveryForm geneDelivForm = ( GeneDeliveryForm ) form;
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );		
		
		// Grab the current modelID from the session 
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );
		
		System.out.println( "<GeneDeliveryPopulateAction populate> Grab the current modelID= " +modelID );
		
		
		
		
		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, geneDelivForm );		
		
		System.out.println( "<GeneDeliveryPopulateAction populate> returning to submitGeneDelivery.jsp " );		
		
		return mapping.findForward("submitGeneDelivery");

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
		
		System.out.println( "<GeneDeliveryPopulateAction dropdown> Entering dropdown() " );
		
		//blank out the FORMDATA Constant field
		GeneDeliveryForm GeneDelivForm = ( GeneDeliveryForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, GeneDelivForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<GeneDeliveryPopulateAction dropdown> before return submitRadiation " );
		
		return mapping.findForward( "submitGeneDelivery" );
		
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
		
			System.out.println( "<GeneDeliveryPopulateAction dropdown> Entering void dropdown()" );
			
			//Prepopulate all dropdown fields, set the global Constants to the following
			NewDropdownUtil drop = new NewDropdownUtil();
		
			//Prepopulate all dropdown fields, set the global Constants to the following
			drop.populateDropdown( request, Constants.Dropdowns.VIRALVECTORDROP, "" );
			
			System.out.println( "<GeneDeliveryPopulateAction dropdown> Exiting void dropdown()" );

	}			

}
