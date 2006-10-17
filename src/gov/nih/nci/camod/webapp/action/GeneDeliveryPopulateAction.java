/**
 * 
 * $Id: GeneDeliveryPopulateAction.java,v 1.17 2006-10-17 16:11:00 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.15  2005/11/29 16:31:52  pandyas
 * Defect #223: Fixed code so Organ object is not created when no Organ is selected. Added code to check for null Organ in the populateAction.
 *
 * Revision 1.14  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.13  2005/11/02 20:29:09  pandyas
 * modified GeneDelivery dropdown source
 *
 * Revision 1.12  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.11  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.10  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.9  2005/10/26 13:45:16  georgeda
 * More cleanup
 *
 * Revision 1.8  2005/10/20 20:24:22  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GeneDeliveryPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("<GeneDeliveryPopulateAction populate> Entering populate() ");

		// Create a form to edit
		GeneDeliveryForm geneDeliveryForm = (GeneDeliveryForm) form;

		// Grab the current aCarcinogenExposureID we are working with related to
		// this
		// animalModel
		String aCarcinogenExposureID = request
				.getParameter("aCarcinogenExposureID");
		GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getBean("geneDeliveryManager");
		GeneDelivery gene = geneDeliveryManager.get(aCarcinogenExposureID);

		// Handle back-arrow on the delete
		if (gene == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {

			request
					.setAttribute("aCarcinogenExposureID",
							aCarcinogenExposureID);

			// Set the otherViralVector and/or the selected viral vector
			// attribute
			if (gene.getViralVectorUnctrlVocab() != null) {
				geneDeliveryForm
						.setViralVector(Constants.Dropdowns.OTHER_OPTION);
				geneDeliveryForm.setOtherViralVector(gene
						.getViralVectorUnctrlVocab());
			} else {
				geneDeliveryForm.setViralVector(gene.getViralVector());
			}

			geneDeliveryForm.setGeneInVirus(gene.getGeneInVirus());
			geneDeliveryForm.setRegimen(gene.getTreatment().getRegimen());

			if (gene.getTreatment().getSexDistribution() != null) {
				geneDeliveryForm.setType(gene.getTreatment()
						.getSexDistribution().getType());
			}
			geneDeliveryForm.setAgeAtTreatment(gene.getTreatment()
					.getAgeAtTreatment());
			geneDeliveryForm.setAgeAtTreatmentUnit(gene.getTreatment()
					.getAgeAtTreatmentUnit());

			/* set Organ attributes */
			log
					.info("<GeneDeliveryPopulateAction populate> get the Organ attributes");

			// since we are always querying from concept code (save and edit),
			// simply display VSPreferredDescription
			if (gene.getOrgan() != null) {
				geneDeliveryForm.setOrganTissueCode(gene.getOrgan().getConceptCode());
				log.info("OrganTissueCode= "+ gene.getOrgan().getConceptCode());

					geneDeliveryForm.setOrgan(gene.getOrgan()
							.getEVSPreferredDescription());
					log.info("setOrgan= "
							+ gene.getOrgan().getEVSPreferredDescription());
			}

		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		return mapping.findForward("submitGeneDelivery");

	}

	/**
	 * Populate the dropdown menus for submitEnvironmentalFactors
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dropdown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("<GeneDeliveryPopulateAction dropdown> Entering dropdown() ");

		// setup dropdown menus
		this.dropdown(request, response);

		log
				.info("<GeneDeliveryPopulateAction dropdown> before return submitRadiation ");

		return mapping.findForward("submitGeneDelivery");

	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log
				.info("<GeneDeliveryPopulateAction dropdown> Entering void dropdown()");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request,
				Constants.Dropdowns.VIRALVECTORDROP, "");
		NewDropdownUtil.populateDropdown(request,
				Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request,
				Constants.Dropdowns.AGEUNITSDROP, "");

		log
				.info("<GeneDeliveryPopulateAction dropdown> Exiting void dropdown()");

	}

}
