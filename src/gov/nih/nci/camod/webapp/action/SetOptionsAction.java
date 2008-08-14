/**
 * 
 * $Id: SetOptionsAction.java,v 1.6 2008-08-14 16:57:03 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:09:41  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

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
    	  	    	 
    	 String speciesName = request.getParameter( "speciesName" );
    	 String page = request.getParameter( "page" );
    	 
         log.info( "<SetOptionsActions execute> speciesName: " + speciesName + "  page: " + page );
    	 
         NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.STRAINDROP, speciesName );
    	        
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
