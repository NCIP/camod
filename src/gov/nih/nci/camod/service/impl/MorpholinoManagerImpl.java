/**
 * @pandyas
 * 
 * $Id: MorpholinoManagerImpl.java,v 1.1 2006-05-03 20:04:04 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.MorpholinoManager;
import gov.nih.nci.camod.webapp.form.*;
import gov.nih.nci.camod.util.MailUtil;

/**
 * Manager provides get method
 */
public class MorpholinoManagerImpl extends BaseManager implements MorpholinoManager
{

    public List getAll() throws Exception
    {
        log.info("In MorpholinoManagerImpl.getAll");
        return super.getAll(Morpholino.class);
    }

    /**
     * Get a specific Morpholino by id
     * 
     * @param id
     *            the unique id for a Morpholino
     * 
     * @return the matching Morpholino object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Morpholino get(String id) throws Exception
    {
        log.info("In MorpholinoManagerImpl.get");
        return (Morpholino) super.get(id, Morpholino.class);
    }

    /**
     * Save Morpholino
     * 
     * @param Morpholino
     *            the Morpholino to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Morpholino morpholino) throws Exception
    {
        log.info("In MorpholinoManagerImpl.save");
        super.save(morpholino);
    }

    /**
     * Remove a specific Morpholino by id
     * 
     * @param id
     *            the unique id for a Morpholino
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.info("In MorpholinoManagerImpl.remove");

        Morpholino theMorpholino = get(id);

        inAnimalModel.getMorpholinoCollection().remove(theMorpholino);
        super.save(inAnimalModel);
    }


    /**
     * Create a Morpholino object with the correct data filled in.
     * 
     * @param inMorpholinoData
     *            the interface to create the Morpholino object from
     * 
     * @returns a Morpholino
     */
    public Morpholino create(AnimalModel inAnimaModel,
                             MorpholinoData inMorpholinoData) throws Exception
    {

        log.info("In MorpholinoManagerImpl.create Entering");

        Morpholino theMorpholino = new Morpholino();

        populateMorpholino(inMorpholinoData, theMorpholino, inAnimaModel);

        log.info("In MorpholinoManagerImpl.create Exiting");
        return theMorpholino;
    }

    public void update(AnimalModel inAnimalModel,
                       MorpholinoData inMorpholinoData,
                       Morpholino inMorpholino) throws Exception
    {

        log.debug("In MorpholinoManagerImpl.update");
        populateMorpholino(inMorpholinoData, inMorpholino, inAnimalModel);

        save(inMorpholino);

    }

    private void populateMorpholino(MorpholinoData inMorpholinoData,
                                    Morpholino inMorpholino,
                                    AnimalModel inAnimalModel)
    {
        log.info("<MorpholinoManagerImpl> Entering populateMorpholino");

        // Save Source
        if (inMorpholinoData.getSource().equals(Constants.Dropdowns.OTHER_OPTION))
        {
            log.info("source equals other");
            inMorpholino.setSource(null);
            inMorpholino.setSourceUnctrVocab(inMorpholinoData.getOtherSource());

            log.info("Sending Notification eMail - new Source added");
            sendEmail(inAnimalModel, inMorpholinoData.getOtherSource(), "otherSource");
        }
        else if (inMorpholinoData.getSource() != null)
        {
            log.info("source not other or null");
            inMorpholino.setSource(inMorpholinoData.getSource());
            inMorpholino.setSourceUnctrVocab(null);
        }
        
        // Save Type 
        inMorpholino.setType(inMorpholinoData.getType());
        
        // Save SequenceDirection 
        inMorpholino.setSequenceDirection(inMorpholinoData.getSequenceDirection());
        
        // Save Targeted Region 
        inMorpholino.setTargetedRegion(inMorpholinoData.getTargetedRegion());
        
        // Save Concentration
        inMorpholino.setConcentration(inMorpholinoData.getConcentration());
        inMorpholino.setConcentrationUnit(inMorpholinoData.getConcentrationUnit());


        // Save Delivery Method
        if (inMorpholinoData.getDeliveryMethod().equals(Constants.Dropdowns.OTHER_OPTION))
        {
            log.info("DeliveryMethod equals other");
            inMorpholino.setDeliveryMethod(null);
            inMorpholino.setDeliveryMethodUnctrlVocab(inMorpholinoData.getOtherDeliveryMethod());

            log.info("Sending Notification eMail - new DeliveryMethod added");
            sendEmail(inAnimalModel, inMorpholinoData.getOtherDeliveryMethod(), "otherDeliveryMethod");
        }
        else if (inMorpholinoData.getDeliveryMethod() != null)
        {
            log.info("DeliveryMethod not other or null");
            inMorpholino.setDeliveryMethod(inMorpholinoData.getDeliveryMethod());
            inMorpholino.setDeliveryMethodUnctrlVocab(null);
        }
        
        // Save visualizationLigands
        if (inMorpholinoData.getVisualLigand().equals(Constants.Dropdowns.OTHER_OPTION))
        {
            log.info("visualizationLigands equals other");
            inMorpholino.setVisualLigand(null);
            inMorpholino.setVisualLigandUnctrlVocab(inMorpholinoData.getOtherVisualLigand());

            log.info("Sending Notification eMail - new VisualLigands added");
            sendEmail(inAnimalModel, inMorpholinoData.getOtherVisualLigand(), "otherVisualLigands");
        }
        else if (inMorpholinoData.getVisualLigand() != null)
        {
            log.info("visualLigands not other or null");
            inMorpholino.setVisualLigand(inMorpholinoData.getVisualLigand());
            inMorpholino.setVisualLigandUnctrlVocab(null);
        }

        log.info("<MorpholinoManagerImpl> Exiting populateMorpholino");
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
