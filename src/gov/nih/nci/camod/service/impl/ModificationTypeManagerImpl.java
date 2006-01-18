package gov.nih.nci.camod.service.impl;


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
}
