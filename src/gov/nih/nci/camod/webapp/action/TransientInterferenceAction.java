/**
 * @author pandyas
 * 
 * $Id: TransientInterferenceAction.java,v 1.2 2006-10-18 18:10:26 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.1  2006/05/03 20:04:55  pandyas
 * Modified to add Morpholino object data to application
 *
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.TransientInterference;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TransientInterferenceManager;
import gov.nih.nci.camod.webapp.form.TransientInterferenceForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class TransientInterferenceAction extends BaseAction
{

    /**
     * Edit
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception
    {
        log.info("<TransientInterferenceAction> Entering edit method");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        // Create a form to edit
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

        // Grab the current aTransIntID for this animalModel
        String aTransIntID = request.getParameter("aTransIntID");
        
        System.out.println("<MorpholinoAction edit> following Characteristics:" 
          + "\n\t Concentration: " + transientInterferenceForm.getConcentration() 
          + "\n\t ConcentrationUnit: " + transientInterferenceForm.getConcentrationUnit() 
          + "\n\t DeliveryMethod: " + transientInterferenceForm.getDeliveryMethod() 
          + "\n\t OtherDeliveryMethod: " + transientInterferenceForm.getOtherDeliveryMethod() 
          + "\n\t Source: " + transientInterferenceForm.getSource() 
          + "\n\t OtherSource: " + transientInterferenceForm.getOtherSource() 
          + "\n\t VisualizationLigands: " + transientInterferenceForm.getVisualLigand()    
          + "\n\t OtherVisualizationLigands: " + transientInterferenceForm.getOtherVisualLigand()
          + "\n\t SequenceDirection: " + transientInterferenceForm.getSequenceDirection() 
          + "\n\t TargetedRegion: " + transientInterferenceForm.getTargetedRegion() 
          + "\n\t Type: " + transientInterferenceForm.getType()
        + "\n\t Comment: " + transientInterferenceForm.getComments()           
          + "\n\t user: " + (String) request.getSession().getAttribute(                                                                                                                       
                 "camod.loggedon.username"));
        
  
        TransientInterferenceManager transientInterferenceManager = (TransientInterferenceManager) getBean("transientInterferenceManager");

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try
        {
            // retrieve animal model by it's id         
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(modelID); 

            if ("Delete".equals(theAction)) {
            	transientInterferenceManager.remove(aTransIntID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("morpholino.delete.successful"));
                saveErrors(request, msg);

            } else {
            	TransientInterference theTransientInterference = transientInterferenceManager.get(aTransIntID);
                transientInterferenceManager.update(theAnimalModel, transientInterferenceForm, theTransientInterference);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("morpholino.edit.successful"));
                saveErrors(request, msg);
            }

        }  catch (Exception e)
        {

            log.error("Unable to get a Morpholino action: ", e);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }

        return mapping.findForward("AnimalModelTreePopulateAction");
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
    public ActionForward save(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception
    {

        log.info("<TransientInterferenceAction> Entering 'save' method");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
        
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

		String aConceptCode = request.getParameter("aConceptCode");

		transientInterferenceForm.setAConceptCode(aConceptCode);        
        log.info("transientInterferenceForm.getConceptCode(): " + transientInterferenceForm.getAConceptCode());
        
        System.out.println("<TransientInterferenceAction save> following Characteristics:" 
	    + "\n\t Concentration: " + transientInterferenceForm.getConcentration() 
	    + "\n\t ConcentrationUnit: " + transientInterferenceForm.getConcentrationUnit() 
	    + "\n\t DeliveryMethod: " + transientInterferenceForm.getDeliveryMethod() 
        + "\n\t OtherDeliveryMethod: " + transientInterferenceForm.getOtherDeliveryMethod() 
        + "\n\t Source: " + transientInterferenceForm.getSource() 
        + "\n\t OtherSource: " + transientInterferenceForm.getOtherSource() 
        + "\n\t VisualizationLigands: " + transientInterferenceForm.getVisualLigand()    
        + "\n\t OtherVisualizationLigands: " + transientInterferenceForm.getOtherVisualLigand()
        + "\n\t SequenceDirection: " + transientInterferenceForm.getSequenceDirection() 
        + "\n\t TargetedRegion: " + transientInterferenceForm.getTargetedRegion() 
        + "\n\t Type: " + transientInterferenceForm.getType() 
        + "\n\t Comment: " + transientInterferenceForm.getComments()         
    	+ "\n\t user: " + (String) request.getSession().getAttribute(                                                                                                                       
              "camod.loggedon.username"));

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

        AnimalModel animalModel = animalModelManager.get(modelID);

        try
        {
            log.info("<TransientInterferenceAction> Entering try block");            
            animalModelManager.addTransientInterference(animalModel, transientInterferenceForm);
            
			log.info("New Transient Interference (Morpholino) created");            

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("morpholino.creation.successful"));
            saveErrors(request, msg);
        } catch (Exception e)
        {
            log.error("Exception occurred creating a Morpholino", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.info("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
    

}
