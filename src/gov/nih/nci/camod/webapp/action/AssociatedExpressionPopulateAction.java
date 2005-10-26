package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

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

        // Grab the current Engineered Transgene that is being edited from the session
        String aEngineeredGeneID = request.getParameter("aEngineeredTransgeneID");
        String aAssociatedExpressionID = request.getParameter("aAssociatedExpressionID");
        
        EngineeredTransgeneManager theEngineeredTransgeneManager = (EngineeredTransgeneManager) getBean( "engineeredTransgeneManager" );
        Transgene engGene = theEngineeredTransgeneManager.get( aEngineeredGeneID );
        List expFeatureList = engGene.getExpressionFeatureCollection();
        
        for( int i=0; i < expFeatureList.size(); i++ ) 
        {
        	ExpressionFeature expFeature = (ExpressionFeature) expFeatureList.get(i);
        	
        	if( expFeature.getId().toString().equals( aAssociatedExpressionID ) ) {
        		//System.out.println( "Found it: " + expFeature.getId() + " = " + aAssociatedExpressionID );
        		ExpressionLevelDesc expLevelDesc = expFeature.getExpressionLevelDesc();
        		Organ organ = expFeature.getOrgan();
        		
        		associatedExpressionForm.setName( organ.getName() );
        		associatedExpressionForm.setOrgan( organ.getName() );
        		associatedExpressionForm.setExpressionLevel( expLevelDesc.getExpressionLevel() );
        		associatedExpressionForm.setEngineeredGeneID( aAssociatedExpressionID );
        		associatedExpressionForm.setOrganTissueCode( organ.getConceptCode() );
        		associatedExpressionForm.setOrganTissueName( organ.getName() );
        	}        		
        }
        
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
	    
	    NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.EXPRESSIONLEVEL, "" );
	    
	    System.out.println( "<AssociatedExpressionPopulateAction dropdown> Exiting void dropdown()" );
	}	
}
