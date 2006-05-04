/**
 * @author schroedln
 * 
 * $Id: InducedMutationManagerImpl.java,v 1.22 2006-05-04 19:27:45 pandyas Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.21  2006/04/19 15:08:17  georgeda
 * Fixed issue w/ display of induced mutation
 *
 * Revision 1.20  2006/04/18 16:20:05  pandyas
 * Updated populate method to save IM correctly
 *
 * Revision 1.19  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.18  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.17  2005/11/28 13:46:53  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.16  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.15  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.14  2005/11/03 19:23:47  schroedn
 * Minor bug fixes
 *
 * Revision 1.13  2005/11/02 19:44:20  schroedn
 * Merged changes, modified Image function, fix MGI num bug
 *
 * Revision 1.12  2005/11/02 19:07:25  pandyas
 * Added e-mail functionality
 *
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
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.service.InducedMutationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.InducedMutationData;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class InducedMutationManagerImpl extends BaseManager implements InducedMutationManager
{
    public List getAll() throws Exception
    {
        log.info("In InducedMutationManagerImpl.getAll");
        return super.getAll(InducedMutation.class);
    }

    public InducedMutation get(String id) throws Exception
    {
        log.info("In InducedMutationManagerImpl.get");
        return (InducedMutation) super.get(id, InducedMutation.class);
    }

    public void save(InducedMutation InducedMutation) throws Exception
    {
        log.info("In InducedMutationManagerImpl.save");
        super.save(InducedMutation);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.info("In InducedMutationManagerImpl.remove");
        inAnimalModel.getEngineeredGeneCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public InducedMutation create(AnimalModel inAnimalModel,
                                  InducedMutationData inInducedMutationData) throws Exception
    {
        log.info("Entering InducedMutationManagerImpl.create");

        InducedMutation theInducedMutation = new InducedMutation();

        populateInducedMutation(inAnimalModel, inInducedMutationData, theInducedMutation);

        log.info("Exiting InducedMutationManagerImpl.create");

        return theInducedMutation;
    }

    public void update(AnimalModel inAnimalModel,
                       InducedMutationData inInducedMutationData,
                       InducedMutation inInducedMutation) throws Exception
    {
        log.info("Entering InducedMutationManagerImpl.update");
        log.info("Updating InducedMutationForm: " + inInducedMutation.getId());

        // Populate w/ the new values and save
        populateInducedMutation(inAnimalModel, inInducedMutationData, inInducedMutation);
        save(inInducedMutation);

        log.info("Exiting InducedMutationManagerImpl.update");
    }

    private void populateInducedMutation(AnimalModel inAnimalModel,
                                         InducedMutationData inInducedMutationData,
                                         InducedMutation inInducedMutation) throws Exception
    {
        log.info("Entering populateInducedMutation");

        EnvironmentalFactor theEnvironFactor = null;

        // Check to see if a Environmental Factor already exists,
        // if it does edit it else create a new EnvironmentalFactor
        if (inInducedMutation.getEnvironmentalFactor() != null)
        {
            theEnvironFactor = (EnvironmentalFactor) inInducedMutation.getEnvironmentalFactor();
        }
        else
        {
            theEnvironFactor = new EnvironmentalFactor();
            inInducedMutation.setEnvironmentalFactor(theEnvironFactor);
        }
        log.info("In InducedMutationManagerImpl.populateInducedMutation created EF");

        // Name of Inducing Agent - Saved in uncontrolled vocab field since it is free text
        theEnvironFactor.setNameUnctrlVocab(inInducedMutationData.getName());
        log.info("In InducedMutationManagerImpl.save set name : " + inInducedMutationData.getName());

        // Inducing Agent Category type / Other type
        if (inInducedMutationData.getOtherType() != null)
        {
            log.info("Sending Notification eMail - new InducedMutation Agent added");
            // Send the email
            sendEmail(inAnimalModel, inInducedMutationData.getOtherType(), "OtherInducedMutation");
            // Do not save 'Other' in the database
            theEnvironFactor.setTypeUnctrlVocab(inInducedMutationData.getOtherType());
            log.info("inInducedMutationData.getOtherType(): " + inInducedMutationData.getOtherType());
        }
        else
        {
            theEnvironFactor.setType(inInducedMutationData.getType());
            log.info("inInducedMutationData.getType(): " + inInducedMutationData.getType());
        }

        // CAS Number
        theEnvironFactor.setCasNumber(inInducedMutationData.getCasNumber());

        // GeneID
        inInducedMutation.setGeneId(inInducedMutationData.getGeneId());

        // Description
        inInducedMutation.setDescription(inInducedMutationData.getDescription());
        log.info("inInducedMutationData.getDescription(): " + inInducedMutationData.getDescription());

        // Observation and Method of Observation
        // No genetic alteration in DB - data is entered from the GUI
        if (inInducedMutation.getGeneticAlteration() == null && inInducedMutationData.getObservation() != null && inInducedMutationData.getObservation().length() > 0)
        {
            inInducedMutation.setGeneticAlteration(new GeneticAlteration());
            log.info("Saving: inInducedMutation.getGeneticAlteration() attributes ");

            inInducedMutation.getGeneticAlteration().setObservation(inInducedMutationData.getObservation());
            inInducedMutation.getGeneticAlteration().setMethodOfObservation(inInducedMutationData.getMethodOfObservation());
        }

        // Already have a genetic alteration in DB. Either blank it out or update it
        else
        {
            if (inInducedMutationData.getObservation() != null && inInducedMutationData.getObservation().length() > 0)
            {
                inInducedMutation.getGeneticAlteration().setObservation(inInducedMutationData.getObservation());
                inInducedMutation.getGeneticAlteration().setMethodOfObservation(inInducedMutationData.getMethodOfObservation());
            }
            else
            {
                inInducedMutation.setGeneticAlteration(null);
            }
        }

        // MGI Number
        // Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if (inInducedMutation.getMutationIdentifier() != null)
        {
            inMutationIdentifier = inInducedMutation.getMutationIdentifier();
        }
        else
        {
            inMutationIdentifier = new MutationIdentifier();
        }

        if (inInducedMutationData.getMgiNumber() == null || inInducedMutationData.getMgiNumber().equals(""))
        {
            inInducedMutation.setMutationIdentifier(null);
        }
        else
        {
            inMutationIdentifier.setMgiNumber(inInducedMutationData.getMgiNumber());
            inInducedMutation.setMutationIdentifier(inMutationIdentifier);
        }

        // Comments
        inInducedMutation.setComments(inInducedMutationData.getComments());

        log.info("Exiting populateInducedMutation");
    }

    private void sendEmail(AnimalModel inAnimalModel,
                           String theUncontrolledVocab,
                           String inType)
    {
        // Get the e-mail resource
        ResourceBundle theBundle = ResourceBundle.getBundle("camod");

        // Iterate through all the reciepts in the config file
        String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
        StringTokenizer st = new StringTokenizer(recipients, ",");
        String inRecipients[] = new String[st.countTokens()];
        for (int i = 0; i < inRecipients.length; i++)
        {
            inRecipients[i] = st.nextToken();
        }

        String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
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
        try
        {
            MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys, values);
        }
        catch (Exception e)
        {
            log.error("Caught exception sending mail: ", e);
            e.printStackTrace();
        }
    }
}
