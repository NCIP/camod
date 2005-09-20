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

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AnimalAvailability extends BaseObject implements Serializable {
    
    private static final long serialVersionUID = 4259705453799404851L;
    
	private Long id;
	private String name;
	private String stockNumber;
	private List animalDistributorCollection = new ArrayList();
	
	/**
	 * @return Returns the animalDistributorCollection.
	 */
	public List getAnimalDistributorCollection() {
		return animalDistributorCollection;
	}
	/**
	 * @param animalDistributorCollection The animalDistributorCollection to set.
	 */
	public void setAnimalDistributorCollection(List animalDistributorCollection) {
		this.animalDistributorCollection = animalDistributorCollection;
	}
	/**
	 * @param animalDistributor The animalDistributor to add.
	 */
	public void addAnimalDistributor(AnimalDistributor animalDistributor) {
		animalDistributor.getAnimalAvailabilityCollection().add(this);
		animalDistributorCollection.add(animalDistributor);
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
	 * @return Returns the stockNumber.
	 */
	public String getStockNumber() {
		return stockNumber;
	}
	/**
	 * @param stockNumber The stockNumber to set.
	 */
	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AnimalAvailability)) {
			return false;
		}
		AnimalAvailability rhs = (AnimalAvailability) object;
		return new EqualsBuilder().append(
				this.stockNumber, rhs.stockNumber).append(this.name, rhs.name)
				.append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1592551365, 1293638993).append(this.stockNumber).append(this.name)
				.append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append("id",
				this.id).append("stockNumber", this.stockNumber).toString();
	}
}
