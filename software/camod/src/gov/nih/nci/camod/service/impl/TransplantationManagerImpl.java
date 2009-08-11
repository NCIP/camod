/**
 * 
 * $Id: TransplantationManagerImpl.java,v 1.3 2009-03-25 16:23:53 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/01/16 18:30:22  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/10/31 19:13:27  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.4  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.3  2007/08/07 18:33:02  pandyas
 * Renamed to GRAFT as per VCDE comments
 *
 * Revision 1.2  2007/08/01 18:06:03  pandyas
 * VCDE changes
 *
 * Revision 1.1  2007/07/31 12:05:41  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.38  2007/06/26 16:13:43  pandyas
 * Fixed save when organ cleared from text entry and by use of the clear button for trees
 *
 * Revision 1.37  2007/06/25 17:48:37  pandyas
 * Fixed save and edit for text
 *
 * Revision 1.36  2007/06/18 16:14:02  pandyas
 * Cleaned up unused object
 *
 * Revision 1.35  2007/05/10 02:20:34  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.34  2007/04/04 13:18:06  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.33  2007/03/26 12:01:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.32  2006/09/18 16:25:21  georgeda
 * moved getOrgan code inside check for null - check first, get organ if selected by user
 *
 * Revision 1.31  2006/08/17 18:12:20  pandyas
 * Defect# 410: Externalize properties files - Code changes to get properties
 *
 * Revision 1.30  2006/05/22 18:19:26  pandyas
 * set adminSiteUnctrlVocab to null for editing from 'Other' to a selected value
 *
 * Revision 1.29  2006/05/22 18:14:36  pandyas
 * Fixed otherAdminSite so text is not saved in correct column
 *
 * Revision 1.28  2006/05/22 15:02:27  pandyas
 * Fixed Xenograft so organ is reused/created each time
 *
 * Revision 1.27  2006/05/19 18:50:37  pandyas
 * defect #225 - Add clearOrgan functionality to Xenograft screen
 *
 * Revision 1.26  2006/05/19 16:39:43  pandyas
 * Defect #249 - add other to species on the Xenograft screen
 *
 * Revision 1.25  2006/04/20 18:11:30  pandyas
 * Cleaned up Species or Strain save of Other in DB
 *
 * Revision 1.24  2006/04/19 17:38:26  pandyas
 * Removed TODO text
 *
 * Revision 1.23  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.22  2005/12/12 17:33:37  georgeda
 * Defect #265, store host/origin species in correct places
 *
 * Revision 1.21  2005/12/01 13:43:36  georgeda
 * Defect #226, reuse Taxon objects and do not delete them from Database
 *
 * Revision 1.20  2005/11/28 22:54:11  pandyas
 * Defect #186: Added organ/tissue to Xenograft page, modified search page to display multiple Xenografts with headers, modified XenograftManagerImpl so it does not create or save an organ object if not organ is selected
 *
 * Revision 1.19  2005/11/28 13:46:53  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.18  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.17  2005/11/14 14:19:37  georgeda
 * Cleanup
 *
 * Revision 1.16  2005/11/11 16:27:44  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.Strain;
import gov.nih.nci.camod.domain.Transplantation;
import gov.nih.nci.camod.service.TransplantationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.TransplantationData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author rajputs
 */
