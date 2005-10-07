/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AnimalModel extends AbstractCancerModel {

    private static final long serialVersionUID = 4259665453799404851L;

    private String url;
    private Boolean isToolMouse;
    private List cellLineCollection = new ArrayList();
    private List spontaneousMutationCollection = new ArrayList();
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
     * @return Returns the engineeredGeneCollection.
     */
    public List getEngineeredGeneCollection() {
        return engineeredGeneCollection;
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
     * @return Returns the histopathologyCollection.
     */
    public List getDistinctNomenclatureFromEngineeredGeneCollection() {

        List theList = new ArrayList();
        for (int i = 0, j = engineeredGeneCollection.size(); i < j; i++) {
            EngineeredGene theEngineeredGene = (EngineeredGene) engineeredGeneCollection.get(i);

            String theNomenclature = theEngineeredGene.getGenotypeSummary().getNomenclature().getName();

            if (theNomenclature != null) {
                theNomenclature = theNomenclature.trim();

                if (!theList.contains(theNomenclature) && theNomenclature.length() > 0) {
                    theList.add(theNomenclature);
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

            String theGenotype = theEngineeredGene.getGenotypeSummary().getGenotype();

            if (theGenotype != null) {
                theGenotype = theGenotype.trim();

                if (!theList.contains(theGenotype) && theGenotype.length() > 0) {
                    theList.add(theGenotype);
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AnimalModel)) {
            return false;
        }
        AnimalModel rhs = (AnimalModel) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.spontaneousMutationCollection,
                rhs.spontaneousMutationCollection).append(this.phenotype, rhs.phenotype).append(
                this.animalAvailabilityCollection, rhs.animalAvailabilityCollection).append(this.repositoryInfo,
                rhs.repositoryInfo).append(this.geneDeliveryCollection, rhs.geneDeliveryCollection).append(
                this.histopathologyCollection, rhs.histopathologyCollection).append(this.therapyCollection,
                rhs.therapyCollection).append(this.cellLineCollection, rhs.cellLineCollection).append(
                this.imageCollection, rhs.imageCollection).append(this.isToolMouse, rhs.isToolMouse).append(this.url,
                rhs.url).append(this.engineeredGeneCollection, rhs.engineeredGeneCollection).append(
                this.xenograftCollection, rhs.xenograftCollection).append(this.environmentalFactorCollection,
                rhs.environmentalFactorCollection).append(this.microArrayDataCollection, rhs.microArrayDataCollection)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(795592963, -1409924013).appendSuper(super.hashCode()).append(
                this.spontaneousMutationCollection).append(this.phenotype).append(this.animalAvailabilityCollection)
                .append(this.repositoryInfo).append(this.geneDeliveryCollection).append(this.histopathologyCollection)
                .append(this.therapyCollection).append(this.cellLineCollection).append(this.imageCollection).append(
                        this.isToolMouse).append(this.url).append(this.engineeredGeneCollection).append(
                        this.xenograftCollection).append(this.environmentalFactorCollection).append(
                        this.microArrayDataCollection).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        
        System.out.println("In to string!!!!!");
        return "Snarf";
    }
}
