/**
 * 
 * $Id: GeneDeliveryPopulateAction.java,v 1.8 2005-10-20 20:24:22 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

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
		GeneDeliveryForm geneDeliveryForm = ( GeneDeliveryForm ) form;
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );		
		
		// Grab the current modelID from the session 
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );
		
		System.out.println( "<GeneDeliveryPopulateAction populate> Grab the current modelID= " + modelID );
		
		List geneDeliveryList = am.getGeneDeliveryCollection();
		GeneDelivery gene = new GeneDelivery();
		
        for (int i = 0; i < geneDeliveryList.size(); i++) {        	
        	gene = (GeneDelivery) geneDeliveryList.get(i);                                              
			if ( gene.getId().toString().equals( aTherapyID) )
				break;
        }

		System.out.println( "<GeneDeliveryPopulateAction populate> get the non-Organ attributes" );
		
        // Set the otherViralVector and/or the selected viral vector attribute
        if (gene.getViralVectorUnctrlVocab() != null) {
        	geneDeliveryForm.setViralVector(Constants.Dropdowns.OTHER_OPTION);        	
        	geneDeliveryForm.setOtherViralVector(gene.getViralVectorUnctrlVocab());
        } else {
        	geneDeliveryForm.setViralVector( gene.getViralVector() );
        }        
        
        geneDeliveryForm.setGeneInVirus( gene.getGeneInVirus() );
        geneDeliveryForm.setRegimen( gene.getTreatment().getRegimen() );
        geneDeliveryForm.setType( gene.getTreatment().getSexDistribution().getType() );
        geneDeliveryForm.setAgeAtTreatment( gene.getTreatment().getAgeAtTreatment() );        
        
		/*set Organ attributes*/
		System.out.println( "<GeneDeliveryPopulateAction populate> get the Organ attributes" );
		
		//since we are always querying from concept code (save and edit), simply display VSPreferredDescription
        geneDeliveryForm.setOrgan( gene.getOrgan().getEVSPreferredDescription() );
		System.out.println( "setOrgan= " + gene.getOrgan().getEVSPreferredDescription()); 

		geneDeliveryForm.setOrganTissueCode( gene.getOrgan().getConceptCode());
		System.out.println( "OrganTissueCode= " +gene.getOrgan().getConceptCode());		

		//Prepopulate all dropdown fields, set the global Constants to the following
		this.dropdown( request, response );

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, geneDeliveryForm );		
		
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
			NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.VIRALVECTORDROP, "" );
	        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );			 		
	        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.AGEUNITSDROP, "" );			
			
			System.out.println( "<GeneDeliveryPopulateAction dropdown> Exiting void dropdown()" );

	}			

}
