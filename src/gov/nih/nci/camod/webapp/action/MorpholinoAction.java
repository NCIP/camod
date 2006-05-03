/**
 * @author pandyas
 * 
 * $Id: MorpholinoAction.java,v 1.1 2006-05-03 20:04:55 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Morpholino;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.MorpholinoManager;
import gov.nih.nci.camod.webapp.form.MorpholinoForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MorpholinoAction extends BaseAction
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

        log.info("<MorpholinoAction> Entering 'edit' method");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        // Create a form to edit
        MorpholinoForm morpholinoForm = (MorpholinoForm) form;

        // Grab the current aMorpholinoID for this animalModel
        String aMorpholinoID = request.getParameter("aMorpholinoID");
        log.info("aMorpholinoID: " + aMorpholinoID);

        System.out.println("<MorpholinoAction edit> following Characteristics:" 
          + "\n\t Concentration: " + morpholinoForm.getConcentration() 
          + "\n\t ConcentrationUnit: " + morpholinoForm.getConcentrationUnit() 
          + "\n\t DeliveryMethod: " + morpholinoForm.getDeliveryMethod() 
          + "\n\t OtherDeliveryMethod: " + morpholinoForm.getOtherDeliveryMethod() 
          + "\n\t Source: " + morpholinoForm.getOtherSource() 
          + "\n\t OtherSource: " + morpholinoForm.getOtherSource() 
          + "\n\t VisualizationLigands: " + morpholinoForm.getVisualLigand()    
          + "\n\t OtherVisualizationLigands: " + morpholinoForm.getOtherVisualLigand()
          + "\n\t SequenceDirection: " + morpholinoForm.getSequenceDirection() 
          + "\n\t TargetedRegion: " + morpholinoForm.getTargetedRegion() 
          + "\n\t Type: " + morpholinoForm.getType()     
          + "\n\t user: " + (String) request.getSession().getAttribute(                                                                                                                       
                 "camod.loggedon.username"));
        
  
        MorpholinoManager morpholinoManager = (MorpholinoManager) getBean("morpholinoManager");

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try
        {
            // retrieve animal model by it's id         
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(modelID);            

            if ("Delete".equals(theAction)) {
                morpholinoManager.remove(aMorpholinoID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("morpholino.delete.successful"));
                saveErrors(request, msg);

            } else {
                Morpholino theMorpholino = morpholinoManager.get(aMorpholinoID);
                morpholinoManager.update(theAnimalModel, morpholinoForm, theMorpholino);

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

        log.info("<MorpholinoAction> Entering 'save' method");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        // Create a form to edit
        MorpholinoForm morpholinoForm = (MorpholinoForm) form;
        
        System.out.println("<MorpholinoAction save> following Characteristics:" 
	    + "\n\t Concentration: " + morpholinoForm.getConcentration() 
	    + "\n\t ConcentrationUnit: " + morpholinoForm.getConcentrationUnit() 
	    + "\n\t DeliveryMethod: " + morpholinoForm.getDeliveryMethod() 
        + "\n\t OtherDeliveryMethod: " + morpholinoForm.getOtherDeliveryMethod() 
        + "\n\t Source: " + morpholinoForm.getOtherSource() 
        + "\n\t OtherSource: " + morpholinoForm.getOtherSource() 
        + "\n\t VisualizationLigands: " + morpholinoForm.getVisualLigand()    
        + "\n\t OtherVisualizationLigands: " + morpholinoForm.getOtherVisualLigand()
        + "\n\t SequenceDirection: " + morpholinoForm.getSequenceDirection() 
        + "\n\t TargetedRegion: " + morpholinoForm.getTargetedRegion() 
        + "\n\t Type: " + morpholinoForm.getType()     
    	+ "\n\t user: " + (String) request.getSession().getAttribute(                                                                                                                       
              "camod.loggedon.username"));

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

        AnimalModel animalModel = animalModelManager.get(modelID);

        try
        {
            log.info("<TherapyAction> Entering try block");            
            animalModelManager.addMorpholino(animalModel, morpholinoForm);

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
