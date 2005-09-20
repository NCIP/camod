/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Availability extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259655453799404851L;

    private Long id;
    private Date enteredDate;
    private String visibleTo;
    private Date modifiedDate;
    private Date releaseDate;

    /**
     * @return Returns the enteredDate.
     */
    public Date getEnteredDate() {
        return enteredDate;
    }

    /**
     * @param enteredDate
     *            The enteredDate to set.
     */
    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
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
     * @return Returns the modifiedDate.
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate
     *            The modifiedDate to set.
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return Returns the releaseDate.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate
     *            The releaseDate to set.
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return Returns the visibleTo.
     */
    public String getVisibleTo() {
        return visibleTo;
    }

    /**
     * @param visibleTo
     *            The visibleTo to set.
     */
    public void setVisibleTo(String visibleTo) {
        this.visibleTo = visibleTo;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability rhs = (Availability) object;
        return new EqualsBuilder().append(this.enteredDate, rhs.enteredDate).append(this.visibleTo, rhs.visibleTo)
                .append(this.modifiedDate, rhs.modifiedDate).append(this.releaseDate, rhs.releaseDate).append(this.id,
                        rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1736335199, -1526405401).append(this.enteredDate).append(this.visibleTo).append(
                this.modifiedDate).append(this.releaseDate).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("releaseDate", this.releaseDate).append(
                "enteredDate", this.enteredDate).append("visibleTo", this.visibleTo).append("modifiedDate",
                this.modifiedDate).toString();
    }
}
