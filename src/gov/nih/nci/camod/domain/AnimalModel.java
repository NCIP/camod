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
      Collections.sort(engineeredGeneCollection);
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
      Collections.sort(spontaneousMutationCollection);
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
      Collections.sort(spontaneousMutationCollection);
      return spontaneousMutationCollection;               
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

            String theGenotype = theEngineeredGene.getGenotypeSummary().getSummary();

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
        Collections.sort(environmentalFactorCollection);      
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
      Collections.sort(animalAvailabilityCollection);    
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
      Collections.sort(geneDeliveryCollection);    
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
      Collections.sort(therapyCollection);    
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
      Collections.sort(xenograftCollection);    
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
      Collections.sort(microArrayDataCollection);    
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
      Collections.sort(imageCollection);    
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
      Collections.sort(cellLineCollection);    
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
     * @see java.lang.Object#toString()
     */    
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getUrl();                
      return result;
    }         
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }

}
