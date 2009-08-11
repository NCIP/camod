/**
 * 
 * $Id: PublicationAction.java,v 1.17 2008-08-14 19:00:13 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2008/08/14 06:18:41  schroedn
 * Allow for RAT submission and search of RGD numbers
 *
 * Revision 1.15  2007/10/31 17:12:35  pandyas
 * Modified comments for #8355 	Add comments field to every submission page
 *
 * Revision 1.14  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.13  2005/11/14 14:21:06  georgeda
 * Handle delete of child publications
 *
 * Revision 1.12  2005/11/09 00:17:25  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.11  2005/11/01 20:48:41  schroedn
 * Fixed bugs concerning "Fill in Fields"
 *
 * Revision 1.10  2005/11/01 18:14:28  schroedn
 * Implementing 'Enter Publication' for CellLines and Therapy, fixed many bugs with Publication. Remaining known bug with "Fill in Fields" button
 *
 * Revision 1.9  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/27 12:53:00  georgeda
 * Refactor of publication manager
 *
 * Revision 1.6  2005/10/26 20:12:43  pandyas
 * clean up, java docs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.PublicationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * PublicationAction Class
 */
public final class PublicationAction extends BaseAction {

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addCellLinePublication(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entering addCellLinePublication method");

		PublicationForm pubForm = (PublicationForm) form;

		String aCellID = request.getParameter("aCellID");

		pubForm.setACellID(aCellID);

		log.debug("<PublicationAction save> following Characteristics:" + "\n\t name: " + pubForm.getName()
				+ "\n\t Aurthur: " + pubForm.getAuthors() + "\n\t Year: " + pubForm.getYear() + "\n\t Volume: "
				+ pubForm.getVolume() + "\n\t PMID: " + pubForm.getPmid()
                + "\n\t J Number: " + pubForm.getJaxJNumber()+ "\n\t Start Page: "
				+ pubForm.getStartPage() + "\n\t End Page: " + pubForm.getEndPage() + "\n\t Title: "
				+ pubForm.getTitle() + "\n\t journal: " + pubForm.getJournal() + "\n\t ACellID: "
				+ pubForm.getACellID() + "\n\t ATherapyID: " + pubForm.getATherapyID() + "\n\t APubID: "
				+ pubForm.getAPubID() + "\n\t FirstTimeReported: " + pubForm.getFirstTimeReported() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if ("Fill in Fields".equals(theAction)) {
				return mapping.findForward("PubMedPopulateAction");
			} else {

				CellLineManager theCellLineManager = (CellLineManager) getBean("cellLineManager");
				CellLine theCellLine = theCellLineManager.get(aCellID);

				PublicationManager publicationManager = (PublicationManager) getBean("publicationManager");
				publicationManager.addCellLinePublication(pubForm, theCellLine);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.creation.successful"));
				saveErrors(request, msg);
			}

		} catch (Exception e) {

			log.error("Unable to add a publication: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTherapyPublication(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entering addCellLinePublication method");

		PublicationForm pubForm = (PublicationForm) form;

		String aTherapyID = request.getParameter("aTherapyID");

		pubForm.setATherapyID(aTherapyID);

		log.debug("<PublicationAction save> following Characteristics:" + "\n\t name: " + pubForm.getName()
				+ "\n\t Aurthur: " + pubForm.getAuthors() + "\n\t Year: " + pubForm.getYear() + "\n\t Volume: "
				+ pubForm.getVolume() + "\n\t PMID: " + pubForm.getPmid() + "\n\t Start Page: "
				+ pubForm.getStartPage() + "\n\t End Page: " + pubForm.getEndPage() 
                + "\n\t J Number: " + pubForm.getJaxJNumber() + "\n\t Title: "
				+ pubForm.getTitle() + "\n\t journal: " + pubForm.getJournal() + "\n\t ACellID: "
				+ pubForm.getACellID() + "\n\t ATherapyID: " + pubForm.getATherapyID() + "\n\t APubID: "
				+ pubForm.getAPubID() + "\n\t FirstTimeReported: " + pubForm.getFirstTimeReported() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			if ("Fill in Fields".equals(theAction)) {
				return mapping.findForward("PubMedPopulateAction");
			} else {

				TherapyManager theTherapyManager = (TherapyManager) getBean("therapyManager");
				Therapy theTherapy = theTherapyManager.get(aTherapyID);

				PublicationManager publicationManager = (PublicationManager) getBean("publicationManager");
				publicationManager.addTherapyPublication(pubForm, theTherapy);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.creation.successful"));
				saveErrors(request, msg);
			}

		} catch (Exception e) {

			log.error("Unable to add a publication: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

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
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entering PublicationAction.edit");

		// Create a form to edit
		PublicationForm pubForm = (PublicationForm) form;
		String aPubID = pubForm.getAPubID();

		log.debug("<PublicationAction save> following Characteristics:" + "\n\t name: " + pubForm.getName()
				+ "\n\t Aurthur: " + pubForm.getAuthors() + "\n\t Year: " + pubForm.getYear() + "\n\t Volume: "
				+ pubForm.getVolume() + "\n\t PMID: " + pubForm.getPmid() + "\n\t Start Page: "
				+ pubForm.getStartPage() + "\n\t End Page: " + pubForm.getEndPage() + "\n\t Title: "
				+ pubForm.getTitle() + "\n\t journal: " + pubForm.getJournal() + "\n\t FirstTimeReported: "
				+ pubForm.getFirstTimeReported() + "\n\t ACellID: " + pubForm.getACellID() 
				+ "\n\t RGD Pub ID: " + pubForm.getRgdPubId() 				
				+ "\n\t Comments: " + pubForm.getComments() + "\n\t ATherapyID: "
				+ pubForm.getATherapyID() + "\n\t APubID: " + pubForm.getAPubID() 
				+ "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

			PublicationManager publicationManager = (PublicationManager) getBean("publicationManager");

			if ("Delete".equals(theAction)) {

				// It's associated w/ a therapy
				if (pubForm.getATherapyID() != null && pubForm.getATherapyID().length() > 0) {
					TherapyManager theTherapyManager = (TherapyManager) getBean("therapyManager");
					Therapy theTherapy = theTherapyManager.get(pubForm.getATherapyID());

					publicationManager.removeTherapyPublication(pubForm.getAPubID(), theTherapy);
				}

				// It's associated w/ a cell line
				else if (pubForm.getACellID() != null && pubForm.getACellID().length() > 0) {
					CellLineManager theCellLineManager = (CellLineManager) getBean("cellLineManager");
					CellLine theCellLine = theCellLineManager.get(pubForm.getACellID());

					publicationManager.removeCellLinePublication(pubForm.getAPubID(), theCellLine);
				}

				// It's associated w/ an animal model
				else {
					String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
					AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
					AnimalModel animalModel = animalModelManager.get(modelID);

					publicationManager.remove(pubForm.getAPubID(), animalModel);
				}

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.delete.successful"));
				saveErrors(request, msg);

			} else if ("Fill in Fields".equals(theAction)) {
				return mapping.findForward("PubMedPopulateAction");
			} else {

				Publication thePublication = publicationManager.get(aPubID);
				publicationManager.update(pubForm, thePublication);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.edit.successful"));
				saveErrors(request, msg);
			}

		} catch (Exception e) {

			log.error("Unable to update a publication: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}

	/**
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

		log.debug("Entering 'save' method");

		PublicationForm pubForm = (PublicationForm) form;

		log.debug("<PublicationAction save> following Characteristics:" + "\n\t name: " + pubForm.getName()
				+ "\n\t Aurthur: " + pubForm.getAuthors() + "\n\t Year: " + pubForm.getYear() + "\n\t Volume: "
				+ pubForm.getVolume() + "\n\t PMID: " + pubForm.getPmid() + "\n\t Start Page: "
				+ pubForm.getStartPage() + "\n\t End Page: " + pubForm.getEndPage() + "\n\t Title: "
				+ pubForm.getTitle() + "\n\t journal: " + pubForm.getJournal()
				+ "\n\t RGD Pub ID: " + pubForm.getRgdPubId() 
				+ "\n\t Comments: " + pubForm.getComments() + "\n\t FirstTimeReported: "
				+ pubForm.getFirstTimeReported() 
				 + "\n\t Comments: " + pubForm.getComments() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		/* Grab the current modelID from the session */
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
			if ("Fill in Fields".equals(theAction)) {
				return mapping.findForward("PubMedPopulateAction");
			} else {

				AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
				AnimalModel animalModel = animalModelManager.get(modelID);
				animalModelManager.addPublication(animalModel, pubForm);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.creation.successful"));
				saveErrors(request, msg);
			}

		} catch (Exception e) {

			log.error("Unable to add a publication: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}
}