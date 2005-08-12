/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Histopathology extends BaseObject implements Serializable {
	private Long id;
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
	 * @param metastatisCollection The metastatisCollection to set.
	 */
	public void setMetastatisCollection(List metastatisCollection) {
		this.metastatisCollection = metastatisCollection;
	}
	/**
	 * @param histopathology The histopathology to add.
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
	 * @param organ The organ to set.
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
	 * @param geneticAlteration The geneticAlteration to set.
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
	 * @param diseaseCollection The diseaseCollection to set.
	 */
	public void setDiseaseCollection(List diseaseCollection) {
		this.diseaseCollection = diseaseCollection;
	}
	/**
	 * @param disease The disease to add.
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
	 * @param clinicalMarkerCollection The clinicalMarkerCollection to set.
	 */
	public void setClinicalMarkerCollection(List clinicalMarkerCollection) {
		this.clinicalMarkerCollection = clinicalMarkerCollection;
	}
	/**
	 * @param clinicalMarker The clinicalMarker to add.
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
	 * @param ageOfOnset The ageOfOnset to set.
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
	 * @param comments The comments to set.
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
	 * @param comparativeData The comparativeData to set.
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
	 * @param grossDescription The grossDescription to set.
	 */
	public void setGrossDescription(String grossDescription) {
		this.grossDescription = grossDescription;
	}
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the microscopicDescription.
	 */
	public String getMicroscopicDescription() {
		return microscopicDescription;
	}
	/**
	 * @param microscopicDescription The microscopicDescription to set.
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
	 * @param relationalOperation The relationalOperation to set.
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
	 * @param survivalInfo The survivalInfo to set.
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
	 * @param tumorIncidenceRate The tumorIncidenceRate to set.
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
	 * @param volumeOfTumor The volumeOfTumor to set.
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
	 * @param weightOfTumor The weightOfTumor to set.
	 */
	public void setWeightOfTumor(Float weightOfTumor) {
		this.weightOfTumor = weightOfTumor;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Histopathology)) {
			return false;
		}
		Histopathology rhs = (Histopathology) object;
		return new EqualsBuilder().append(
				this.diseaseCollection, rhs.diseaseCollection).append(
				this.comments, rhs.comments).append(this.metastatisCollection,
				rhs.metastatisCollection).append(this.grossDescription,
				rhs.grossDescription).append(this.relationalOperation,
				rhs.relationalOperation).append(this.volumeOfTumor,
				rhs.volumeOfTumor).append(this.comparativeData,
				rhs.comparativeData).append(this.tumorIncidenceRate,
				rhs.tumorIncidenceRate).append(this.geneticAlteration,
				rhs.geneticAlteration).append(this.clinicalMarkerCollection,
				rhs.clinicalMarkerCollection).append(this.weightOfTumor,
				rhs.weightOfTumor).append(this.survivalInfo, rhs.survivalInfo)
				.append(this.organ, rhs.organ).append(this.ageOfOnset,
						rhs.ageOfOnset).append(this.microscopicDescription,
						rhs.microscopicDescription).append(this.id, rhs.id)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1967347847, 453124975).append(this.diseaseCollection).append(
				this.comments).append(this.metastatisCollection).append(
				this.grossDescription).append(this.relationalOperation).append(
				this.volumeOfTumor).append(this.comparativeData).append(
				this.tumorIncidenceRate).append(this.geneticAlteration).append(
				this.clinicalMarkerCollection).append(this.weightOfTumor)
				.append(this.survivalInfo).append(this.organ).append(
						this.ageOfOnset).append(this.microscopicDescription)
				.append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("weightOfTumor",
				this.weightOfTumor).append("id", this.id).append(
				"geneticAlteration", this.geneticAlteration).append(
				"relationalOperation", this.relationalOperation).append(
				"survivalInfo", this.survivalInfo).append("comparativeData",
				this.comparativeData).append("grossDescription",
				this.grossDescription).append("ageOfOnset", this.ageOfOnset)
				.append("volumeOfTumor", this.volumeOfTumor).append(
						"diseaseCollection", this.diseaseCollection).append(
						"comments", this.comments).append("tumorIncidenceRate",
						this.tumorIncidenceRate).append("metastatisCollection",
						this.metastatisCollection).append("organ", this.organ)
				.append("clinicalMarkerCollection",
						this.clinicalMarkerCollection).append(
						"microscopicDescription", this.microscopicDescription)
				.toString();
	}
}
