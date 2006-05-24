/**
 * 
 * $Id: StainingMethodManagerImpl.java,v 1.2 2006-05-24 16:46:14 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.StainingMethod;
import gov.nih.nci.camod.service.StainingMethodManager;
import gov.nih.nci.common.persistence.Search;
import java.util.List;

public class StainingMethodManagerImpl extends BaseManager implements StainingMethodManager
{

    public List getAll() throws Exception
    {
        log.trace("In StainingMethod.getAll");
        return super.getAll(StainingMethod.class);
    }

    public StainingMethod get(String id) throws Exception
    {
        log.trace("In StainingMethodManagerImpl.get");
        return (StainingMethod) super.get(id, StainingMethod.class);
    }


    /**
     * Get a specific StainingMethod by name or create one
     * 
     * @param name
     *            the unique name for a StainingMethod
     * 
     * @return the matching StainingMethod, or create one if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     *  
     */
    public StainingMethod getOrCreate(String inName,
                                      String inOtherName) throws Exception
    {
        log.info("<StainingMethodManagerImpl> Entering getOrCreate");

        StainingMethod theQBEStainingMethod = new StainingMethod();

        // If Other is selected, look for a match to the uncontrolled vocab
        // create new one either way if not found
        if (inName != null && !inName.equals(Constants.Dropdowns.OTHER_OPTION))
        {
            theQBEStainingMethod.setName(inName);
        }
        else
        {
            theQBEStainingMethod.setNameUnctrlVocab(inOtherName);
        }

        StainingMethod theStainingMethod = null;
        try
        {
            List theList = Search.query(theQBEStainingMethod);

            // Does exist, return object. 
            if (theList != null && theList.size() > 0)
            {
                theStainingMethod = (StainingMethod) theList.get(0);
            }
            //  Doesn't exist. Create object with either name or otherName set, don't save the word 'Other'
            else
            {
                theStainingMethod = theQBEStainingMethod;
                if (inName != null && !inName.equals(Constants.Dropdowns.OTHER_OPTION))
                {
                    theQBEStainingMethod.setName(inName);
                }
                else
                {
                    theQBEStainingMethod.setNameUnctrlVocab(inOtherName);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching StainingMethod object.  Creating new one.", e);
            theStainingMethod = theQBEStainingMethod;
        }
        log.info("<StainingMethodManagerImpl> theStainingMethod: " + theStainingMethod.toString());
        return theStainingMethod;
    }
}
