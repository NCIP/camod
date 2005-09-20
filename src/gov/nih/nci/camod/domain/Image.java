/*
 * Created on May 4, 2005
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
public class Image extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259255453799404851L;
    
    private Long id;
    private String title;
    private String description;
    private String staining;
    private String stainingUnctrlVocab;
    private String fileServerLocation;
    private Availability availability;

    /**
     * @return Returns the fileServerLocation.
     */
    public String getFileServerLocation() {
        return fileServerLocation;
    }

    /**
     * @param fileServerLocation
     *            The fileServerLocation to set.
     */
    public void setFileServerLocation(String fileServerLocation) {
        this.fileServerLocation = fileServerLocation;
    }

    /**
     * @return Returns the availability.
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability
     *            The availability to set.
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return Returns the staining.
     */
    public String getStaining() {
        return staining;
    }

    /**
     * @param staining
     *            The staining to set.
     */
    public void setStaining(String staining) {
        this.staining = staining;
    }

    /**
     * @return Returns the stainingUnctrlVocab.
     */
    public String getStainingUnctrlVocab() {
        return stainingUnctrlVocab;
    }

    /**
     * @param stainingUnctrlVocab
     *            The stainingUnctrlVocab to set.
     */
    public void setStainingUnctrlVocab(String stainingUnctrlVocab) {
        this.stainingUnctrlVocab = stainingUnctrlVocab;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Image)) {
            return false;
        }
        Image rhs = (Image) object;
        return new EqualsBuilder().append(this.availability, rhs.availability).append(this.title, rhs.title).append(
                this.description, rhs.description).append(this.fileServerLocation, rhs.fileServerLocation).append(
                this.id, rhs.id).append(this.staining, rhs.staining).append(this.stainingUnctrlVocab,
                rhs.stainingUnctrlVocab).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1070912003, -540974217).append(this.availability).append(this.title).append(
                this.description).append(this.fileServerLocation).append(this.id).append(this.staining).append(
                this.stainingUnctrlVocab).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("fileServerLocation", this.fileServerLocation).append("id", this.id)
                .append("description", this.description).append("availability", this.availability).append("title",
                        this.title).append("staining", this.staining).append("staining_unctrl_vocab",
                        this.stainingUnctrlVocab).toString();
    }
}
