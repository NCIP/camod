/**
 * @author pandyas
 * 
 * $Id: CellLinePopulateAction.java,v 1.19 2008-08-14 16:41:59 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.18  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.17  2007/08/14 17:05:47  pandyas
 * Bug #8414:  getEVSPreferredDiscription needs to be implemented for Zebrafish vocabulary source
 *
 * Revision 1.16  2007/08/14 12:04:12  pandyas
 * Implementing EVSPreferredName for Zebrafish models
 *
 * Revision 1.15  2007/06/18 16:13:20  pandyas
 * EVS preferred name does not work for Zebrafish tree so changed
 * Will add this item to EVS gforge to fix, if possilbe
 *
 * Revision 1.14  2007/06/13 20:20:24  pandyas
 * Copy built to dev for EVSTree project
 *
 * Revision 1.13  2006/11/09 17:23:54  pandyas
 * Commented out debug code
 *
 * Revision 1.12  2006/04/27 18:32:34  pandyas
 * Fixed incorrect header
 *
 * Revision 1.11  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.10  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.9  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.8  2005/10/27 12:53:24  georgeda
 * Added validation
 *
 * Revision 1.7  2005/10/20 20:26:21  pandyas
 * EVSTree (organ) functions properly
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.webapp.form.CellLineForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CellLinePopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Create a form to edit
		CellLineForm cellLineForm = (CellLineForm) form;

		// Grab the current Cell Line we are working with related to this
		// animalModel
		String aCellID = request.getParameter("aCellID");

		CellLineManager theCellLineManager = (CellLineManager) getBean("cellLineManager");
		CellLine cellLine = theCellLineManager.get(aCellID);

		if (cellLine == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {
			request.setAttribute("aCellID", aCellID);

			cellLineForm.setCellLineName(cellLine.getName());
			cellLineForm.setExperiment(cellLine.getExperiment());
			cellLineForm.setResults(cellLine.getResults());
			cellLineForm.setComments(cellLine.getComments());

			/* set Organ attributes */
            // since we are always querying from concept code (save and edit),
            // simply display EVSPreferredDescription, unless concept code is '00000'
            if (cellLine.getOrgan().getConceptCode().equals(Constants.Dropdowns.CONCEPTCODEZEROS)) {
                cellLineForm.setOrgan(cellLine.getOrgan().getName());
                log.debug("cellLine.getOrgan().getName(): " + cellLine.getOrgan().getName());
    
                cellLineForm.setOrganTissueCode(cellLine.getOrgan().getConceptCode());
                log.debug("OrganTissueCode: " + cellLine.getOrgan().getConceptCode());             
                
            } else {
                cellLineForm.setOrgan(cellLine.getOrgan().getEVSPreferredDescription());
                log.debug("cellLine.getOrgan().getEVSPreferredDescription(): " + cellLine.getOrgan().getEVSPreferredDescription());
    
                cellLineForm.setOrganTissueCode(cellLine.getOrgan().getConceptCode());
                log.debug("OrganTissueCode: " + cellLine.getOrgan().getConceptCode());
            }

		}

		//System.out.println("<CellLinePopulateAction populate> Exited");

		return mapping.findForward("submitCellLines");
	}

	/**
	 * Populate the dropdown menus for submitCellLines
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("submitCellLines");
	}

}
