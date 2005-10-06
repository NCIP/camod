package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.service.impl.InducedMutationManagerSingleton;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InducedMutationPopulateAction extends BaseAction {	

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
	
	    System.out.println("<InducedMutationPopulateAction populate> Entering populate() ");
        
	    String aInducedMutationID = request.getParameter("aInducedMutationID");
	    InducedMutation theInducedMutation = InducedMutationManagerSingleton.instance().get(aInducedMutationID);
        
	    InducedMutationForm inducedMutationForm = (InducedMutationForm) form;

	    EnvironmentalFactor environFactor = theInducedMutation.getEnvironmentalFactor();
	    
	    inducedMutationForm.setType( environFactor.getType() );
	    if ( environFactor.getTypeUnctrlVocab() != null )
	    	inducedMutationForm.setOtherType( environFactor.getTypeUnctrlVocab() );
	    
	    inducedMutationForm.setCASNumber( environFactor.getCasNumber() );
	    inducedMutationForm.setGeneId( theInducedMutation.getGeneId() );
	    inducedMutationForm.setName( environFactor.getName() );
	    inducedMutationForm.setDescription( theInducedMutation.getDescription() );
	    
	    MutationIdentifier identifier = theInducedMutation.getMutationIdentifier();
	    inducedMutationForm.setNumberMGI( identifier.getNumberMGI().toString() );
	    
	     
	    List geneticList = theInducedMutation.getGeneticAlterationCollection();
	    GeneticAlteration geneticAlt = (GeneticAlteration) geneticList.get(0);
	    
	    inducedMutationForm.setObservation( geneticAlt.getObservation() );
	    inducedMutationForm.setMethodOfObservation( geneticAlt.getMethodOfObservation() );
        
	    // setup dropdown menus
        this.dropdown(request, response);
        
        request.getSession().setAttribute(Constants.FORMDATA, inducedMutationForm);
        
	    return mapping.findForward("submitInducedMutation");
	}
	
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
	
	    System.out.println("<InducedMutationPopulateAction dropdown> Entering dropdown() ");
	
	    // blank out the FORMDATA Constant field
	    InducedMutationForm inducedMutationForm = (InducedMutationForm) form;
	    request.getSession().setAttribute(Constants.FORMDATA, inducedMutationForm);       
        
	    // setup dropdown menus
        this.dropdown(request, response);
        
	   return mapping.findForward("submitInducedMutation");
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
	
	    System.out.println("<InducedMutationPopulateAction dropdown> Entering void dropdown()");
	    
	    NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.INDUCEDMUTATIONDROP, "");
	    
	    System.out.println("<InducedMutationPopulateAction dropdown> Exiting void dropdown()");
	}	

}
