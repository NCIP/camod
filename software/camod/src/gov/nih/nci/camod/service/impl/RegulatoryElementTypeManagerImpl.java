/**
 * 
 * $Id: RegulatoryElementTypeManagerImpl.java,v 1.1 2005-10-24 14:15:52 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.RegulatoryElementType;
import gov.nih.nci.camod.service.RegulatoryElementTypeManager;
import gov.nih.nci.common.persistence.Search;

import java.util.List;

/**
 * Manager provides lookup by type
 */
public class RegulatoryElementTypeManagerImpl extends BaseManager implements RegulatoryElementTypeManager {

    /**
     * Get the SexDistribution by it's type
     * 
     * @param inName
     *            the type of the sex-distribution
     * 
     * @return the sex distribution that matches the type
     */
    public RegulatoryElementType getByType(String inName) throws Exception {

        RegulatoryElementType theRegulatoryElementType = null;

        try {

            // The following two objects are needed for eQBE.
            RegulatoryElementType theQBERegulatoryElementType = new RegulatoryElementType();
            theQBERegulatoryElementType.setName(inName);

            List theList = Search.query(theQBERegulatoryElementType);

            if (theList != null && theList.size() > 0) {
                theRegulatoryElementType = (RegulatoryElementType) theList.get(0);
            }

        } catch (Exception e) {

            log.error("Unable to get RegulatoryElementType", e);
            throw e;
        }

        return theRegulatoryElementType;
    }
}
