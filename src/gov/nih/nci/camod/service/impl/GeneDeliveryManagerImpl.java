/**
 * @author schroedln
 * 
 * $Id: GeneDeliveryManagerImpl.java,v 1.13 2005-11-29 16:32:27 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2005/11/28 13:45:05  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.11  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.10  2005/11/14 14:18:33  georgeda
 * Cleanup
 *
 * Revision 1.9  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.8  2005/11/03 21:47:48  georgeda
 * Changed EVS api
 *
 * Revision 1.7  2005/11/02 19:02:55  pandyas
 * Added e-mail functionality
 *
 * Revision 1.6  2005/10/20 20:02:42  pandyas
 * EVSTree (organ) functions properly
 *
 * Revision 1.5  2005/10/19 18:08:18  pandyas
 * added age and gender to genedelivery
 *
 * Revision 1.4  2005/09/28 21:20:01  georgeda
 * Finished up converting to new manager
 *
 * Revision 1.3  2005/09/28 15:12:27  schroedn
 * Added GeneDelivery and Xenograft/Transplant, businass logic in Managers
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryData;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.MailUtil;

public class GeneDeliveryManagerImpl extends BaseManager implements GeneDeliveryManager {

    public List getAll() throws Exception {
        log.debug("In GeneDeliveryManagerImpl.getAll");
        return super.getAll(GeneDelivery.class);
    }

    public GeneDelivery get(String id) throws Exception {
        log.debug("In GeneDeliveryManagerImpl.get");
        return (GeneDelivery) super.get(id, GeneDelivery.class);
    }

    public void save(GeneDelivery geneDelivery) throws Exception {
        log.debug("In GeneDeliveryManagerImpl.save");
        super.save(geneDelivery);
    }

    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.debug("In GeneDeliveryManagerImpl.remove");
        inAnimalModel.getGeneDeliveryCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public GeneDelivery create(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryForm) throws Exception {

        log.info("Entering GeneDeliveryManagerImpl.create");

        GeneDelivery theGeneDelivery = new GeneDelivery();

        populateGeneDelivery(inAnimalModel, inGeneDeliveryForm, theGeneDelivery);
        
        log.info("Exiting GeneDeliveryManagerImpl.create");        
        return theGeneDelivery;
    }

    public void update(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryForm, GeneDelivery inGeneDelivery) throws Exception {

        log.info("Entering GeneDeliveryManagerImpl.update");
        log.info("Updating GeneDeliveryForm: " + inGeneDelivery.getId());

        // Populate w/ the new values and save
        populateGeneDelivery(inAnimalModel, inGeneDeliveryForm, inGeneDelivery);

        save(inGeneDelivery);

        log.debug("Exiting GeneDeliveryManagerImpl.update");
    }

    private void populateGeneDelivery(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryData, GeneDelivery inGeneDelivery)
            throws Exception {

        log.info("Entering GeneDeliveryManagerImpl.populateGeneDelivery");
        
        // Set the treatment
        Treatment theTreatment = inGeneDelivery.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            inGeneDelivery.setTreatment(theTreatment);
        }        
        
        // Set the gender
        SexDistribution sexDistribution = SexDistributionManagerSingleton.instance().getByType(inGeneDeliveryData.getType());
        
        // save the treatment
        theTreatment.setSexDistribution(sexDistribution);

        // Append the ageunit onto the age at treatment variable
        theTreatment.setAgeAtTreatment(inGeneDeliveryData.getAgeAtTreatment() + " " + inGeneDeliveryData.getAgeUnit());
        
        //anytime the viral vector is "other"
        if (inGeneDeliveryData.getViralVector().equals(Constants.Dropdowns.OTHER_OPTION) )   {
            
            ResourceBundle theBundle = ResourceBundle.getBundle("camod");

            // Iterate through all the reciepts in the config file
            String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
            StringTokenizer st = new StringTokenizer(recipients, ",");
            String inRecipients[] = new String[st.countTokens()];
            for (int i = 0; i < inRecipients.length; i++) {
                inRecipients[i] = st.nextToken();
            }

            String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
            String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

            // gather message keys and variable values to build the e-mail
            // content with
            String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
            Map values = new TreeMap();
            values.put("type", "ViralVector");
            values.put("value", inGeneDeliveryData.getOtherViralVector());
            values.put("submitter", inAnimalModel.getSubmitter());
            values.put("model", inAnimalModel.getModelDescriptor());
            values.put("modelstate", inAnimalModel.getState());

            // Send the email
            try {
                MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys, values);
            } catch (Exception e) {
                log.error("Caught exception sending mail: ", e);
                e.printStackTrace();
            }            

            inGeneDelivery.setViralVector(null);
            inGeneDelivery.setViralVectorUnctrlVocab(inGeneDeliveryData.getOtherViralVector());
        }        
        // anytime viral vector is not other set uncontrolled vocab to null (covers editing)
        else   {
        	System.out.println("viral vector not other");
        	inGeneDelivery.setViralVector(inGeneDeliveryData.getViralVector());
        	inGeneDelivery.setViralVectorUnctrlVocab(null);
        }        
        
        inGeneDelivery.getTreatment().setRegimen(inGeneDeliveryData.getRegimen());
        inGeneDelivery.setGeneInVirus(inGeneDeliveryData.getGeneInVirus());

        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode, only if organ is selected by user
         */        
        if (inGeneDeliveryData.getOrganTissueCode() !=null && inGeneDeliveryData.getOrganTissueCode().length() >0){
        	log.info("newConceptCode is not null: ");
        	
        	String newConceptCode = inGeneDeliveryData.getOrganTissueCode();
        	log.info("newConceptCode: " + newConceptCode);
        		
            	// Organ will be null for new submissions 
            	if (inGeneDelivery.getOrgan() == null) {
        			log.info("Organ is new so create new object and retrieve attributes");            		

        			inGeneDelivery.setOrgan(new Organ());
        		
            		/*
            		 * Always get/store organ name through the concept code - never deal
            		 * with converting name back and forth
            		 */
            		String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inGeneDeliveryData.getOrganTissueCode());

            		log.info("preferedOrganName: " + preferedOrganName);
            		inGeneDelivery.getOrgan().setName(preferedOrganName);

            		log.info("populateXenograft - getOrgan().setConceptCode - OrganTissueCode: "
    	        		+ inGeneDeliveryData.getOrganTissueCode());
            		inGeneDelivery.getOrgan().setConceptCode(inGeneDeliveryData.getOrganTissueCode());
        	
            	} else {
            		//  edited screen - submit organ only if changed
           			log.info("Organ is modified so compare concept code in DB");
           			
            		String oldConceptCode = inGeneDelivery.getOrgan().getConceptCode();
            		log.info("oldConceptCode: " + oldConceptCode);

            		if (!newConceptCode.equals(oldConceptCode)) {
            			log.info("oldConceptCode != newConceptCode: "  );
            			/*
            			 * Always get/store organ name through the concept code - never deal
            			 * with converting name back and forth
            			 */
            			String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inGeneDeliveryData.getOrganTissueCode());
            		
            			log.info("preferedOrganName: " + preferedOrganName);
            			inGeneDelivery.getOrgan().setName(preferedOrganName);

            			log.info("populateXenograft - getOrgan().setConceptCode - OrganTissueCode: "
            					+ inGeneDeliveryData.getOrganTissueCode());
            			inGeneDelivery.getOrgan().setConceptCode(inGeneDeliveryData.getOrganTissueCode());
            		}
            	}
        } 

        log.info("Exiting GeneDeliveryManagerImpl.populateGeneDelivery");
    }

}
