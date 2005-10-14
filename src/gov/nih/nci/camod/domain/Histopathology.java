/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Histopathology extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259275453799404851L;
    
    private String comments;
    private String grossDescription;
    private String relationalOperation;
    private Float tumorIncidenceRate;
    private String survivalInfo;
    private String ageOfOnset;
    private String microscopicDescription;
    private Float weightOfTumor;
    private Float volumeOfTumor;
    private String comparativeData;
    private List clinicalMarkerCollection = new ArrayList();
    private List diseaseCollection = new ArrayList();
    private GeneticAlteration geneticAlteration;
    private Organ organ;
    private List metastatisCollection;

    /**
     * @return Returns the metastatisCollection.
     */
    public List getMetastatisCollection() {
        return metastatisCollection;
    }

    /**
     * @param metastatisCollection
     *            The metastatisCollection to set.
     */
    public void setMetastatisCollection(List metastatisCollection) {
        this.metastatisCollection = metastatisCollection;
    }

    /**
     * @param histopathology
     *            The histopathology to add.
     */
    public void addHistopathology(Histopathology histopathology) {
        metastatisCollection.add(histopathology);
    }

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan() {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    /**
     * @return Returns the geneticAlteration.
     */
    public GeneticAlteration getGeneticAlteration() {
        return geneticAlteration;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to set.
     */
    public void setGeneticAlteration(GeneticAlteration geneticAlteration) {
        this.geneticAlteration = geneticAlteration;
    }

    /**
     * @return Returns the diseaseCollection.
     */
    public List getDiseaseCollection() {
        return diseaseCollection;
    }

    /**
     * @param diseaseCollection
     *            The diseaseCollection to set.
     */
    public void setDiseaseCollection(List diseaseCollection) {
        this.diseaseCollection = diseaseCollection;
    }

    /**
     * @param disease
     *            The disease to add.
     */
    public void addDisease(Disease disease) {
        disease.getHistopathologyCollection().add(this);
        diseaseCollection.add(disease);
    }

    /**
     * @return Returns the clinicalMarkerCollection.
     */
    public List getClinicalMarkerCollection() {
        return clinicalMarkerCollection;
    }

    /**
     * @param clinicalMarkerCollection
     *            The clinicalMarkerCollection to set.
     */
    public void setClinicalMarkerCollection(List clinicalMarkerCollection) {
        this.clinicalMarkerCollection = clinicalMarkerCollection;
    }

    /**
     * @param clinicalMarker
     *            The clinicalMarker to add.
     */
    public void addClinicalMarker(ClinicalMarker clinicalMarker) {
        clinicalMarkerCollection.add(clinicalMarker);
    }

    /**
     * @return Returns the ageOfOnset.
     */
    public String getAgeOfOnset() {
        return ageOfOnset;
    }

    /**
     * @param ageOfOnset
     *            The ageOfOnset to set.
     */
    public void setAgeOfOnset(String ageOfOnset) {
        this.ageOfOnset = ageOfOnset;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the comparativeData.
     */
    public String getComparativeData() {
        return comparativeData;
    }

    /**
     * @param comparativeData
     *            The comparativeData to set.
     */
    public void setComparativeData(String comparativeData) {
        this.comparativeData = comparativeData;
    }

    /**
     * @return Returns the grossDescription.
     */
    public String getGrossDescription() {
        return grossDescription;
    }

    /**
     * @param grossDescription
     *            The grossDescription to set.
     */
    public void setGrossDescription(String grossDescription) {
        this.grossDescription = grossDescription;
    }
  

    /**
     * @return Returns the microscopicDescription.
     */
    public String getMicroscopicDescription() {
        return microscopicDescription;
    }

    /**
     * @param microscopicDescription
     *            The microscopicDescription to set.
     */
    public void setMicroscopicDescription(String microscopicDescription) {
        this.microscopicDescription = microscopicDescription;
    }

    /**
     * @return Returns the relationalOperation.
     */
    public String getRelationalOperation() {
        return relationalOperation;
    }

    /**
     * @param relationalOperation
     *            The relationalOperation to set.
     */
    public void setRelationalOperation(String relationalOperation) {
        this.relationalOperation = relationalOperation;
    }

    /**
     * @return Returns the survivalInfo.
     */
    public String getSurvivalInfo() {
        return survivalInfo;
    }

    /**
     * @param survivalInfo
     *            The survivalInfo to set.
     */
    public void setSurvivalInfo(String survivalInfo) {
        this.survivalInfo = survivalInfo;
    }

    /**
     * @return Returns the tumorIncidenceRate.
     */
    public Float getTumorIncidenceRate() {
        return tumorIncidenceRate;
    }

    /**
     * @param tumorIncidenceRate
     *            The tumorIncidenceRate to set.
     */
    public void setTumorIncidenceRate(Float tumorIncidenceRate) {
        this.tumorIncidenceRate = tumorIncidenceRate;
    }

    /**
     * @return Returns the volumeOfTumor.
     */
    public Float getVolumeOfTumor() {
        return volumeOfTumor;
    }

    /**
     * @param volumeOfTumor
     *            The volumeOfTumor to set.
     */
    public void setVolumeOfTumor(Float volumeOfTumor) {
        this.volumeOfTumor = volumeOfTumor;
    }

    /**
     * @return Returns the weightOfTumor.
     */
    public Float getWeightOfTumor() {
        return weightOfTumor;
    }

    /**
     * @param weightOfTumor
     *            The weightOfTumor to set.
     */
    public void setWeightOfTumor(Float weightOfTumor) {
        this.weightOfTumor = weightOfTumor;
    }

  
    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getGrossDescription() + " - " + this.getComments();
       return result;
     }  
     
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
     
}
