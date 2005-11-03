package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.AssociatedExpressionManager;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AssociatedExpressionPopulateAction extends BaseAction {

    public ActionForward populate( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<AssociatedExpressionPopulateAction populate> Entering populate() " );
	    
	    AssociatedExpressionForm associatedExpressionForm = (AssociatedExpressionForm) form;
        String aAssociatedExpressionID = request.getParameter("aAssociatedExpressionID");
        
    	AssociatedExpressionManager theAssociatedExpressionManager = (AssociatedExpressionManager) getBean( "associatedExpressionManager");
    	ExpressionFeature theExpressionFeature = theAssociatedExpressionManager.get( aAssociatedExpressionID );
    	  	
		ExpressionLevelDesc expLevelDesc = theExpressionFeature.getExpressionLevelDesc();
		Organ organ = theExpressionFeature.getOrgan();
		
		associatedExpressionForm.setName( organ.getName() );
		associatedExpressionForm.setOrgan( organ.getName() );
		
		if( expLevelDesc != null )
				associatedExpressionForm.setExpressionLevel( expLevelDesc.getExpressionLevel() );
		
		associatedExpressionForm.setEngineeredGeneID( aAssociatedExpressionID );
		associatedExpressionForm.setOrganTissueCode( organ.getConceptCode() );
		associatedExpressionForm.setOrganTissueName( organ.getName() );
       
	    // setup dropdown menus
	    this.dropdown( request, response );
	    
	    request.getSession().setAttribute( Constants.FORMDATA, associatedExpressionForm );  	    
	    System.out.println( "<AssociatedExpressionPopulateAction populate> Exiting populate() " );
	    return mapping.findForward( "submitAssocExpression" );
	}
	
	public ActionForward dropdown( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<AssociatedExpressionPopulateAction dropdown> Entering dropdown()" );

	    // blank out the FORMDATA Constant field
	    AssociatedExpressionForm AssociatedExpressionForm = (AssociatedExpressionForm) form;
	    request.getSession().setAttribute( Constants.FORMDATA, AssociatedExpressionForm );       
	    
	    // setup dropdown menus
	    this.dropdown( request, response );
	    
	   return mapping.findForward( "submitAssocExpression" );
	} 
	
	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown( HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<AssociatedExpressionPopulateAction dropdown> Entering void dropdown()" );
	    
	    NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.EXPRESSIONLEVEL, Constants.Dropdowns.ADD_BLANK );
	    
	    System.out.println( "<AssociatedExpressionPopulateAction dropdown> Exiting void dropdown()" );
	}	
}
