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
public class ContactInfo extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259535453799404851L;

    private Long id;
    private String city;
    private String state;
    private String address;
    private String zip;
    private String fax;
    private String lab;
    private String phone;
    private String email;
    private String institute;
    private List partyCollection = new ArrayList();

    /**
     * @return Returns the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the fax.
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
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
     * @return Returns the institute.
     */
    public String getInstitute() {
        return institute;
    }

    /**
     * @param institute
     *            The institute to set.
     */
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    /**
     * @return Returns the lab.
     */
    public String getLab() {
        return lab;
    }

    /**
     * @param lab
     *            The lab to set.
     */
    public void setLab(String lab) {
        this.lab = lab;
    }

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
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Returns the zip.
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     *            The zip to set.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ContactInfo)) {
            return false;
        }
        ContactInfo rhs = (ContactInfo) object;
        return new EqualsBuilder().append(this.phone, rhs.phone).append(this.institute, rhs.institute).append(
                this.address, rhs.address).append(this.email, rhs.email).append(this.lab, rhs.lab).append(this.state,
                rhs.state).append(this.partyCollection, rhs.partyCollection).append(this.zip, rhs.zip).append(this.fax,
                rhs.fax).append(this.city, rhs.city).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1132890619, 1368783481).append(this.phone).append(this.institute).append(
                this.address).append(this.email).append(this.lab).append(this.state).append(this.partyCollection)
                .append(this.zip).append(this.fax).append(this.city).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("state", this.state).append("phone", this.phone).append("id", this.id)
                .append("partyCollection", this.partyCollection).append("institute", this.institute).append("email",
                        this.email).append("address", this.address).append("lab", this.lab).append("fax", this.fax)
                .append("zip", this.zip).append("city", this.city).toString();
    }
}
