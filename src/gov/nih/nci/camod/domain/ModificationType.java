/*
 * Created on August 9, 2005
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
public class ModificationType extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259155453799404851L;

    private Long id;
    private String name;
    private List targetedModificationCollection = new ArrayList();

    /**
     * @return Returns the targetedModificationCollection.
     */
    public List getTargetedModificationCollection() {
        return targetedModificationCollection;
    }

    /**
     * @param targetedModificationCollection
     *            The targetedModificationCollection to set.
     */
    public void setTargetedModificationCollection(List targetedModificationCollection) {
        this.targetedModificationCollection = targetedModificationCollection;
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
        if (!(object instanceof ModificationType)) {
            return false;
        }
        ModificationType rhs = (ModificationType) object;
        return new EqualsBuilder().append(this.targetedModificationCollection, rhs.targetedModificationCollection)
                .append(this.name, rhs.name).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(726628189, 1147964673).append(this.targetedModificationCollection).append(this.name)
                .append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("targetedModificationCollection",
                this.targetedModificationCollection).append("id", this.id).toString();
    }
}
