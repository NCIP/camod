package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SetOptionsAction extends BaseAction {
		
	/**
	 * This method will take in a species and set the dropdown for it's strains
	 * Then will return back to where it's supposed to goto
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {    	 
    	  	 
    	 //DropdownUtil drop = new DropdownUtil();
    	 NewDropdownUtil drop = new NewDropdownUtil();
    	 
    	 String speciesName = request.getParameter( "speciesName" );
    	 String page = request.getParameter( "page" );
    	 
    	 System.out.println( "<SetOptionsActions execute> speciesName: " + speciesName + "  page: " + page );
    	 
    	 drop.populateDropdown( request, Constants.Dropdowns.STRAINDROP, speciesName );
    	
    	 if( page.equals("submitNewModel" )) {    		 
			 ModelCharacteristicsForm modelChar = ( ModelCharacteristicsForm ) form;
			 request.getSession().setAttribute( Constants.FORMDATA, modelChar );			 
			 return mapping.findForward( "submitNewModel" );
    	 }    	 
    	 else if( page.equals("submitModelCharacteristics" )) {    		 
			 ModelCharacteristicsForm modelChar = ( ModelCharacteristicsForm ) form;
			 request.getSession().setAttribute( Constants.FORMDATA, modelChar );			 
			 return mapping.findForward( "submitModelCharacteristics" );
    	 }
    	 else
    		 return mapping.findForward( "submitNewModel" );
    }        
}
