/**
 *  @author 
 *  
 *  $Id: AnimalModelTreePopulateAction.java,v 1.33 2005-10-31 13:46:28 georgeda Exp $
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

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalDistributor;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Populate the lists necessary to display an animal model.
 * 
 */
public class AnimalModelTreePopulateAction extends BaseAction {

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

		System.out.println("<AnimalModelTreePopulateAction populate> Entering... ");

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

		// Transfer back to request scope
		Object theErrors = request.getSession().getAttribute(org.apache.struts.Globals.ERROR_KEY);
		request.getSession().setAttribute(org.apache.struts.Globals.ERROR_KEY, null);

		if (theErrors != null) {
			request.setAttribute(org.apache.struts.Globals.ERROR_KEY, theErrors);
		}

		try {

			AnimalModel animalModel = animalModelManager.get(modelID);

			// Retrieve a list of all publications assoicated with this Animal
			// model
			List publicationList = animalModel.getPublicationCollection();
			List pubList = new ArrayList();

			for (int i = 0; i < publicationList.size(); i++) {
				Publication pub = (Publication) publicationList.get(i);

				// System.out.println("Adding Publication: " + pub.getTitle());

				pubList.add(pub);
			}

			// Print the list of GeneDelivery viralVestors for the Gene Delivery
			// (Cardiogenic Intervention) Section
			List geneDeliveryList = animalModel.getGeneDeliveryCollection();
			List geneList = new ArrayList();

			for (int i = 0; i < geneDeliveryList.size(); i++) {
				GeneDelivery geneDelivery = (GeneDelivery) geneDeliveryList.get(i);

				// System.out.println("\tAdded GeneDelivery= " +
				// geneDelivery.getViralVector() );

				geneList.add(geneDelivery);
			}

			// Retrieve a list of all cell lines assoicated with this Animal
			// model
			List cellLineList = animalModel.getCellLineCollection();
			List cellList = new ArrayList();

			for (int i = 0; i < cellLineList.size(); i++) {
				CellLine cellLine = (CellLine) cellLineList.get(i);

				// System.out.println("\tAdded CellLine= " + cellLine);

				cellList.add(cellLine);
			}

			// Retrieve a list of all availablty entries assoicated with this
			// Animal model
			List availabilityList = animalModel.getAnimalAvailabilityCollection();
			// System.out.println("availabilityList.size()" +
			// availabilityList.size());
			List investigatorList = new ArrayList();
			List jacksonLabList = new ArrayList();
			List mmhccList = new ArrayList();
			List imsrList = new ArrayList();

			for (int i = 0; i < availabilityList.size(); i++) {
				AnimalAvailability availability = (AnimalAvailability) availabilityList.get(i);

				if (availability.getAnimalDistributorCollection() != null) {
					List animalDistributorList = availability.getAnimalDistributorCollection();
					// System.out.println("animalDistributorList.size()" +
					// animalDistributorList.size());

					for (int j = 0; j < animalDistributorList.size(); j++) {
						AnimalDistributor animalDistributor = (AnimalDistributor) animalDistributorList.get(j);
						System.out.println("\tanimalDistributor.getName(): " + animalDistributor.getName());

						if (animalDistributor.getName().equals("Jackson Laboratory")) {

							System.out.println("\tAdded Jackson Laboratory Availability = " + availability);
							jacksonLabList.add(availability);
						}
						if (animalDistributor.getName().equals("Investigator")) {

							System.out.println("\tAdded Investigator Availability = " + availability);
							investigatorList.add(availability);
						}

						if (animalDistributor.getName().equals("MMHCC Repository")) {

							System.out.println("\tAdded MMHCC Repository Availability = " + availability);
							mmhccList.add(availability);
						}
						if (animalDistributor.getName().equals("IMSR")) {

							System.out.println("\tAdded IMSR Repository Availability = " + availability);
							imsrList.add(availability);
						}

					} // end of if
				}
			} // end of for

			// Retrive the list of all Xenograft transplants assoicated with
			// this Animal Model
			List xenoList = new ArrayList();
			List xenograftList = animalModel.getXenograftCollection();

			for (int i = 0; i < xenograftList.size(); i++) {
				Xenograft xenograft = (Xenograft) xenograftList.get(i);

				// System.out.println("\tAdded Xenograft= " + xenograft);

				xenoList.add(xenograft);
			}

			// Retrieve the list all SpontaneousMutations assoc with this
			// AnimalModel
			List spontaneousMutationList = animalModel.getSpontaneousMutationCollection();
			List mutationList = new ArrayList();

			for (int i = 0; i < spontaneousMutationList.size(); i++) {
				SpontaneousMutation spontaneousMutation = (SpontaneousMutation) spontaneousMutationList.get(i);

				// System.out.println("\tAdded spontaneousMutationList= " +
				// spontaneousMutation.getName() );

				mutationList.add(spontaneousMutation);
			}

			// Retrieve the list all SpontaneousMutations assoc with this
			// AnimalModel
			List engineeredGeneList = animalModel.getEngineeredGeneCollection();
			List inducedList = new ArrayList();
			List targetedList = new ArrayList();
			List segmentList = new ArrayList();
			List engineeredList = new ArrayList();
			List associatedExpressionList = new ArrayList();

			for (int i = 0; i < engineeredGeneList.size(); i++) {
				EngineeredGene engineeredGene = (EngineeredGene) engineeredGeneList.get(i);

				if (engineeredGene instanceof InducedMutation) {
					InducedMutation inInduced = (InducedMutation) engineeredGeneList.get(i);
					if (inInduced.getEnvironmentalFactor() != null) {
						inducedList.add((InducedMutation) engineeredGeneList.get(i));
						// System.out.println( "\tAdded a Induced Mutation" );
					}
				}

				if (engineeredGene instanceof TargetedModification) {
					// TargetedModification inTargeted = (TargetedModification)
					// engineeredGeneList.get(i);
					targetedList.add((TargetedModification) engineeredGeneList.get(i));
					// System.out.println( "\tAdded a TargetedModification" );
					// associatedExpressionList.addAll(
					// engineeredGene.getExpressionFeatureCollection() );

				}

				if (engineeredGene instanceof GenomicSegment) {
					// GenomicSegment inGenomicSegment = (GenomicSegment)
					// engineeredGeneList.get(i);
					segmentList.add((GenomicSegment) engineeredGeneList.get(i));
					// System.out.println( "\tAdded a GenomicSegment");
					// associatedExpressionList.addAll(
					// engineeredGene.getExpressionFeatureCollection() );
				}

				if (engineeredGene instanceof Transgene) {
					engineeredList.add((Transgene) engineeredGeneList.get(i));
					// System.out.println( "\tAdded a Transgene");
					// associatedExpressionList.addAll(
					// engineeredGene.getExpressionFeatureCollection() );
					// System.out.println("\n\n\tassociatedExpressionList:" +
					// associatedExpressionList );
				}
			}

			// Retrieve List of Images in Images Category
			List imageCollection = animalModel.getImageCollection();

			// Print the list of EnvironmentalFactors for the Cardiogenic
			// Interventions Section
			List tyList = animalModel.getTherapyCollection();

			List surgeryList = new ArrayList();
			List hormoneList = new ArrayList();
			List growthFactorList = new ArrayList();
			List viraltreatmentList = new ArrayList();
			List chemicaldrugList = new ArrayList();
			List environFactorList = new ArrayList();
			List radiationList = new ArrayList();
			List nutritionalFactorList = new ArrayList();
			List therapyList = new ArrayList();

			System.out.println("<AnimalModelTreePopulateAction> Building Tree ...");

			if (tyList == null || tyList.size() == 0) {
				System.out.println("<AnimalModelTreePopulateAction populate> nothing!");
			} else {
				for (int i = 0; i < tyList.size(); i++) {
					Therapy ty = (Therapy) tyList.get(i);

					// check to see if it is a Therapy
					if (ty.getTherapeuticExperiment().booleanValue() == true) {
						Agent agent = ty.getAgent();
						if (agent != null) {
							if (agent.getType() == null) {
								// System.out.println("\tAdded therapy to
								// therapyList");
								therapyList.add(ty);
							}
						}
					}

					// check to see if it is an EnvironmentalFactor
					if (ty.getTherapeuticExperiment().booleanValue() == false) {
						Agent agent = ty.getAgent();
						if (agent != null) {
							if (agent.getType().equals("Other")) {
								// System.out.println("\tAdded therapy to
								// surgeryList");
								surgeryList.add(ty);
							}
							if (agent.getType().equals("Hormone")) {
								// System.out.println(" therapy to
								// hormoneList");
								hormoneList.add(ty);
							}
							if (agent.getType().equals("Growth Factor")) {
								// System.out.println("\tAdded therapy to
								// growthFactorList");
								growthFactorList.add(ty);
							}
							if (agent.getType().equals("Viral")) {
								// System.out.println("\tAdded therapy to
								// viraltreatmentList");
								viraltreatmentList.add(ty);
							}
							if (agent.getType().equals("Chemical / Drug")) {
								// System.out.println("\tAdded therapy to
								// chemicaldrugList");
								chemicaldrugList.add(ty);
							}
							if (agent.getType().equals("Environment")) {
								// System.out.println("\tAdded therapy to
								// environFactorList");
								environFactorList.add(ty);
							}

							if (agent.getType().equals("Nutrition")) {
								// System.out.println("\tAdded therapy to
								// nutritionalFactorList");
								nutritionalFactorList.add(ty);
							}

							if (agent.getType().equals("Radiation")) {
								// System.out.println("\tAdded therapy to
								// radiationList");
								radiationList.add(ty);
							}
						}
					}
				}
			}

			request.getSession().setAttribute(Constants.Submit.GROWTHFACTORS_LIST, growthFactorList);
			request.getSession().setAttribute(Constants.Submit.HORMONE_LIST, hormoneList);
			request.getSession().setAttribute(Constants.Submit.SURGERYOTHER_LIST, surgeryList);
			request.getSession().setAttribute(Constants.Submit.VIRALTREATMENT_LIST, viraltreatmentList);
			request.getSession().setAttribute(Constants.Submit.CHEMICALDRUG_LIST, chemicaldrugList);
			request.getSession().setAttribute(Constants.Submit.ENVIRONMENTALFACTOR_LIST, environFactorList);
			request.getSession().setAttribute(Constants.Submit.RADIATION_LIST, radiationList);
			request.getSession().setAttribute(Constants.Submit.NUTRITIONALFACTORS_LIST, nutritionalFactorList);
			request.getSession().setAttribute(Constants.Submit.PUBLICATION_LIST, pubList);
			request.getSession().setAttribute(Constants.Submit.GENEDELIVERY_LIST, geneList);
			request.getSession().setAttribute(Constants.Submit.XENOGRAFT_LIST, xenoList);
			request.getSession().setAttribute(Constants.Submit.SPONTANEOUSMUTATION_LIST, mutationList);
			request.getSession().setAttribute(Constants.Submit.INDUCEDMUTATION_LIST, inducedList);
			request.getSession().setAttribute(Constants.Submit.CELLLINE_LIST, cellList);
			request.getSession().setAttribute(Constants.Submit.TARGETEDMODIFICATION_LIST, targetedList);
			request.getSession().setAttribute(Constants.Submit.GENOMICSEGMENT_LIST, segmentList);
			request.getSession().setAttribute(Constants.Submit.THERAPY_LIST, therapyList);
			request.getSession().setAttribute(Constants.Submit.ENGINEEREDTRANSGENE_LIST, engineeredList);
			request.getSession().setAttribute(Constants.Submit.IMAGE_LIST, imageCollection);
			request.getSession().setAttribute(Constants.Submit.ASSOCIATEDEXPRESSION_LIST, associatedExpressionList);

			request.getSession().setAttribute(Constants.Submit.INVESTIGATOR_LIST, investigatorList);
			request.getSession().setAttribute(Constants.Submit.JACKSONLAB_LIST, jacksonLabList);
			request.getSession().setAttribute(Constants.Submit.MMHCC_LIST, mmhccList);
			request.getSession().setAttribute(Constants.Submit.IMSR_LIST, imsrList);

			// System.out.println( "TargedModList: " + targetedList);

			// UserManager theUserManager = (UserManager)
			// getBean("userManager");
			// Set up the form. Should be only one coordinator
			// Get the coordinator
			ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
			String theCoordinator = theBundle.getString(Constants.BundleKeys.COORDINATOR_USERNAME_KEY);

			AnimalModelStateForm theForm = new AnimalModelStateForm();

			// Set the fields
			theForm.setModelId(modelID);
			theForm.setModelDescriptor(animalModel.getModelDescriptor());
			theForm.setNote("Model has been moved to complete");
			theForm.setAssignedTo(theCoordinator);
			theForm.setEvent(Constants.Admin.Actions.COMPLETE);

			request.setAttribute(Constants.FORMDATA, theForm);

		} catch (Exception e) {
			log.error("Caught an exception populating the data: ", e);

			// Encountered an error populating the data
			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("submitOverview");
	}
}
