/**
 * @author dgeorge
 * 
 * $Id: AnimalModelSearchResult.java,v 1.11 2006-04-28 19:05:47 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.9  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.8  2005/11/28 18:02:10  georgeda
 * Defect #182.  Get unique set of organs and only display metas. next to the originating organ
 *
 * Revision 1.7  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.6  2005/11/15 19:10:23  schroedn
 * Defect #49
 *
 * Fixed misspelling of Metastasis in getMetastatisSites
 *
 * Revision 1.5  2005/10/17 18:19:28  georgeda
 * Added ability to sort
 *
 * Revision 1.4  2005/10/17 13:27:54  georgeda
 * Updates
 *
 * Revision 1.3  2005/10/12 18:18:48  georgeda
 * Small fix
 *
 * Revision 1.2  2005/10/12 18:09:29  georgeda
 * Update for metastatis organs
 *
 * Revision 1.1  2005/10/07 16:27:50  georgeda
 * Implemented paganation
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.AnimalModelManagerSingleton;

import java.util.*;

/**
 * Used as wrapper around animal model for speedy display during paganation.
 */
public class AnimalModelSearchResult implements Comparable
{

    private String myAnimalModelId;
    private String myTumorSites = null;
    private String mySpecies = null;
    private String myModelDescriptor = null;
    private String mySubmitterName = null;
    private String mySubmittedDate = null;
    private AnimalModel myAnimalModel = null;
    private String myStrain = null;    
    private String myModelId = null;
    
    private String myPrincipalInvestigator = null;
    private String myGender = null;
    private String myTransgene = null;
    private String myTranscriptional1 = null;
    private String mySegmentType = null;
    private String myDesignator = null;
    private String myTargetedGeneLocus = null;
    private String myTypeOfModification = null;
    private String myNameOfInducingAgent = null;
    private String myGeneName = null;
    private String myCarcinogen = null;
    
    private String myChemical = null;
    private String myEnvironmentalFactor = null;
    private String myViralVector = null;
    
    private String myGene = null;
    private String myGrowthFactor = null;
    
    private String myHormone = null;
    private String myNutritionalFactor = null;
    private String myRadiation = null;
    private String myVirus = null;
    
    private String myMicroarray = null;
    private String myYearOfPublication = null;
    private String myJournal = null;
    private String myPMIDNumber = null;
    private String myPublications = null;
    private String mySiteOfLesionTumor = null;
    private String myDiagnosis = null;
    private String myAgeOfOnset = null;
    private String myTumorIncidenceOverLifetime = null;
    private String mySiteAndDiagnosisOfMetastasis = null;
    private String myDrugCompoundName = null;
    private String myNameOfCellLine = null;
    private String myOrganTissue = null;
    private String myImageTitle = null;
    private String myDistributor = null;
    private String myCellLine = null;
    private String myDonorSpecies = null;
    private String myGraftType = null;
    /**
     * Create the wraper object
     * 
     * @param inAnimalModel
     *            the animal model we will be wrapping. Saves only the id.
     */
    public AnimalModelSearchResult(AnimalModel inAnimalModel)
    {
        myAnimalModelId = inAnimalModel.getId().toString();
        myModelDescriptor = inAnimalModel.getModelDescriptor();
    }

