/*
 * Created on May 4, 2005
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
public class Party extends BaseObject implements Serializable {
	private Long id;
	private List contactInfoCollection = new ArrayList();
	private List roleCollection = new ArrayList();
	
	/**
	 * @return Returns the roleCollection.
	 */
	public List getRoleCollection() {
		return roleCollection;
	}
	/**
	 * @param roleCollection The roleCollection to set.
	 */
	public void setRoleCollection(List roleCollection) {
		this.roleCollection = roleCollection;
	}
	/**
	 * @param role The role to add.
	 */
	public void addRole(Role role) {
		role.getPartyCollection().add(this);
		roleCollection.add(role);
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
	 * @return Returns the contactInfoCollection.
	 */
	public List getContactInfoCollection() {
		return contactInfoCollection;
	}
	/**
	 * @param contactInfoCollection The contactInfoCollection to set.
	 */
	public void setContactInfoCollection(List contactInfoCollection) {
		this.contactInfoCollection = contactInfoCollection;
	}
	public void addContactInfo(ContactInfo contactInfo) {
		contactInfo.getPartyCollection().add(this);
		contactInfoCollection.add(contactInfo);
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Party)) {
			return false;
		}
		Party rhs = (Party) object;
		return new EqualsBuilder().append(
				this.roleCollection, rhs.roleCollection).append(
				this.contactInfoCollection, rhs.contactInfoCollection).append(
				this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1530808795, -1178033565).append(this.roleCollection).append(
				this.contactInfoCollection).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"contactInfoCollection", this.contactInfoCollection).append(
				"roleCollection", this.roleCollection).toString();
	}
}