public class TransplantationManagerImpl extends BaseManager implements
TransplantationManager {

	/**
	 * Get all Transplantation objects
	 * 
	 * 
	 * @return the matching Transplantation objects, or null if not found.
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public List getAll() throws Exception {
		log.trace("In TransplantationManagerImpl.getAll");
		return super.getAll(Transplantation.class);
	}

	/**
	 * Get a specific Transplantation by id
	 * 
	 * @param id
	 *            the unique id for a Transplantation
	 * 
	 * @return the matching Transplantation object, or null if not found.
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public Transplantation get(String id) throws Exception {
		log.trace("In TransplantationManagerImpl.get");
		return (Transplantation) super.get(id, Transplantation.class);
	}

	public void save(Transplantation transplantation) throws Exception {
		log.trace("In TransplantationManagerImpl.save");
		super.save(transplantation);
	}

	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.trace("In TransplantationManagerImpl.remove");

		inAnimalModel.getTransplantationCollection().remove(get(id));
		super.save(inAnimalModel);
	}

	public Transplantation create(TransplantationData inTransplantationData,
			AnimalModel inAnimalModel) throws Exception {

		log
				.trace("<TransplantationManagerImpl> Entering TransplantationManagerImpl.create");

		Transplantation theTransplantation = new Transplantation();
		populateSpeciesStrain(inTransplantationData, theTransplantation, inAnimalModel);
		populateOrgan(inTransplantationData, theTransplantation);
		populateTransplantation(inTransplantationData, theTransplantation, inAnimalModel);

		log.debug("<TransplantationManagerImpl> Exiting TransplantationManagerImpl.create");

		return theTransplantation;
	}

	public void update(TransplantationData inTransplantationData, Transplantation inTransplantation,
			AnimalModel inAnimalModel) throws Exception {
		log.debug("Entering TransplantationManagerImpl.update TransplantationId: "
				+ inTransplantation.getId());

        // Populate w/ the new values and save
        populateSpeciesStrain(inTransplantationData, inTransplantation, inAnimalModel);
        populateOrgan(inTransplantationData, inTransplantation);
        populateTransplantation(inTransplantationData, inTransplantation, inAnimalModel);
        save(inTransplantation);

        log.debug("Exiting TransplantationDataManagerImpl.update");
    }

	private void populateTransplantation(TransplantationData inTransplantationData,
			Transplantation inTransplantation, AnimalModel inAnimalModel) throws Exception {

        log.debug("Entering TransplantationManagerImpl.populateGraft");

        /* Set Transplantation name */
        inTransplantation.setName(inTransplantationData.getName());

		/* Set other adminstrative site or selected adminstrative site */
		// save directly in administrativeSite column of table
		if (inTransplantationData.getAdministrativeSite().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			// Do not save other in the DB
			inTransplantation.setAdminSiteAlternEntry(inTransplantationData
					.getOtherAdministrativeSite());

			// Send e-mail for other administrativeSite
			sendEmail(inAnimalModel, inTransplantationData
					.getOtherAdministrativeSite(), "AdministrativeSite");
		} else {
			inTransplantation.setAdministrativeSite(inTransplantationData
					.getAdministrativeSite());

			// Null out during editing from 'other' to selected
			inTransplantation.setAdminSiteAlternEntry(null);
		}
		
		// save directly in ConditioningRegimen column of table
		if (inTransplantationData.getConditioningRegimen().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			inTransplantation.setConditioningRegimen(null);
			log.debug("ConditioningRegimen = Other");
			// Do not save "other" value in the DB
			inTransplantation.setCondRegimenAlternEntry(inTransplantationData.getOtherConditioningRegimen());
			log.debug("OtherConditioningRegimen = " + inTransplantationData.getOtherConditioningRegimen());

			// Send e-mail for other ConditioningRegime
			sendEmail(inAnimalModel, inTransplantationData
					.getOtherConditioningRegimen(), "ConditioningRegimen");
		} else {
			inTransplantation.setConditioningRegimen(inTransplantationData
					.getConditioningRegimen());
			log.debug("ConditioningRegimen not other= " + inTransplantationData
					.getConditioningRegimen());

			// Null out during editing from 'other' to selected
			inTransplantation.setCondRegimenAlternEntry(null);
		}
		
		inTransplantation.setGeneticManipulation(inTransplantationData
				.getGeneticManipulation());
		inTransplantation.setModificationDescription(inTransplantationData
				.getModificationDescription());
		inTransplantation.setParentalCellLineName(inTransplantationData
				.getParentalCellLineName());
		inTransplantation.setAtccNumber(inTransplantationData.getAtccNumber());
		inTransplantation.setCellAmount(inTransplantationData.getCellAmount());
		inTransplantation.setGrowthPeriod(inTransplantationData.getGrowthPeriod());
		inTransplantation.setComments(inTransplantationData.getComments());
		
		// anytime the Source type is "other"
		if (inTransplantationData.getSourceType().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			// Set Source type
			inTransplantation.setSourceType(null);
			inTransplantation.setSourceTypeAlternEntry(inTransplantationData
					.getOtherSourceType());

			// Send e-mail for other Transplantationt Type
			sendEmail(inAnimalModel, inTransplantationData.getOtherSourceType(),
					"SourceType");

		}
		// anytime Source type is not other set uncontrolled vocab to null
		// (covers editing)
		else {
			inTransplantation.setSourceType(inTransplantationData.getSourceType());
			inTransplantation.setSourceTypeAlternEntry(null);
		}

		log.debug("Exiting TransplantationManagerImpl.populateGraft");
	}

	private void populateSpeciesStrain(TransplantationData inTransplantationData,
			Transplantation inTransplantation, AnimalModel inAnimalModel) throws Exception {

		// Use Species to create strain
		Strain theStrain = StrainManagerSingleton.instance().getOrCreate(
				inTransplantationData.getDonorEthinicityStrain(),
				inTransplantationData.getOtherDonorEthinicityStrain(),
				inTransplantationData.getDonorScientificName(),
				inTransplantationData.getOtherDonorScientificName());

		// other option selected for species - send e-mail
		if (inTransplantationData.getDonorScientificName().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			// Send e-mail for other donor species
			sendEmail(inAnimalModel, inTransplantationData
					.getOtherDonorScientificName(), "Donor Species");
		}
		// other option selected for strain - send e-mail
		if (inTransplantationData.getDonorEthinicityStrain().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			// Send e-mail for other donor species
			sendEmail(inAnimalModel, inTransplantationData
					.getOtherDonorEthinicityStrain(), "Donor Strain");
		}

		log.debug("\n <populateSpeciesStrain> theSpecies is NOT other: ");
		inTransplantation.setStrain(theStrain);

	}

	private void populateOrgan(TransplantationData inTransplantationData,
			Transplantation inTransplantation) throws Exception {
        
        log.debug("<TransplantationManagerImpl> populateOrgan: ");        

        // Update loop handeled separately for conceptCode = 00000
        if (inTransplantationData.getOrganTissueCode().equals(Constants.Dropdowns.CONCEPTCODEZEROS)){
            if(inTransplantationData.getOrgan() != null && inTransplantationData.getOrgan().length() >0 ) {
                log.debug("Organ update loop for text: " + inTransplantationData.getOrgan()); 
                inTransplantation.setOrgan(new Organ());
                inTransplantation.getOrgan().setName(inTransplantationData.getOrgan());   
                inTransplantation.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS);     
            } else {
                log.debug("Clear previously entered organ text: " );
                inTransplantation.setOrgan(null); 
            }
        } else {            
            // Using trees loop, new save loop and update loop
            if (inTransplantationData.getOrganTissueCode() != null && inTransplantationData.getOrganTissueCode().length() > 0
                    && inTransplantationData.getOrganTissueName() != null && inTransplantationData.getOrganTissueName().length() > 0) {
                log.debug("OrganTissueCode: " + inTransplantationData.getOrganTissueCode());
                log.debug("OrganTissueName: " + inTransplantationData.getOrganTissueName()); 
                
                log.debug("OrganTissueCode() != null - getOrCreate method used");
                // when using tree, organTissueName populates the organ name entry
                Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(
                		inTransplantationData.getOrganTissueCode(),
                		inTransplantationData.getOrganTissueName());
                
                log.debug("theNewOrgan: " + theNewOrgan);
                inTransplantation.setOrgan(theNewOrgan); 
            } 
            // Clear organ selection via GUI
            if (inTransplantationData.getOrgan() == null && inTransplantationData.getOrganTissueCode().length() <1 ) {
                log.debug("Null out organ when cleared: " );
                inTransplantation.setOrgan(null);                 

            }
            // initial save text entry organ from GUI
            if (inTransplantationData.getOrgan() != null && inTransplantationData.getOrgan().length() >0 ) {
                // text entry loop = new save
                log.debug("Organ (initial text entry): " + inTransplantationData.getOrgan()); 
                inTransplantation.setOrgan(new Organ());
                inTransplantation.getOrgan().setName(inTransplantationData.getOrgan());                
                inTransplantation.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS); 
                log.debug("New Organ: " + inTransplantation.getOrgan().toString());
            }           
            
        } 

	}

	private void sendEmail(AnimalModel inAnimalModel,
			String theUncontrolledVocab, String inType) {
		// Get the e-mail resource
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System
				.getProperty("gov.nih.nci.camod.camodProperties");

		try {

			FileInputStream in = new FileInputStream(camodPropertiesFileName);
			camodProperties.load(in);

		} catch (FileNotFoundException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();
		}

		// Iterate through all the reciepts in the config file
		String recipients = UserManagerSingleton.instance()
				.getEmailForCoordinator();
		StringTokenizer st = new StringTokenizer(recipients, ",");
		String inRecipients[] = new String[st.countTokens()];
		for (int i = 0; i < inRecipients.length; i++) {
			inRecipients[i] = st.nextToken();
		}

		String inSubject = camodProperties
				.getProperty("model.new_unctrl_vocab_subject");
		String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

		// gather message keys and variable values to build the e-mail
		String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
		Map<String, Object> values = new TreeMap<String, Object>();
		values.put("type", inType);
		values.put("value", theUncontrolledVocab);
		values.put("submitter", inAnimalModel.getSubmitter());
		values.put("model", inAnimalModel.getModelDescriptor());
		values.put("modelstate", inAnimalModel.getState());

		// Send the email
		try {
			MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys,
					values);
		} catch (Exception e) {
			log.error("Caught exception sending mail: ", e);
			e.printStackTrace();
		}
	}
}
