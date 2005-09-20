package gov.nih.nci.camod.webapp.action;

import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.CellLineForm;
import gov.nih.nci.camod.webapp.form.PublicationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

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
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aCellID = request.getParameter( "aCellID" );		
		
		/* Grab the current modelID from the session */
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );
        	
		// Use the current animalModel based on the ID stored in the session
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
		AnimalModel am = animalModelManager.get( modelID );	
		
		//retrieve the list of all therapies from the current animalModel
		List cellList = am.getCellLineCollection();
		
		CellLine cell = new CellLine();
		
		//find the specific one we need
		for ( int i=0; i<cellList.size(); i++ ) {
			cell = (CellLine)cellList.get(i);
			if ( cell.getId().toString().equals( aCellID ) )
				break;
		}

		cellLineForm.setCellLineName(cell.getName());
		cellLineForm.setExperiment( cell.getExperiment() );
		cellLineForm.setResults( cell.getResults() );
		cellLineForm.setComments(cell.getComments());
		
		Organ organ = new Organ();
		cellLineForm.setOrganName(organ.getName());
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, cellLineForm );
		
		System.out.println( "<CellLinePopulateAction populate> Exited" );

		return mapping.findForward("submitCellLines");
	}

}
