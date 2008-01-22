/**
 * 
 * $Id: GeneIdentifierManagerImpl.java,v 1.1 2008-01-22 18:38:15 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *  
 */

package gov.nih.nci.camod.service.impl;

import java.util.List;
import gov.nih.nci.camod.domain.GeneIdentifier;
import gov.nih.nci.camod.service.GeneIdentifierManager;
import gov.nih.nci.common.persistence.Search;

/**
 * @author pandyas
 * 
 * Impl class for the GeneIdentifierManager
 */
public class GeneIdentifierManagerImpl extends BaseManager implements GeneIdentifierManager
{
    public GeneIdentifier getOrCreate(String inEntrezGeneId) throws Exception
    {

        log.info("<GeneIdentifierManagerImpl> Entering getOrCreate(String)");

        GeneIdentifier theQBEGeneIdentifier = new GeneIdentifier();
        theQBEGeneIdentifier.setEntrezGeneID(inEntrezGeneId);

        GeneIdentifier theGeneIdentifier = null;

        try
        {
            List theList = Search.query(theQBEGeneIdentifier);

            // Does exist - get object
            if (theList != null && theList.size() > 0)
            {
                theGeneIdentifier = (GeneIdentifier) theList.get(0);
            }
            // Doesn't exist. Create object with name 
            else
            {
                log.debug("<GeneIdentifierManagerImpl> No matching GeneIdentifier. Create new one");
                theGeneIdentifier = theQBEGeneIdentifier;
                if (inEntrezGeneId != null)
                {
                    theQBEGeneIdentifier.setEntrezGeneID(inEntrezGeneId);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching GeneIdentifier object.  Creating new one.", e);
            theGeneIdentifier = theQBEGeneIdentifier;
        }
        log.info("<GeneIdentifierManagerImpl> theGeneIdentifier: " + theGeneIdentifier.toString());
        return theGeneIdentifier;
    }
}
