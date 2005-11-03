/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.UserManagerSingleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 */
public class Person extends Party implements Comparable {

    private static final long serialVersionUID = 3258795453799404851L;

    private final Log log = LogFactory.getLog(Person.class);

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private Boolean isPrincipalInvestigtor;

    /**
     * NOTE: the following two methods do NOT user the getXXX naming because it
     * causes problems with the common persistence package
     */
    /**
     * @return Returns the display name
     */
    public String displayName() {

        String theDisplayName = "";
        if (lastName != null && firstName != null) {
            theDisplayName = lastName.trim() + ", " + firstName.trim();
        }
        return theDisplayName;
    }

    /**
     * @return Returns the e-mail address
     */
    public String emailAddress() {

        String theEmailAddress = "";

        try {
            theEmailAddress = UserManagerSingleton.instance().getEmailForUser(username);
        } catch (Exception e) {
            log.error("Unable to get email address for user: " + username);
        }

        return theEmailAddress;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the middleName.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName
     *            The middleName to set.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return Returns the isPrincipleInvestigator flag.
     */
    public Boolean getIsPrincipalInvestigator() {
        return isPrincipalInvestigtor;
    }

    /**
     * @param inIsPrincipleInvestigtor
     *            Set's whether or not this user is a PI
     */
    public void setIsPrincipalInvestigator(Boolean inIsPrincipalInvestigtor) {
        this.isPrincipalInvestigtor = inIsPrincipalInvestigtor;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String result = super.toString() + " - ";
        result += this.getUsername();
        return result;
    }

     public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final Person obj = (Person) o;
      if (HashCodeUtil.notEqual(this.getLastName(), obj.getLastName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getLastName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof Person) && (this.getLastName() != null) && (((Person)o).getLastName() != null)) {   
        int result = this.getLastName().compareTo( ((Person)o).getLastName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }  
}
