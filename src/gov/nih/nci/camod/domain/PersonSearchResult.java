/**
 * @author dgeorge
 * 
 * $Id: PersonSearchResult.java,v 1.3 2006-01-18 14:23:31 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.1  2005/10/17 13:15:31  georgeda
 * Initial revision
 *
 * Revision 1.3  2005/10/12 18:18:48  georgeda
 * Small fix
 *
 * Revision 1.2  2005/10/12 18:09:29  georgeda
 * Update for metastatis organs
 *
 * Revision 1.1  2005/10/07 16:27:50  georgeda
 * Implemented paganation
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.PersonManagerSingleton;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;

import java.util.List;

/**
 * Used as wrapper around comments for speedy display during paganation.
 */
public class PersonSearchResult
{
    private String myPersonId;
    private String myDisplayName = null;
    private Person myPerson = null;
    private List myRoles = null;
    private ContactInfo myContactInfo = null;

    /**
     * Create the wraper object
     * 
     * @param inAnimalModel
     *            the animal model we will be wrapping. Saves only the id.
     */
    public PersonSearchResult(Person inPerson)
    {
        myPersonId = inPerson.getId().toString();
    }

    /**
     * Return the id for the associated comments
     * 
     * @return the id for the model
     */
    public String getId()
    {
        return myPersonId;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model descriptor for the associated model
     * 
     * @throws Exception
     */
    public String getDisplayName() throws Exception
    {

        if (myDisplayName == null)
        {
            fetchPerson();
            myDisplayName = myPerson.getDisplayName();
        }
        return myDisplayName;
    }

    /**
     * Return the contact information for the user
     * 
     * @return the contact information for the user
     * 
     * @throws Exception
     */
    public ContactInfo getContactInfo() throws Exception
    {

        if (myContactInfo == null)
        {

            fetchPerson();
            myContactInfo = UserManagerSingleton.instance().getContactInformationForUser(myPerson.getUsername());
        }
        return myContactInfo;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the roles for the user
     * 
     * @throws Exception
     */
    public List getRoles() throws Exception
    {

        if (myRoles == null)
        {
            fetchPerson();
            myRoles = UserManagerSingleton.instance().getRolesForUser(myPerson.getUsername());
        }
        return myRoles;
    }

    // Fetch the animal model from the DB
    private void fetchPerson() throws Exception
    {
        if (myPerson == null)
        {
            myPerson = PersonManagerSingleton.instance().get(myPersonId);
        }
    }
}