    /**
     * Return the id for the associated model
     * 
     * @return the id for the model
     */
    public String getId()
    {

        return myAnimalModelId;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model descriptor for the associated model
     * 
     * @throws Exception
     */
    public String getModelDescriptor() throws Exception
    {
        return myModelDescriptor;
    }

    public String getModelId() throws Exception
    {
        if (myModelId == null)
        {
            fetchAnimalModel();
            myModelId = myAnimalModel.getId().toString();
        }
        return myModelId;        
    }
    
    public String getPrincipalInvestigator() throws Exception
    {
        if ( myPrincipalInvestigator == null )
        {
            fetchAnimalModel();
            
            String theEmailAddress = "";
            
            if ( myAnimalModel.getPrincipalInvestigator() != null )
            {
                Set contactInfoList = myAnimalModel.getPrincipalInvestigator().getContactInfoCollection();          
                Iterator contactInfoIter = contactInfoList.iterator();                                  
                
                while ( contactInfoIter.hasNext() ) {
                    ContactInfo contact = (ContactInfo) contactInfoIter.next();                        
                    theEmailAddress = contact.getEmail();
                }
                        
                if ( theEmailAddress.length() > 0 ) {
                    myPrincipalInvestigator = "<a href=\"mailto:" + theEmailAddress + "\"/>" + myAnimalModel.getPrincipalInvestigator().getDisplayName();
                }            
            }
        }
        return myPrincipalInvestigator;
    }
    
    public String getGender() throws Exception 
    {
        if ( myGender == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getPhenotype().getSexDistribution() != null )
                myGender = myAnimalModel.getPhenotype().getSexDistribution().getType();
        }
        return myGender;
    }
    
