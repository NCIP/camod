/*
 * Created on May 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.*;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Xenograft extends AbstractCancerModel {
    
    private static final long serialVersionUID = 3257445453799404851L;
    
	private String administrativeSite;
	private String geneticManipulation;
	private String modificationDescription;
	private String parentalCellLineName;
	private String name;
	private String atccNumber;
	private String cellAmount;
	private Date harvestDate;
	private String graftType;
	private String graftTypeUnctrlVocab;
	private TumorCode tumorCode;
	private List invivoResultCollection = new ArrayList();
	private Taxon originSpecies;
	private Taxon hostSpecies;
	private Organ organ;
	
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
	 * @return Returns the hostSpecies.
	 */
	public Taxon getHostSpecies() {
		return hostSpecies;
	}
	/**
	 * @param hostSpecies The hostSpecies to set.
	 */
	public void setHostSpecies(Taxon hostSpecies) {
		this.hostSpecies = hostSpecies;
	}
	/**
	 * @return Returns the originSpecies.
	 */
	public Taxon getOriginSpecies() {
		return originSpecies;
	}
	/**
	 * @param originSpecies The originSpecies to set.
	 */
	public void setOriginSpecies(Taxon originSpecies) {
		this.originSpecies = originSpecies;
	}
	/**
	 * @return Returns the atccNumber.
	 */
	public String getAtccNumber() {
		return atccNumber;
	}
	/**
	 * @param atccNumber The atccNumber to set.
	 */
	public void setAtccNumber(String atccNumber) {
		this.atccNumber = atccNumber;
	}
	/**
	 * @return Returns the cellAmount.
	 */
	public String getCellAmount() {
		return cellAmount;
	}
	/**
	 * @param cellAmount The cellAmount to set.
	 */
	public void setCellAmount(String cellAmount) {
		this.cellAmount = cellAmount;
	}
	/**
	 * @return Returns the graftType.
	 */
	public String getGraftType() {
		return graftType;
	}
	/**
	 * @param graftType The graftType to set.
	 */
	public void setGraftType(String graftType) {
		this.graftType = graftType;
	}
	/**
	 * @return Returns the graftTypeUnctrlVocab.
	 */
	public String getGraftTypeUnctrlVocab() {
		return graftTypeUnctrlVocab;
	}
	/**
	 * @param graftTypeUnctrlVocab The graftTypeUnctrlVocab to set.
	 */
	public void setGraftTypeUnctrlVocab(String graftTypeUnctrlVocab) {
		this.graftTypeUnctrlVocab = graftTypeUnctrlVocab;
	}	
	/**
	 * @return Returns the harvestDate.
	 */
	public Date getHarvestDate() {
		return harvestDate;
	}
	/**
	 * @param harvestDate The harvestDate to set.
	 */
	public void setHarvestDate(Date harvestDate) {
		this.harvestDate = harvestDate;
	}
	/**
	 * @return Returns the invivoResultCollection.
	 */
	public List getInvivoResultCollection() {
		return invivoResultCollection;
	}
	/**
	 * @param invivoResultCollection The invivoResultCollection to set.
	 */
	public void setInvivoResultCollection(List invivoResultCollection) {
		this.invivoResultCollection = invivoResultCollection;
	}
	public void addInvivoResult(InvivoResult invivoResult) {
		invivoResultCollection.add(invivoResult);
	}	
	/**
	 * @return Returns the administrativeSite.
	 */
	public String getAdministrativeSite() {
		return administrativeSite;
	}
	/**
	 * @param administrativeSite The administrativeSite to set.
	 */
	public void setAdministrativeSite(String administrativeSite) {
		this.administrativeSite = administrativeSite;
	}
	/**
	 * @return Returns the geneticManipulation.
	 */
	public String getGeneticManipulation() {
		return geneticManipulation;
	}
	/**
	 * @param geneticManipulation The geneticManipulation to set.
	 */
	public void setGeneticManipulation(String geneticManipulation) {
		this.geneticManipulation = geneticManipulation;
	}	
	/**
	 * @return Returns the modificationDescription.
	 */
	public String getModificationDescription() {
		return modificationDescription;
	}
	/**
	 * @param modificationDescription The modificationDescription to set.
	 */
	public void setModificationDescription(String modificationDescription) {
		this.modificationDescription = modificationDescription;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the parentalCellLineName.
	 */
	public String getParentalCellLineName() {
		return parentalCellLineName;
	}
	/**
	 * @param parentalCellLineName The parentalCellLineName to set.
	 */
	public void setParentalCellLineName(String parentalCellLineName) {
		this.parentalCellLineName = parentalCellLineName;
	}
	/**
	 * @return Returns the tumorCode.
	 */
	public TumorCode getTumorCode() {
		return tumorCode;
	}
	/**
	 * @param tumorCode The tumorCode to set.
	 */
	public void setTumorCode(TumorCode tumorCode) {
		this.tumorCode = tumorCode;
	}
	/**  
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Xenograft)) {
			return false;
		}
		Xenograft rhs = (Xenograft) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.originSpecies, rhs.originSpecies).append(this.tumorCode,
				rhs.tumorCode).append(this.invivoResultCollection,
				rhs.invivoResultCollection).append(this.graftType,
				rhs.graftType).append(this.graftTypeUnctrlVocab, rhs.graftTypeUnctrlVocab)
				.append(this.geneticManipulation, rhs.geneticManipulation)
				.append(this.administrativeSite, rhs.administrativeSite)
				.append(this.cellAmount, rhs.cellAmount)
				.append(this.atccNumber, rhs.atccNumber).append(
						this.modificationDescription,
						rhs.modificationDescription).append(
						this.parentalCellLineName, rhs.parentalCellLineName)
				.append(this.organ, rhs.organ).append(this.harvestDate,
						rhs.harvestDate).append(this.name, rhs.name).append(
						this.hostSpecies, rhs.hostSpecies).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-2123253831, 472004439).appendSuper(
				super.hashCode()).append(this.originSpecies).append(
				this.tumorCode).append(this.invivoResultCollection).append(
				this.graftType).append(this.geneticManipulation).append(
				this.administrativeSite).append(this.cellAmount).append(
				this.atccNumber).append(this.modificationDescription).append(
				this.parentalCellLineName).append(this.organ)
				.append(this.graftTypeUnctrlVocab).append(
				this.harvestDate).append(this.name).append(this.hostSpecies)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.getId())
				.append("experimentDesign", this.getExperimentDesign())
				.append("geneticManipulation", this.geneticManipulation)
				.append("invivoResultCollection", this.invivoResultCollection)
				.append("hostSpecies", this.hostSpecies)
				.append("principalInvestigator",
						this.getPrincipalInvestigator())
				.append("atccNumber", this.atccNumber)
				.append("originSpecies", this.originSpecies)
				.append("species", this.getSpecies())
				.append("state", this.getState())
				.append("graftType", this.graftType)
				.append("graftType_unctrl_vocab", this.graftTypeUnctrlVocab)
				.append("harvestDate", this.harvestDate)
				.append("name", this.name)
				.append("parentalCellLineName", this.parentalCellLineName)
				.append("availability", this.getAvailability())
				.append("cellAmount", this.cellAmount)
				.append("submitter", this.getSubmitter())
				.append("tumorCode", this.tumorCode)
				.append("publicationCollection",
						this.getPublicationCollection())
				.append("organ", this.organ)
				.append("administrativeSite", this.administrativeSite)
				.append("modelDescriptor", this.getModelDescriptor())
				.append("modificationDescription", this.modificationDescription)
				.toString();
	}
}
