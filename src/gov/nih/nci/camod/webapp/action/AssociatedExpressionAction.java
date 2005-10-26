package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AssociatedExpressionAction extends BaseAction {

    /**
     * Save
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("Entering edit");

        // Create a form to edit
        AssociatedExpressionForm associatedExpressionForm = (AssociatedExpressionForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, associatedExpressionForm);
             
        // Grab the current modelID from the session
        //String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        
        // Grab the current Engineered Transgene that is being edited from the session
        String aEngineeredGeneID = request.getParameter("engineeredGeneID");
        
        // Grab the current modelID from the session
        String aAssociatedExpressionID = request.getParameter("aAssociatedExpressionID");
        
        associatedExpressionForm.setEngineeredGeneID( aAssociatedExpressionID );
        
        log.info("<AssocExpression save> following Characteristics:" 
        		+ "\n\t getExpressionLevel: " + associatedExpressionForm.getExpressionLevel()
                + "\n\t getName: " + associatedExpressionForm.getName()        
                + "\n\t getEngineeredGeneID: " + aEngineeredGeneID
                + "\n\t getOrganTissueCode: " + associatedExpressionForm.getOrganTissueCode()
                + "\n\t getOrganTissueName: " + associatedExpressionForm.getOrganTissueName()
                + "\n\t getOrgan: " + associatedExpressionForm.getOrgan()
                + "\n\t username:" + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";
        
        try {
        	
            EngineeredTransgeneManager theEngineeredTransgeneManager = (EngineeredTransgeneManager) getBean( "engineeredTransgeneManager" );
            Transgene engGene = theEngineeredTransgeneManager.get( aEngineeredGeneID );
            
            theEngineeredTransgeneManager.updateAssociatedExpression( associatedExpressionForm, engGene );
            
            log.info("New AssociatedExpression created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocexpression.edit.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating AssociatedExpression", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
        return mapping.findForward(theForward);
    }
    
    /**
     * Save
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("Entering save");

        // Create a form to edit
        AssociatedExpressionForm associatedExpressionForm = (AssociatedExpressionForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, associatedExpressionForm);
             
        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        
        // Grab the current Engineered Transgene that is being edited from the session
        String aEngineeredGeneID = request.getParameter("engineeredGeneID");
        
        // Grab the current modelID from the session
        //String aAssociatedExpressionID = request.getParameter("aAssociatedExpressionID");
        
        log.info("<AssocExpression save> following Characteristics:" 
        		+ "\n\t getExpressionLevel: " + associatedExpressionForm.getExpressionLevel()
                + "\n\t getName: " + associatedExpressionForm.getName()        
                + "\n\t getEngineeredGeneID: " + aEngineeredGeneID
                + "\n\t getOrganTissueCode: " + associatedExpressionForm.getOrganTissueCode()
                + "\n\t getOrganTissueName: " + associatedExpressionForm.getOrganTissueName()
                + "\n\t getOrgan: " + associatedExpressionForm.getOrgan()
                + "\n\t username:" + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";
        
        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get( theModelId );
            
            EngineeredTransgeneManager theEngineeredTransgeneManager = (EngineeredTransgeneManager) getBean( "engineeredTransgeneManager" );
            Transgene engGene = theEngineeredTransgeneManager.get( aEngineeredGeneID );
           
            theAnimalModelManager.addAssociatedExpression( theAnimalModel, engGene, associatedExpressionForm  );

            log.info("New AssociatedExpression created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocexpression.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception ocurred creating AssociatedExpression", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward(theForward);
    }
}
