/**
 * @author pandyas
 * 
 * $Id: CellLinePopulateAction.java,v 1.7 2005-10-20 20:26:21 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.action;

import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.CellLineForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CellLinePopulateAction  extends BaseAction {
	
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
		
		System.out.println( "<CellLinePopulateAction populate> Entered" );	

		// Create a form to edit
		CellLineForm cellLineForm = ( CellLineForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, cellLineForm );		
		
    	// Grab the current Cell Line we are working with related to this animalModel
    	String aCellID = request.getParameter( "aCellID" );
		
		// Grab the current modelID from the session 
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );
		
		System.out.println( "<CellLinePopulateAction populate> Grab the current modelID= " + modelID );		
		
		//retrieve the list of all cell lines from the current animalModel
		List cellList = am.getCellLineCollection();
		
		CellLine cellLine = new CellLine();
		
		//find the specific one we need
		for ( int i=0; i<cellList.size(); i++ ) {
			cellLine = (CellLine)cellList.get(i);
			if ( cellLine.getId().toString().equals( aCellID ) )
				break;
		}
		cellLineForm.setCellLineName(cellLine.getName());
		cellLineForm.setExperiment( cellLine.getExperiment() );
		cellLineForm.setResults( cellLine.getResults() );
		cellLineForm.setComments(cellLine.getComments());		

		/*set Organ attributes*/
		System.out.println( "<CellLinePopulateAction populate> get the Organ attributes" );

		//since we are always querying from concept code (save and edit), simply display VSPreferredDescription
		cellLineForm.setOrgan( cellLine.getOrgan().getEVSPreferredDescription() );
		System.out.println( "setOrgan= " + cellLine.getOrgan().getEVSPreferredDescription()); 

		cellLineForm.setOrganTissueCode( cellLine.getOrgan().getConceptCode());
		System.out.println( "OrganTissueCode= " +cellLine.getOrgan().getConceptCode());	 		

		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, cellLineForm );
		
		System.out.println( "<CellLinePopulateAction populate> Exited" );

		return mapping.findForward("submitCellLines");
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
		
		System.out.println( "<CellLinePopulateAction dropdown> Entering dropdown() " );
		
		//blank out the FORMDATA Constant field
		CellLineForm cellLineForm = ( CellLineForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, cellLineForm );
		
		System.out.println( "<CellLinePopulateAction dropdown> before return submitCellLines " );
		
		return mapping.findForward( "submitCellLines" );
		
	}	
	
}
