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


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContactInfo extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259535453799404851L;
    
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
       String result = super.toString() + " - ";      
       result += this.getAddress()+" - "+this.getCity()+", "+this.getState()+" "+this.getZip();     
       return result;
    }    
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
    
}
