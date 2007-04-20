/**
 * 
 * $Id: StainingMethodManagerImpl.java,v 1.3 2007-04-20 17:51:27 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/24 16:46:14  pandyas
 * Converted StainingMethod to lookup - modified code to pull dropdown list from DB
 *
 * Revision 1.1  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.StainingMethod;
import gov.nih.nci.camod.service.StainingMethodManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
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
    public StainingMethod getOrCreate(String inConceptCode,
                                      String inStainingName) throws Exception
    {
        log.info("<StainingMethodManagerImpl> Entering getOrCreate");
        
        StainingMethod theQBEStainingMethod = new StainingMethod();
        theQBEStainingMethod.setConceptCode(inConceptCode);

        StainingMethod theStainingMethod = null;

        List theList = Search.query(theQBEStainingMethod);

        // Doesn't exist. Use the QBE StainingMethod since it has the same data
        if (theList != null && theList.size() > 0)
        {
            theStainingMethod = (StainingMethod) theList.get(0);
        }
        else
        {
            theStainingMethod = theQBEStainingMethod;
            String thePreferredDiscription = EvsTreeUtil.getEVSPreferedDescription(inConceptCode);

            if (thePreferredDiscription != null && thePreferredDiscription.length() > 0)
            {
                theStainingMethod.setName(thePreferredDiscription);
            }
            else
            {
                theStainingMethod.setName(inStainingName);
            }
            theStainingMethod.setConceptCode(inConceptCode);
        }

        return theStainingMethod;
    }
}
