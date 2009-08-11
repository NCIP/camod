/**
 * 
 * $Id: ModificationTypeManagerImpl.java,v 1.7 2007-10-31 19:07:00 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.5  2006/04/20 18:11:51  pandyas
 * Cleaned up modificationType save of Other in DB
 *
 * Revision 1.4  2006/04/20 14:06:40  pandyas
 * changed Modification Type to getOrCreate
 *
 * Revision 1.3  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service.impl;


import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ModificationType;
import gov.nih.nci.camod.service.ModificationTypeManager;
import gov.nih.nci.common.persistence.Search;
import java.util.List;


public class ModificationTypeManagerImpl extends BaseManager implements ModificationTypeManager
{
    /**
     * Get the ModificationType by it's name
     * 
     * @param inName
     *            the name of the Modification Type
     * 
     * @return the ModificationType that matches the name
     * @throws Exception 
     */
    public ModificationType getByName(String inName) throws Exception
    {

        ModificationType theModificationType = null;

        try
        {
            // The following two objects are needed for eQBE.
            ModificationType theQueryObj = new ModificationType();
            theQueryObj.setName(inName);

            List theList = Search.query(theQueryObj);

            if (theList != null && theList.size() > 0)
            {
                theModificationType = (ModificationType) theList.get(0);
            }
        }
        catch (Exception e)
        {
            log.error("Exception in getByName", e);
            throw e;
        }
        return theModificationType;
    }

    /**
     * Get or create the ModificationType by it's name
     * 
     * @param inName
     *            the name of the Modification Type
     * 
     * @return the ModificationType that matches the name or create a new object
     * @throws Exception 
     * 
     */
    public ModificationType getOrCreate(String inName,
                                        String inOtherName) throws Exception
    {
        log.debug("<ModificationTypeImpl> Entering getOrCreate");

        ModificationType theQBEModType = new ModificationType();

        // If Other is selected, look for a match to the uncontrolled vocab
        // create new one either way if not found
        if (inName != null && !inName.equals(Constants.Dropdowns.OTHER_OPTION))
        {
            theQBEModType.setName(inName);
        }
        else
        {
            theQBEModType.setNameAlternEntry(inOtherName);
        }

        ModificationType theModificationType = null;
        try
        {
            List theList = Search.query(theQBEModType);

            // Does exist. 
            if (theList != null && theList.size() > 0)
            {
                theModificationType = (ModificationType) theList.get(0);
            }
            // Doesn't exist. Create object with either name or otherName set, don't save the word 'Other'
            else
            {
                theModificationType = theQBEModType;
                if (inName != null && !inName.equals(Constants.Dropdowns.OTHER_OPTION))
                {
                    theQBEModType.setName(inName);
                }
                else
                {
                    theQBEModType.setNameAlternEntry(inOtherName);
                }

            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching ModificationType object.  Creating new one.", e);
            theModificationType = theQBEModType;
        }

        return theModificationType;
    }
}
