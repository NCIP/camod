/*
 * $Id: Person.java,v 1.14 2007-07-31 12:03:28 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2006/05/15 13:33:55  georgeda
 * Cleaned up contact info management
 *
 * Revision 1.12  2006/05/10 14:13:51  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.11  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.util.Iterator;
import java.util.Set;

import gov.nih.nci.camod.service.impl.UserManagerSingleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 */
public class Person extends Party implements Comparable
{
    private static final long serialVersionUID = 3258795453799404851L;

    private final Log log = LogFactory.getLog(Person.class);
    private String partyId;    
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private Boolean isPrincipalInvestigtor;

    /**
     * @return Returns the display name
     */
    public String getDisplayName()
    {

        String theDisplayName = "";
        if (lastName != null && firstName != null)
        {
            theDisplayName = lastName.trim() + ", " + firstName.trim();
        }
        return theDisplayName;
    }

    /**
     * @return Returns the display name with the organization (if any)
     */
    public String getDisplayNameWithOrg()
    {
        String theDisplayName = getDisplayName();
        
        Set theContactInfos = getContactInfoCollection();

        if (theContactInfos != null && theContactInfos.size() > 0) {
            Iterator theIterator = theContactInfos.iterator();
            ContactInfo theContactInfo = (ContactInfo) theIterator.next();
            String theInstitute = theContactInfo.getInstitute();
            
            if (theInstitute != null && theInstitute.trim().length() > 0) {
                theDisplayName += " (" + theInstitute.trim() + ")";
            }
        } 
        return theDisplayName;
    }
    
    /**
     * @return Returns the e-mail address
     */
    public String getEmailAddress()
    {
        String theEmailAddress = "";

        try
        {
            theEmailAddress = UserManagerSingleton.instance().getEmailForUser(username);
        }
        catch (Exception e)
        {
            log.error("Unable to get email address for user: " + username);
        }

        return theEmailAddress;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return Returns the middleName.
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * @param middleName
     *            The middleName to set.
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    /**
     * @return Returns the firstName.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return Returns the lastName.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return Returns the isPrincipleInvestigator flag.
     */
    public Boolean getIsPrincipalInvestigator()
    {
        return isPrincipalInvestigtor;
    }

    /**
     * @param inIsPrincipleInvestigtor
     *            Set's whether or not this user is a PI
     */
    public void setIsPrincipalInvestigator(Boolean inIsPrincipalInvestigtor)
    {
        this.isPrincipalInvestigtor = inIsPrincipalInvestigtor;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getUsername();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Person obj = (Person) o;
        if (HashCodeUtil.notEqual(this.getLastName(), obj.getLastName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getLastName());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof Person) && (this.getLastName() != null) && (((Person) o).getLastName() != null))
        {
            int result = this.getLastName().compareTo(((Person) o).getLastName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
}
