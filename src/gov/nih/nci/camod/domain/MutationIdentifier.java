/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MutationIdentifier extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259135453799404851L;

    private Long id;
    private Long numberMGI;

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
     * @return Returns the numberMGI.
     */
    public Long getNumberMGI() {
        return numberMGI;
    }

    /**
     * @param numberMGI
     *            The numberMGI to set.
     */
    public void setNumberMGI(Long numberMGI) {
        this.numberMGI = numberMGI;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MutationIdentifier)) {
            return false;
        }
        MutationIdentifier rhs = (MutationIdentifier) object;
        return new EqualsBuilder().append(this.numberMGI, rhs.numberMGI).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(642166741, 2129744149).append(this.numberMGI).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("numberMGI", this.numberMGI).toString();
    }
}