    public String getTransgene() throws Exception 
    {
        if ( myTransgene == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getPhenotype().getSexDistribution() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTransgene = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if( eg instanceof Transgene){
                        Transgene t = (Transgene) eg;
                        myTransgene += t.getName() + "<br>";  
                    }
                }
            }
        }
        return myTransgene;
    }
    
    public String getTranscriptional1() throws Exception 
    {
        if ( myTranscriptional1 == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getEngineeredGeneCollection() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTranscriptional1 = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();    
                    if( eg instanceof Transgene){
                        Transgene t = (Transgene) eg;

                        Set<RegulatoryElement> regElementList = t.getRegulatoryElementCollection();
                        Iterator<RegulatoryElement> regListIter =  regElementList.iterator();                    
                        
                        while ( regListIter.hasNext() ){
                            RegulatoryElement re = (RegulatoryElement) regListIter.next();
                            if ( re.getRegulatoryElementType().getName().equals("Transcriptional 1") ) {
                                myTranscriptional1 += re.getName() + "<br>";
                            }
                        }                    
                    }

                }
            }
        }
        return myTranscriptional1;
    }
    
    public String getSegmentType() throws Exception 
    {
        if ( mySegmentType == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getEngineeredGeneCollection() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                mySegmentType = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if( eg instanceof GenomicSegment){
                        GenomicSegment g = (GenomicSegment) eg;
                         
                            if( g.getSegmentType().getNameUnctrlVocab() == null || g.getSegmentType().getNameUnctrlVocab().equals( "" ) ) {
                                mySegmentType += g.getSegmentType().getName() + "<br>";
                            } else {
                                mySegmentType += g.getSegmentType().getNameUnctrlVocab() + "<br>";                                        
                            }
                    }
                }
            }
        }
        return mySegmentType;
    }
    
    public String getDesignator() throws Exception 
    {
        if ( myDesignator == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getEngineeredGeneCollection() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myDesignator = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if( eg instanceof GenomicSegment){
                        GenomicSegment g = (GenomicSegment) eg;
                        myDesignator += g.getCloneDesignator() + "<br>";   
                    }
                }
            }
        }
        return myDesignator;
    }

    public String getTargetedGeneLocus() throws Exception 
    {
        if ( myTargetedGeneLocus == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getEngineeredGeneCollection() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTargetedGeneLocus = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if( eg instanceof TargetedModification){
                        TargetedModification tm = (TargetedModification) eg;
                        myTargetedGeneLocus += tm.getName() + "<br>";   
                    }
                }
            }
        }
        return myTargetedGeneLocus;
    }
    
    public String getTypeOfModification() throws Exception 
    {
        if ( myTypeOfModification == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getEngineeredGeneCollection() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myTypeOfModification = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if( eg instanceof TargetedModification){
                        TargetedModification tm = (TargetedModification) eg;
                        myTypeOfModification += tm.getModificationTypeCollection() + "<br>";   
                        
                        Set<ModificationType> modTypeList = tm.getModificationTypeCollection();
                        Iterator<ModificationType> modIter =  modTypeList.iterator();                    
                        
                        while ( modIter.hasNext() ){
                            ModificationType mt = (ModificationType) modIter.next();
                            
                            if ( mt.getNameUnctrlVocab() == null || mt.getNameUnctrlVocab().equals( "" )) {
                                myTypeOfModification += mt.getName() + "<br>";
                            } else {
                                myTypeOfModification += mt.getNameUnctrlVocab() + "<br>";                                     
                            }                                
                        }         
                    }
                }
            }
        }
        return myTypeOfModification;
    } 
    
    public String getNameOfInducingAgent() throws Exception 
    {
        if ( myNameOfInducingAgent == null )
        {
            fetchAnimalModel();
            if ( myAnimalModel.getEngineeredGeneCollection() != null ) {
                Set <EngineeredGene> engineeredList = myAnimalModel.getEngineeredGeneCollection();
                Iterator<EngineeredGene> engineeredListIter = engineeredList.iterator();
                myNameOfInducingAgent = "";
                
                while ( engineeredListIter.hasNext() ){
                    EngineeredGene eg = (EngineeredGene) engineeredListIter.next();
                    if( eg instanceof InducedMutation){
                        InducedMutation im = (InducedMutation) eg;
                        if( im.getEnvironmentalFactor().getName() != null )
                        {
                            myNameOfInducingAgent += im.getEnvironmentalFactor().getName() + "<br>";
                        } else {
                            myNameOfInducingAgent += im.getEnvironmentalFactor().getNameUnctrlVocab() + "<br>";
                        }                            
                    }
                }
            }
        }
        return myNameOfInducingAgent;
    }

    public String getGeneName() throws Exception 
    {
        if ( myGeneName == null )
        {
            fetchAnimalModel();
            
            Set <SpontaneousMutation> smSet = myAnimalModel.getSpontaneousMutationCollection();
            Iterator<SpontaneousMutation> smIter = smSet.iterator();
            myGeneName = "";
            
            while (smIter.hasNext()) {
                SpontaneousMutation sm = (SpontaneousMutation) smIter.next();                
                myGeneName += sm.getName() + "<br>";
            }
        }
        return myGeneName;
    }
    
    public String getChemical() throws Exception 
    {
        if ( myChemical == null )
        {
            fetchAnimalModel();
            
            Set <CarcinogenExposure> ceSet = myAnimalModel.getCarcinogenExposureCollection();
            Iterator<CarcinogenExposure> ceIter = ceSet.iterator();
            myChemical = "";
            
            while (ceIter.hasNext()) {
                CarcinogenExposure ce = (CarcinogenExposure) ceIter.next();
                EnvironmentalFactor ef = ce.getEnvironmentalFactor();
                
                if( ef != null )
                {
                    if (ef.getType().equals("Chemical / Drug"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Chemical/Drug)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Chemical/Drug)<br>";
                    }
                    if (ef.getType().equals("Hormone"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Hormone)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Hormone)<br>";
                    }
                    if (ef.getType().equals("Growth Factor"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Growth Factor)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Growth Factor)<br>";
                    }
                    if (ef.getType().equals("Viral"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Viral)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Viral)<br>";
                    }
                    if (ef.getType().equals("Environment"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Environment)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Environment)<br>";
                    }
                    if (ef.getType().equals("Nutrition"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Nutrition)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Nutrition)<br>";
                    }
                    if (ef.getType().equals("Other"))
                    {
                        if( ef.getName() != null )
                            myChemical += ef.getName() + " (Other)<br>";
                        else
                            myChemical += ef.getNameUnctrlVocab() + " (Other)<br>";
                    }
                }
            }
        }
        return myChemical;
    }
    
    public String getCarcinogen() throws Exception
    {
        if ( myCarcinogen == null )
        {
            fetchAnimalModel();
            
            Set <CarcinogenExposure> ceSet = myAnimalModel.getCarcinogenExposureCollection();
            Iterator<CarcinogenExposure> ceIter = ceSet.iterator();
            myCarcinogen = "";
            
            while (ceIter.hasNext()) {
                CarcinogenExposure ce = (CarcinogenExposure) ceIter.next();
                EnvironmentalFactor ef = ce.getEnvironmentalFactor();
                
                if( ef != null )
                {
                    if (ef.getType().equals("Chemical / Drug"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Chemical/Drug)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Chemical/Drug)<br>";
                    }
                    if (ef.getType().equals("Hormone"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Hormone)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Hormone)<br>";
                    }
                    if (ef.getType().equals("Growth Factor"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Growth Factor)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Growth Factor)<br>";
                    }
                    if (ef.getType().equals("Viral"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Viral)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Viral)<br>";
                    }
                    if (ef.getType().equals("Environment"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Environment)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Environment)<br>";
                    }
                    if (ef.getType().equals("Nutrition"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Nutrition)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Nutrition)<br>";
                    }
                    if (ef.getType().equals("Virus"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Virus)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Virus)<br>";
                    }                    
                    if (ef.getType().equals("Other"))
                    {
                        if( ef.getName() != null )
                            myCarcinogen += ef.getName() + " (Other)<br>";
                        else
                            myCarcinogen += ef.getNameUnctrlVocab() + " (Other)<br>";
                    }
                }
            }
        }
        return myCarcinogen;
    }
    
    public String getEnvironmentalFactor() throws Exception 
    {
        if ( myEnvironmentalFactor == null )
        {
            fetchAnimalModel();
        }
        return myEnvironmentalFactor;
    }
    
    public String getViralVector() throws Exception 
    {
        if ( myViralVector == null )
        {
            fetchAnimalModel();
            // Print the list of GeneDelivery viralVectors for the Gene Delivery
            // (Cardiogenic Intervention) Section
            Set <GeneDelivery>geneDeliverySet = myAnimalModel.getGeneDeliveryCollection();
            Iterator <GeneDelivery> geneDeliveryIter = geneDeliverySet.iterator();
            myViralVector = "";
            
            while (geneDeliveryIter.hasNext())
            {
                GeneDelivery geneDelivery = (GeneDelivery) geneDeliveryIter.next();
                
                if ( geneDelivery.getViralVector() != null )
                    myViralVector += geneDelivery.getViralVector() + "<br>";
                else
                    myViralVector += geneDelivery.getViralVectorUnctrlVocab() + "<br>";
            }

        }
        return myViralVector;
    }
    
    public String getGene() throws Exception 
    {
        if ( myGene == null )
        {
            fetchAnimalModel();

            Set <GeneDelivery>geneDeliverySet = myAnimalModel.getGeneDeliveryCollection();
            Iterator <GeneDelivery> geneDeliveryIter = geneDeliverySet.iterator();
            myGene = "";
            
            while (geneDeliveryIter.hasNext())
            {
                GeneDelivery geneDelivery = (GeneDelivery) geneDeliveryIter.next();
                myGene += geneDelivery.getDisplayName() + "<br>";
                
            }
        }
        return myGene;
    }
    
    public String getGrowthFactor() throws Exception 
    {
        if ( myGrowthFactor == null )
        {
            fetchAnimalModel();
        }
        return myGrowthFactor;
    }
    
    public String getHormone() throws Exception 
    {
        if ( myHormone == null )
        {
            fetchAnimalModel();
        }
        return myHormone;
    }
    
    public String getNutritionalFactor() throws Exception 
    {
        if ( myNutritionalFactor == null )
        {
            fetchAnimalModel();
        }
        return myNutritionalFactor;
    }
    
    public String getRadiation() throws Exception 
    {
        if ( myRadiation == null )
        {
            fetchAnimalModel();
        }
        return myRadiation;
    }
    
    public String getVirus() throws Exception 
    {
        if ( myVirus == null )
        {
            fetchAnimalModel();
        }
        return myVirus;
    }
    
    public String getYearOfPublication() throws Exception 
    {
        if ( myYearOfPublication == null )
        {
            fetchAnimalModel();
            
            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myYearOfPublication = "";
            
            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myYearOfPublication += pub.getYear() + "<br>";                
            }            
        }
        return myYearOfPublication;
    }
    
    public String getJournal() throws Exception 
    {
        if ( myJournal == null )
        {
            fetchAnimalModel();
            
            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myJournal = "";
            
            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myJournal += pub.getJournal() + "<br>";                
            }  
        }
        return myJournal;
    }
    
    public String getPMIDNumber() throws Exception 
    {
        if ( myPMIDNumber == null )
        {
            fetchAnimalModel();
            
            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myPMIDNumber = "";
            
            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myPMIDNumber += "<a target=\"_pubmed\" href=\" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=" + pub.getPmid() + "\">" + pub.getPmid() + "</a><br>";                
            }  
        }
        return myPMIDNumber;
    }

    public String getPublications() throws Exception 
    {
        if ( myPublications == null )
        {
            fetchAnimalModel();
            
            Set pubSet = myAnimalModel.getPublicationCollection();
            Iterator pubSetIter = pubSet.iterator();
            myPublications = "";
            
            while (pubSetIter.hasNext())
            {
                Publication pub = (Publication) pubSetIter.next();
                myPublications += pub.getJournal() + "(" + pub.getYear() +") " + "<a target=\"_pubmed\" href=\" http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?cmd=retrieve&db=pubmed&dopt=abstract&list_uids=" + pub.getPmid() + "\">" + pub.getPmid() + "</a><br>";                
            }  
        }
        return myPublications;
    }
    
    public String getSiteOfLesionTumor() throws Exception 
    {
        if ( mySiteOfLesionTumor == null )
        {
            fetchAnimalModel();
                        
            Set <Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator <Histopathology> hisSetIter = hisSet.iterator();
            mySiteOfLesionTumor = "";
            
            while ( hisSetIter.hasNext() )
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                mySiteOfLesionTumor += his.getOrgan().getName() + "<br>";                
            }              
        }
        return mySiteOfLesionTumor;
    }
    
    public String getDiagnosis() throws Exception 
    {
        if ( myDiagnosis == null )
        {
            fetchAnimalModel();
            
            Set <Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator <Histopathology> hisSetIter = hisSet.iterator();
            myDiagnosis = "";
            
            while ( hisSetIter.hasNext() )
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                myDiagnosis += his.getDisease().getName() + "<br>";                
            }              
        }
        return myDiagnosis;
    }
    
    public String getAgeOfOnset() throws Exception 
    {
        if ( myAgeOfOnset == null )
        {
            fetchAnimalModel();
            Set <Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator <Histopathology> hisSetIter = hisSet.iterator();
            myAgeOfOnset = "";
            
            while ( hisSetIter.hasNext() )
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                if (  his.getAgeOfOnset() != null )
                    myAgeOfOnset += his.getAgeOfOnset();
                if( his.getAgeOfOnsetUnit() != null )
                    myAgeOfOnset += " " + his.getAgeOfOnsetUnit();
                
                myAgeOfOnset += "<br>";
            }             
        }
        return myAgeOfOnset;
    }
    
    public String getTumorIncidenceOverLifetime() throws Exception 
    {
        if ( myTumorIncidenceOverLifetime == null )
        {
            fetchAnimalModel();
            Set <Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator <Histopathology> hisSetIter = hisSet.iterator();
            myTumorIncidenceOverLifetime = "";
            
            while ( hisSetIter.hasNext() )
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                if (  his.getTumorIncidenceRate() != null )
                    myTumorIncidenceOverLifetime += his.getTumorIncidenceRate() + "<br>";                
            }               
        }
        return myTumorIncidenceOverLifetime;
    }
    
    public String getSiteAndDiagnosisOfMetastasis() throws Exception 
    {
        if ( mySiteAndDiagnosisOfMetastasis == null )
        {
            fetchAnimalModel();
            Set <Histopathology> hisSet = myAnimalModel.getHistopathologyCollection();
            Iterator <Histopathology> hisSetIter = hisSet.iterator();
            mySiteAndDiagnosisOfMetastasis = "";
            
            while ( hisSetIter.hasNext() )
            {
                Histopathology his = (Histopathology) hisSetIter.next();
                Set<Histopathology> metSet = his.getMetastasisCollection();
                Iterator<Histopathology> metSetIter =  metSet.iterator();                    
                
                while ( metSetIter.hasNext() ){
                    Histopathology met = (Histopathology) metSetIter.next();
                    mySiteAndDiagnosisOfMetastasis += met.getDisease().getName() + " (" + met.getOrgan().getName() + ")" + "<br>";
                }
            }             
        }
        return mySiteAndDiagnosisOfMetastasis;
    }
    
    public String getDrugCompoundName() throws Exception 
    {
        if ( myDrugCompoundName == null )
        {
            fetchAnimalModel();
            Set <Therapy> set = myAnimalModel.getTherapyCollection();
            Iterator <Therapy> setIter = set.iterator();
            myDrugCompoundName = "";
            
            while ( setIter.hasNext() )
            {
                Therapy it = (Therapy) setIter.next();
                if (  it.getTreatment() != null ) {
                     if ( it.getTreatment().getAdministrativeRoute() != null )   
                        myDrugCompoundName += it.getTreatment().getAdministrativeRoute() + "<br>";
                     else
                        myDrugCompoundName += it.getTreatment().getAdminRouteUnctrlVocab() + "<br>";
                }
            }   
        }
        return myDrugCompoundName;
    }
    
    public String getNameOfCellLine() throws Exception 
    {
        if ( myNameOfCellLine == null )
        {
            fetchAnimalModel();
            Set <CellLine> set = myAnimalModel.getCellLineCollection();
            Iterator <CellLine> setIter = set.iterator();
            myNameOfCellLine = "";
            
            while ( setIter.hasNext() )
            {
                CellLine it = (CellLine) setIter.next();
                if (  it.getName() != null )
                    myNameOfCellLine += it.getName() + "<br>";
            }   
        }
        return myNameOfCellLine;
    }    
    
    public String getOrganTissue() throws Exception 
    {
        if ( myOrganTissue == null )
        {
            fetchAnimalModel();
            Set <CellLine> set = myAnimalModel.getCellLineCollection();
            Iterator <CellLine> setIter = set.iterator();
            myOrganTissue = "";
            
            while ( setIter.hasNext() )
            {
                CellLine it = (CellLine) setIter.next();
                if (  it.getOrgan() != null )
                    myOrganTissue += it.getOrgan().getName() + "<br>";
            }   
        }
        return myOrganTissue;
    }    
    
    public String getImageTitle() throws Exception 
    {
        if ( myImageTitle == null )
        {
            fetchAnimalModel();
            Set <Image> set = myAnimalModel.getImageCollection();
            Iterator <Image> setIter = set.iterator();
            myImageTitle = "";
            
            while ( setIter.hasNext() )
            {
                Image it = (Image) setIter.next();
                
                myImageTitle += "<IMG src=\"/camod/images/image.gif\"> ";
                if (  it.getTitle() != null )
                    myImageTitle += it.getTitle();
                
                myImageTitle += "<br>";
            }              
        }
        return myImageTitle;
    }    
    
    public String getDistributor() throws Exception 
    {
        if ( myDistributor == null )
        {
            fetchAnimalModel();
            Set <AnimalAvailability> set = myAnimalModel.getAnimalAvailabilityCollection();
            Iterator <AnimalAvailability> setIter = set.iterator();
            myDistributor = "";
            
            while ( setIter.hasNext() )
            {
                AnimalAvailability it = (AnimalAvailability) setIter.next();                
                if (  it.getAnimalDistributor() != null )
                    myDistributor += it.getAnimalDistributor().getName() + "<br>";                
            }                  
        }
        return myDistributor;
    }    
    
    public String getCellLine() throws Exception 
    {
        if ( myCellLine == null )
        {
            fetchAnimalModel();
            Set <Xenograft> set = myAnimalModel.getXenograftCollection();
            Iterator <Xenograft> setIter = set.iterator();
            myCellLine = "";
            
            while ( setIter.hasNext() )
            {
                Xenograft it = (Xenograft) setIter.next();                
                if (  it.getParentalCellLineName() != null )
                    myCellLine += it.getParentalCellLineName() + "<br>";                
            }                 
        }
        return myCellLine;
    }    
    
    public String getDonorSpecies() throws Exception 
    {
        if ( myDonorSpecies == null )
        {
            fetchAnimalModel();
            Set <Xenograft> set = myAnimalModel.getXenograftCollection();
            Iterator <Xenograft> setIter = set.iterator();
            myDonorSpecies = "";
            
            while ( setIter.hasNext() )
            {
                Xenograft it = (Xenograft) setIter.next();                
                if (  it.getDonorSpecies() != null )
                    if ( it.getDonorSpecies().getCommonName() != null )
                        myDonorSpecies += it.getDonorSpecies().getCommonName() + "<br>";
                    else
                        myDonorSpecies += it.getDonorSpecies().getCommonNameUnctrlVocab() + "<br>";
            }               
        }
        return myDonorSpecies;
    }   
    
    public String getGraftType() throws Exception 
    {
        if ( myGraftType == null )
        {
            fetchAnimalModel();
            Set <Xenograft> set = myAnimalModel.getXenograftCollection();
            Iterator <Xenograft> setIter = set.iterator();
            myGraftType = "";
            
            while ( setIter.hasNext() )
            {
                Xenograft it = (Xenograft) setIter.next();                
                if (  it.getGraftType() != null )
                    myGraftType += it.getGraftType() + "<br>";
                
                if ( it.getGraftTypeUnctrlVocab() != null )
                    myGraftType += it.getGraftTypeUnctrlVocab() + "<br>";
            }               
        }
        return myGraftType;
    }    
    
    public String getMicroarray() throws Exception 
    {
        if ( myMicroarray == null )
        {
            fetchAnimalModel();
            Set <MicroArrayData> set = myAnimalModel.getMicroArrayDataCollection();
            Iterator <MicroArrayData> setIter = set.iterator();
            myMicroarray = "";
            
            while ( setIter.hasNext() )
            {
                MicroArrayData it = (MicroArrayData) setIter.next(); 
                myMicroarray += "<IMG src=\"/camod/images/table_multiple.png\"> ";
                if (  it.getExperimentName() != null )
                    myMicroarray += it.getExperimentName() + "<br>";
                
            }               
        }
        return myMicroarray;
    }    
    
    /**
     * Return the species. It will fetch the animal model from the DB if it
     * hasn't already happened.
     * 
     * @return the species for the associated model
     * @throws Exception
     */
    public String getSpecies() throws Exception
    {        
        if (mySpecies == null)
        {
            fetchAnimalModel();
            mySpecies = myAnimalModel.getStrain().getSpecies().getScientificName();
        }
        return mySpecies;
    }

    /**
     * Return the strain. It will fetch the animal model from the DB if it
     * hasn't already happened.
     * 
     * @return the strain for the associated model
     * @throws Exception
     */
    public String getStrain() throws Exception
    {        
        if (myStrain == null)
        {
            fetchAnimalModel();
            myStrain = myAnimalModel.getStrain().getName();
        }
        return myStrain;
    }
    
    /**
     * Return the list of tumor sites. It will fetch the animal model from the
     * DB if it hasn't already happened.
     * 
     * @return the list of tumor sites for the associated model
     * @throws Exception
     */
    public String getTumorSites() throws Exception
    {

        Set<String> theOrgans = new TreeSet<String>();
        Hashtable<String, TreeSet<String>> theMetas = new Hashtable<String, TreeSet<String>>();

        if (myTumorSites == null)
        {
            fetchAnimalModel();

            myTumorSites = "";
            Set<Histopathology> theHistopathologySet = myAnimalModel.getHistopathologyCollection();
            Iterator it = theHistopathologySet.iterator();

            while (it.hasNext())
            {
                Histopathology theHistopathology = (Histopathology) it.next();
                String theOrgan = theHistopathology.getOrgan().getEVSPreferredDescription();

                if (!theOrgans.contains(theOrgan))
                {
                    theOrgans.add(theOrgan);
                    theMetas.put(theOrgan, new TreeSet<String>());
                }

                TreeSet<String> theMetaSet = theMetas.get(theOrgan);
                Set theMetastasisSet = theHistopathology.getMetastasisCollection();
                it = theMetastasisSet.iterator();

                while(it.hasNext()){
                    Histopathology theMetastasis = (Histopathology)it.next();
                    String theMetaOrgan = theMetastasis.getOrgan().getEVSPreferredDescription();
                    if (!theMetaSet.contains(theMetaOrgan))
                    {
                        theMetaSet.add(theMetaOrgan);
                    }
                }                

            }

        }


        Iterator theOrganIterator = theOrgans.iterator();

        while (theOrganIterator.hasNext())
        {

            String theOrgan = (String) theOrganIterator.next();

            myTumorSites += "<b>" + theOrgan + "</b><br/>";

            TreeSet theMetaSet = (TreeSet) theMetas.get(theOrgan);

            Iterator theMetaIterator = theMetaSet.iterator();

            while (theMetaIterator.hasNext())
            {
                String theMetaOrgan = (String) theMetaIterator.next();

                myTumorSites += theMetaOrgan + " (Metastasis)<br>";
            }

        }

        return myTumorSites;
    }

    /**
     * Gets the display name of the submitter in an html linked format
     * 
     * @return the display name of the submitter
     * @throws Exception
     */
    public String getSubmitterName() throws Exception
    {

        if (mySubmitterName == null)
        {
            fetchAnimalModel();

            String theEmailAddress = null;
            
            if (myAnimalModel.getSubmitter() != null ) 
            {
                Set contactInfoList = myAnimalModel.getSubmitter().getContactInfoCollection();          
                Iterator contactInfoIter = contactInfoList.iterator();                       
                
                while ( contactInfoIter.hasNext() ){
                    ContactInfo contact = (ContactInfo) contactInfoIter.next();                        
                    theEmailAddress = contact.getEmail();
                }            
            }
            
            if (theEmailAddress.length() > 0)
            {
                mySubmitterName = "<a href=\"mailto:" + theEmailAddress + "\"/>" + myAnimalModel.getSubmitter().getDisplayName();
            }
            else
            {
                mySubmitterName = myAnimalModel.getSubmitter().getDisplayName();
            }
        }
        return mySubmitterName;
    }

    /**
     * Gets the date for which the model was submitted
     * 
     * @return the date the model was submitted
     * @throws Exception
     */
    public String getSubmittedDate() throws Exception
    {

        if (mySubmittedDate == null)
        {
            fetchAnimalModel();

            mySubmittedDate = myAnimalModel.getAvailability().getEnteredDate().toString();
        }
        return mySubmittedDate;
    }

    public int compareTo(Object inObject)
    {

        AnimalModelSearchResult theResult = (AnimalModelSearchResult) inObject;

        return this.myModelDescriptor.compareTo(theResult.myModelDescriptor);
    }

    // Fetch the animal model from the DB
    private void fetchAnimalModel() throws Exception
    {
        if (myAnimalModel == null)
        {
            myAnimalModel = AnimalModelManagerSingleton.instance().get(myAnimalModelId);
        }
    }
}
