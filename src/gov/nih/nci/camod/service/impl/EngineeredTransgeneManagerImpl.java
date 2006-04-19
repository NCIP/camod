/**
 * 
 * $Id: EngineeredTransgeneManagerImpl.java,v 1.24 2006-04-19 17:40:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.23  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.domain.GeneFunction;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.RegulatoryElement;
import gov.nih.nci.camod.domain.RegulatoryElementType;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;
import gov.nih.nci.camod.webapp.form.ImageForm;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;


public class EngineeredTransgeneManagerImpl extends BaseManager implements EngineeredTransgeneManager
{

    public List getAll() throws Exception
    {
        log.info("In EngineeredTransgeneManagerImpl.getAll");
        return super.getAll(EngineeredGene.class);
    }

    public Transgene get(String id) throws Exception
    {
        log.info("In EngineeredTransgeneManagerImpl.get");
        return (Transgene) super.get(id, Transgene.class);
    }

    public void save(Transgene engineeredGene) throws Exception
    {
        log.info("In EngineeredTransgeneManagerImpl.save");
        super.save(engineeredGene);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.info("In EngineeredTransgeneManagerImpl.remove");
        inAnimalModel.getEngineeredGeneCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public Transgene create(EngineeredTransgeneData inEngineeredTransgeneData,
                            HttpServletRequest request) throws Exception
    {

        log.info("Entering EngineeredTransgeneManagerImpl.create");

        Transgene inEngineeredTransgene = new Transgene();
        populateEngineeredTransgene(inEngineeredTransgeneData, inEngineeredTransgene, request);

        log.info("Exiting EngineeredTransgeneManagerImpl.create");

        return inEngineeredTransgene;
    }

    public void update(EngineeredTransgeneData inEngineeredTransgeneData,
                       Transgene inEngineeredTransgene,
                       HttpServletRequest request) throws Exception
    {

        log.info("Entering EngineeredTransgeneManagerImpl.update");
        log.debug("Updating EngineeredTransgeneForm: " + inEngineeredTransgene.getId());

        // Populate w/ the new values and save
        populateEngineeredTransgene(inEngineeredTransgeneData, inEngineeredTransgene, request);
        save(inEngineeredTransgene);

        log.info("Exiting EngineeredTransgeneManagerImpl.update");
    }

    public void createAssocExpression(AssociatedExpressionData inAssociatedExpressionData,
                                      EngineeredGene inEngineeredTransgene) throws Exception
    {

        log.info("Entering EngineeredTransgeneManagerImpl.createAssocExpression");

        populateAssocExpression(inAssociatedExpressionData, inEngineeredTransgene);

        log.info("Exiting EngineeredTransgeneManagerImpl.createAssocExpression");
    }

    public void updateAssociatedExpression(AssociatedExpressionData inAssociatedExpressionData,
                                           EngineeredGene inEngineeredTransgene) throws Exception
    {

        log.info("Entering EngineeredTransgeneManagerImpl.updateAssociatedExpression");

        Set expFeatureSet = inEngineeredTransgene.getExpressionFeatureCollection();
        Iterator it = expFeatureSet.iterator();

        while(it.hasNext()){
            ExpressionFeature expFeature = (ExpressionFeature)it.next();
            if (expFeature.getId().toString().equals(inAssociatedExpressionData.getEngineeredGeneID()))
            {

                String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inAssociatedExpressionData.getOrganTissueCode());
                Organ organ = expFeature.getOrgan();
                organ.setName(preferedOrganName);
                organ.setConceptCode(inAssociatedExpressionData.getOrganTissueCode());

                ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName(
                                                                                                            inAssociatedExpressionData.getExpressionLevel());

                expFeature.setExpressionLevelDesc(expLevelDesc);
                expFeature.setOrgan(organ);
            }
        }        
        /*
        for (int i = 0; i < expFeatureList.size(); i++)
        {
            ExpressionFeature expFeature = (ExpressionFeature) expFeatureList.get(i);

            if (expFeature.getId().toString().equals(inAssociatedExpressionData.getEngineeredGeneID()))
            {

                String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inAssociatedExpressionData.getOrganTissueCode());
                Organ organ = expFeature.getOrgan();
                organ.setName(preferedOrganName);
                organ.setConceptCode(inAssociatedExpressionData.getOrganTissueCode());

                ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName(
                                                                                                            inAssociatedExpressionData.getExpressionLevel());

                expFeature.setExpressionLevelDesc(expLevelDesc);
                expFeature.setOrgan(organ);
            }
        }
        */
        save(inEngineeredTransgene);

        log.info("Entering EngineeredTransgeneManagerImpl.updateAssociatedExpression");
    }

    private void populateAssocExpression(AssociatedExpressionData inAssociatedExpressionData,
                                         EngineeredGene inEngineeredTransgene) throws Exception
    {

        log.info("Entering populateAssocExpression");

        ExpressionFeature expFeature = new ExpressionFeature();

        String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inAssociatedExpressionData.getOrganTissueCode());
        expFeature.setOrgan(new Organ());
        expFeature.getOrgan().setName(preferedOrganName);
        expFeature.getOrgan().setConceptCode(inAssociatedExpressionData.getOrganTissueCode());

        ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName(
                                                                                                    inAssociatedExpressionData.getExpressionLevel());

        expFeature.setExpressionLevelDesc(expLevelDesc);
        inEngineeredTransgene.addExpressionFeature(expFeature);
        log.info("Exiting populateAssocExpression");
    }

    private void populateEngineeredTransgene(EngineeredTransgeneData inEngineeredTransgeneData,
                                             Transgene inEngineeredTransgene,
                                             HttpServletRequest request) throws Exception
    {

        log.info("Entering populateEngineeredTransgene");

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        AnimalModel theAnimalModel = AnimalModelManagerSingleton.instance().get(theModelId);

        // Transgene Integration - adds term 'Random' if random and the text entered by the user if 'Targeted'
        if (inEngineeredTransgeneData.getIsRandom().equals("Targeted"))
        {
            inEngineeredTransgene.setIsRandom(false);
            inEngineeredTransgene.setLocationOfIntegration(inEngineeredTransgeneData.getLocationOfIntegration());
        }
        else
        {
            inEngineeredTransgene.setIsRandom(true);
        }

        // Transgene (coding sequence only)
        inEngineeredTransgene.setName(inEngineeredTransgeneData.getName());
        inEngineeredTransgene.setConstructSequence(inEngineeredTransgeneData.getConstructSequence());

        // Did we add a taxon?
        //clears taxon (species)
        //inEngineeredTransgene.getSpecies().equals(null);
        if (inEngineeredTransgeneData.getScientificName() != null && inEngineeredTransgeneData.getScientificName().length() > 0)
        {
            // Create/reuse or create the species object
            Species theSpecies = SpeciesManagerSingleton.instance().getOrCreate(inEngineeredTransgeneData.getScientificName());

            //Species is other, add 'other' to commonName field and send e-mail
            if (!inEngineeredTransgeneData.getScientificName().equals(Constants.Dropdowns.OTHER_OPTION))
            {
                // Sima TODO:  make sure this is consistent - do not add other, but put this in commonName field
                theSpecies.setCommonName(Constants.Dropdowns.OTHER_OPTION);
                inEngineeredTransgene.setSpecies(theSpecies);
                sendEmail(theAnimalModel, inEngineeredTransgeneData.getOtherScientificName(), "Transgene ScientificName");                
            }
            else
            {
                inEngineeredTransgene.setSpecies(theSpecies);
            }            
            
        }

        inEngineeredTransgene.getRegulatoryElementCollection().clear();

        // Transcriptional 1
        addRegulatoryElement(inEngineeredTransgeneData.getTranscriptional1_name(), "Transcriptional 1", inEngineeredTransgene,
                             inEngineeredTransgeneData.getTranscriptional1_species(),
                             inEngineeredTransgeneData.getTranscriptional1_otherSpecies(), theAnimalModel);

        // Transcriptional 2
        addRegulatoryElement(inEngineeredTransgeneData.getTranscriptional2_name(), "Transcriptional 2", inEngineeredTransgene,
                             inEngineeredTransgeneData.getTranscriptional2_species(),
                             inEngineeredTransgeneData.getTranscriptional2_otherSpecies(), theAnimalModel);

        // Transcriptional 3
        addRegulatoryElement(inEngineeredTransgeneData.getTranscriptional3_name(), "Transcriptional 3", inEngineeredTransgene,
                             inEngineeredTransgeneData.getTranscriptional3_species(),
                             inEngineeredTransgeneData.getTranscriptional3_otherSpecies(), theAnimalModel);

        // Poly A Signal
        addRegulatoryElement(inEngineeredTransgeneData.getPolyASignal_name(), "Poly A Signal", inEngineeredTransgene,
                             inEngineeredTransgeneData.getPolyASignal_species(), inEngineeredTransgeneData.getPolyASignal_otherSpecies(),
                             theAnimalModel);

        // Splice Site
        addRegulatoryElement(inEngineeredTransgeneData.getSpliceSites_name(), "Splice Site", inEngineeredTransgene,
                             inEngineeredTransgeneData.getSpliceSites_species(), inEngineeredTransgeneData.getSpliceSites_otherSpecies(),
                             theAnimalModel);

        // MGI Number
        // Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if (inEngineeredTransgene.getMutationIdentifier() != null)
            inMutationIdentifier = inEngineeredTransgene.getMutationIdentifier();
        else
            inMutationIdentifier = new MutationIdentifier();

        if (inEngineeredTransgeneData.getMgiNumber() == null || inEngineeredTransgeneData.getMgiNumber().equals(""))
        {
            inEngineeredTransgene.setMutationIdentifier(null);
        }
        else
        {
            String strNumberMGI = inEngineeredTransgeneData.getMgiNumber().trim();
            Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
            Matcher m = p.matcher(strNumberMGI);
            if (m.matches() && strNumberMGI != null && !strNumberMGI.equals(""))
            {
                inMutationIdentifier.setMgiNumber(strNumberMGI);
                inEngineeredTransgene.setMutationIdentifier(inMutationIdentifier);
            }
        }

        // Gene Functions
        Set<GeneFunction> geneList = null;
        if (inEngineeredTransgene.getGeneFunctionCollection() != null)
        {
            geneList = inEngineeredTransgene.getGeneFunctionCollection();
            // clear out the list
            geneList.removeAll(geneList);
        }
        else
            geneList = new HashSet<GeneFunction>();

        // Seperate by commas
        String geneFunctions = inEngineeredTransgeneData.getGeneFunctions();
        StringTokenizer st2 = new StringTokenizer(geneFunctions, ",");
        while (st2.hasMoreTokens())
        {
            GeneFunction inGeneFunctions = new GeneFunction();
            inGeneFunctions.setFunction(st2.nextToken().trim());
            System.out.println("\tAdding GeneFunction:" + inGeneFunctions.getFunction());
            geneList.add(inGeneFunctions);
        }
        inEngineeredTransgene.setGeneFunctionCollection(geneList);

        if (inEngineeredTransgeneData.getConditionedBy() != null)
        {
            // Conditional, Conditional Description
            Conditionality theConditionality = null;
            if (inEngineeredTransgene.getConditionality() != null)
            {
                theConditionality = inEngineeredTransgene.getConditionality();
            }
            else
            {
                theConditionality = new Conditionality();
            }

            if (inEngineeredTransgeneData.getConditionedBy().equals(Constants.CONDITIONAL))
            {
                theConditionality.setConditionedBy("1");
            }
            else
            {
                theConditionality.setConditionedBy("0");
            }

            theConditionality.setDescription(inEngineeredTransgeneData.getDescription());
            inEngineeredTransgene.setConditionality(theConditionality);
        }
        else
        {
            inEngineeredTransgene.setConditionality(null);
        }

        // Additional Features (i.e. Comments)
        inEngineeredTransgene.setComments(inEngineeredTransgeneData.getComments());

        if (inEngineeredTransgene.getImage() != null)
        {
            Image image = inEngineeredTransgene.getImage();
            image.setTitle(inEngineeredTransgeneData.getTitle());
            image.setDescription(inEngineeredTransgeneData.getDescriptionOfConstruct());
            inEngineeredTransgene.setImage(image);
        }

        // Upload Construct File location, Title of Construct, Description of Construct
        // Check for exisiting Image for this GenomicSegment
        if (inEngineeredTransgeneData.getFileLocation() != null)
            if (inEngineeredTransgeneData.getFileLocation().getFileName() != null && !inEngineeredTransgeneData.getFileLocation().getFileName().equals(
                                                                                                                                                       ""))
            {

                ImageForm inImageData = new ImageForm();

                String inPath = request.getSession().getServletContext().getRealPath("/config/temp.jpg");

                inImageData.setDescriptionOfConstruct(inEngineeredTransgeneData.getDescriptionOfConstruct());
                inImageData.setTitle(inEngineeredTransgeneData.getTitle());
                inImageData.setFileServerLocation(inEngineeredTransgeneData.getFileServerLocation());
                inImageData.setFileLocation(inEngineeredTransgeneData.getFileLocation());

                Image image = ImageManagerSingleton.instance().create(new AnimalModel(), inImageData, inPath,
                                                                      Constants.CaImage.FTPGENCONSTORAGEDIRECTORY);

                inEngineeredTransgene.setImage(image);
            }

        log.info("Exiting populateEngineeredTransgene");
    }

    private void addRegulatoryElement(String inName,
                                      String inType,
                                      Transgene inEngineeredTransgene,
                                      String theSpeciesName,
                                      String theOtherSpeciesName,
                                      AnimalModel inAnimalModel) throws Exception
    {

        if (inName != null && inName.length() > 0)
        {

            RegulatoryElement theRegElement = new RegulatoryElement();
            RegulatoryElementType theRegType = RegulatoryElementTypeManagerSingleton.instance().getByType(inType);
            theRegElement.setRegulatoryElementType(theRegType);
            theRegElement.setName(inName);
            inEngineeredTransgene.addRegulatoryElement(theRegElement);

            // We need to create a taxon
            if (theSpeciesName != null && theSpeciesName.length() > 0)
            {

                Species theNewSpecies = SpeciesManagerSingleton.instance().getOrCreate(theSpeciesName);
                if (theSpeciesName.equals(Constants.Dropdowns.OTHER_OPTION))
                {
                    //Species is other, set 'other' in commonName field and send e-mail
                    theNewSpecies.setCommonName(Constants.Dropdowns.OTHER_OPTION);
                    sendEmail(inAnimalModel, theOtherSpeciesName, inType + " ScientificName");
                    theRegElement.setSpecies(theNewSpecies);
                }
                else
                {
                    theRegElement.setSpecies(theNewSpecies);
                }

            }
        }
    }

    private void sendEmail(AnimalModel inAnimalModel,
                           String theUncontrolledVocab,
                           String inType)
    {

        /*
         * Get the e-mail resource
         */
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
        // content with
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
