/**
 *  @author 
 *  
 *  $Id: AnimalModelTreePopulateActionSetup.java,v 1.1 2005-10-31 13:46:28 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.32  2005/10/27 17:17:34  schroedn
 *  Enter Assoc Expression to Engineered Transgene, Genomic Segment, Targeted Modification
 *
 *  Revision 1.31  2005/10/26 20:40:51  schroedn
 *  Added AssocExpression to EngineeredTransgene submission page
 *
 *  Revision 1.30  2005/10/26 20:14:42  pandyas
 *  implemented model availability
 *
 *  Revision 1.29  2005/10/24 21:04:47  schroedn
 *  Bug fixes, Javascript and Other fields
 *
 *  Revision 1.28  2005/10/24 13:28:17  georgeda
 *  Cleanup changes
 *
 *  Revision 1.27  2005/10/21 16:07:01  pandyas
 *  implementation of animal availability
 *
 *  Revision 1.26  2005/10/12 20:10:49  schroedn
 *  Added Validation
 *
 *  Revision 1.25  2005/10/11 20:52:55  schroedn
 *  EngineeredTransgene and GenomicSegment edit/save works, not image
 *
 *  TODO EngineeredTransgene - 'Other' Species not working
 *
 *  Revision 1.24  2005/10/10 14:11:45  georgeda
 *  Changes for comment curation
 *
 *  Revision 1.23  2005/10/06 20:40:12  schroedn
 *  InducedMutation, TargetedMutation, GenomicSegment changes
 *
 *  Revision 1.22  2005/10/06 19:27:55  pandyas
 *  modified for Therapy screen
 *
 *  Revision 1.21  2005/10/04 20:13:20  schroedn
 *  Added Spontaneous Mutation, InducedMutation, Histopathology, TargetedModification and GenomicSegment
 *
 *  Revision 1.20  2005/09/28 15:13:57  schroedn
 *  Merged changes, tested
 *
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Populate the lists necessary to display an animal model.
 * 
 */
public class AnimalModelTreePopulateActionSetup extends BaseAction {

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

		Object theErrors = request.getAttribute(org.apache.struts.Globals.ERROR_KEY);

		if (theErrors != null) {
			request.getSession().setAttribute(org.apache.struts.Globals.ERROR_KEY, theErrors);
		}
		return mapping.findForward("next");
	}
}
