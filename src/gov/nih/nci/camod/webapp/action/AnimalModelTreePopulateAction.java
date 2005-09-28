/**
 *  @author 
 *  
 *  $Id: AnimalModelTreePopulateAction.java,v 1.20 2005-09-28 15:13:57 schroedn Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.18  2005/09/22 15:17:20  georgeda
 *  More changes
 *
 *  Revision 1.17  2005/09/20 19:32:40  pandyas
 *  Added Cell Line functionality
 *
 *  Revision 1.16  2005/09/20 19:10:57  schroedn
 *  Added check for Agent != null, GeneDelivery has no Agent, uses GeneDelivery
 *
 *  Revision 1.15  2005/09/20 18:45:03  schroedn
 *  Merged many changes, added GeneDelivery
 *
 *  Revision 1.14  2005/09/19 18:15:28  georgeda
 *  Organized imports and changed boolean to Boolean
 *
 *  Revision 1.13  2005/09/16 15:52:56  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Populate the lists necessary to display an animal model.
 * 
 */
public class AnimalModelTreePopulateAction extends BaseAction {

    /**
     * Create the links for the submission subMenu
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<AnimalModelTreePopulateAction populate> Entering... ");

        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

    try {
        AnimalModel animalModel = animalModelManager.get(modelID);

        // Retrieve a list of all publications assoicated with this Animal model
        List publicationList = animalModel.getPublicationCollection();
        List pubList = new ArrayList();

        for (int i = 0; i < publicationList.size(); i++) {
            Publication pub = (Publication) publicationList.get(i);

            System.out.println("Adding Publication: " + pub.getTitle());

            pubList.add(pub);
        }
        
        // Print the list of GeneDelivery viralVestors for the Gene Delivery
        // (Cardiogenic Intervention) Section
        List geneDeliveryList = animalModel.getGeneDeliveryCollection();
        List geneList = new ArrayList();
        
        for (int i = 0; i < geneDeliveryList.size(); i++) {
        	GeneDelivery geneDelivery = (GeneDelivery) geneDeliveryList.get(i);
                        
            System.out.println("\tAdded GeneDelivery= " + geneDelivery.getViralVector() );
            
            geneList.add( geneDelivery );
        }
        
        // Retrieve a list of all cell lines assoicated with this Animal model
        List cellLineList = animalModel.getCellLineCollection();
        List cellList = new ArrayList(); 
     
        for (int i = 0; i < cellLineList.size(); i++) {
            CellLine cellLine = (CellLine) cellLineList.get(i);
            
            System.out.println("\tAdded CellLine= " + cellLine);
            
            cellList.add(cellLine);
    	} 
        
        //Retrive the list of all Xenograft transplants assoicated with this Animal Model
        List xenoList = new ArrayList();
        List xenograftList = animalModel.getXenograftCollection();
        
        for (int i = 0; i < xenograftList.size(); i++) {
            Xenograft xenograft = (Xenograft) xenograftList.get(i);
            
            System.out.println("\tAdded Xenograft= " + xenograft);
            
            xenoList.add(xenograft);
    	} 
        
        // Print the list of EnvironmentalFactors for the Cardiogenic
        // Interventions Section
        List tyList = animalModel.getTherapyCollection();

        List surgeryList = new ArrayList();
        List hormoneList = new ArrayList();
        List growthFactorList = new ArrayList();
        List viraltreatmentList = new ArrayList();
        List chemicaldrugList = new ArrayList();
        List environFactorList = new ArrayList();
        List radiationList = new ArrayList();
        List nutritionalFactorList = new ArrayList();
        
        System.out.println("<AnimalModelTreePopulateAction> Building Tree ...");

        if (tyList == null || tyList.size() == 0) {
            System.out.println("<AnimalModelTreePopulateAction populate> nothing!");
        } else {
            for (int i = 0; i < tyList.size(); i++) {
                Therapy ty = (Therapy) tyList.get(i);

                // check to see if it is an EnvironmentalFactor
                if (ty.getTherapeuticExperiment().booleanValue() == false) {
                    Agent agent = ty.getAgent();
                    if ( agent != null ) {
	                    if (agent.getType().equals("Other")) {
	                        System.out.println("\tAdded therapy to surgeryList");
	                        surgeryList.add(ty);
	                    }
	                    if (agent.getType().equals("Hormone")) {
	                        System.out.println(" therapy to hormoneList");
	                        hormoneList.add(ty);
	                    }
	                    if (agent.getType().equals("Growth Factor")) {
	                        System.out.println("\tAdded therapy to growthFactorList");
	                        growthFactorList.add(ty);
	                    }
	                    if (agent.getType().equals("Viral")) {
	                        System.out.println("\tAdded therapy to viraltreatmentList");
	                        viraltreatmentList.add(ty);
	                    }
	                    if (agent.getType().equals("Chemical / Drug")) {
	                        System.out.println("\tAdded therapy to chemicaldrugList");
	                        chemicaldrugList.add(ty);
	                    }
	                    if (agent.getType().equals("Environment")) {
	                        System.out.println("\tAdded therapy to environFactorList");
	                        environFactorList.add(ty);
	                    }
	
	                    if (agent.getType().equals("Nutrition")) {
	                        System.out.println("\tAdded therapy to nutritionalFactorList");
	                        nutritionalFactorList.add(ty);
	                    }
	
	                    if (agent.getType().equals("Radiation")) {
	                        System.out.println("\tAdded therapy to radiationList");
	                        radiationList.add(ty);
	                    }
                    }
                }
            }
        }

        request.getSession().setAttribute(Constants.Submit.GROWTHFACTORS_LIST, growthFactorList);
        request.getSession().setAttribute(Constants.Submit.HORMONE_LIST, hormoneList);
        request.getSession().setAttribute(Constants.Submit.SURGERYOTHER_LIST, surgeryList);
        request.getSession().setAttribute(Constants.Submit.VIRALTREATMENT_LIST, viraltreatmentList);
        request.getSession().setAttribute(Constants.Submit.CHEMICALDRUG_LIST, chemicaldrugList);
        request.getSession().setAttribute(Constants.Submit.ENVIRONMENTALFACTOR_LIST, environFactorList);
        request.getSession().setAttribute(Constants.Submit.RADIATION_LIST, radiationList);
        request.getSession().setAttribute(Constants.Submit.NUTRITIONALFACTORS_LIST, nutritionalFactorList);
        request.getSession().setAttribute(Constants.Submit.PUBLICATION_LIST, pubList);
        request.getSession().setAttribute(Constants.Submit.GENEDELIVERY_LIST, geneList);
        request.getSession().setAttribute(Constants.Submit.XENOGRAFT_LIST, xenoList);
       
        request.getSession().setAttribute(Constants.Submit.CELLLINE_LIST, cellList);        
        
       // UserManager theUserManager = (UserManager) getBean("userManager");

           // Set up the form. Should be only one coordinator
            // Get the coordinator
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            String theCoordinator = theBundle.getString(Constants.COORDINATOR_USERNAME_KEY);

            AnimalModelStateForm theForm = new AnimalModelStateForm();

            // Set the fields
            theForm.setModelId(modelID);
            theForm.setModelDescriptor(animalModel.getModelDescriptor());
            theForm.setComment("Model has been moved to complete");
            theForm.setAssignedTo(theCoordinator);
            theForm.setEvent(Constants.Admin.Actions.COMPLETE);

            request.setAttribute(Constants.FORMDATA, theForm);
            
        } catch (Exception e) {
            log.error("Caught an exception populating the data: ", e);

            // Encountered an error populating the data
            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }
        return mapping.findForward("submitOverview");
    }
}
