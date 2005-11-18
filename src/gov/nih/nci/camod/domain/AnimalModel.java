/*
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: AnimalModel.java,v 1.15 2005-11-18 15:20:21 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalModel extends AbstractCancerModel {

    private static final long serialVersionUID = 4259665453799404851L;

    private String url;
    private Boolean isToolMouse;
    private List cellLineCollection = new ArrayList();
    private List spontaneousMutationCollection = new ArrayList();
    private Set logCollection = new HashSet();
    private List imageCollection = new ArrayList();
    private List microArrayDataCollection = new ArrayList();
    private List xenograftCollection = new ArrayList();
    private List therapyCollection = new ArrayList();
    private List geneDeliveryCollection = new ArrayList();
    private List animalAvailabilityCollection = new ArrayList();
    private List environmentalFactorCollection = new ArrayList();
    private List histopathologyCollection = new ArrayList();
    private List engineeredGeneCollection = new ArrayList();
    private RepositoryInfo repositoryInfo;
    private Phenotype phenotype;

    /**
     * @return Returns the logCollection.
     */
    public Set getLogCollection() {
        return logCollection;
    }
        
    /**
     * @param logCollection
     *            The logCollection to set.
     */
    public void setLogCollection(Set logCollection) {
        this.logCollection = logCollection;
    }

    /**
     * @param log
     *            The log to add.
     */
    public void addLog(Log log) {
        logCollection.add(log);
    }

    
    /**
     * @return Returns the engineeredGeneCollection.
     */
    public List getEngineeredGeneCollection() {
        return engineeredGeneCollection;
    }

    public List getEngineeredGeneCollectionSorted() {
        if (engineeredGeneCollection != null)
            return new ArrayList(new TreeSet(engineeredGeneCollection));
        return null;
    }

    /**
     * @param engineeredGeneCollection
     *            The engineeredGeneCollection to set.
     */
    public void setEngineeredGeneCollection(List engineeredGeneCollection) {
        this.engineeredGeneCollection = engineeredGeneCollection;
    }

    /**
     * @param engineeredGene
     *            The engineeredGene to add.
     */
    public void addEngineeredGene(EngineeredGene engineeredGene) {
        engineeredGeneCollection.add(engineeredGene);
    }

    /**
     * @return Returns the spontaneousMutationCollection.
     */
    public List getSpontaneousMutationCollection() {
        return spontaneousMutationCollection;
    }

    public List getSpontaneousMutationCollectionSorted() {
        if (spontaneousMutationCollection != null)
            return new ArrayList(new TreeSet(spontaneousMutationCollection));
        return null;
    }

    /**
     * @param spontaneousMutationCollection
     *            The spontaneousMutationCollection to set.
     */
    public void setSpontaneousMutationCollection(List spontaneousMutationCollection) {
        this.spontaneousMutationCollection = spontaneousMutationCollection;
    }

    /**
     * @param spontaneousMutation
     *            The spontaneousMutation to add.
     */
    public void addSpontaneousMutation(SpontaneousMutation spontaneousMutation) {
        spontaneousMutationCollection.add(spontaneousMutation);
    }

    /**
     * @return Returns the histopathologyCollection.
     */
    public List getHistopathologyCollection() {
        return histopathologyCollection;
    }

    public List getHistopathologyCollectionSorted() {
        if (histopathologyCollection != null)
            return new ArrayList(new TreeSet(histopathologyCollection));
        return null;
    }

    /**
     * @return Returns the histopathologyCollection.
     */
    public List getDistinctOrgansFromHistopathologyCollection() {

        List theList = new ArrayList();
        for (int i = 0, j = histopathologyCollection.size(); i < j; i++) {
            Histopathology theHistopathology = (Histopathology) histopathologyCollection.get(i);

            if (!theList.contains(theHistopathology.getOrgan().getEVSPreferredDescription())) {
                theList.add(theHistopathology.getOrgan().getEVSPreferredDescription());
            }
        }

        Collections.sort(theList);

        return theList;
    }

    /**
     * @return Returns the unique organs.
     */
    public List getDistinctMetastatisOrgansFromHistopathologyCollection() {

        List theList = new ArrayList();
        for (int i = 0, j = histopathologyCollection.size(); i < j; i++) {
            Histopathology theHistopathology = (Histopathology) histopathologyCollection.get(i);

            List theMetastatisCollection = theHistopathology.getMetastatisCollection();

            for (int k = 0, l = theMetastatisCollection.size(); k < l; k++) {
                Histopathology theMetastatis = (Histopathology) theMetastatisCollection.get(k);

                String theMetastatisOrgan = theMetastatis.getOrgan().getEVSPreferredDescription();
                if (!theList.contains(theMetastatisOrgan)) {
                    theList.add(theMetastatisOrgan);
                }
            }
        }

        Collections.sort(theList);

        return theList;
    }

    /**
     * @return Returns the histopathologyCollection.
     */
    public List getDistinctNomenclatureFromEngineeredGeneCollection() {

        List theList = new ArrayList();
        for (int i = 0, j = engineeredGeneCollection.size(); i < j; i++) {
            EngineeredGene theEngineeredGene = (EngineeredGene) engineeredGeneCollection.get(i);

            if (theEngineeredGene.getGenotypeSummary() != null) {
                String theNomenclature = theEngineeredGene.getGenotypeSummary().getNomenclature().getName();

                if (theNomenclature != null) {
                    theNomenclature = theNomenclature.trim();

                    if (!theList.contains(theNomenclature) && theNomenclature.length() > 0) {
                        theList.add(theNomenclature);
                    }
                }
            }
        }

        Collections.sort(theList);

        return theList;
    }

    /**
     * @return Returns the histopathologyCollection.
     */
    public List getDistinctGenotypeFromEngineeredGeneCollection() {

        List theList = new ArrayList();
        for (int i = 0, j = engineeredGeneCollection.size(); i < j; i++) {
            EngineeredGene theEngineeredGene = (EngineeredGene) engineeredGeneCollection.get(i);

            if (theEngineeredGene.getGenotypeSummary() != null) {
                String theGenotype = theEngineeredGene.getGenotypeSummary().getSummary();

                if (theGenotype != null) {
                    theGenotype = theGenotype.trim();

                    if (!theList.contains(theGenotype) && theGenotype.length() > 0) {
                        theList.add(theGenotype);
                    }
                }
            }
        }

        Collections.sort(theList);

        return theList;
    }

    /**
     * @param histopathologyCollection
     *            The histopathologyCollection to set.
     */
    public void setHistopathologyCollection(List histopathologyCollection) {
        this.histopathologyCollection = histopathologyCollection;
    }

    /**
     * @param histopathology
     *            The histopathology to add.
     */
    public void addHistopathology(Histopathology histopathology) {
        histopathologyCollection.add(histopathology);
    }

    /**
     * @return Returns the environmentalFactorCollection.
     */
    public List getEnvironmentalFactorCollection() {
        return environmentalFactorCollection;
    }

    public List getEnvironmentalFactorCollectionSorted() {
        if (environmentalFactorCollection != null)
            return new ArrayList(new TreeSet(environmentalFactorCollection));
        return null;
    }

    /**
     * @param environmentalFactorCollection
     *            The environmentalFactorCollection to set.
     */
    public void setEnvironmentalFactorCollection(List environmentalFactorCollection) {
        this.environmentalFactorCollection = environmentalFactorCollection;
    }

    /**
     * @param environmentalFactor
     *            The environmentalFactor to add.
     */
    public void addEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
        environmentalFactorCollection.add(environmentalFactor);
    }

    /**
     * @return Returns the animalAvailabilityCollection.
     */
    public List getAnimalAvailabilityCollection() {
        return animalAvailabilityCollection;
    }

    public List getAnimalAvailabilityCollectionSorted() {
        if (animalAvailabilityCollection != null)
            return new ArrayList(new TreeSet(animalAvailabilityCollection));
        return null;
    }

    /**
     * @param animalAvailabilityCollection
     *            The animalAvailabilityCollection to set.
     */
    public void setAnimalAvailabilityCollection(List animalAvailabilityCollection) {
        this.animalAvailabilityCollection = animalAvailabilityCollection;
    }

    /**
     * @param animalAvailability
     *            The animalAvailability to add.
     */
    public void addAnimalAvailability(AnimalAvailability animalAvailability) {
        animalAvailabilityCollection.add(animalAvailability);
    }

    /**
     * @return Returns the geneDeliveryCollection.
     */
    public List getGeneDeliveryCollection() {
        return geneDeliveryCollection;
    }

    public List getGeneDeliveryCollectionSorted() {
        if (geneDeliveryCollection != null)
            return new ArrayList(new TreeSet(geneDeliveryCollection));
        return null;
    }

    /**
     * @param geneDeliveryCollection
     *            The geneDeliveryCollection to set.
     */
    public void setGeneDeliveryCollection(List geneDeliveryCollection) {
        this.geneDeliveryCollection = geneDeliveryCollection;
    }

    /**
     * @param geneDelivery
     *            The geneDelivery to add.
     */
    public void addGeneDelivery(GeneDelivery geneDelivery) {
        geneDeliveryCollection.add(geneDelivery);
    }

    /**
     * @return Returns the isToolMouse.
     */
    public Boolean getIsToolMouse() {
        return isToolMouse;
    }

    /**
     * @param isToolMouse
     *            The isToolMouse to set.
     */
    public void setIsToolMouse(Boolean isToolMouse) {
        this.isToolMouse = isToolMouse;
    }

    /**
     * @return Returns the phenotype.
     */
    public Phenotype getPhenotype() {
        return phenotype;
    }

    /**
     * @param phenotype
     *            The phenotype to set.
     */
    public void setPhenotype(Phenotype phenotype) {
        this.phenotype = phenotype;
    }

    /**
     * @return Returns the repositoryInfo.
     */
    public RepositoryInfo getRepositoryInfo() {
        return repositoryInfo;
    }

    /**
     * @param repositoryInfo
     *            The repositoryInfo to set.
     */
    public void setRepositoryInfo(RepositoryInfo repositoryInfo) {
        this.repositoryInfo = repositoryInfo;
    }

    /**
     * @return Returns the therapyCollection.
     */
    public List getTherapyCollection() {
        return therapyCollection;
    }

    public List getTherapyCollectionSorted() {
        if (therapyCollection != null)
            return new ArrayList(new TreeSet(therapyCollection));
        return null;
    }

    /**
     * @param therapyCollection
     *            The therapyCollection to set.
     */
    public void setTherapyCollection(List therapyCollection) {
        this.therapyCollection = therapyCollection;
    }

    /**
     * @param therapy
     *            The therapy to add.
     */
    public void addTherapy(Therapy therapy) {
        therapyCollection.add(therapy);
    }

    /**
     * @return Returns the xenograftCollection.
     */
    public List getXenograftCollection() {
        return xenograftCollection;
    }

    public List getXenograftCollectionSorted() {
        if (xenograftCollection != null)
            return new ArrayList(new TreeSet(xenograftCollection));
        return null;
    }

    /**
     * @param xenograftCollection
     *            The xenograftCollection to set.
     */
    public void setXenograftCollection(List xenograftCollection) {
        this.xenograftCollection = xenograftCollection;
    }

    public void addXenograft(Xenograft xenograft) {
        xenograftCollection.add(xenograft);
    }

    /**
     * @return Returns the microArrayDataCollection.
     */
    public List getMicroArrayDataCollection() {
        return microArrayDataCollection;
    }

    public List getMicroArrayDataCollectionSorted() {
        if (microArrayDataCollection != null)
            return new ArrayList(new TreeSet(microArrayDataCollection));
        return null;
    }

    /**
     * @param microArrayDataCollection
     *            The microArrayDataCollection to set.
     */
    public void setMicroArrayDataCollection(List microArrayDataCollection) {
        this.microArrayDataCollection = microArrayDataCollection;
    }

    /**
     * @param microArrayData
     *            The microArrayData to add.
     */
    public void addMicroArrayData(MicroArrayData microArrayData) {
        microArrayDataCollection.add(microArrayData);
    }

    /**
     * @return Returns the imageCollection.
     */
    public List getImageCollection() {
        return imageCollection;
    }

    public List getImageCollectionSorted() {
        if (imageCollection != null)
            return new ArrayList(new TreeSet(imageCollection));
        return null;
    }

    /**
     * @param imageCollection
     *            The imageCollection to set.
     */
    public void setImageCollection(List imageCollection) {
        this.imageCollection = imageCollection;
    }

    /**
     * @param image
     *            The image to add.
     */
    public void addImage(Image image) {
        imageCollection.add(image);
    }

    /**
     * @return Returns the cellLineCollection.
     */
    public List getCellLineCollection() {
        return cellLineCollection;
    }

    public List getCellLineCollectionSorted() {
        if (cellLineCollection != null)
            return new ArrayList(new TreeSet(cellLineCollection));
        return null;
    }

    /**
     * @param cellLineCollection
     *            The cellLineCollection to set.
     */
    public void setCellLineCollection(List cellLineCollection) {
        this.cellLineCollection = cellLineCollection;
    }

    /**
     * @param cellLine
     *            The cellLine to add.
     */
    public void addCellLine(CellLine cellLine) {
        cellLineCollection.add(cellLine);
    }

    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String result = super.toString() + " - ";
        result += this.getUrl();
        return result;
    }

    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }

}
