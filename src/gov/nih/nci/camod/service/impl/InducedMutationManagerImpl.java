/**
 * @author schroedln
 * 
 * $Id: InducedMutationManagerImpl.java,v 1.11 2005-11-02 16:33:48 georgeda Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2005/10/31 18:55:51  georgeda
 * More validation changes
 *
 * Revision 1.9  2005/10/31 18:00:24  georgeda
 * Validation changes
 *
 * Revision 1.8  2005/10/27 20:54:31  schroedn
 * added buttons and caIMAGE dev server locations
 *
 * Revision 1.7  2005/10/24 21:04:03  schroedn
 * Added Image to submission
 *
 * Revision 1.6  2005/10/20 21:12:53  stewardd
 * modified to use extended MailUtil API
 *
 * Revision 1.5  2005/10/12 20:10:49  schroedn
 * Added Validation
 *
 * Revision 1.4  2005/10/06 20:41:49  schroedn
 * InducedMutation, TargetedMutation, GenomicSegment changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.InducedMutationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.InducedMutationData;

import java.util.*;

public class InducedMutationManagerImpl extends BaseManager implements InducedMutationManager {

    public List getAll() throws Exception {
        log.trace("In InducedMutationManagerImpl.getAll");
        return super.getAll(InducedMutation.class);
    }

    public InducedMutation get(String id) throws Exception {
        log.trace("In InducedMutationManagerImpl.get");
        return (InducedMutation) super.get(id, InducedMutation.class);
    }

    public void save(InducedMutation InducedMutation) throws Exception {
        log.trace("In InducedMutationManagerImpl.save");
        super.save(InducedMutation);
    }

    public void remove(String id) throws Exception {
        log.trace("In InducedMutationManagerImpl.remove");
        super.remove(id, InducedMutation.class);
    }

    public InducedMutation create(InducedMutationData inInducedMutationData) throws Exception {

        log.trace("Entering InducedMutationManagerImpl.create");

        InducedMutation theInducedMutation = new InducedMutation();

        populateInducedMutation(inInducedMutationData, theInducedMutation);

        log.trace("Exiting InducedMutationManagerImpl.create");

        return theInducedMutation;
    }

    public void update(InducedMutationData inInducedMutationData, InducedMutation inInducedMutation) throws Exception {

        log.trace("Entering InducedMutationManagerImpl.update");
        log.debug("Updating InducedMutationForm: " + inInducedMutation.getId());

        // Populate w/ the new values and save
        populateInducedMutation(inInducedMutationData, inInducedMutation);
        save(inInducedMutation);

        log.trace("Exiting InducedMutationManagerImpl.update");
    }

    private void populateInducedMutation(InducedMutationData inInducedMutationData, InducedMutation inInducedMutation)
            throws Exception {

        log.trace("Entering populateInducedMutation");

        EnvironmentalFactor inEnvironFactor = null;

        // Check to see if a Environmental Factor already exists,
        // if it does edit it else create a new EnvironmentalFactor
        if (inInducedMutation.getEnvironmentalFactorCollection().size() > 0)
            inEnvironFactor = (EnvironmentalFactor) inInducedMutation.getEnvironmentalFactorCollection().get(0);
        else
            inEnvironFactor = new EnvironmentalFactor();

        // Inducing Agent Category type
        inEnvironFactor.setType(inInducedMutationData.getType());

        // Other type
        if (inInducedMutationData.getOtherType() != null) {
            if (!inInducedMutationData.getOtherType().equals("")) {

                log.trace("Sending Notification eMail - new InducedMutation Agent added");
                ResourceBundle theBundle = ResourceBundle.getBundle("camod");

                // Iterate through all the reciepts in the config file
                String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
                StringTokenizer st = new StringTokenizer(recipients, ",");
                String inRecipients[] = new String[st.countTokens()];

                for (int i = 0; i < inRecipients.length; i++)
                    inRecipients[i] = st.nextToken();

                String inSubject = theBundle.getString(Constants.EmailMessage.SUBJECT);
                String inMessage = theBundle.getString(Constants.EmailMessage.MESSAGE)
                        + " Inducing Agent Type added ( " + inInducedMutationData.getOtherType()
                        + " ) and is awaiting your approval.";
                String inFrom = theBundle.getString(Constants.EmailMessage.FROM);
                // theBundle.getString(Constants.EmailMessage.SENDER);

                // Send the email
                try {
                    log.trace("Sending Notification eMail - new InducedMutation Agent added");

                    // gather message keys and variable values to build the
                    // e-mail content with
                    String[] messageKeys = { Constants.Admin.INDUCED_MUTATION_AGENT_ADDED };
                    TreeMap values = new TreeMap();
                    values.put(Constants.Admin.INDUCED_MUTATION_AGENT_NAME, inInducedMutationData.getName());
                    values.put(Constants.Admin.INDUCED_MUTATION_AGENT_TYPE, inInducedMutationData.getOtherType());

                    // Send the email
                    MailUtil.sendMail(inRecipients, inSubject, inMessage, inFrom, messageKeys, values);
                    log.trace("Notification eMail sent");
                } catch (Exception e) {
                    log.trace("Caught exception " + e);
                    // System.out.println("Caught exception" + e);
                    e.printStackTrace();
                }

                // 2. Set flag, this Strain will need to be approved before
                // being added the list
                inEnvironFactor.setTypeUnctrlVocab(inInducedMutationData.getOtherType());
            }
        }

        // CAS Number
        inEnvironFactor.setCasNumber(inInducedMutationData.getCASNumber());

        // Name of Inducing Agent
        inEnvironFactor.setName(inInducedMutationData.getName());

        // inEnvironFactor.setNameUnctrlVocab( );
        if (inInducedMutation.getEnvironmentalFactorCollection().size() < 1)
            inInducedMutation.addEnvironmentalFactor(inEnvironFactor);

        // GeneID
        inInducedMutation.setGeneId(inInducedMutationData.getGeneId());

        // Description
        inInducedMutation.setDescription(inInducedMutationData.getDescription());

        // Observation and Method of Observation
        List geneticList = inInducedMutation.getGeneticAlterationCollection();

        if (geneticList.size() > 0) {
            GeneticAlteration inGeneticAlteration = (GeneticAlteration) geneticList.get(0);
            inGeneticAlteration.setObservation(inInducedMutationData.getObservation());
            inGeneticAlteration.setMethodOfObservation(inInducedMutationData.getMethodOfObservation());
        } else {
            if (inInducedMutationData.getObservation() != null) {
                if (!inInducedMutationData.getObservation().equals("")) {
                    GeneticAlteration inGeneticAlteration = new GeneticAlteration();
                    inGeneticAlteration.setObservation(inInducedMutationData.getObservation());
                    inGeneticAlteration.setMethodOfObservation(inInducedMutationData.getMethodOfObservation());
                    inInducedMutation.addGeneticAlteration(inGeneticAlteration);
                }
            }
        }

        // MGI Number
        // Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if (inInducedMutation.getMutationIdentifier() != null)
            inMutationIdentifier = inInducedMutation.getMutationIdentifier();
        else
            inMutationIdentifier = new MutationIdentifier();

        inInducedMutation.setComments(inInducedMutationData.getComments());

        inMutationIdentifier.setNumberMGI(Long.valueOf(inInducedMutationData.getNumberMGI().trim()));
        inInducedMutation.setMutationIdentifier(inMutationIdentifier);

        log.trace("Exiting populateInducedMutation");
    }
}
