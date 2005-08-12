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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AnimalDistributor extends BaseObject implements Serializable {
	private Long id;
	private String name;
	private List animalAvailabilityCollection = new ArrayList();
	
	/**
	 * @return Returns the animalAvailabilityCollection.
	 */
	public List getAnimalAvailabilityCollection() {
		return animalAvailabilityCollection;
	}
	/**
	 * @param animalAvailabilityCollection The animalAvailabilityCollection to set.
	 */
	public void setAnimalAvailabilityCollection(List animalAvailabilityCollection) {
		this.animalAvailabilityCollection = animalAvailabilityCollection;
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AnimalDistributor)) {
			return false;
		}
		AnimalDistributor rhs = (AnimalDistributor) object;
		return new EqualsBuilder().append(
				this.animalAvailabilityCollection,
				rhs.animalAvailabilityCollection).append(this.name, rhs.name)
				.append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1926372733, 899020659).append(this.animalAvailabilityCollection)
				.append(this.name).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append("id",
				this.id).append("animalAvailabilityCollection",
				this.animalAvailabilityCollection).toString();
	}
}
