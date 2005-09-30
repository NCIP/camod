package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.webapp.form.CellLineForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * CellLineAction Class
 */
public final class CellLineAction extends BaseAction {
	
    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
    }

	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	
    	 return mapping.findForward("");
    }    
    
    
    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {

    	log.trace( "Entering edit" );    	
        
        CellLineForm cellLineForm = ( CellLineForm ) form;

		// Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute( Constants.MODELID );

		// Grab the current aCellID from the session
        String aCellID = request.getParameter( "aCellID" );        
    	
		System.out.println( "<CellLineAction save> following Characteristics:" + 
								"\n\t name: " + cellLineForm.getCellLineName() + 
								"\n\t otherName: " + cellLineForm.getOrganName() + 
								"\n\t dosage: " + cellLineForm.getExperiment() + 
								"\n\t administrativeRoute: " + cellLineForm.getResults() +
								"\n\t regimen: " + cellLineForm.getComments() +
								"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		String theForward = "AnimalModelTreePopulateAction";
		
	    try {		
        
	    	// Use the current animalModel based on the ID stored in the session
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            CellLineManager theCellLineManager = (CellLineManager) getBean("cellLineManager");
            CellLine theCellLine = theCellLineManager.get(aCellID);
            
            theCellLineManager.update(cellLineForm, theCellLine, theAnimalModel);

            ActionMessages msg = new ActionMessages();
            msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "cellline.edit.successful" ) );
            saveErrors( request, msg );
        
	    } catch( Exception e ) {
            log.error("Exception ocurred creating CellLine", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);

            theForward = "failure";
	    }
	    
        log.trace("Exiting edit");
        return mapping.findForward(theForward);
    }

    /**
     * Save
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
    	
    	log.trace( "Entering save" );
        
        CellLineForm cellLineForm = ( CellLineForm ) form;
        
		// Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);        

		System.out.println( "<CellLineAction save> following Characteristics:" + 
								"\n\t name: " + cellLineForm.getCellLineName() + 
								"\n\t otherName: " + cellLineForm.getOrganName() + 
								"\n\t dosage: " + cellLineForm.getExperiment() + 
								"\n\t administrativeRoute: " + cellLineForm.getResults() +
								"\n\t regimen: " + cellLineForm.getComments() +
								"\n\t ConceptCode: "      + cellLineForm.getOrgan() +
								"\n\t organTissueCode: "  + cellLineForm.getOrganTissueCode() +
								"\n\t organTissueName: "  + cellLineForm.getOrganTissueName() +								
								"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
	    try {		
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.addCellLine(theAnimalModel, cellLineForm);

            log.info("New Cell Line created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cellline.creation.successful"));
            saveErrors(request, msg);	
        
        } catch (Exception e) {
            log.error("Exception ocurred creating Cell Line", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");    
    }
}