package gov.nih.nci.camod.service.impl;

//import gov.nih.nci.camod.domain.EngineeredTransgene;
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
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;
import gov.nih.nci.camod.webapp.form.ImageForm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

//import org.apache.struts.upload.FormFile;

public class EngineeredTransgeneManagerImpl extends BaseManager implements EngineeredTransgeneManager {

    public List getAll() throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.getAll");
        return super.getAll(EngineeredGene.class);
    }

    public Transgene get(String id) throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.get");
        return (Transgene) super.get(id, Transgene.class);
    }

    public void save(Transgene engineeredGene) throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.save");
        super.save(engineeredGene);
    }

    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.remove");
        inAnimalModel.getEngineeredGeneCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public Transgene create(EngineeredTransgeneData inEngineeredTransgeneData, HttpServletRequest request)
            throws Exception {

        log.trace("Entering EngineeredTransgeneManagerImpl.create");

        Transgene inEngineeredTransgene = new Transgene();
        populateEngineeredTransgene(inEngineeredTransgeneData, inEngineeredTransgene, request);

        log.trace("Exiting EngineeredTransgeneManagerImpl.create");

        return inEngineeredTransgene;
    }

    public void update(EngineeredTransgeneData inEngineeredTransgeneData, Transgene inEngineeredTransgene,
            HttpServletRequest request) throws Exception {

        log.trace("Entering EngineeredTransgeneManagerImpl.update");
        log.debug("Updating EngineeredTransgeneForm: " + inEngineeredTransgene.getId());

        // Populate w/ the new values and save
        populateEngineeredTransgene(inEngineeredTransgeneData, inEngineeredTransgene, request);
        save(inEngineeredTransgene);

        log.trace("Exiting EngineeredTransgeneManagerImpl.update");
    }

    public void createAssocExpression(AssociatedExpressionData inAssociatedExpressionData,
            EngineeredGene inEngineeredTransgene) throws Exception {

        log.trace("Entering EngineeredTransgeneManagerImpl.createAssocExpression");

        populateAssocExpression(inAssociatedExpressionData, inEngineeredTransgene);

        log.trace("Exiting EngineeredTransgeneManagerImpl.createAssocExpression");
    }

    public void updateAssociatedExpression(AssociatedExpressionData inAssociatedExpressionData,
            EngineeredGene inEngineeredTransgene) throws Exception {

        log.trace("Entering EngineeredTransgeneManagerImpl.updateAssociatedExpression");

        List expFeatureList = inEngineeredTransgene.getExpressionFeatureCollection();

        for (int i = 0; i < expFeatureList.size(); i++) {
            ExpressionFeature expFeature = (ExpressionFeature) expFeatureList.get(i);

            if (expFeature.getId().toString().equals(inAssociatedExpressionData.getEngineeredGeneID())) {

                String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inAssociatedExpressionData
                        .getOrganTissueCode());
                Organ organ = expFeature.getOrgan();
                organ.setName(preferedOrganName);
                organ.setConceptCode(inAssociatedExpressionData.getOrganTissueCode());

                ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName(
                        inAssociatedExpressionData.getExpressionLevel());

                expFeature.setExpressionLevelDesc(expLevelDesc);
                expFeature.setOrgan(organ);
            }
        }

        save(inEngineeredTransgene);

        log.trace("Entering EngineeredTransgeneManagerImpl.updateAssociatedExpression");
    }

    private void populateAssocExpression(AssociatedExpressionData inAssociatedExpressionData,
            EngineeredGene inEngineeredTransgene) throws Exception {

        log.trace("Entering populateAssocExpression");

        ExpressionFeature expFeature = new ExpressionFeature();

        String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inAssociatedExpressionData
                .getOrganTissueCode());
        expFeature.setOrgan(new Organ());
        expFeature.getOrgan().setName(preferedOrganName);
        expFeature.getOrgan().setConceptCode(inAssociatedExpressionData.getOrganTissueCode());

        ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName(
                inAssociatedExpressionData.getExpressionLevel());

        expFeature.setExpressionLevelDesc(expLevelDesc);
        inEngineeredTransgene.addExpressionFeature(expFeature);
        log.trace("Exiting populateAssocExpression");
    }

    private void populateEngineeredTransgene(EngineeredTransgeneData inEngineeredTransgeneData,
            Transgene inEngineeredTransgene, HttpServletRequest request) throws Exception {

        log.trace("Entering populateEngineeredTransgene");

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        AnimalModel theAnimalModel = AnimalModelManagerSingleton.instance().get(theModelId);

        // Transgene Integration
        if (inEngineeredTransgeneData.getLocationOfIntegration().equals("Targeted")) {
            inEngineeredTransgene.setLocationOfIntegration(inEngineeredTransgeneData.getOtherLocationOfIntegration());
        } else {
            inEngineeredTransgene.setLocationOfIntegration(inEngineeredTransgeneData.getLocationOfIntegration());
        }

        // Transgene (coding sequence only)
        inEngineeredTransgene.setName(inEngineeredTransgeneData.getName());

        // Did we add a taxon?
        inEngineeredTransgene.getTaxonCollection().clear();
        if (inEngineeredTransgeneData.getScientificName() != null && inEngineeredTransgeneData.getScientificName().length() > 0) {
            // Create/reuse the taxon
            Taxon theTaxon = null;
            if (!inEngineeredTransgeneData.getScientificName().equals(Constants.Dropdowns.OTHER_OPTION)) {
                theTaxon = TaxonManagerSingleton.instance().getOrCreate(inEngineeredTransgeneData.getScientificName(),
                        null, null);
            } else {
                theTaxon = TaxonManagerSingleton.instance().getOrCreate(
                        inEngineeredTransgeneData.getOtherScientificName(), null, null);
            }

            inEngineeredTransgene.addTaxon(theTaxon);

            if (theTaxon.getEthnicityStrainUnctrlVocab() != null) {

                log.info("Sending Notification eMail - new ScientificName added");
                sendEmail(theAnimalModel, inEngineeredTransgeneData.getOtherScientificName(),
                        "Transgene ScientificName");
            }
        }

        inEngineeredTransgene.getRegulatoryElementCollection().clear();

        // Transcriptional 1
        addRegulatoryElement(inEngineeredTransgeneData.getTranscriptional1_name(), "Transcriptional 1",
                inEngineeredTransgene, inEngineeredTransgeneData.getTranscriptional1_species(),
                inEngineeredTransgeneData.getTranscriptional1_otherSpecies(), theAnimalModel);

        // Transcriptional 2
        addRegulatoryElement(inEngineeredTransgeneData.getTranscriptional2_name(), "Transcriptional 2",
                inEngineeredTransgene, inEngineeredTransgeneData.getTranscriptional2_species(),
                inEngineeredTransgeneData.getTranscriptional2_otherSpecies(), theAnimalModel);

        // Transcriptional 3
        addRegulatoryElement(inEngineeredTransgeneData.getTranscriptional3_name(), "Transcriptional 3",
                inEngineeredTransgene, inEngineeredTransgeneData.getTranscriptional3_species(),
                inEngineeredTransgeneData.getTranscriptional3_otherSpecies(), theAnimalModel);

        // Poly A Signal
        addRegulatoryElement(inEngineeredTransgeneData.getPolyASignal_name(), "Poly A Signal", inEngineeredTransgene,
                inEngineeredTransgeneData.getPolyASignal_species(), inEngineeredTransgeneData
                        .getPolyASignal_otherSpecies(), theAnimalModel);

        // Splice Site
        addRegulatoryElement(inEngineeredTransgeneData.getSpliceSites_name(), "Splice Site", inEngineeredTransgene,
                inEngineeredTransgeneData.getSpliceSites_species(), inEngineeredTransgeneData
                        .getSpliceSites_otherSpecies(), theAnimalModel);

        // MGI Number
        // Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if (inEngineeredTransgene.getMutationIdentifier() != null)
            inMutationIdentifier = inEngineeredTransgene.getMutationIdentifier();
        else
            inMutationIdentifier = new MutationIdentifier();

        if (inEngineeredTransgeneData.getNumberMGI() == null || inEngineeredTransgeneData.getNumberMGI().equals("")) {
            inEngineeredTransgene.setMutationIdentifier(null);
        } else {
            String strNumberMGI = inEngineeredTransgeneData.getNumberMGI().trim();
            Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
            Matcher m = p.matcher(strNumberMGI);
            if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
                inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
                inEngineeredTransgene.setMutationIdentifier(inMutationIdentifier);
            }
        }

        // Gene Functions
        Set geneList = null;
        if (inEngineeredTransgene.getGeneFunctionCollection() != null) {
            geneList = inEngineeredTransgene.getGeneFunctionCollection();
            // clear out the list
            geneList.removeAll(geneList);
        } else
            geneList = new HashSet();

        // Seperate by commas
        String geneFunctions = inEngineeredTransgeneData.getGeneFunctions();
        StringTokenizer st2 = new StringTokenizer(geneFunctions, ",");
        while (st2.hasMoreTokens()) {
            GeneFunction inGeneFunctions = new GeneFunction();
            inGeneFunctions.setFunction(st2.nextToken().trim());
            System.out.println("\tAdding GeneFunction:" + inGeneFunctions.getFunction());
            geneList.add(inGeneFunctions);
        }
        inEngineeredTransgene.setGeneFunctionCollection(geneList);

        if (inEngineeredTransgeneData.getConditionedBy() != null) {
            // Conditional, Conditional Description
            Conditionality theConditionality = null;
            if (inEngineeredTransgene.getConditionality() != null) {
                theConditionality = inEngineeredTransgene.getConditionality();
            } else {
                theConditionality = new Conditionality();
            }

            if (inEngineeredTransgeneData.getConditionedBy().equals(Constants.CONDITIONAL)) {
                theConditionality.setConditionedBy("1");
            } else {
                theConditionality.setConditionedBy("0");
            }

            theConditionality.setDescription(inEngineeredTransgeneData.getDescription());
            inEngineeredTransgene.setConditionality(theConditionality);
        } else {
            inEngineeredTransgene.setConditionality(null);
        }

        // Additional Features (i.e. Comments)
        inEngineeredTransgene.setComments(inEngineeredTransgeneData.getComments());

        if (inEngineeredTransgene.getImage() != null) {
            Image image = inEngineeredTransgene.getImage();
            image.setTitle(inEngineeredTransgeneData.getTitle());
            image.setDescription(inEngineeredTransgeneData.getDescriptionOfConstruct());
            inEngineeredTransgene.setImage(image);
        }

        // Upload Construct File location, Title of Construct, Description of
        // Construct
        // Check for exisiting Image for this GenomicSegment
        if (inEngineeredTransgeneData.getFileLocation().getFileName() != null
                && !inEngineeredTransgeneData.getFileLocation().getFileName().equals("")) {

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

        log.trace("Exiting populateEngineeredTransgene");
    }

    private void addRegulatoryElement(String inName, String inType, Transgene inEngineeredTransgene, String theSpecies,
            String theOtherSpecies, AnimalModel inAnimalModel) throws Exception {

        if (inName != null && inName.length() > 0) {

            RegulatoryElement theRegElement = new RegulatoryElement();
            RegulatoryElementType theRegType = RegulatoryElementTypeManagerSingleton.instance().getByType(inType);
            theRegElement.setRegulatoryElementType(theRegType);
            theRegElement.setName(inName);
            inEngineeredTransgene.addRegulatoryElement(theRegElement);

            // We need to create a taxon
            if (theSpecies != null && theSpecies.length() > 0) {

                Taxon theTaxon = null;
                if (theSpecies.equals(Constants.Dropdowns.OTHER_OPTION)) {
                    theTaxon = TaxonManagerSingleton.instance().getOrCreate(theOtherSpecies, null, null);
                } else {
                    theTaxon = TaxonManagerSingleton.instance().getOrCreate(theSpecies, null, null);
                }

                theRegElement.setTaxon(theTaxon);

                if (theTaxon.getEthnicityStrainUnctrlVocab() != null) {

                    log.info("Sending Notification eMail - new " + inType + " ScientificName added");
                    sendEmail(inAnimalModel, theOtherSpecies, inType + " ScientificName");
                }
            }
        }
    }

    private void sendEmail(AnimalModel inAnimalModel, String theUncontrolledVocab, String inType) {

        /*
         * Get the e-mail resource
         */
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
        values.put("type", inType);
        values.put("value", theUncontrolledVocab);
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
    }
}
