/*
 * LoginAction.java
 *
 * Created on June 24, 2005, 11:31 AM
 *
 */

package gov.nih.nci.camod.webapp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.SimpleSearchForm;



public final class SimpleSearchAction extends BaseAction {      
    
    private static Log log = LogFactory.getLog( SimpleSearchAction.class );

	/**
    */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
	throws IOException, ServletException
	{
        SimpleSearchForm ssf = (SimpleSearchForm) form; 
         
        log.debug("Keyword: " + ssf.getKeyword());
        
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
		List results = animalModelManager.search();
		request.getSession().setAttribute( Constants.SEARCH_RESULTS, results);
        // Forward control to the specified success URI
        return mapping.findForward( "searchResults" );
    }
}