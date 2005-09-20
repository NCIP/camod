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

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Role extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258695453799404851L;

    private Long id;
    private String name;
    private List partyCollection = new ArrayList();

    /**
     * @return Returns the partyCollection.
     */
    public List getPartyCollection() {
        return partyCollection;
    }

    /**
     * @param partyCollection
     *            The partyCollection to set.
     */
    public void setPartyCollection(List partyCollection) {
        this.partyCollection = partyCollection;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
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
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role rhs = (Role) object;
        return new EqualsBuilder().append(this.partyCollection, rhs.partyCollection).append(this.name, rhs.name)
                .append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1816386241, -275903977).append(this.partyCollection).append(this.name).append(
                this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("id", this.id).append("partyCollection",
                this.partyCollection).toString();
    }
}
