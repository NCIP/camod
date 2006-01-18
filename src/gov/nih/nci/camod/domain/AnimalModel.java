/*
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2005/11/28 18:02:10  georgeda
 * Defect #182.  Get unique set of organs and only display metas. next to the originating organ
 *
 * Revision 1.15  2005/11/18 15:20:21  georgeda
 * Defect #120, gracefully handle the deletion of models.
 *
 * Revision 1.14  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: AnimalModel.java,v 1.17 2006-01-18 14:23:31 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalModel extends AbstractCancerModel {

    private static final long serialVersionUID = 4259665453799404851L;

    private String url;
    private Boolean isToolMouse;
    private List<CellLine> cellLineCollection = new ArrayList<CellLine>();
    private List<SpontaneousMutation> spontaneousMutationCollection = new ArrayList<SpontaneousMutation>();
    private Set<Log> logCollection = new HashSet<Log>();
    private List<Image> imageCollection = new ArrayList<Image>();
    private List<MicroArrayData> microArrayDataCollection = new ArrayList<MicroArrayData>();
    private List<Xenograft> xenograftCollection = new ArrayList<Xenograft>();
    private List<Therapy> therapyCollection = new ArrayList<Therapy>();
    private List<GeneDelivery> geneDeliveryCollection = new ArrayList<GeneDelivery>();
    private List<AnimalAvailability> animalAvailabilityCollection = new ArrayList<AnimalAvailability>();
    private List<EnvironmentalFactor> environmentalFactorCollection = new ArrayList<EnvironmentalFactor>();
    private List<Histopathology> histopathologyCollection = new ArrayList<Histopathology>();
    private List<EngineeredGene> engineeredGeneCollection = new ArrayList<EngineeredGene>();
    private RepositoryInfo repositoryInfo;
    private Phenotype phenotype;

    /**
     * @return Returns the logCollection.
     */
    public Set<Log> getLogCollection() {
        return logCollection;
    }
        
    /**
     * @param logCollection
     *            The logCollection to set.
     */
    public void setLogCollection(Set<Log> logCollection) {
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
    public List<EngineeredGene> getEngineeredGeneCollection() {
        return engineeredGeneCollection;
    }

    public List getEngineeredGeneCollectionSorted() {
        if (engineeredGeneCollection != null)
            return new ArrayList<EngineeredGene>(new TreeSet<EngineeredGene>(engineeredGeneCollection));
        return null;
    }

    /**
     * @param engineeredGeneCollection
     *            The engineeredGeneCollection to set.
     */
    public void setEngineeredGeneCollection(List<EngineeredGene> engineeredGeneCollection) {
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
    public List<SpontaneousMutation> getSpontaneousMutationCollection() {
        return spontaneousMutationCollection;
    }

    public List<SpontaneousMutation> getSpontaneousMutationCollectionSorted() {
        if (spontaneousMutationCollection != null)
            return new ArrayList<SpontaneousMutation>(new TreeSet<SpontaneousMutation>(spontaneousMutationCollection));
        return null;
    }

    /**
     * @param spontaneousMutationCollection
     *            The spontaneousMutationCollection to set.
     */
    public void setSpontaneousMutationCollection(List<SpontaneousMutation> spontaneousMutationCollection) {
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
    public List<Histopathology> getHistopathologyCollection() {
        return histopathologyCollection;
    }

    public List<Histopathology> getHistopathologyCollectionSorted() {
        if (histopathologyCollection != null)
            return new ArrayList<Histopathology>(new TreeSet<Histopathology>(histopathologyCollection));
        return null;
    }

    /**
     * @return Returns the histopathologyCollection.
     */
    public List<String> getDistinctNomenclatureFromEngineeredGeneCollection() {

        List<String> theList = new ArrayList<String>();
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
    public List<String> getDistinctGenotypeFromEngineeredGeneCollection() {

        List<String> theList = new ArrayList<String>();
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
    public void setHistopathologyCollection(List<Histopathology> histopathologyCollection) {
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
    public List<EnvironmentalFactor> getEnvironmentalFactorCollection() {
        return environmentalFactorCollection;
    }

    public List<EnvironmentalFactor> getEnvironmentalFactorCollectionSorted() {
        if (environmentalFactorCollection != null)
            return new ArrayList<EnvironmentalFactor>(new TreeSet<EnvironmentalFactor>(environmentalFactorCollection));
        return null;
    }

    /**
     * @param environmentalFactorCollection
     *            The environmentalFactorCollection to set.
     */
    public void setEnvironmentalFactorCollection(List<EnvironmentalFactor> environmentalFactorCollection) {
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
    public List<AnimalAvailability> getAnimalAvailabilityCollection() {
        return animalAvailabilityCollection;
    }

    public List<AnimalAvailability> getAnimalAvailabilityCollectionSorted() {
        if (animalAvailabilityCollection != null)
            return new ArrayList<AnimalAvailability>(new TreeSet<AnimalAvailability>(animalAvailabilityCollection));
        return null;
    }

    /**
     * @param animalAvailabilityCollection
     *            The animalAvailabilityCollection to set.
     */
    public void setAnimalAvailabilityCollection(List<AnimalAvailability> animalAvailabilityCollection) {
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
    public List<GeneDelivery> getGeneDeliveryCollection() {
        return geneDeliveryCollection;
    }

    public List<GeneDelivery> getGeneDeliveryCollectionSorted() {
        if (geneDeliveryCollection != null)
            return new ArrayList<GeneDelivery>(new TreeSet<GeneDelivery>(geneDeliveryCollection));
        return null;
    }

    /**
     * @param geneDeliveryCollection
     *            The geneDeliveryCollection to set.
     */
    public void setGeneDeliveryCollection(List<GeneDelivery> geneDeliveryCollection) {
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
    public List<Therapy> getTherapyCollection() {
        return therapyCollection;
    }

    public List<Therapy> getTherapyCollectionSorted() {
        if (therapyCollection != null)
            return new ArrayList<Therapy>(new TreeSet<Therapy>(therapyCollection));
        return null;
    }

    /**
     * @param therapyCollection
     *            The therapyCollection to set.
     */
    public void setTherapyCollection(List<Therapy> therapyCollection) {
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
    public List<Xenograft> getXenograftCollection() {
        return xenograftCollection;
    }

    public List<Xenograft> getXenograftCollectionSorted() {
        if (xenograftCollection != null)
            return new ArrayList<Xenograft>(new TreeSet<Xenograft>(xenograftCollection));
        return null;
    }

    /**
     * @param xenograftCollection
     *            The xenograftCollection to set.
     */
    public void setXenograftCollection(List<Xenograft> xenograftCollection) {
        this.xenograftCollection = xenograftCollection;
    }

    public void addXenograft(Xenograft xenograft) {
        xenograftCollection.add(xenograft);
    }

    /**
     * @return Returns the microArrayDataCollection.
     */
    public List<MicroArrayData> getMicroArrayDataCollection() {
        return microArrayDataCollection;
    }

    public List<MicroArrayData> getMicroArrayDataCollectionSorted() {
        if (microArrayDataCollection != null)
            return new ArrayList<MicroArrayData>(new TreeSet<MicroArrayData>(microArrayDataCollection));
        return null;
    }

    /**
     * @param microArrayDataCollection
     *            The microArrayDataCollection to set.
     */
    public void setMicroArrayDataCollection(List<MicroArrayData> microArrayDataCollection) {
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
    public List<Image> getImageCollection() {
        return imageCollection;
    }

    public List<Image> getImageCollectionSorted() {
        if (imageCollection != null)
            return new ArrayList<Image>(new TreeSet<Image>(imageCollection));
        return null;
    }

    /**
     * @param imageCollection
     *            The imageCollection to set.
     */
    public void setImageCollection(List<Image> imageCollection) {
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
    public List<CellLine> getCellLineCollection() {
        return cellLineCollection;
    }

    public List<CellLine> getCellLineCollectionSorted() {
        if (cellLineCollection != null)
            return new ArrayList<CellLine>(new TreeSet<CellLine>(cellLineCollection));
        return null;
    }

    /**
     * @param cellLineCollection
     *            The cellLineCollection to set.
     */
    public void setCellLineCollection(List<CellLine> cellLineCollection) {
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
