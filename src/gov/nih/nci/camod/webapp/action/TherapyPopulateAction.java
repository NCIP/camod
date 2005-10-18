package gov.nih.nci.camod.webapp.action;

import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.TherapyForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TherapyPopulateAction extends BaseAction {
	
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
		System.out.println( "<TherapyPopulateAction populate> Entering populate() " );

		// Create a form to edit
		TherapyForm therapyForm = ( TherapyForm ) form;
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );		
		
        String modelID = "" + request.getSession().getAttribute(Constants.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response);	
		
        // retrieve the list of all therapies from the current animalModel
        List therapyList = am.getTherapyCollection();

        Therapy ty = new Therapy();

        // find the specific one we need
        for (int i = 0; i < therapyList.size(); i++) {
            ty = (Therapy) therapyList.get(i);
            if (ty.getId().toString().equals(aTherapyID))
                break;
        }
        
        therapyForm.setType(ty.getTreatment().getSexDistribution().getType());
        if (ty.getTreatment().getAgeAtTreatment() != null) {
        	therapyForm.setAgeAtTreatment(ty.getTreatment().getAgeAtTreatment());
        } 
        
        therapyForm.setDosage(ty.getTreatment().getDosage());
        therapyForm.setName(ty.getAgent().getName());
        therapyForm.setAdministrativeRoute(ty.getTreatment().getAdministrativeRoute());        
        
        if (ty.getAgent().getCasNumber() != null) {        
        therapyForm.setCASNumber(ty.getAgent().getCasNumber());
        }        
        
        if (ty.getAgent().getNscNumber() != null) {
        	therapyForm.setNSCNumber(ty.getAgent().getNscNumber().toString());
        }        
		
		//Therapy object attributes
		therapyForm.setToxicityGrade(ty.getToxicityGrade());		
		therapyForm.setBiomarker(ty.getBiomarker());
		therapyForm.setTumorResponse(ty.getTumorResponse());
		therapyForm.setExperiment(ty.getExperiment());
		therapyForm.setResults(ty.getResults());		
		therapyForm.setComments(ty.getComments());
		
		/*TODO get collection of targetName(s), chemicalClassName(s), and processName(s)
		//therapyForm.setTargetName(therapy.getAgent().getAgentTargetCollection());
		List targetNames = therapy.getAgent().getAgentTargetCollection();
		List targets = new ArrayList();
		for (int i=0; i<targetNames.size(); i++){
			therapyForm.setTargetName(i);
		}
		*/
		
		//Store the Form in session to be used by the JSP
		request.getSession().setAttribute( Constants.FORMDATA, therapyForm );
		
		System.out.println( "<TherapyPopulateAction save> Requested form");

		return mapping.findForward("submitTherapy");
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

		System.out.println( "<TherapyPopulateAction dropdown> Entering ActionForward dropdown()" );
		//blank out the FORMDATA Constant field
		TherapyForm therapyForm = ( TherapyForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, therapyForm );
		
		//setup dropdown menus
		this.dropdown( request, response );
		
		System.out.println( "<TherapyPopulateAction dropdown> Exiting ActionForward dropdown()" );
		
		return mapping.findForward( "submitTherapy" );
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
		
			System.out.println( "<TherapyPopulateAction dropdown> Entering void dropdown()" );
		
			//Prepopulate all dropdown fields, set the global Constants to the following
			NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMICALCLASSESDROP, "" );
			NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.BIOLOGICALPROCESSDROP, "" );
			NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.THERAPEUTICTARGETSDROP, "" );
			
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMTHERAPYDOSEUNITSDROP, "" );
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "" );
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, "" );
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.TOXICITYGRADESDROP, "");
            
            System.out.println( "<TherapyPopulateAction dropdown> Exiting void dropdown()" );

	}
}
