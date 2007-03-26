/**
 * 
 * $Id: InducedMutationPopulateAction.java,v 1.17 2007-03-26 12:02:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2006/11/09 17:29:47  pandyas
 * Commented out debug code
 *
 * Revision 1.15  2006/05/04 19:27:37  pandyas
 * Changed GeneticAlterationCollection to GeneticAlteration relationship from SpontaneousMutation and InducedMutation objects
 *
 * Revision 1.14  2006/04/19 17:38:57  pandyas
 * Removed TODO text
 *
 * Revision 1.13  2006/04/18 16:21:31  pandyas
 * modified populate to pull name from uncontrolled vocab - IM is now saving the free text into uncontrolled vocab in the Impl class
 *
 * Revision 1.12  2006/04/17 19:09:41  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.service.impl.InducedMutationManagerSingleton;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InducedMutationPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("<InducedMutationPopulateAction populate> Entering populate() ");

        String aInducedMutationID = request.getParameter("aInducedMutationID");
        InducedMutation theInducedMutation = InducedMutationManagerSingleton.instance().get(aInducedMutationID);

        if (theInducedMutation == null) {
        	request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {
            request.setAttribute("aInducedMutationID", aInducedMutationID);

            InducedMutationForm inducedMutationForm = (InducedMutationForm) form;

            EnvironmentalFactor environFactor = theInducedMutation.getEnvironmentalFactor();

            inducedMutationForm.setType(environFactor.getType());
            if (environFactor.getTypeUnctrlVocab() != null) {
                inducedMutationForm.setOtherType(environFactor.getTypeUnctrlVocab());
                inducedMutationForm.setType(Constants.Dropdowns.OTHER_OPTION);
            }

            inducedMutationForm.setCasNumber(environFactor.getCasNumber());
            inducedMutationForm.setGeneId(theInducedMutation.getGeneId());
            
            // Name of Inducing Agent - Saved in uncontrolled vocab in 2.1 (free text)
            if (environFactor.getNameUnctrlVocab() != null) {
                inducedMutationForm.setName(environFactor.getNameUnctrlVocab());
            }  else {          
            inducedMutationForm.setName(environFactor.getName());
            }
            
            inducedMutationForm.setDescription(theInducedMutation.getDescription());
            
            MutationIdentifier identifier = theInducedMutation.getMutationIdentifier();
            if (identifier != null){
            	if (identifier.getMgiNumber() != null && identifier.getMgiNumber().length() > 0) {
            		inducedMutationForm.setMgiNumber(identifier.getMgiNumber());
            	}
            	if (identifier.getZfinNumber() != null && identifier.getZfinNumber().length() > 0) {
            		inducedMutationForm.setZfinNumber(identifier.getZfinNumber());
                }
            	if (identifier.getRgdNumber() != null && identifier.getRgdNumber().length() > 0) {
            		inducedMutationForm.setRgdNumber(identifier.getRgdNumber());
                }             	
            }
            // Set GeneticAlteration attributes 
            if (theInducedMutation.getGeneticAlteration() != null) {
                inducedMutationForm.setObservation(theInducedMutation.getGeneticAlteration().getObservation());
                inducedMutationForm.setMethodOfObservation(theInducedMutation.getGeneticAlteration()
                        .getMethodOfObservation());
            }
            
            if (theInducedMutation.getComments() != null) {
                inducedMutationForm.setComments(theInducedMutation.getComments());
            }
        }
        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitInducedMutation");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("<InducedMutationPopulateAction dropdown> Entering dropdown() ");

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
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("<InducedMutationPopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.INDUCEDMUTATIONDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

        log.debug("<InducedMutationPopulateAction dropdown> Exiting void dropdown()");
    }

}
