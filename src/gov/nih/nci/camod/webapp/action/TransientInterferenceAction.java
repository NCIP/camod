/**
 * @author pandyas
 * 
 * $Id: TransientInterferenceAction.java,v 1.7 2007-09-12 19:36:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2007/04/04 13:19:27  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.5  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.4  2006/11/09 17:10:31  pandyas
 * modified sucess, edit, and delete message key to be used for both SiRNA and Morpholino.  Modified code to use only one manager so both messages must be generic.
 *
 * Revision 1.3  2006/10/19 16:30:22  pandyas
 * modified aConceptCode to conceptCode to match all other code
 *
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
        log.debug("<TransientInterferenceAction> Entering edit method");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        // Create a form to edit
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

        // Grab the current aTransIntID for this animalModel
        String aTransIntID = request.getParameter("aTransIntID");
        
        System.out.println("<TransientInterferenceAction edit> following Characteristics:" 
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
          + "\n\t targetSite: " + transientInterferenceForm.getTargetSite()
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
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transientinterference.delete.successful"));
                saveErrors(request, msg);

            } else {
            	TransientInterference theTransientInterference = transientInterferenceManager.get(aTransIntID);
                transientInterferenceManager.update(theAnimalModel, transientInterferenceForm, theTransientInterference);

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transientinterference.edit.successful"));
                saveErrors(request, msg);
            }

        }  catch (Exception e)
        {

            log.error("Unable to get a transient interference action: ", e);

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

        log.debug("<TransientInterferenceAction> Entering 'save' method");
        
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
        
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

		String conceptCode = request.getParameter("aConceptCode");

		transientInterferenceForm.setConceptCode(conceptCode);        
        log.debug("transientInterferenceForm.getConceptCode(): " + transientInterferenceForm.getConceptCode());
        
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
        + "\n\t targetSite: " + transientInterferenceForm.getTargetSite()
        + "\n\t Comment: " + transientInterferenceForm.getComments()         
    	+ "\n\t user: " + (String) request.getSession().getAttribute(                                                                                                                       
              "camod.loggedon.username"));

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

        AnimalModel animalModel = animalModelManager.get(modelID);

        try
        {
            log.debug("<TransientInterferenceAction> Entering try block");            
            animalModelManager.addTransientInterference(animalModel, transientInterferenceForm);
            
			log.debug("New Transient Interference (Morpholino) created");            

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("transientinterference.creation.successful"));
            saveErrors(request, msg);
        } catch (Exception e)
        {
            log.error("Exception occurred creating a transient interference", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.debug("Exiting save");
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
    

}
